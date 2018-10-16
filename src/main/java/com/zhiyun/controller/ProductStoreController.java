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
import com.zhiyun.dto.ProductStorePlmDto;
import com.zhiyun.entity.ProductStorePlm;
import com.zhiyun.service.ProductStorePlmService;
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
 * 产品库
 *
 * @author xufei
 * @version v1.0
 * @date 2018-9-7 08:49:46
 */
@Controller
@RequestMapping(value = "/productStore", produces = "application/json; charset=UTF-8")
public class ProductStoreController extends PublicController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductStoreController.class);

    @Resource
    private ProductStorePlmService productStorePlmService;

    /**
     * 分页查询
     *
     * @param productStorePlmDto
     * @param pager
     * @return
     * @author xufei
     * @date 2018-9-7 11:01:22
     */
    @ResponseBody
    @RequestMapping(value = "/pg", method = {RequestMethod.GET, RequestMethod.POST})
    public Object pg(ProductStorePlmDto productStorePlmDto, Pager pager, BindingResult bindingResult) {
        BaseResult<DataGrid<ProductStorePlmDto>> baseResult = new BaseResult<DataGrid<ProductStorePlmDto>>();
        baseResult.setResult(true);
        baseResult.setMessage("查询成功！");
        try {
            vaildParamsDefault(baseResult, bindingResult);
            productStorePlmDto.setCompanyId(UserHolder.getCompanyId());
            Params params = Params.create().add("entity", productStorePlmDto);
            DataGrid<ProductStorePlmDto> dataGrid = productStorePlmService.pg(params, pager);
            for (ProductStorePlmDto sp : dataGrid.getItems()) {
                sp.setProdMess(sp.getNorms() + "/" + sp.getParam() + "/" + sp.getModelDesc());
                sp.setProdName(sp.getProdNo() + "/" + sp.getProdName());
            }
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
     * @param productStorePlm
     * @param bindingResult
     * @return
     * @author xufei
     * @date 2018-9-7 13:10:05
     */
    @ResponseBody
    @RequestMapping(value = "/add", method = {RequestMethod.GET, RequestMethod.POST})
    public Object add(ProductStorePlm productStorePlm, BindingResult bindingResult) {
        BaseResult<ProductStorePlm> baseResult = new BaseResult<ProductStorePlm>();
        baseResult.setResult(true);
        baseResult.setMessage("新增成功！");
        try {
            vaildParamsDefault(baseResult, bindingResult);
            productStorePlm.setCompanyId(UserHolder.getCompanyId());
            productStorePlmService.add(productStorePlm);
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
     * @param productStorePlm
     * @param bindingResult
     * @return
     * @author xufei
     * @date 2018-9-7 13:10:05
     */
    @ResponseBody
    @RequestMapping(value = "/upd", method = {RequestMethod.GET, RequestMethod.POST})
    public Object upd(ProductStorePlm productStorePlm, BindingResult bindingResult) {
        BaseResult<ProductStorePlm> baseResult = new BaseResult<ProductStorePlm>();
        baseResult.setResult(true);
        baseResult.setMessage("编辑成功！");
        try {
            vaildParamsDefault(baseResult, bindingResult);
            productStorePlm.setCompanyId(UserHolder.getCompanyId());
            productStorePlmService.upd(productStorePlm);
            baseResult.setModel(productStorePlm);
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
        BaseResult<ProductStorePlm> baseResult = new BaseResult<ProductStorePlm>();
        baseResult.setResult(true);
        baseResult.setMessage("产品信息已删除！");
        try {
            if (ArrayUtils.isEmpty(ids)) {
                throw new BusinessException("请选择需要删除的产品信息！");
            }
            productStorePlmService.del(ids);
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
     * 查看详情
     *
     * @param productStorePlm
     * @param bindingResult
     * @return
     * @author xufei
     * @date 2018-9-7 13:10:05
     */
    @ResponseBody
    @RequestMapping(value = "/get", method = {RequestMethod.GET, RequestMethod.POST})
    public Object get(ProductStorePlm productStorePlm, BindingResult bindingResult) {
        BaseResult<ProductStorePlmDto> baseResult = new BaseResult<ProductStorePlmDto>();
        baseResult.setResult(true);
        baseResult.setMessage("查询成功！");
        try {
            vaildParamsDefault(baseResult, bindingResult);
            ProductStorePlmDto productStorePlmDto = productStorePlmService.getById(productStorePlm);
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
     * 下拉查询产品信息
     * 下拉产品信息，供配方bom搜索使用
     *
     * @param
     * @return String
     * @author 邓艺
     * @date 2018/10/13 14:56
     */
    @ResponseBody
    @RequestMapping(value = "/optionProduct", method = RequestMethod.GET)
    public String optionProduct() {
        BaseResult<List<ProductStorePlmDto>> baseResult = new BaseResult<>();
        try {
            List<ProductStorePlmDto> productStorePlmDtos = productStorePlmService.optionProduct();
            baseResult.setResult(true);
            baseResult.setMessage("查询成功");
            baseResult.setModel(productStorePlmDtos);
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