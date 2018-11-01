package com.zhiyun.util;

import com.zhiyun.client.UserHolder;
import com.zhiyun.context.OnlineUser;
import com.zhiyun.context.RequestHolder;
import com.zhiyun.entity.CasCompany;
import com.zhiyun.entity.CasUser;
import com.zhiyun.internal.server.auth.service.InterfaceForUser;
import com.zhiyun.service.CasCompanyService;
import com.zhiyun.service.CasUserService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 请求拦截。<br>
 *
 * @author 庄超
 */
public class RequestContextInterceptor extends HandlerInterceptorAdapter implements InitializingBean {

    private Map<String, String> parameterMap = new HashMap<String, String>();

    private Resource contextPropertiesLocation;

    @javax.annotation.Resource
    private CasUserService casUserService;
    @javax.annotation.Resource
    private CasCompanyService casCompanyService;
    @javax.annotation.Resource
    private InterfaceForUser interfaceForUser;
    /**
     * 人资版本
     */
    @Value("${hcmVersion}")
    private String hcmVersion;

    @Override
    public void afterPropertiesSet() throws Exception {
        Properties props = null;
        if (this.contextPropertiesLocation != null) {
            try {
                props = PropertiesLoaderUtils.loadProperties(this.contextPropertiesLocation);
            } catch (Throwable e) {
                e.printStackTrace();
                props = new Properties();
            }
            for (Object key : props.keySet()) {
                Object value = props.get(key);
                this.parameterMap.put(key.toString(), value.toString());
            }
        }
    }

    /**
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        RequestHolder.init();
        OnlineUser user = (OnlineUser) request.getSession().getAttribute("user");
        if (user != null && (user.getCompanyName() == null)) {
            CasUser casUser = new CasUser();
            casUser.setAccount("p13511111111");
//            casUser.setAccount(user.getAccountName());
            List<CasUser> casUsers = casUserService.customeFind(casUser);
            if (!CollectionUtils.isEmpty(casUsers) && casUsers.size() == 1) {
                //                if (!interfaceForUser.getIsAble(casUsers.get(0).getId(), "plm", hcmVersion)) {
                //                    throw new BusinessException("没有权限");
                //                }
                CasUser entity = casUsers.get(0);
                user.setCompanyId(entity.getCompanyId());
                user.setOrgId(entity.getOrgId());
                user.setUserName(entity.getName());
                user.setId(entity.getId());
                user.setIp(request.getRemoteAddr());
                //添加员工编码
                CasCompany casCompany = casCompanyService.customGet(entity.getCompanyId());
                user.setCompanyName(casCompany.getCompanyName());

                request.getSession().setAttribute("user", user);
            } else {
                response.setHeader("Content-type", "application/json;charset=UTF-8");
                response.getWriter().write("{\"errorMsg\":\"请重新登录\"}");
                return false;
            }

        }
        UserHolder.setUser(user);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    public void setParameterMap(Map<String, String> parameterMap) {
        this.parameterMap = parameterMap;
    }

    public void setContextPropertiesLocation(Resource contextPropertiesLocation) {
        this.contextPropertiesLocation = contextPropertiesLocation;
    }
}
