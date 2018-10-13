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
import com.zhiyun.client.UserHolder;
import com.zhiyun.dto.CrafworkInputMaterPlmDto;
import com.zhiyun.entity.CrafworkInputMaterPlm;
import com.zhiyun.form.CrafworkInputMaterPlmForm;
import com.zhiyun.service.CrafworkInputMaterPlmService;
import com.zhiyun.util.StatusUtil;
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
 * 工艺输入物料\半成品
 *
 * @author xufei
 */
@Controller
@RequestMapping(value = "/input", produces = "application/json;charset=UTF-8")
public class CrafworkInputMaterPlmController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CrafworkInputMaterPlmController.class);

    @Resource
    private CrafworkInputMaterPlmService crafworkInputMaterPlmService;

    /**
     * 工艺输入物料\半成品列表查看
     *
     * @param crafworkInputMaterPlm
     * @param bindingResult
     * @return
     * @author xufei
     * @date 2018-9-15 8:20:56
     */
    @ResponseBody
    @RequestMapping(value = "/findInput", method = {RequestMethod.GET, RequestMethod.POST})
    public Object findInput(@RequestBody CrafworkInputMaterPlm crafworkInputMaterPlm, BindingResult bindingResult) {
        BaseResult<List<CrafworkInputMaterPlmDto>> baseResult = new BaseResult<List<CrafworkInputMaterPlmDto>>();
        baseResult.setResult(true);
        baseResult.setMessage("查询成功！");
        try {
            vaildParamsDefault(baseResult, bindingResult);
            crafworkInputMaterPlm.setCompanyId(UserHolder.getCompanyId());
            List<CrafworkInputMaterPlmDto> list = crafworkInputMaterPlmService.findInput(crafworkInputMaterPlm);
            if (CollectionUtils.isNotEmpty(list)) {
                for (CrafworkInputMaterPlmDto dto : list) {
                    dto.setStatus(StatusUtil.matterStatus.map.get(dto.getStatus()));
                }
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
     * 工艺输入物料\半成品保存
     *
     * @param crafworkInputMaterPlmForm
     * @param bindingResult
     * @return
     * @author xufei
     * @date 2018-9-15 10:15:18
     */
    @ResponseBody
    @RequestMapping(value = "/addInput", method = {RequestMethod.GET, RequestMethod.POST})
    public Object addInput(@RequestBody CrafworkInputMaterPlmForm crafworkInputMaterPlmForm, BindingResult bindingResult) {
        BaseResult<List<CrafworkInputMaterPlmDto>> baseResult = new BaseResult<List<CrafworkInputMaterPlmDto>>();
        baseResult.setResult(true);
        baseResult.setMessage("保存成功！");
        try {
            vaildParamsDefault(baseResult, bindingResult);
            List<CrafworkInputMaterPlmDto> list = crafworkInputMaterPlmService.addInput(crafworkInputMaterPlmForm);
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