/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.controller;

import com.alibaba.fastjson.JSON;
import com.zhiyun.base.controller.BaseController;
import com.zhiyun.base.dto.BaseResult;
import com.zhiyun.base.exception.BusinessException;
import com.zhiyun.base.model.LabelValue;
import com.zhiyun.base.util.CommonUtils;
import com.zhiyun.client.UserHolder;
import com.zhiyun.dto.ProdDto;
import com.zhiyun.dto.ProdMidDto;
import com.zhiyun.dto.ProdStatus;
import com.zhiyun.entity.FactorySetMes;
import com.zhiyun.entity.ProdTypeCrm;
import com.zhiyun.entity.ProductMidPlm;
import com.zhiyun.entity.ProductStorePlm;
import com.zhiyun.service.FactorySetMesService;
import com.zhiyun.service.ProdTypeCrmService;
import com.zhiyun.service.ProductMidPlmService;
import com.zhiyun.service.ProductStorePlmService;
import com.zhiyun.util.ConstansUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 公共方法放这里
 * 使用时自己写的Controller继承这个Controller就可以了
 *
 * @author sun
 * @version v1.0
 * @date 2018-09-06 10:45
 */
public class PublicController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PublicController.class);

    @Resource
    private FactorySetMesService factorySetMesService;
    @Resource
    private ProductStorePlmService productStorePlmService;
    @Resource
    private ProductMidPlmService productMidPlmService;
    @Resource
    private ProdTypeCrmService prodTypeCrmService;

    /**
     * 生产场地下拉
     *
     * @author sun
     */
    @ResponseBody
    @RequestMapping(value = "/factoryList", method = {RequestMethod.GET, RequestMethod.POST})
    public String pageMids(FactorySetMes factorySetMes) {
        BaseResult<List<LabelValue>> baseResult = new BaseResult<List<LabelValue>>();
        baseResult.setResult(true);
        baseResult.setMessage("查询成功");
        try {
            factorySetMes.setCompanyId(UserHolder.getCompanyId());
            List<LabelValue> labelValues = new ArrayList<LabelValue>();
            List<FactorySetMes> factorySetM = factorySetMesService.find(factorySetMes);
            for (FactorySetMes factory : factorySetM) {
                LabelValue labelValue = new LabelValue();
                labelValue.setLabel(factory.getFactoryName());
                labelValue.setValue(factory.getId().toString());
                labelValues.add(labelValue);
            }
            baseResult.setModel(labelValues);
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
     * 产品下拉
     *
     * @author sun
     */
    @ResponseBody
    @RequestMapping(value = "/prodList", method = {RequestMethod.GET, RequestMethod.POST})
    public String prodList(ProductStorePlm productStorePlm) {
        BaseResult<List<LabelValue>> baseResult = new BaseResult<List<LabelValue>>();
        baseResult.setResult(true);
        baseResult.setMessage("查询成功");
        try {
            productStorePlm.setCompanyId(UserHolder.getCompanyId());
            List<LabelValue> labelValues = new ArrayList<LabelValue>();
            List<ProductStorePlm> productStorePlms = productStorePlmService.find(productStorePlm);
            for (ProductStorePlm storePlm : productStorePlms) {
                LabelValue labelValue = new LabelValue();
                labelValue.setLabel(storePlm.getProdNo() + "|" + storePlm.getProdName());
                labelValue.setValue(storePlm.getProdNo());
                labelValues.add(labelValue);
            }
            baseResult.setModel(labelValues);
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
     * 半成品下拉
     *
     * @author sun
     */
    @ResponseBody
    @RequestMapping(value = "/midList", method = {RequestMethod.GET, RequestMethod.POST})
    public String midList(ProductMidPlm productMidPlm) {
        BaseResult<List<LabelValue>> baseResult = new BaseResult<List<LabelValue>>();
        baseResult.setResult(true);
        baseResult.setMessage("查询成功");
        try {
            productMidPlm.setCompanyId(UserHolder.getCompanyId());
            List<LabelValue> labelValues = new ArrayList<LabelValue>();
            List<ProductMidPlm> midPlms = productMidPlmService.find(productMidPlm);
            for (ProductMidPlm storePlm : midPlms) {
                LabelValue labelValue = new LabelValue();
                labelValue.setLabel(storePlm.getMidProdNo() + "|" + storePlm.getMidProdName());
                labelValue.setValue(storePlm.getMidProdNo());
                labelValues.add(labelValue);
            }
            baseResult.setModel(labelValues);
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
     * 产品半产品树
     *
     * @author sun
     */
    @ResponseBody
    @RequestMapping(value = "/midTree", method = {RequestMethod.GET, RequestMethod.POST})
    public String midTree(ProductStorePlm storePlm) {
        BaseResult<List<ProdDto>> baseResult = new BaseResult<List<ProdDto>>();
        baseResult.setResult(true);
        baseResult.setMessage("查询成功");
        try {

            // 查询所有的产品
            storePlm.setCompanyId(UserHolder.getCompanyId());
            List<ProductStorePlm> prodDtos = productStorePlmService.find(storePlm);
            List<ProdDto> prodDtos1 = new ArrayList<ProdDto>();
            for (ProductStorePlm storePlm1 : prodDtos) {
                ProdDto prodDto = new ProdDto();
                prodDto.setCompanyId(UserHolder.getCompanyId());
                BeanUtils.copyProperties(storePlm1, prodDto);
                prodDto.setCosName(storePlm1.getProdName());
                // 查询产品下面的半成品
                ProdMidDto prodMidDtos = new ProdMidDto();
                prodMidDtos.setCompanyId(UserHolder.getCompanyId());
                prodMidDtos.setProdNo(storePlm1.getProdNo());
                prodMidDtos.setParentNo(storePlm1.getProdNo());
                List<ProdMidDto> prodMidDto = productMidPlmService.findMid(prodMidDtos);
                prodDto.setProdMidDtos(prodMidDto);
                // 递归查询
                if (!CommonUtils.isEmpty(prodMidDto)) {
                    for (ProdMidDto prodMidDto1 : prodMidDto) {
                        this.findMidTree(prodMidDto1);
                    }
                }
                prodDtos1.add(prodDto);
            }
            baseResult.setModel(prodDtos1);
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
    // 递归查询半成品
    private ProdMidDto findMidTree(ProdMidDto prodMidDto) {
        ProdMidDto prodMidDto1 = new ProdMidDto();
        prodMidDto1.setCompanyId(UserHolder.getCompanyId());
        prodMidDto1.setParentNo(prodMidDto.getMidProdNo());
        List<ProdMidDto> prodMidDtos = productMidPlmService.findMid(prodMidDto1);
        if (!CommonUtils.isEmpty(prodMidDtos)) {
            prodMidDto.setProdMidDtos(prodMidDtos);
            for (ProdMidDto prodMidDtoes : prodMidDtos) {
                this.findMidTree(prodMidDtoes);
            }
        }
        return prodMidDto;
    }

    /**
     * 产品分类下拉模糊查询
     *
     * @param prodTypeCrm
     * @param bindingResult
     * @return
     * @author xufei
     * @date 2018-9-7 10:24:36
     */
    @ResponseBody
    @RequestMapping(value = "/gt", method = {RequestMethod.GET, RequestMethod.POST})
    public Object getType(ProdTypeCrm prodTypeCrm, BindingResult bindingResult) {
        BaseResult<List<LabelValue>> baseResult = new BaseResult<List<LabelValue>>();
        baseResult.setResult(true);
        baseResult.setMessage("下拉查询成功！");
        try {
            vaildParamsDefault(baseResult, bindingResult);
            List<LabelValue> labelValues = new ArrayList<LabelValue>();
            List<ProdTypeCrm> list = prodTypeCrmService.getType(prodTypeCrm);
            for (ProdTypeCrm pc : list) {
                LabelValue labelValue = new LabelValue();
                labelValue.setLabel(pc.getTypeDesc());
                labelValue.setValue(String.valueOf(pc.getId()));
                labelValues.add(labelValue);
            }
            baseResult.setModel(labelValues);
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
     * 设备班制
     *
     * @param
     * @return
     * @author xufei
     * @date 2018-9-15 13:04:05
     */
    @ResponseBody
    @RequestMapping(value = "/getRunTotal", method = {RequestMethod.GET, RequestMethod.POST})
    public String getRunTotal() {
        BaseResult<List<LabelValue>> baseResult = new BaseResult<List<LabelValue>>();
        baseResult.setResult(true);
        baseResult.setMessage("获取成功");
        try {
            List<LabelValue> labelValues = new ArrayList<LabelValue>();
            for (ConstansUtil.RunTotal runTotal : ConstansUtil.RunTotal.values()) {
                LabelValue labelValue = new LabelValue();
                labelValue.setLabel(ConstansUtil.RunTotal.getRunTotalDesc(runTotal.ordinal()));
                labelValue.setValue("" + runTotal.ordinal());
                labelValues.add(labelValue);
            }
            baseResult.setModel(labelValues);
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
     * 产品状态
     *
     * @return
     * @author xufei
     * @date 2018-9-15 10:04:51
     */
    @ResponseBody
    @RequestMapping(value = "/prodStatus", method = {RequestMethod.GET, RequestMethod.POST})
    public Object prodStatus() {
        BaseResult<List<ProdStatus>> baseResult = new BaseResult<List<ProdStatus>>();
        baseResult.setResult(true);
        baseResult.setMessage("下拉查询成功！");
        List<ProdStatus> list = new ArrayList<ProdStatus>(4);
        ProdStatus ps = new ProdStatus();
        ps.setLabel("缺货");
        ps.setValue("缺货");
        list.add(0, ps);
        ProdStatus ps1 = new ProdStatus();
        ps1.setLabel("正常供货");
        ps1.setValue("正常供货");
        list.add(1, ps1);
        ProdStatus ps2 = new ProdStatus();
        ps2.setLabel("停产");
        ps2.setValue("停产");
        list.add(2, ps2);
        ProdStatus ps3 = new ProdStatus();
        ps3.setLabel("关闭");
        ps3.setValue("关闭");
        list.add(3, ps3);
        baseResult.setModel(list);
        return JSON.toJSONString(baseResult);
    }

}
