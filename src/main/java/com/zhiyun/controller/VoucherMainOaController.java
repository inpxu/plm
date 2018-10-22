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
import com.zhiyun.dto.CrafworkChangeMainDto;
import com.zhiyun.dto.ProdCrafworkMainPlmDto;
import com.zhiyun.dto.ProdMidDto;
import com.zhiyun.entity.CrafworkChangeMain;
import com.zhiyun.entity.ProdCrafworkMainPlm;
import com.zhiyun.service.ProdCrafworkMainPlmService;
import com.zhiyun.service.VoucherMainOaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 单据
 *
 * @author xufei
 */
@Controller
@RequestMapping(value = "/voucher", produces = "application/json;charset=UTF-8")
public class VoucherMainOaController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(VoucherMainOaController.class);
    @Resource
    private VoucherMainOaService voucherMainOaService;
    @Resource
    private ProdCrafworkMainPlmService prodCrafworkMainPlmService;

    /**
     * 工艺路线提交审核
     *
     * @param prodCrafworkMainPlm
     * @param bindingResult
     * @return
     * @author xufei
     * @date 2018-9-18 17:18:56
     */
    @ResponseBody
    @RequestMapping(value = "/submit ", method = {RequestMethod.GET, RequestMethod.POST})
    public Object submit(@RequestBody ProdCrafworkMainPlm prodCrafworkMainPlm, BindingResult bindingResult) {
        BaseResult<ProdCrafworkMainPlm> baseResult = new BaseResult<ProdCrafworkMainPlm>();
        baseResult.setResult(true);
        baseResult.setMessage("审核已提交！");
        try {
            vaildParamsDefault(baseResult, bindingResult);
            prodCrafworkMainPlm.setCompanyId(UserHolder.getCompanyId());
            voucherMainOaService.submit(prodCrafworkMainPlm);
            prodCrafworkMainPlm.setStatus("审批中");
            baseResult.setModel(prodCrafworkMainPlm);
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
     * 工艺路线列表
     *
     * @param prodMidDto
     * @param bindingResult
     * @return
     * @author xufei
     * @date 2018-9-18 17:18:56
     */
    @ResponseBody
    @RequestMapping(value = "/getMsg ", method = {RequestMethod.GET, RequestMethod.POST})
    public Object getMsg(@RequestBody ProdMidDto prodMidDto, BindingResult bindingResult) {
        BaseResult<ProdCrafworkMainPlmDto> baseResult = new BaseResult<ProdCrafworkMainPlmDto>();
        baseResult.setResult(true);
        baseResult.setMessage("查询成功！");
        try {
            vaildParamsDefault(baseResult, bindingResult);
            ProdCrafworkMainPlmDto dto = prodCrafworkMainPlmService.getMsg(prodMidDto);
            baseResult.setModel(dto);
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
     * 启用停用
     *
     * @param prodCrafworkMainPlmDto
     * @param bindingResult
     * @return
     * @author xufei
     * @date 2018-9-18 17:18:56
     */
    @ResponseBody
    @RequestMapping(value = "/enDis ", method = {RequestMethod.GET, RequestMethod.POST})
    public Object enDis(@RequestBody ProdCrafworkMainPlmDto prodCrafworkMainPlmDto, BindingResult bindingResult) {
        BaseResult<ProdCrafworkMainPlmDto> baseResult = new BaseResult<ProdCrafworkMainPlmDto>();
        baseResult.setResult(true);
        try {
            vaildParamsDefault(baseResult, bindingResult);
            String t = prodCrafworkMainPlmDto.getEnDis();
            if ("true".equals(t)) {
                baseResult.setMessage("启用成功！");
            } else {
                baseResult.setMessage("已停用！");
            }
            prodCrafworkMainPlmService.enDis(prodCrafworkMainPlmDto);
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
     * 工艺路线变更提交审核
     *
     * @param crafworkChangeMainDto
     * @param bindingResult
     * @return
     * @author xufei
     * @date 2018-10-8 10:18:56
     */
    @ResponseBody
    @RequestMapping(value = "/audit ", method = {RequestMethod.GET, RequestMethod.POST})
    public Object audit(@RequestBody CrafworkChangeMainDto crafworkChangeMainDto, BindingResult bindingResult) {
        BaseResult<CrafworkChangeMain> baseResult = new BaseResult<CrafworkChangeMain>();
        baseResult.setResult(true);
        baseResult.setMessage("审核已提交！");
        try {
            vaildParamsDefault(baseResult, bindingResult);
            voucherMainOaService.audit(crafworkChangeMainDto);
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
     * 工艺路线变更审核
     *
     * @param voucherNo
     * @param isPass
     * @return
     * @author xufei
     * @date 2018-10-8 10:18:56
     */
    @ResponseBody
    @RequestMapping("examine")
    public Object examine(@RequestParam(value = "voucherNo", required = true) String voucherNo,
            @RequestParam(value = "isPass", required = true) boolean isPass) {
        BaseResult<CrafworkChangeMain> baseResult = new BaseResult<CrafworkChangeMain>();

        baseResult.setResult(true);
        baseResult.setMessage("操作成功");
        try {
            voucherMainOaService.examine(voucherNo, isPass);
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