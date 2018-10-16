/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */
package com.zhiyun.controller;

import com.alibaba.fastjson.JSON;
import com.zhiyun.base.controller.BaseController;
import com.zhiyun.base.dto.BaseResult;
import com.zhiyun.base.exception.BusinessException;
import com.zhiyun.client.UserHolder;
import com.zhiyun.dto.ProductStorePlmDto;
import com.zhiyun.entity.MattersStoreIos;
import com.zhiyun.entity.ProdBomPlm;
import com.zhiyun.service.ProdBomPlmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 产品bom controller
 *
 * @author 邓艺
 * @date 2018/10/7 12:53
 */
@Controller
@RequestMapping(value = "/bomPlm", produces = "application/json; charset=UTF-8")
public class ProdBomPlmController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProdBomPlmController.class);

    @Resource
    private ProdBomPlmService prodBomPlmService;

    /**
     * 根据产品获取下属半成品
     *
     * @param prodBomPlm
     * @param bindingResult
     * @return
     * @author xufei
     * @date 2018-9-10 13:10:05
     */
    @RequestMapping(value = "/getBom", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object getBom(@RequestBody ProdBomPlm prodBomPlm, BindingResult bindingResult) {
        BaseResult<ProdBomPlm> baseResult = new BaseResult<ProdBomPlm>();
        baseResult.setResult(true);
        baseResult.setMessage("添加成功！");
        try {
            vaildParamsDefault(baseResult, bindingResult);
            prodBomPlm.setCompanyId(UserHolder.getCompanyId());
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
     * 通过产品名或者bom编码搜索产品
     *
     * @param productName 产品名
     * @param bomCode bom编码
     * @return java.lang.String
     * @author 邓艺
     * @date 2018/10/7 13:29
     */
    @RequestMapping(value = "searchForProduct", method = RequestMethod.POST)
    @ResponseBody
    public String searchForProduct(String productName, String bomCode) {
        BaseResult<ProductStorePlmDto> baseResult = new BaseResult<>();
        try {
            ProductStorePlmDto productStorePlmDto = prodBomPlmService.searchForProduct(productName, bomCode);
            baseResult.setResult(true);
            baseResult.setModel(productStorePlmDto);
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
     * 通过产品编码或者半成品编码查询bom
     *
     * @param pNo
     * @param mpno
     * @return java.lang.String
     * @author 邓艺
     * @date 2018/10/7 14:51
     */
    @RequestMapping(value = "findBomByPnoOrMpno", method = RequestMethod.POST)
    @ResponseBody
    public String findBomByPnoOrMpno(String pNo, String mpno) {
        BaseResult<ProdBomPlm> baseResult = new BaseResult<>();
        try {
            ProdBomPlm prodBomPlm = prodBomPlmService.findBomByPnoOrMpno(pNo, mpno);
            baseResult.setResult(true);
            baseResult.setModel(prodBomPlm);
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
     * 通过产品编码或者半成品编码查询bom
     *
     * @param prodBomPlm
     * @return java.lang.String
     * @author 邓艺
     * @date 2018/10/7 14:51
     */
    @RequestMapping(value = "addBomNo", method = RequestMethod.POST)
    @ResponseBody
    public String addBomNo(ProdBomPlm prodBomPlm) {
        BaseResult<ProdBomPlm> baseResult = new BaseResult<>();
        try {
            prodBomPlm.setCompanyId(UserHolder.getCompanyId());
            prodBomPlm.setMakeEmp(UserHolder.getUserName());
            prodBomPlm.setMakeDate(new Date());
            prodBomPlm.setBomStatus("启用中");
            prodBomPlmService.addBomNo(prodBomPlm);
            baseResult.setResult(true);
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
     * 新增物料
     *
     * @param mattersStoreIos
     * @return java.lang.String
     * @author 邓艺
     * @date 2018/10/7 14:51
     */
    @RequestMapping(value = "addMatters", method = RequestMethod.POST)
    @ResponseBody
    public String addMatters(MattersStoreIos mattersStoreIos) {
        BaseResult<ProdBomPlm> baseResult = new BaseResult<>();
        try {
            mattersStoreIos.setCompanyId(UserHolder.getCompanyId());
            prodBomPlmService.addMatters(mattersStoreIos);
            baseResult.setResult(true);
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