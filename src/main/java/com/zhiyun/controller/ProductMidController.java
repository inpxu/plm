/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.controller;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSON;
import com.zhiyun.base.dto.BaseResult;
import com.zhiyun.base.exception.BusinessException;
import com.zhiyun.base.model.DataGrid;
import com.zhiyun.base.model.LabelValue;
import com.zhiyun.base.model.Pager;
import com.zhiyun.base.model.Params;
import com.zhiyun.base.util.CommonUtils;
import com.zhiyun.client.UserHolder;
import com.zhiyun.dao.MattersStoreIosDao;
import com.zhiyun.dto.ProdMidDto;
import com.zhiyun.entity.MattersStoreIos;
import com.zhiyun.entity.ProductMidPlm;
import com.zhiyun.entity.ProductStorePlm;
import com.zhiyun.service.MattersStoreIosService;
import com.zhiyun.service.ProductMidPlmService;
import com.zhiyun.service.ProductStorePlmService;
import com.zhiyun.util.ConstansUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author sun
 * @version v1.0
 * @date 2018-09-06 08:50
 */

@Controller
@RequestMapping(value = "/prodMid", produces = "application/json;charset=UTF-8")
public class ProductMidController extends PublicController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductMidController.class);
    @Resource
    private ProductMidPlmService productMidPlmService;
    @Resource
    private MattersStoreIosService mattersStoreIosService;
    @Resource
    private ProductStorePlmService productStorePlmService;
    @Resource
    private MattersStoreIosDao mattersStoreIosDao;

    /**
     * 分页查询
     *
     * @param productMidPlm 查询条件
     * @param pager 分页条件
     */
    @ResponseBody
    @RequestMapping(value = "/pageMid", method = {RequestMethod.GET, RequestMethod.POST})
    public String pageMids(ProdMidDto productMidPlm, Pager pager) {
        BaseResult<DataGrid<ProdMidDto>> baseResult = new BaseResult<DataGrid<ProdMidDto>>();
        baseResult.setResult(true);
        baseResult.setMessage("查询成功");
        try {
            productMidPlm.setCompanyId(UserHolder.getCompanyId());
            Params params = Params.create().add("entity", productMidPlm);
            DataGrid<ProdMidDto> dataGrid = productMidPlmService.pageMid(params, pager);
            for (ProdMidDto prodMidDto : dataGrid.getItems()) {
                prodMidDto.setSourceName(ConstansUtil.Sources.getSourceDesc(Integer.parseInt(prodMidDto.getSource())));
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
     * 新增半成品
     *
     * @param productMidPlm 新增
     */
    @ResponseBody
    @RequestMapping(value = "/addMid", method = {RequestMethod.GET, RequestMethod.POST})
    public String addMid(@Valid ProductMidPlm productMidPlm, BindingResult bindingResult) {
        BaseResult<ProductMidPlm> baseResult = new BaseResult<ProductMidPlm>();
        baseResult.setResult(true);
        baseResult.setMessage("新增成功");
        try {
            vaildParamsDefault(baseResult, bindingResult);
            String midProdNo = productMidPlm.getMidProdNo();
            MattersStoreIos ios = new MattersStoreIos();
            ios.setMattersNo(midProdNo);
            ios.setCompanyId(UserHolder.getCompanyId());
            List<MattersStoreIos> storeIosList = mattersStoreIosDao.find(ios);
            if (CollectionUtils.isNotEmpty(storeIosList)) {
                throw new BusinessException("半成品编码与物料编码重复！");
            }
            // 新增半成品
            // 通过产品编码查寻产品信息
            String paraentNo = productMidPlm.getParentNo();
            productMidPlm.setCompanyId(UserHolder.getCompanyId());
            ProductStorePlm storePlm = new ProductStorePlm();
            storePlm.setCompanyId(UserHolder.getCompanyId());
            storePlm.setProdNo(productMidPlm.getProdNo());
            List<ProductStorePlm> productStorePlms = productStorePlmService.find(storePlm);
            if (!CommonUtils.isEmpty(productStorePlms) && productStorePlms.size() == 1) {
                productMidPlm.setProdName(productStorePlms.get(0).getProdName());
            } else {
                throw new BusinessException("产品编码不唯一");
            }
            ProductMidPlm productMidPlm1 = new ProductMidPlm();
            productMidPlm1.setCompanyId(UserHolder.getCompanyId());
            productMidPlm1.setMidProdNo(productMidPlm.getMidProdNo());
            productMidPlm1.setMidProdName(productMidPlm.getMidProdName());
            //存在性校验
            List<ProductMidPlm> midPlms = productMidPlmService.find(productMidPlm);
            if (!CommonUtils.isEmpty(midPlms)) {
                throw new BusinessException("半成品编码或名称已存在");
            }
            // 设置层级
            if (productMidPlm.getParentNo().equals(productMidPlm.getProdNo())) {
                productMidPlm.setHierarchy(2L);
            } else {
                productMidPlm1.setMidProdNo(productMidPlm.getParentNo());
                productMidPlm1.setMidProdName(null);
                midPlms = productMidPlmService.find(productMidPlm1);
                if (CollectionUtils.isNotEmpty(midPlms)) {
                    if (midPlms.get(0).getHierarchy() >= 7) {
                        throw new BusinessException("超过层级上限");
                    }
                    productMidPlm.setHierarchy(midPlms.get(0).getHierarchy() + 1);
                } else {
                    productMidPlm.setHierarchy(1L);
                }

            }

            if (paraentNo == null || paraentNo == ""){
                productMidPlm.setParentNo(productMidPlm.getProdNo());
            }
            productMidPlm = productMidPlmService.insert(productMidPlm);
            // 新增物料库
            MattersStoreIos mattersStoreIos = new MattersStoreIos();
            mattersStoreIos.setCompanyId(UserHolder.getCompanyId());
            mattersStoreIos.setMattersNo(productMidPlm.getMidProdNo());
            mattersStoreIos.setMattersName(productMidPlm.getMidProdName());
            mattersStoreIos.setIsMidprod("2");
            mattersStoreIos.setNorms(productMidPlm.getNorms());
            mattersStoreIos.setModelDesc(productMidPlm.getModelDesc());
            mattersStoreIos.setUnit(productMidPlm.getUnit());
            mattersStoreIos.setParam(productMidPlm.getParam());
            mattersStoreIosService.insert(mattersStoreIos);
            baseResult.setModel(productMidPlm);
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
     * 编辑半成品
     *
     * @param productMidPlm 查询条件
     */
    @ResponseBody
    @RequestMapping(value = "/editMid", method = {RequestMethod.GET, RequestMethod.POST})
    public String editMid(@Valid ProductMidPlm productMidPlm, BindingResult bindingResult) {
        BaseResult<ProductMidPlm> baseResult = new BaseResult<ProductMidPlm>();
        baseResult.setResult(true);
        baseResult.setMessage("修改成功");
        try {
            vaildParamsDefault(baseResult, bindingResult);
            ProductMidPlm productMidPlm1 = new ProductMidPlm();
            productMidPlm1.setCompanyId(UserHolder.getCompanyId());
            productMidPlm1.setMidProdNo(productMidPlm.getMidProdNo());
            productMidPlm1.setMidProdName(productMidPlm.getMidProdName());
            // 存在性校验
            List<ProductMidPlm> midPlms = productMidPlmService.find(productMidPlm);
            if (!CommonUtils.isEmpty(midPlms) && midPlms.size() > 1) {
                throw new BusinessException("半成品编码或名称已存在");
            }
            MattersStoreIos mattersStoreIos = new MattersStoreIos();
            mattersStoreIos.setCompanyId(UserHolder.getCompanyId());
            mattersStoreIos.setMattersNo(productMidPlm.getMidProdNo());
            List<MattersStoreIos> mattersStoreIosList = mattersStoreIosService.find(mattersStoreIos);
            if (!CommonUtils.isEmpty(mattersStoreIosList)) {
                // 更新物料
                for (MattersStoreIos mattersStoreIos1 : mattersStoreIosList) {
                    if ("2".equals(mattersStoreIos1.getIsMidprod())) {
                        mattersStoreIos1.setMattersName(productMidPlm.getMidProdName());
                        mattersStoreIos1.setIsMidprod("2");
                        mattersStoreIos1.setNorms(productMidPlm.getNorms());
                        mattersStoreIos1.setModelDesc(productMidPlm.getModelDesc());
                        mattersStoreIos1.setUnit(productMidPlm.getUnit());
                        mattersStoreIos1.setParam(productMidPlm.getParam());
                        mattersStoreIosService.update(mattersStoreIos1);
                    }

                }
            }
            // 更新半成品
            productMidPlmService.update(productMidPlm);
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
     * 删除半成品
     *
     * @param ids
     */
    @SuppressWarnings("all")
    @ResponseBody
    @RequestMapping(value = "removeMid", method = {RequestMethod.POST, RequestMethod.GET})
    public String removeMid(@RequestParam("ids") Long[] ids) {
        BaseResult<String> baseResult = new BaseResult<String>();
        baseResult.setResult(true);
        baseResult.setMessage("删除成功");
        try {
            // 删除物料
            for (Long id : ids) {
                ProductMidPlm productMidPlm = productMidPlmService.get(id);
                // 查找删除半成品的子集
                // 查询产品下面的半成品
                ProdMidDto prodMidDtos = new ProdMidDto();
                prodMidDtos.setCompanyId(UserHolder.getCompanyId());
                prodMidDtos.setParentNo(productMidPlm.getProdNo());
                List<ProdMidDto> prodMidDto = productMidPlmService.findMid(prodMidDtos);
                // 递归查询删除子集
                if (!CommonUtils.isEmpty(prodMidDto)) {
                    for (ProdMidDto prodMidDto1 : prodMidDto) {
                        // 通过编码 名称和是否半成品查找物料 并删除
                        MattersStoreIos mattersStoreIos = new MattersStoreIos();
                        mattersStoreIos.setCompanyId(UserHolder.getCompanyId());
                        mattersStoreIos.setMattersNo(prodMidDto1.getMidProdNo());
                        mattersStoreIos.setMattersName(prodMidDto1.getMidProdName());
                        mattersStoreIos.setIsMidprod("2");
                        List<MattersStoreIos> mattersStoreIos1 = mattersStoreIosService.find(mattersStoreIos);
                        for (MattersStoreIos mattersStoreIos2 : mattersStoreIos1) {
                            mattersStoreIosService.delete(mattersStoreIos2.getId());
                        }
                        productMidPlmService.delete(prodMidDto1.getId());
                        this.deletedMidTree(prodMidDto1);
                    }
                }
                // 通过编码 名称和是否半成品查找物料
                MattersStoreIos mattersStoreIos = new MattersStoreIos();
                mattersStoreIos.setCompanyId(UserHolder.getCompanyId());
                mattersStoreIos.setMattersNo(productMidPlm.getMidProdNo());
                mattersStoreIos.setMattersName(productMidPlm.getMidProdName());
                mattersStoreIos.setIsMidprod("2");
                List<MattersStoreIos> mattersStoreIos1 = mattersStoreIosService.find(mattersStoreIos);
                for (MattersStoreIos mattersStoreIos2 : mattersStoreIos1) {
                    mattersStoreIosService.delete(mattersStoreIos2.getId());
                }
            }
            // 删除选中的半成品
            productMidPlmService.delete(Arrays.asList(ids));
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
     * 来源下啦
     */
    @ResponseBody
    @RequestMapping(value = "SourceList", method = {RequestMethod.POST, RequestMethod.GET})
    public String sourceList() {
        BaseResult<List<LabelValue>> baseResult = new BaseResult<List<LabelValue>>();
        baseResult.setResult(true);
        baseResult.setMessage("获取成功");
        try {
            List<LabelValue> labelValues = new ArrayList<LabelValue>();
            for (ConstansUtil.Sources source : ConstansUtil.Sources.values()) {
                LabelValue labelValue = new LabelValue();
                labelValue.setLabel(ConstansUtil.Sources.getSourceDesc(source.ordinal()));
                labelValue.setValue("" + source.ordinal());
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

    // 递归删除半成品
    @SuppressWarnings("all")
    private List<ProdMidDto> deletedMidTree(ProdMidDto prodMid) {
        ProdMidDto prodMidDto1 = new ProdMidDto();
        prodMidDto1.setCompanyId(UserHolder.getCompanyId());
        prodMidDto1.setParentNo(prodMid.getMidProdNo());
        // 通过parentNo查询子集
        List<ProdMidDto> ProdMidDto = productMidPlmService.findMid(prodMidDto1);
        for (ProdMidDto prodMidDto : ProdMidDto) {
            // 通过编码 名称和是否半成品查找物料删除物料并删除
            MattersStoreIos mattersStoreIos = new MattersStoreIos();
            mattersStoreIos.setCompanyId(UserHolder.getCompanyId());
            mattersStoreIos.setMattersNo(prodMidDto1.getMidProdNo());
            mattersStoreIos.setMattersName(prodMidDto1.getMidProdName());
            mattersStoreIos.setIsMidprod("2");
            List<MattersStoreIos> mattersStoreIos1 = mattersStoreIosService.find(mattersStoreIos);
            for (MattersStoreIos mattersStoreIos2 : mattersStoreIos1) {
                mattersStoreIosService.delete(mattersStoreIos2.getId());
            }
            // 删除子集半成品
            productMidPlmService.delete(prodMidDto.getId());
            // 递归
            this.deletedMidTree(prodMidDto);
        }
        return ProdMidDto;
    }

    /**
     * 获取半成品信息
     *
     * @param productMidPlm
     * @param bindingResult
     * @return
     * @author xufei
     * @date 2018-9-26 15:43:40
     */
    @ResponseBody
    @RequestMapping(value = "/getMidMess", method = {RequestMethod.GET, RequestMethod.POST})
    public Object getMidMess(ProductMidPlm productMidPlm, BindingResult bindingResult) {
        BaseResult<List<ProductMidPlm>> baseResult = new BaseResult<List<ProductMidPlm>>();
        baseResult.setResult(true);
        baseResult.setMessage("查询成功！");
        try {
            vaildParamsDefault(baseResult, bindingResult);
            List<ProductMidPlm> list = productMidPlmService.getMidMess(productMidPlm);
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
     * 上级编码下拉
     *
     * @param prodMidDto
     * @return
     * @author xufei
     * @date 2018-10-15 17:03:33
     */
    @ResponseBody
    @RequestMapping(value = "/getParentNo", method = {RequestMethod.GET, RequestMethod.POST})
    public Object getParentNo(ProductMidPlm prodMidDto) {
        BaseResult<List<ProductMidPlm>> baseResult = new BaseResult<List<ProductMidPlm>>();
        baseResult.setResult(true);
        baseResult.setMessage("查询成功！");
        try {
            List<ProductMidPlm> list = productMidPlmService.getParentNo(prodMidDto);
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
