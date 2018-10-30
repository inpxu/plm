/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */
package com.zhiyun.controller;

import com.alibaba.fastjson.JSON;
import com.zhiyun.base.dto.BaseResult;
import com.zhiyun.base.exception.BusinessException;
import com.zhiyun.base.model.DataGrid;
import com.zhiyun.base.model.Pager;
import com.zhiyun.base.model.Params;
import com.zhiyun.client.UserHolder;
import com.zhiyun.entity.ProdTypeCrm;
import com.zhiyun.service.ProdTypeCrmService;
import org.apache.commons.lang3.ArrayUtils;
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
 * @date 2018-9-6 13:33:40
 */
@Controller
@RequestMapping(value = "/prodType", produces = "application/json;charset=UTF-8")
public class ProdTypeController extends PublicController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProdTypeController.class);

    @Resource
    private ProdTypeCrmService prodTypeCrmService;

    /**
     * 分类显示
     *
     * @param prodTypeCrm
     * @return
     * @author xufei
     * @date 2018-9-6 14:50:44
     */
    @ResponseBody
    @RequestMapping(value = "/get", method = {RequestMethod.GET, RequestMethod.POST})
    public Object get(ProdTypeCrm prodTypeCrm, BindingResult bindingResult) {
        BaseResult<List<ProdTypeCrm>> baseResult = new BaseResult<List<ProdTypeCrm>>();
        baseResult.setResult(true);
        baseResult.setMessage("查询成功！");
        try {
            vaildParamsDefault(baseResult, bindingResult);
            prodTypeCrm.setCompanyId(UserHolder.getCompanyId());
            prodTypeCrm.setDeleted("F");
            List<ProdTypeCrm> list = prodTypeCrmService.find(prodTypeCrm);
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
     * @param prodTypeCrm
     * @param pager
     * @return
     * @author xufei
     * @date 2018-9-6 14:51:02
     */
    @ResponseBody
    @RequestMapping(value = "/pg", method = {RequestMethod.GET, RequestMethod.POST})
    public Object prodPage(ProdTypeCrm prodTypeCrm, Pager pager, BindingResult bindingResult) {
        BaseResult<DataGrid<ProdTypeCrm>> baseResult = new BaseResult<DataGrid<ProdTypeCrm>>();
        baseResult.setResult(true);
        baseResult.setMessage("查询成功！");
        try {
            vaildParamsDefault(baseResult, bindingResult);
            prodTypeCrm.setCompanyId(UserHolder.getCompanyId());
            Params params = Params.create().add("entity", prodTypeCrm);
            DataGrid<ProdTypeCrm> dataGrid = prodTypeCrmService.pages(params, pager);
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

    /**
     * 新增
     *
     * @param prodTypeCrm
     * @param bindingResult
     * @return
     * @author xufei
     * @date 2018-9-6 14:51:12
     */
    @ResponseBody
    @RequestMapping(value = "/add", method = {RequestMethod.GET, RequestMethod.POST})
    public Object add(ProdTypeCrm prodTypeCrm, BindingResult bindingResult) {
        BaseResult<ProdTypeCrm> baseResult = new BaseResult<ProdTypeCrm>();
        baseResult.setResult(true);
        baseResult.setMessage("新增成功！");
        try {
            vaildParamsDefault(baseResult, bindingResult);
            prodTypeCrm.setCompanyId(UserHolder.getCompanyId());
            prodTypeCrmService.add(prodTypeCrm);
            baseResult.setModel(prodTypeCrm);
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
     * 编辑
     *
     * @param prodTypeCrm
     * @param bindingResult
     * @return
     * @author xufei
     * @date 2018-9-6 14:51:12
     */
    @ResponseBody
    @RequestMapping(value = "/upd", method = {RequestMethod.GET, RequestMethod.POST})
    public Object update(ProdTypeCrm prodTypeCrm, BindingResult bindingResult) {
        BaseResult<ProdTypeCrm> baseResult = new BaseResult<ProdTypeCrm>();
        baseResult.setResult(true);
        baseResult.setMessage("编辑成功！");
        try {
            vaildParamsDefault(baseResult, bindingResult);
            prodTypeCrm.setCompanyId(UserHolder.getCompanyId());
            prodTypeCrmService.up(prodTypeCrm);
            baseResult.setModel(prodTypeCrm);
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
     * 删除
     *
     * @param ids
     * @return
     * @author xufei
     * @date 2018-9-6 15:53:27
     */
    @ResponseBody
    @RequestMapping(value = "/del", method = {RequestMethod.GET, RequestMethod.POST})
    public Object del(Long[] ids) {
        BaseResult<ProdTypeCrm> baseResult = new BaseResult<ProdTypeCrm>();
        baseResult.setResult(true);
        baseResult.setMessage("分类已删除！");
        try {
            if (ArrayUtils.isEmpty(ids)) {
                throw new BusinessException("请选择需要删除的分类信息！");
            }
            prodTypeCrmService.del(ids);
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