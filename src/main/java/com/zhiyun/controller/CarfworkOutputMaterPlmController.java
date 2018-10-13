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
import com.zhiyun.dto.CarfworkOutputMaterPlmDto;
import com.zhiyun.entity.CarfworkOutputMaterPlm;
import com.zhiyun.service.CarfworkOutputMaterPlmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 工艺输入物料\半成品
 *
 * @author xufei
 */
@Controller
@RequestMapping(value = "/output", produces = "application/json;charset=UTF-8")
public class CarfworkOutputMaterPlmController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarfworkOutputMaterPlmController.class);

    @Resource
    private CarfworkOutputMaterPlmService carfworkOutputMaterPlmService;

    /**
     * 工艺输出半成品查看
     *
     * @param carfworkOutputMaterPlm
     * @param bindingResult
     * @return
     * @author xufei
     * @date 2018-9-17 14:32:59
     */
    @ResponseBody
    @RequestMapping(value = "/findOutput", method = {RequestMethod.GET, RequestMethod.POST})
    public Object findOutput(@RequestBody CarfworkOutputMaterPlm carfworkOutputMaterPlm, BindingResult bindingResult) {
        BaseResult<CarfworkOutputMaterPlmDto> baseResult = new BaseResult<CarfworkOutputMaterPlmDto>();
        baseResult.setResult(true);
        baseResult.setMessage("查询成功！");
        try {
            vaildParamsDefault(baseResult, bindingResult);
            carfworkOutputMaterPlm.setCompanyId(UserHolder.getCompanyId());
            CarfworkOutputMaterPlmDto list = carfworkOutputMaterPlmService.findOutput(carfworkOutputMaterPlm);
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
     * 工艺输出半成品保存
     *
     * @param carfworkOutputMaterPlmDto
     * @param bindingResult
     * @return
     * @author xufei
     * @date 2018-9-17 15:56:49
     */
    @ResponseBody
    @RequestMapping(value = "/addOutput", method = {RequestMethod.GET, RequestMethod.POST})
    public Object addOutput(@RequestBody CarfworkOutputMaterPlmDto carfworkOutputMaterPlmDto, BindingResult bindingResult) {
        BaseResult<CarfworkOutputMaterPlmDto> baseResult = new BaseResult<CarfworkOutputMaterPlmDto>();
        baseResult.setResult(true);
        baseResult.setMessage("查询成功！");
        try {
            vaildParamsDefault(baseResult, bindingResult);
            carfworkOutputMaterPlmDto.setCompanyId(UserHolder.getCompanyId());
            CarfworkOutputMaterPlmDto list = carfworkOutputMaterPlmService.addOutput(carfworkOutputMaterPlmDto);
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