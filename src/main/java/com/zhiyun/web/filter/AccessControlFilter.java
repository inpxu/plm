package com.zhiyun.web.filter;

import com.zhiyun.client.MultiAntPathRequestMatcher;
import com.zhiyun.client.UserHolder;
import com.zhiyun.context.OnlineUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 访问控制过滤器。
 *
 * @author 庄超
 */
public class AccessControlFilter extends OncePerRequestFilter {

    // 登录页面URL
    private String loginUrl;
    // 被踢下线URL
    private String puntUrl;
    // 忽略拦截的URL
    private RequestMatcher ignoreRequestMatcher = new RequestMatcher() {
        @Override
        public boolean matches(HttpServletRequest request) {
            // 默认不匹配任何URL，直接返回False。
            return true;
        }
    };

    /**
     * 初始化数据
     */
    @Override
    protected void initFilterBean() throws ServletException {
        this.loginUrl = this.getFilterConfig().getInitParameter("loginUrl");
        this.puntUrl = this.getFilterConfig().getInitParameter("puntUrl");
        String ignore = this.getFilterConfig().getInitParameter("ignoreUrls");
        logger.debug("Setting ignoreUrls: " + ignore);
        if (ignore != null) {
            this.ignoreRequestMatcher = new MultiAntPathRequestMatcher(ignore);
        }
    }

    /**
     * 执行过滤
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 判断是否为忽略的URL，如果是，直接执行下一个过滤器
        logger.debug("FILTER IN！");
        String uri = request.getRequestURI();
        if (this.isIgnore(request)) {
            UserHolder.setRequestIgnoreAuth();
            filterChain.doFilter(request, response);
            return;
        }
        this.doNextFilter(null, request, response, filterChain);
    }

    /**
     * 执行用户过滤
     */
    public void doNextFilter(String ticket, HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        OnlineUser user = (OnlineUser) request.getSession().getAttribute("user");
        if (user == null || StringUtils.isBlank(user.getAccountName())) {
            String smac = "";

            try {

                String sip = request.getHeader("x-forwarded-for");
                if (sip == null || sip.length() == 0 || "unknown".equalsIgnoreCase(sip)) {
                    sip = request.getHeader("Proxy-Client-IP");
                }
                if (sip == null || sip.length() == 0 || "unknown".equalsIgnoreCase(sip)) {
                    sip = request.getHeader("WL-Proxy-Client-IP");
                }
                if (sip == null || sip.length() == 0 || "unknown".equalsIgnoreCase(sip)) {
                    sip = request.getRemoteAddr();
                }
                //				UdpGetClientMacAddr umac = new UdpGetClientMacAddr(sip);
                //				smac = umac.GetRemoteMacAddr();
            } catch (Exception e) {
                logger.error("获取MAC失败");
            }
            user = new OnlineUser();
            user.setAccountName(request.getRemoteUser());
            user.setIp(request.getRemoteAddr());
            user.setMac(smac);
            user.setSessionId(request.getSession().getId());
            request.getSession().setAttribute("user", user);

            UserHolder.setUser(user);
        }
        // 过滤完成，执行下一步操作
        try {
            // UserHolder.setUserKey(userKey);
            filterChain.doFilter(request, response);
        } finally {
            UserHolder.clear();
        }
    }

    protected boolean isMultipleUserLogin(OnlineUser user) {
        // 只有管理员才能多个会话登录
        // return 判定为管理员的条件
        return false;
    }

    /**
     * 判断当前URL是否忽略过滤
     *
     * @param request
     * @return
     */
    protected boolean isIgnore(HttpServletRequest request) {
        return this.ignoreRequestMatcher.matches(request);
    }

    /**
     * 验证ticket是否有效。
     *
     * @param ticket
     * @return
     */
    protected boolean validateTicket(String ticket) {
        return true;
    }

    /**
     * 跳转到用户登录页面
     *
     * @param request
     * @param response
     * @throws IOException
     */
    protected void redirectLoginUrl(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String serviceUrl = this.buildServiceUrl(request, response);
        String redirectUrl = this.buildLoginRedirectUrl(serviceUrl);
        if (logger.isDebugEnabled()) {
            logger.debug("Redirect: " + redirectUrl);
        }
        response.sendRedirect(redirectUrl);
    }

    /**
     * 构造当前请求URL地址
     *
     * @param request
     * @param response
     * @return
     */
    public String buildServiceUrl(HttpServletRequest request, HttpServletResponse response) {
        StringBuilder builder = new StringBuilder();

        builder.append(this.getServerName(request));
        builder.append(request.getRequestURI());

        String queryString = request.getQueryString();
        if (StringUtils.isNotBlank(queryString)) {
            int index = queryString.indexOf("ticket=");

            if (index == -1) {
                builder.append("?").append(queryString);
            } else if (index == 0) {
                String returnValue = response.encodeURL(builder.toString());
                if (logger.isDebugEnabled()) {
                    logger.debug("serviceUrl generated: " + returnValue);
                }
                return returnValue;
            } else {
                builder.append("?");
                int actualIndex = queryString.indexOf("&ticket=");

                if (actualIndex == -1) {
                    builder.append(queryString);
                } else if (actualIndex > 0) {
                    builder.append(queryString.substring(0, actualIndex));
                }
            }
        }

        String returnValue = response.encodeURL(builder.toString());
        if (logger.isDebugEnabled()) {
            logger.debug("serviceUrl generated: " + returnValue);
        }
        return returnValue;
    }

    /**
     * 构造登录跳转URL
     *
     * @param serviceUrl 当前服务URL
     * @return
     */
    protected String buildLoginRedirectUrl(String serviceUrl) {
        // try {
        StringBuilder builder = new StringBuilder();
        builder.append(loginUrl);
        // builder.append(loginUrl.indexOf("?") != -1 ? "&" : "?");
        // builder.append("redirectURL=");
        // builder.append(URLEncoder.encode(serviceUrl, "UTF-8"));
        return builder.toString();
        // } catch (UnsupportedEncodingException e) {
        // throw new RuntimeException(e);
        // }
    }

    private String getServerName(HttpServletRequest request) {
        String schema = request.getScheme();
        String serverName = request.getServerName();
        int port = request.getServerPort();
        StringBuilder builder = new StringBuilder();
        builder.append(schema).append("://").append(serverName);
        if ("http".equals(schema) && port != 80) {
            builder.append(":").append(request.getServerPort());
        } else if ("https".equals(schema) && port != 443) {
            builder.append(":").append(request.getServerPort());
        }
        return builder.toString();
    }
}
