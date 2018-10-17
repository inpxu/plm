/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */
package com.zhiyun.controller;

import com.alibaba.fastjson.JSON;
import com.zhiyun.base.controller.BaseController;
import com.zhiyun.base.dto.BaseResult;
import com.zhiyun.base.exception.BusinessException;
import com.zhiyun.base.model.DataGrid;
import com.zhiyun.base.model.Pager;
import com.zhiyun.base.model.Params;
import com.zhiyun.client.UserHolder;
import com.zhiyun.dto.CrafworkChangeMainDto;
import com.zhiyun.entity.CasOrg;
import com.zhiyun.entity.CrafworkChangeMain;
import com.zhiyun.service.CrafworkChangeMainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 产品分类
 *
 * @author xufei
 * @version v1.0
 * @date 2018-9-26 10:40:25
 */
@Controller
@RequestMapping(value = "/changeMain", produces = "application/json;charset=UTF-8")
public class CrafworkChangeMainController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CrafworkChangeMainController.class);

    @Resource
    private CrafworkChangeMainService crafworkChangeMainService;

    /**
     * 单据号模糊查询下拉
     *
     * @param crafworkChangeMain
     * @param bindingResult
     * @return
     * @author xufei
     * @date 2018-9-26 10:50:43
     */
    @ResponseBody
    @RequestMapping(value = "/findVoucher", method = {RequestMethod.GET, RequestMethod.POST})
    public Object findVoucher(CrafworkChangeMain crafworkChangeMain, BindingResult bindingResult) {
        BaseResult<List<CrafworkChangeMain>> baseResult = new BaseResult<List<CrafworkChangeMain>>();
        baseResult.setResult(true);
        baseResult.setMessage("下拉成功！");
        try {
            vaildParamsDefault(baseResult, bindingResult);
            crafworkChangeMain.setCompanyId(UserHolder.getCompanyId());
            List<CrafworkChangeMain> list = crafworkChangeMainService.findVoucher(crafworkChangeMain);
            baseResult.setModel(list);
        } catch (BusinessException be) {
            LOGGER.debug("业务异常" + be);
            baseResult.setResult(false);
            baseResult.setMessage(be.getMessage());
        } catch (Exception e) {
            LOGGER.debug("系统异常" + e);
            baseResult.setResult(false);
            baseResult.setMessage("系统异常");
        }
        return JSON.toJSONString(baseResult);
    }

    /**
     * 变更部门模糊查询下拉
     *
     * @param casOrg
     * @param bindingResult
     * @return
     * @author xufei
     * @date 2018-9-26 11:12:36
     */
    @ResponseBody
    @RequestMapping(value = "/getOrg", method = {RequestMethod.GET, RequestMethod.POST})
    public Object getOrg(CasOrg casOrg, BindingResult bindingResult) {
        BaseResult<List<CasOrg>> baseResult = new BaseResult<List<CasOrg>>();
        baseResult.setResult(true);
        baseResult.setMessage("下拉成功！");
        try {
            vaildParamsDefault(baseResult, bindingResult);
            casOrg.setCompanyId(UserHolder.getCompanyId());
            List<CasOrg> list = crafworkChangeMainService.getOrg(casOrg);
            baseResult.setModel(list);
        } catch (BusinessException be) {
            LOGGER.debug("业务异常" + be);
            baseResult.setResult(false);
            baseResult.setMessage(be.getMessage());
        } catch (Exception e) {
            LOGGER.debug("系统异常" + e);
            baseResult.setResult(false);
            baseResult.setMessage("系统异常");
        }
        return JSON.toJSONString(baseResult);
    }

    /**
     * 分页查询
     *
     * @param crafworkChangeMain
     * @param pager
     * @return
     * @author xufei
     * @date 2018-9-26 10:44:03
     */
    @ResponseBody
    @RequestMapping(value = "/page", method = {RequestMethod.GET, RequestMethod.POST})
    public Object page(CrafworkChangeMain crafworkChangeMain, Pager pager, BindingResult bindingResult) {
        BaseResult<DataGrid<CrafworkChangeMainDto>> baseResult = new BaseResult<DataGrid<CrafworkChangeMainDto>>();
        baseResult.setResult(true);
        baseResult.setMessage("查询成功！");
        try {
            vaildParamsDefault(baseResult, bindingResult);
            crafworkChangeMain.setCompanyId(UserHolder.getCompanyId());
            Params params = Params.create().add("entity", crafworkChangeMain);
            DataGrid<CrafworkChangeMainDto> dataGrid = crafworkChangeMainService.changePage(params, pager);
            baseResult.setModel(dataGrid);
        } catch (BusinessException be) {
            LOGGER.debug("业务异常" + be);
            baseResult.setResult(false);
            baseResult.setMessage(be.getMessage());
        } catch (Exception e) {
            LOGGER.debug("系统异常" + e);
            baseResult.setResult(false);
            baseResult.setMessage("系统异常");
        }
        return JSON.toJSONString(baseResult);
    }

}