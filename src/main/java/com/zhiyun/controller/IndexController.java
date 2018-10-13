package com.zhiyun.controller;

import com.alibaba.fastjson.JSON;
import com.zhiyun.base.controller.BaseController;
import com.zhiyun.client.UserHolder;
import com.zhiyun.internal.server.auth.dto.AppMenuDto;
import com.zhiyun.internal.server.auth.service.InterfaceForUser;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/index")
public class IndexController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
    @Value("${cloudUrl}")
    private String backUrl;
    @Resource
    private InterfaceForUser interfaceForUser;
    /**
     * 系统版本
     */
    @Value("${hcmVersion}")
    private String hcmVersion;

    /**
     * 同步请求
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/index", method = {RequestMethod.GET, RequestMethod.POST})
    public String index(Model model) {
        logger.debug("request in");
        model.addAttribute("backUrl", backUrl);
        model.addAttribute("userName", UserHolder.getUserName());
        model.addAttribute("companyName", UserHolder.getCompanyName());
        List<AppMenuDto> list = interfaceForUser.getUserMenuForApp("plm", Integer.parseInt(hcmVersion), UserHolder.getId(), UserHolder.getCompanyId());
        if (!CollectionUtils.isEmpty(list)) {
            String a = JSON.toJSONString(list);
            model.addAttribute("menuList", JSON.toJSONString(list));
        } else {
            model.addAttribute("menuList", JSON.toJSONString("[]"));
        }
        return "/demo/index";
    }

}
