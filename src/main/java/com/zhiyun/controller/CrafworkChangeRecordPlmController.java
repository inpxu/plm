/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */
package com.zhiyun.controller;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSON;
import com.zhiyun.base.controller.BaseController;
import com.zhiyun.base.dto.BaseResult;
import com.zhiyun.base.exception.BusinessException;
import com.zhiyun.dto.CrafworkChangeRecordPlmDto;
import com.zhiyun.entity.CrafworkChangeRecordPlm;
import com.zhiyun.form.ChangeRecordForm;
import com.zhiyun.service.CrafworkChangeRecordPlmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 工艺变更申请
 */
@Controller
@RequestMapping(value = "/changeRe", produces = "application/json;charset=UTF-8")
public class CrafworkChangeRecordPlmController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CrafworkChangeRecordPlmController.class);

    @Resource
    private CrafworkChangeRecordPlmService crafworkChangeRecordPlmService;

    /**
     * 工艺路线变更记录
     *
     * @param crafworkChangeRecordPlmDto
     * @param bindingResult
     * @return
     * @author xufei
     * @date 2018-9-18 17:18:56
     */
    @ResponseBody
    @RequestMapping(value = "/getRecord", method = {RequestMethod.GET, RequestMethod.POST})
    public Object getRecord(@RequestBody CrafworkChangeRecordPlmDto crafworkChangeRecordPlmDto, BindingResult bindingResult) {
        BaseResult<ChangeRecordForm> baseResult = new BaseResult<ChangeRecordForm>();
        baseResult.setResult(true);
        baseResult.setMessage("查询成功！");
        try {
            vaildParamsDefault(baseResult, bindingResult);
            ChangeRecordForm form = crafworkChangeRecordPlmService.getRecord(crafworkChangeRecordPlmDto);
            baseResult.setModel(form);
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
     * 工艺变更申请查看详情
     *
     * @param crafworkChangeRecordPlm
     * @param bindingResult
     * @return
     * @author xufei
     * @date 2018-9-26 13:21:10
     */
    @ResponseBody
    @RequestMapping(value = "/getDetail", method = {RequestMethod.GET, RequestMethod.POST})
    public Object getDetail(CrafworkChangeRecordPlm crafworkChangeRecordPlm, BindingResult bindingResult) {
        BaseResult<List<CrafworkChangeRecordPlmDto>> baseResult = new BaseResult<List<CrafworkChangeRecordPlmDto>>();
        baseResult.setResult(true);
        baseResult.setMessage("查询成功！");
        try {
            vaildParamsDefault(baseResult, bindingResult);
            List<CrafworkChangeRecordPlmDto> form = crafworkChangeRecordPlmService.getDetail(crafworkChangeRecordPlm);
            if (CollectionUtils.isNotEmpty(form)) {
                for (CrafworkChangeRecordPlmDto dto : form) {
                    String prodNo = dto.getProdNo();
                    String midProdNo = dto.getMidProdNo();
                    if (prodNo.equals(midProdNo)) {
                        dto.setMidProdMsg("");
                    }
                }
            }
            baseResult.setModel(form);
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
     * 查看工艺变更申请
     *
     * @param crafworkChangeRecordPlmDto
     * @param bindingResult
     * @return
     * @author xufei
     * @date 2018-10-7 10:36:41
     */
    @ResponseBody
    @RequestMapping(value = "/getRecordDetail", method = {RequestMethod.GET, RequestMethod.POST})
    public Object getRecordDetail(CrafworkChangeRecordPlmDto crafworkChangeRecordPlmDto, BindingResult bindingResult) {
        BaseResult<List<CrafworkChangeRecordPlmDto>> baseResult = new BaseResult<List<CrafworkChangeRecordPlmDto>>();
        baseResult.setResult(true);
        baseResult.setMessage("查询成功！");
        try {
            vaildParamsDefault(baseResult, bindingResult);
            List<CrafworkChangeRecordPlmDto> list = crafworkChangeRecordPlmService.getRecordDetail(crafworkChangeRecordPlmDto);
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
}