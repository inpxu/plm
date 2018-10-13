/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service.impl;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.exception.BusinessException;
import com.zhiyun.base.service.BaseServiceImpl;
import com.zhiyun.client.UserHolder;
import com.zhiyun.dao.ProdBomDetailPlmDao;
import com.zhiyun.dao.ProdBomPlmDao;
import com.zhiyun.dao.ProductMidPlmDao;
import com.zhiyun.dao.VoucherMainOaDao;
import com.zhiyun.dto.*;
import com.zhiyun.entity.ProdBomDetailPlm;
import com.zhiyun.entity.ProdBomPlm;
import com.zhiyun.entity.ProductMidPlm;
import com.zhiyun.entity.VoucherMainOa;
import com.zhiyun.internal.uniqueid.UniqueIdService;
import com.zhiyun.service.ProdBomPlmService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 产品物料构成主表Service接口实现类。
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Service("prodBomPlmService")
public class ProdBomPlmServiceImpl extends BaseServiceImpl<ProdBomPlm, Long> implements ProdBomPlmService {
    @Autowired
    private UniqueIdService uniqueIdService;
    @Autowired
    private VoucherMainOaDao voucherMainOaDao;
    @Autowired
    private ProductMidPlmDao productMidPlmDao;
    @Resource
    private ProdBomPlmDao prodBomPlmDao;
    @Autowired
    private ProdBomDetailPlmDao prodBomDetailPlmDao;

    @Override
    protected BaseDao<ProdBomPlm, Long> getBaseDao() {
        return this.prodBomPlmDao;
    }

    @Override
    public ProductStorePlmDto searchForProduct(String productName, String bomCode) {
        Map<String, Object> param = new HashMap<>(3);
        param.put("productName", productName);
        param.put("bomCode", bomCode);
        param.put("companyId", UserHolder.getCompanyId());
        return prodBomPlmDao.searchForProduct(param);
    }

    @Override
    public ProdBomPlmDto findBomByPnoOrMpno(String pNo, String mpno) {
        Map<String, Object> param = new HashMap<>(3);
        param.put("companyId", UserHolder.getCompanyId());
        if (StringUtils.isNotBlank(pNo) && StringUtils.isBlank(mpno)) {
            //通过产品编码查询
            param.put("productNo", pNo);
            ProdBomPlmDto bomByPno = prodBomPlmDao.findBomByPno(param);
            //查询半成品封装
            //查询产品级下有无物料
            List<MattersStoreDto> mattersStoreIosList = prodBomPlmDao.findAllMattersFroProduct(param);
            List<ProductMidPlmDto> list = prodBomPlmDao.findAllMidProductByPno(param);
            //TODO 判断半成品是否含有下一级
            if (CollectionUtils.isNotEmpty(list)) {
                for (ProductMidPlmDto productMidPlmDto : list) {
                    param.put("productNo", productMidPlmDto.getMidProdNo());
                    List<MattersStoreDto> mattersStoreIosLists = prodBomPlmDao.findAllMattersFroProduct(param);
                    List<ProductMidPlmDto> list1 = prodBomPlmDao.findAllMidProductByPno(param);
                    if (CollectionUtils.isEmpty(mattersStoreIosLists) && CollectionUtils.isEmpty(list1)) {
                        productMidPlmDto.setLeaf(true);
                    }
                }
            }
            //设置是否包含
            if (CollectionUtils.isEmpty(list) && CollectionUtils.isEmpty(mattersStoreIosList)) {
                bomByPno.setLeaf(true);
            }
            bomByPno.setProductMidPlms(list);
            bomByPno.setMattersStoreDtos(mattersStoreIosList);
            return bomByPno;
        } else if (StringUtils.isBlank(pNo) && StringUtils.isNotBlank(mpno)) {
            ProdBomPlmDto bomByPno = new ProdBomPlmDto();
            //通过半成品编码查询
            param.put("productNo", mpno);
            //查询半成品封装
            //查询产品级下有无物料
            List<MattersStoreDto> mattersStoreIosList = prodBomPlmDao.findAllMattersFroProduct(param);
            List<ProductMidPlmDto> list = prodBomPlmDao.findAllMidProductByPno(param);
            //TODO 判断半成品是否含有下一级
            if (CollectionUtils.isNotEmpty(list)) {
                for (ProductMidPlmDto productMidPlmDto : list) {
                    param.put("productNo", productMidPlmDto.getMidProdNo());
                    List<MattersStoreDto> mattersStoreIosLists = prodBomPlmDao.findAllMattersFroProduct(param);
                    if (CollectionUtils.isEmpty(mattersStoreIosLists)) {
                        productMidPlmDto.setLeaf(true);
                    }
                }
            }

            //设置是否包含
            if (CollectionUtils.isEmpty(list) && CollectionUtils.isEmpty(mattersStoreIosList)) {
                bomByPno.setLeaf(true);
            }
            bomByPno.setProductMidPlms(list);
            bomByPno.setMattersStoreDtos(mattersStoreIosList);
            return bomByPno;
        }
        return null;
    }

    @Override
    public void addBomNo(ProdBomPlm prodBomPlm) {
        prodBomPlmDao.insert(prodBomPlm);
    }

    @Override
    @Transactional
    public List<ProdBomDetailPlmDto> addMatters(ProdBomDetailPlm[] mattersStoreIos) {
        List<ProdBomDetailPlmDto> list = new ArrayList<>();
        if (ArrayUtils.isNotEmpty(mattersStoreIos)) {
            for (ProdBomDetailPlm mattersStoreIo : mattersStoreIos) {
                mattersStoreIo.setCompanyId(UserHolder.getCompanyId());
                prodBomDetailPlmDao.insert(mattersStoreIo);
                List<ProdBomDetailPlm> list1 = prodBomDetailPlmDao.find(mattersStoreIo);
                if (CollectionUtils.isNotEmpty(list1)) {
                    ProdBomDetailPlm prodBomDetailPlm = list1.get(0);
                    Map<String, Object> param = new HashMap<>(2);
                    param.put("id", prodBomDetailPlm.getId());
                    param.put("companyId", UserHolder.getCompanyId());
                    ProdBomDetailPlmDto prodBomDetailPlmDto = prodBomDetailPlmDao.ReturnInfoForFront(param);
                    list.add(prodBomDetailPlmDto);
                }
            }
        }
        return list;
    }

    @Override
    @Transactional
    public void commit2Approve(String bomCode) {
        String bom = uniqueIdService.mixedId("Bom", 10, UserHolder.getCompanyId());
        VoucherMainOa voucherMainOa = new VoucherMainOa();
        voucherMainOa.setIsFinished("审批中");
        voucherMainOa.setVoucherNo(bom);
        voucherMainOa.setCompanyId(UserHolder.getCompanyId());
        voucherMainOaDao.insert(voucherMainOa);
        Map<String, Object> param = new HashMap<>(3);
        param.put("bomCode", bomCode);
        param.put("companyId", UserHolder.getCompanyId());
        param.put("vocherNo", bom);
        prodBomPlmDao.updateBom(param);
    }

    @Override
    @Transactional
    public void deleteMatters(Long[] ids) {
        if (ArrayUtils.isNotEmpty(ids)) {
            for (Long id : ids) {
                prodBomDetailPlmDao.delete(id);
            }
        }
    }

    @Override
    @Transactional
    public void startOrStopBom(String bomCode) {
        Map<String, Object> param = new HashMap<>(3);
        param.put("companyId", UserHolder.getCompanyId());
        param.put("bomCode", bomCode);
        ProdBomPlm prodBomPlm = prodBomPlmDao.selectBeforeUpdate(param);
        if (prodBomPlm != null) {
            if ("启用中".equals(prodBomPlm.getBomStatus())) {
                param.put("bomStatus", "停用");
            } else {
                param.put("bomStatus", "启用中");
            }
            prodBomPlmDao.startOrStopBom(param);
        }

    }

    @Override
    public ProdBomPlm searchForCompnent(String productName, String bomCode) {
        Map<String, Object> param = new HashMap<>(3);
        param.put("productName", productName);
        param.put("bomCode", bomCode);
        param.put("companyId", UserHolder.getCompanyId());
        return prodBomPlmDao.searchForCompnent(param);
    }

    @Override
    public List<MattersStoreDto> optionBomCodeAndProdName() {
        Map<String, Object> param = new HashMap<>(1);
        param.put("companyId", UserHolder.getCompanyId());
        return prodBomPlmDao.optionBomCodeAndProdName(param);
    }

    @Override
    public List<MattersStoreDto> SearchBeforeAddMatters(String codeOrName) {
        Map<String, Object> param = new HashMap<>(3);
        param.put("codeOrName", codeOrName);
        param.put("companyId", UserHolder.getCompanyId());
        return prodBomPlmDao.SearchBeforeAddMatters(param);
    }

    @Override
    @Transactional
    public void updateMatter(ProdBomDetailPlm prodBomDetailPlm) {
        prodBomDetailPlmDao.update(prodBomDetailPlm);
    }

    @Override
    public List<ProductMidPlmDto> findMidProduct(String parentNo) {
        Map<String, Object> param = new HashMap<>(3);
        param.put("parentNo", parentNo);
        param.put("companyId", UserHolder.getCompanyId());
        List<ProductMidPlmDto> midProduct = prodBomPlmDao.findMidProduct(param);
        if (CollectionUtils.isNotEmpty(midProduct)) {
            for (ProductMidPlmDto productMidPlmDto : midProduct) {
                param.put("parentNo", productMidPlmDto.getMidProdNo());
                List<ProductMidPlmDto> productMidPlmDtos = prodBomPlmDao.findMidProduct(param);
                if (CollectionUtils.isEmpty(productMidPlmDtos)) {
                    productMidPlmDto.setLeaf(true);
                }
            }
        }
        return midProduct;
    }

    @Override
    @Transactional
    public void changeNumber(boolean isMidProduct, Long id, Long number, Long numberBefore) {
        if (isMidProduct) {
            //为半成品，更新半成品数量的同时，要更新半成品下所有的半成品及,其下的半成品数量向上取整
            //1.查询半成品
            Map<String, Object> param = new HashMap<>();
            param.put("companyId", UserHolder.getCompanyId());
            param.put("midProductId", id);
            List<ProductMidPlm> list = productMidPlmDao.findAllMidProductByMidId(param);
            if (CollectionUtils.isNotEmpty(list)) {
                for (ProductMidPlm productMidPlm : list) {
                    //减小
                    if (numberBefore > number) {
                        double ceil = Math.ceil((number / numberBefore) * productMidPlm.getAmount().doubleValue());
                        productMidPlm.setAmount(ceil);
                        //增大
                    } else if (numberBefore < number) {
                        double ceil = Math.ceil((numberBefore / number) * productMidPlm.getAmount().doubleValue());
                        productMidPlm.setAmount(ceil);
                    }

                }
            }
            //2.更新半成品下的物料
            List<MattersStoreDto> mattersStoreIosLists = prodBomPlmDao.findAllMattersFroProduct(param);
        } else {
            //更新的为物料
            Map<String, Object> param = new HashMap<>(2);
            param.put("id", id);
            param.put("number", number);
            param.put("companyId", UserHolder.getCompanyId());
            prodBomDetailPlmDao.updateMatterNumber(param);
        }
    }

    @Override
    public ProdBomPlmDto findCommonBom(String matterName, String bomCode) {
        Map<String, Object> param = new HashMap<>(2);
        param.put("companyId", UserHolder.getCompanyId());
        if (StringUtils.isNotBlank(matterName) && StringUtils.isBlank(bomCode)) {
            //通过产品编码查询
            param.put("matterName", matterName);
            ProdBomPlmDto bomByMatterName = prodBomPlmDao.findCommonBomByMatterName(param);
            if (bomByMatterName == null) {
                throw new BusinessException("bom不存在");
            }
            param.put("productNo", bomByMatterName.getProdNo());
            List<MattersStoreDto> mattersStoreIosLists = prodBomPlmDao.findAllMattersFroProduct(param);
            bomByMatterName.setMattersStoreDtos(mattersStoreIosLists);
            return bomByMatterName;
        } else if (StringUtils.isBlank(matterName) && StringUtils.isNotBlank(bomCode)) {
            param.put("bomCode", bomCode);
            return prodBomPlmDao.findCommonBomByBomCode(param);
        } else {
            //通过产品编码查询
            param.put("matterName", matterName);
            ProdBomPlmDto bomByMatterName = prodBomPlmDao.findCommonBomByMatterName(param);
            if (bomByMatterName == null) {
                throw new BusinessException("bom不存在");
            }
            return bomByMatterName;
        }
    }

    @Override
    public ProdBomPlmDto findCommonBomByProdNo(String prodNo) {
        Map<String, Object> param = new HashMap<>(2);
        param.put("companyId", UserHolder.getCompanyId());
        param.put("prodNo", prodNo);
        return prodBomPlmDao.findCommonBomByProdNo(param);
    }

    @Override
    public List<MattersStoreDto> SearchBeforeAddMattersForCom(String codeOrName) {
        Map<String, Object> param = new HashMap<>(3);
        param.put("codeOrName", codeOrName);
        param.put("companyId", UserHolder.getCompanyId());
        return prodBomPlmDao.SearchBeforeAddMattersForCom(param);
    }

    @Override
    public List<MattersStoreDto> optionComponent() {
        Map<String, Object> param = new HashMap<>(1);
        param.put("companyId", UserHolder.getCompanyId());
        return prodBomPlmDao.optionComponent(param);
    }

    @Override
    @Transactional
    public void approve(String bomCode) {
        Map<String, Object> param = new HashMap<>(2);
        param.put("companyId", UserHolder.getCompanyId());
        param.put("bomCode", bomCode);
        voucherMainOaDao.approve(param);
    }

}
