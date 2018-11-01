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
import com.zhiyun.dao.MattersStoreIosDao;
import com.zhiyun.dto.MattersStoreDto;
import com.zhiyun.dto.ProdBomDetailPlmDto;
import com.zhiyun.entity.MattersStoreIos;
import com.zhiyun.service.MattersStoreIosService;
import com.zhiyun.service.ProdBomDetailPlmService;
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
import javax.validation.Valid;
import java.util.List;

/**
 * 产品物料构成明细
 *
 * @author xufei
 */
@Controller
@RequestMapping(value = "/bomDetail", produces = "application/json; charset=UTF-8")
public class ProdBomDetailPlmController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProdBomDetailPlmController.class);
    @Resource
    private ProdBomDetailPlmService prodBomDetailPlmService;
    @Resource
    private MattersStoreIosService mattersStoreIosService;

    /**
     * 获取半成品构成物料
     *
     * @param prodBomDetailPlmDto
     * @param bindingResult
     * @return
     * @author xufei
     * @date 2018-9-14 17:24:09
     */
//    @ResponseBody
//    @RequestMapping(value = "/getMatter", method = {RequestMethod.GET, RequestMethod.POST})
//    public Object getMatter(@Valid @RequestBody ProdBomDetailPlmDto prodBomDetailPlmDto, BindingResult bindingResult) {
//        BaseResult<List<ProdBomDetailPlmDto>> baseResult = new BaseResult<List<ProdBomDetailPlmDto>>();
//        baseResult.setResult(true);
//        baseResult.setMessage("查询成功！");
//        try {
//            vaildParamsDefault(baseResult, bindingResult);
//            prodBomDetailPlmDto.setCompanyId(UserHolder.getCompanyId());
//            List<ProdBomDetailPlmDto> list = prodBomDetailPlmService.getMatter(prodBomDetailPlmDto);
//            for (ProdBomDetailPlmDto dto : list) {
//                dto.setStatus(StatusUtil.matterStatus.map.get(dto.getStatus()));
//            }
//            baseResult.setModel(list);
//        } catch (BusinessException be) {
//            LOGGER.debug("业务异常" + be);
//            baseResult.setResult(false);
//            baseResult.setMessage(be.getMessage());
//        } catch (Exception e) {
//            LOGGER.debug("系统异常" + e);
//            baseResult.setResult(false);
//            baseResult.setMessage("系统异常");
//        }
//        return JSON.toJSONString(baseResult);
//    }


    /**
     * 获取半成品构成物料
     *
     * @return
     * @author xufei
     * @date 2018-11-1 18:33:33
     */
    @ResponseBody
    @RequestMapping(value = "/getMatter", method = {RequestMethod.GET, RequestMethod.POST})
    public Object getMatter() {
        BaseResult<List<MattersStoreDto>> baseResult = new BaseResult<>();
        baseResult.setResult(true);
        baseResult.setMessage("下拉查询成功！");
        try {
            List<MattersStoreDto> list = mattersStoreIosService.findMatter();
            for (MattersStoreDto dto : list) {
                dto.setAllInfo(dto.getNorms());
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
}