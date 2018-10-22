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
import com.zhiyun.dto.ProdCrafworkResourceMesDto;
import com.zhiyun.entity.ProdCrafworkResourceMes;
import com.zhiyun.service.ProdCrafworkResourceMesService;
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
 * 工艺资料
 *
 * @author xufei
 */
@Controller
@RequestMapping(value = "/resource", produces = "application/json;charset=UTF-8")
public class ProdCrafworkResourceMesController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProdCrafworkResourceMesController.class);

    @Resource
    private ProdCrafworkResourceMesService prodCrafworkResourceMesService;

    /**
     * 工艺资料新增
     *
     * @param prodCrafworkResourceMesDto
     * @param bindingResult
     * @return
     * @author xufei
     * @date 2018-9-18 08:53:21
     */
    @ResponseBody
    @RequestMapping(value = "/add", method = {RequestMethod.GET, RequestMethod.POST})
    public Object add(@RequestBody ProdCrafworkResourceMesDto prodCrafworkResourceMesDto, BindingResult bindingResult) {
        BaseResult<ProdCrafworkResourceMesDto> baseResult = new BaseResult<ProdCrafworkResourceMesDto>();
        baseResult.setResult(true);
        baseResult.setMessage("新增成功！");
        try {
            vaildParamsDefault(baseResult, bindingResult);
            prodCrafworkResourceMesDto.setCompanyId(UserHolder.getCompanyId());
            ProdCrafworkResourceMesDto list = prodCrafworkResourceMesService.add(prodCrafworkResourceMesDto);
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
     * 工艺资料新增
     *
     * @param prodCrafworkResourceMes
     * @param bindingResult
     * @return
     * @author xufei
     * @date 2018-9-18 09:35:50
     */
    @ResponseBody
    @RequestMapping(value = "/get", method = {RequestMethod.GET, RequestMethod.POST})
    public Object get(@RequestBody ProdCrafworkResourceMes prodCrafworkResourceMes, BindingResult bindingResult) {
        BaseResult<List<ProdCrafworkResourceMesDto>> baseResult = new BaseResult<List<ProdCrafworkResourceMesDto>>();
        baseResult.setResult(true);
        baseResult.setMessage("查询成功！");
        try {
            vaildParamsDefault(baseResult, bindingResult);
            prodCrafworkResourceMes.setCompanyId(UserHolder.getCompanyId());
            List<ProdCrafworkResourceMesDto> list = prodCrafworkResourceMesService.get(prodCrafworkResourceMes);
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
     * 工艺资料编辑
     *
     * @param prodCrafworkResourceMesDto
     * @param bindingResult
     * @return
     * @author xufei
     * @date 2018-9-18 10:03:15
     */
    @ResponseBody
    @RequestMapping(value = "/upd", method = {RequestMethod.GET, RequestMethod.POST})
    public Object upd(@RequestBody ProdCrafworkResourceMesDto prodCrafworkResourceMesDto, BindingResult bindingResult) {
        BaseResult<ProdCrafworkResourceMesDto> baseResult = new BaseResult<ProdCrafworkResourceMesDto>();
        baseResult.setResult(true);
        baseResult.setMessage("编辑成功！");
        try {
            vaildParamsDefault(baseResult, bindingResult);
            prodCrafworkResourceMesDto.setCompanyId(UserHolder.getCompanyId());
            ProdCrafworkResourceMesDto list = prodCrafworkResourceMesService.upd(prodCrafworkResourceMesDto);
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
     * 工艺资料删除
     *
     * @param prodCrafworkResourceMesDto
     * @return
     * @author xufei
     * @date 2018-9-18 10:29:19
     */
    @ResponseBody
    @RequestMapping(value = "del", method = {RequestMethod.POST, RequestMethod.GET})
    public String del(@RequestBody ProdCrafworkResourceMesDto prodCrafworkResourceMesDto) {
        BaseResult<String> baseResult = new BaseResult<>();
        baseResult.setResult(true);
        baseResult.setMessage("删除成功");
        try {
            Long[] ids = prodCrafworkResourceMesDto.getIds();
            if (ids.length == 0) {
                throw new BusinessException("请选择需要删除的文件");
            }
            for (Long id : ids) {
                prodCrafworkResourceMesService.delete(id);
            }
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