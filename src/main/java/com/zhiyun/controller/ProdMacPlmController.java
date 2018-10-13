/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */
package com.zhiyun.controller;

import com.alibaba.fastjson.JSON;
import com.zhiyun.base.dto.BaseResult;
import com.zhiyun.base.exception.BusinessException;
import com.zhiyun.client.UserHolder;
import com.zhiyun.dto.MachineSetMesDto;
import com.zhiyun.dto.ProdMacPlmDto;
import com.zhiyun.entity.MachineSetMes;
import com.zhiyun.entity.ProdMacPlm;
import com.zhiyun.form.ProdMacPlmForm;
import com.zhiyun.service.MachineSetMesService;
import com.zhiyun.service.ProdMacPlmService;
import com.zhiyun.util.ConstansUtil;
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
 * 工艺设备
 *
 * @author xufei
 */
@Controller
@RequestMapping(value = "/prodMac", produces = "application/json;charset=UTF-8")
public class ProdMacPlmController extends PublicController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProdMacPlmController.class);

    @Resource
    private ProdMacPlmService prodMacPlmService;
    @Resource
    private MachineSetMesService machineSetMesService;

    /**
     * 工艺输入物料\半成品列表查看
     *
     * @param machineSetMes
     * @param bindingResult
     * @return
     * @author xufei
     * @date 2018-9-15 10:15:18
     */
    @ResponseBody
    @RequestMapping(value = "/getMac", method = {RequestMethod.GET, RequestMethod.POST})
    public Object getMac(@RequestBody MachineSetMes machineSetMes, BindingResult bindingResult) {
        BaseResult<List<MachineSetMesDto>> baseResult = new BaseResult<List<MachineSetMesDto>>();
        baseResult.setResult(true);
        baseResult.setMessage("查询成功！");
        try {
            vaildParamsDefault(baseResult, bindingResult);
            machineSetMes.setCompanyId(UserHolder.getCompanyId());
            List<MachineSetMesDto> list = machineSetMesService.getMac(machineSetMes);
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
     * 工艺设备查看
     *
     * @param prodMacPlm
     * @param bindingResult
     * @return
     * @author xufei
     * @date 2018-9-17 13:22:00
     */
    @ResponseBody
    @RequestMapping(value = "/findMacPlm", method = {RequestMethod.GET, RequestMethod.POST})
    public Object findMacPlm(@RequestBody ProdMacPlm prodMacPlm, BindingResult bindingResult) {
        BaseResult<List<ProdMacPlmDto>> baseResult = new BaseResult<List<ProdMacPlmDto>>();
        baseResult.setResult(true);
        baseResult.setMessage("查询成功！");
        try {
            vaildParamsDefault(baseResult, bindingResult);
            prodMacPlm.setCompanyId(UserHolder.getCompanyId());
            List<ProdMacPlmDto> list = prodMacPlmService.findMacPlm(prodMacPlm);
            for (ProdMacPlmDto dto : list) {
                dto.setTotalName(ConstansUtil.RunTotal.getRunTotalDesc(dto.getRunTotal()));
            }
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
     * 工艺设备添加
     *
     * @param prodMacPlmForm
     * @param bindingResult
     * @return
     * @author xufei
     * @date 2018-9-17 13:22:00
     */
    @ResponseBody
    @RequestMapping(value = "/addMacPlm", method = {RequestMethod.GET, RequestMethod.POST})
    public Object addMacPlm(@RequestBody ProdMacPlmForm prodMacPlmForm, BindingResult bindingResult) {
        BaseResult<List<ProdMacPlmDto>> baseResult = new BaseResult<List<ProdMacPlmDto>>();
        baseResult.setResult(true);
        baseResult.setMessage("新增成功！");
        try {
            vaildParamsDefault(baseResult, bindingResult);
            List<ProdMacPlmDto> list = prodMacPlmService.addMacPlm(prodMacPlmForm);
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