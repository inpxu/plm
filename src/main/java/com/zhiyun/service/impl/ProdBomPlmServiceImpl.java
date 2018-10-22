/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service.impl;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.exception.BusinessException;
import com.zhiyun.base.model.DataGrid;
import com.zhiyun.base.model.Pager;
import com.zhiyun.base.model.Params;
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
import java.util.*;

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
        //查询产品
        if (StringUtils.isNotBlank(pNo) && StringUtils.isBlank(mpno)) {
            //通过产品编码查询
            param.put("productNo", pNo);
            //查询bom
            ProdBomPlmDto bomByPno = prodBomPlmDao.findBomByPno(param);
            if (bomByPno != null) {
                //查询半成品封装
                //查询产品级下有无物料
                List<MattersStoreDto> mattersStoreIosList = prodBomPlmDao.findAllMattersFroProduct(param);
                List<ProductMidPlmDto> list = prodBomPlmDao.findAllMidProductByPno(param);
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
            }

            return bomByPno;
        } else if (StringUtils.isBlank(pNo) && StringUtils.isNotBlank(mpno)) {
            //查询半成品
            ProdBomPlmDto bomByPnor = new ProdBomPlmDto();
            //通过半成品编码查询r
            param.put("productNo", mpno);
            //查询产品级下有无物料
            List<MattersStoreDto> mattersStoreIosList = prodBomPlmDao.findAllMattersFroProduct(param);
            //判断物料是纯物料还是公共组件
            if (CollectionUtils.isNotEmpty(mattersStoreIosList)) {
                for (MattersStoreDto mattersStoreDto : mattersStoreIosList) {
                    //公用组件
                    if ("1".equals(mattersStoreDto.getIsMidprod())) {
                        param.put("productNo", mattersStoreDto.getMattersNo());
                        List<MattersStoreDto> mattersStores = prodBomPlmDao.findAllMattersFroProduct(param);
                        if (CollectionUtils.isEmpty(mattersStores)) {
                            mattersStoreDto.setLeaf(true);
                        }
                    } else if ("2".equals(mattersStoreDto.getIsMidprod())) {
                        param.put("productNo", mattersStoreDto.getMattersNo());
                        List<MattersStoreDto> mattersStores = prodBomPlmDao.findAllMattersFroProduct(param);
                        if (CollectionUtils.isEmpty(mattersStores)) {
                            mattersStoreDto.setLeaf(true);
                        }
                    } else {
                        mattersStoreDto.setLeaf(true);
                    }
                }
            }
            List<ProductMidPlmDto> list = prodBomPlmDao.findAllMidProductByPno(param);
            // 判断半成品是否含有下一级
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
                bomByPnor.setLeaf(true);
            }
            bomByPnor.setProductMidPlms(list);
            bomByPnor.setMattersStoreDtos(mattersStoreIosList);
            return bomByPnor;
        }
        return null;
    }

    @Override
    @Transactional
    public ProdBomPlmDto addBomNo(ProdBomPlm prodBomPlm) {
        //bom编码唯一性校验
        this.uniqueBomNo(prodBomPlm.getBomNo());
        prodBomPlm.setCompanyId(UserHolder.getCompanyId());
        prodBomPlm.setMakeEmp(UserHolder.getUserName());
        prodBomPlm.setMakeDate(new Date());
        prodBomPlm.setBomStatus("启用中");
        prodBomPlm.setVersions(String.valueOf(1));
        prodBomPlmDao.insert(prodBomPlm);
        //新增bom成功以后再次查询返回前段供显示
        Map<String, Object> param = new HashMap<>(3);
        param.put("prodNo", prodBomPlm.getProdNo());
        param.put("versionNo", prodBomPlm.getVersions());
        param.put("companyId", UserHolder.getCompanyId());
        return this.findCommonBomByProdNo(param);
    }

    @Override
    @Transactional
    public List<ProdBomDetailPlmDto> addMatters(ProdBomDetailPlmDto[] mattersStoreIos) {
        List<ProdBomDetailPlmDto> list = new ArrayList<>();
        if (ArrayUtils.isNotEmpty(mattersStoreIos)) {
            for (ProdBomDetailPlmDto mattersStoreIo : mattersStoreIos) {
                //保证自己不能添加自己
                if (mattersStoreIo.getMattersNo().equals(mattersStoreIo.getParentNo())) {
                    throw new BusinessException("物料不能递归添加");
                }
                mattersStoreIo.setCompanyId(UserHolder.getCompanyId());
                mattersStoreIo.setSerial(1L);
                mattersStoreIo.setId(null);
                mattersStoreIo.setParentNo(mattersStoreIo.getParentNo());
                prodBomDetailPlmDao.insert(mattersStoreIo);
                List<ProdBomDetailPlm> list1 = prodBomDetailPlmDao.find(mattersStoreIo);
                if (CollectionUtils.isNotEmpty(list1)) {
                    ProdBomDetailPlm prodBomDetailPlm = list1.get(0);
                    Map<String, Object> param = new HashMap<>(2);
                    param.put("id", prodBomDetailPlm.getId());
                    param.put("companyId", UserHolder.getCompanyId());
                    ProdBomDetailPlmDto prodBomDetailPlmDto = prodBomDetailPlmDao.returnInfoForFront(param);
                    list.add(prodBomDetailPlmDto);
                }
            }
        }
        return list;
    }

    @Override
    @Transactional
    public void commit2Approve(String bomCode) {
        String bom = uniqueIdService.mixedId("BOM", 10, UserHolder.getCompanyId());
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
    public List<MattersStoreDto> SearchBeforeAddMatters(String codeOrName, String parentNo) {
        Map<String, Object> param = new HashMap<>(3);
        param.put("codeOrName", codeOrName);
        param.put("companyId", UserHolder.getCompanyId());
        param.put("parentNo", parentNo);
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

    /**
     * 通过物料编码查询bom(其子只能是物料或者公用组件)
     *
     * @return com.zhiyun.dto.ProdBomPlmDto
     * @author 邓艺
     * @date 2018/10/13 13:45
     */
    @Override
    public ProdBomPlmDto findCommonBom(String matterNo) {
        Map<String, Object> param = new HashMap<>(4);
        param.put("companyId", UserHolder.getCompanyId());
        param.put("matterNo", matterNo);
        //查询bom是否存在，在查询的时候要查询版本号最大的，迫于无奈使用子查询
        ProdBomPlmDto bomByMatterName = prodBomPlmDao.findCommonBomByMatterNo(param);
        if (bomByMatterName == null) {
            throw new BusinessException("BOM不存在");
        } else {
            param.put("serial", Long.parseLong(bomByMatterName.getVersions()));
            //查询其下所有物料和公用组件
            List<MattersStoreDto> mattersStoreIosLists = prodBomPlmDao.findAllMattersAndComponet(param);
            if (CollectionUtils.isNotEmpty(mattersStoreIosLists)) {
                for (MattersStoreDto mattersStoreIosList : mattersStoreIosLists) {
                    //  如果是公用组件
                    if ("1".equals(mattersStoreIosList.getIsMidprod())) {
                        param.put("matterNo", mattersStoreIosList.getMattersNo());
                        List<MattersStoreDto> componets = prodBomPlmDao.findAllMattersAndComponet(param);
                        if (CollectionUtils.isEmpty(componets)) {
                            mattersStoreIosList.setLeaf(true);
                        }
                    } else {
                        mattersStoreIosList.setLeaf(true);
                    }
                }
            }

            bomByMatterName.setMattersStoreDtos(mattersStoreIosLists);
        }

        return bomByMatterName;

    }

    /**
     * 通过产品编码查询通用组件bom
     *
     * @param prodNo
     * @return com.zhiyun.dto.ProdBomPlmDto
     * @author 邓艺
     * @date 2018/10/13 17:16
     */
    @Override
    public ProdBomPlmDto findCommonBomByProdNo(Map<String, Object> prodNo) {
        return prodBomPlmDao.findCommonBomByProdNo(prodNo);
    }

    /**
     * 产品bom新增物料前搜索
     *
     * @param codeOrName
     * @return java.util.List<com.zhiyun.dto.MattersStoreDto>
     * @author 邓艺
     * @date 2018/10/13 17:15
     */
    @Override
    public List<MattersStoreDto> SearchBeforeAddMattersForCom(String codeOrName) {
        Map<String, Object> param = new HashMap<>(3);
        param.put("codeOrName", codeOrName);
        param.put("companyId", UserHolder.getCompanyId());
        return prodBomPlmDao.SearchBeforeAddMattersForCom(param);
    }

    /**
     * 下拉查询类型为组件的物料
     *
     * @param
     * @return java.util.List<com.zhiyun.dto.MattersStoreDto>
     * @author 邓艺
     * @date 2018/10/13 17:15
     */
    @Override
    public List<MattersStoreDto> optionComponent() {
        Map<String, Object> param = new HashMap<>(1);
        param.put("companyId", UserHolder.getCompanyId());
        return prodBomPlmDao.optionComponent(param);
    }

    /**
     * 审批
     *
     * @param bomCode
     * @return void
     * @author 邓艺
     * @date 2018/10/13 17:14
     */
    @Override
    @Transactional
    public void approve(String bomCode) {
        Map<String, Object> param = new HashMap<>(2);
        param.put("companyId", UserHolder.getCompanyId());
        param.put("voucherNo", bomCode);
        voucherMainOaDao.approve(param);
    }

    @Override
    public DataGrid<Object> customPage(Params entity, Pager pager) {
        return prodBomPlmDao.customPage(entity, pager);
    }

    @Override
    public void uniqueBomNo(String bomNo) {
        Map<String, Object> param = new HashMap<>(2);
        param.put("companyId", UserHolder.getCompanyId());
        param.put("bomNo", bomNo);
        List<ProdBomPlm> list = prodBomPlmDao.uniqueBom(param);
        if (CollectionUtils.isNotEmpty(list)) {
            throw new BusinessException("BOM编码已存在");
        }
    }

    @Override
    public void upGradeCommonBom(ProdBomPlmDto prodBomPlmDto) {
        //如果存在的版本为空的话，直接允许修改
        if (prodBomPlmDto.getVersions() == null) {
            throw new BusinessException("BOM历史版本还未审批");
        }
        //1.升级BOM表数据的封装
        ProdBomPlm updateProdBomPlm = new ProdBomPlm();
        updateProdBomPlm.setVoucherNo(uniqueIdService.mixedId("BOM", 10, UserHolder.getCompanyId()));
        updateProdBomPlm.setBomNo(prodBomPlmDto.getBomNo());
        updateProdBomPlm.setProdNo(prodBomPlmDto.getMattersNo());
        updateProdBomPlm.setMakeDate(new Date());
        updateProdBomPlm.setVersions(String.valueOf(Long.valueOf(prodBomPlmDto.getVersions())+1L));
        updateProdBomPlm.setBomStatus(prodBomPlmDto.getBomStatus());
        updateProdBomPlm.setCompanyId(UserHolder.getCompanyId());
        prodBomPlmDao.insert(updateProdBomPlm);
        //2.遍历所有物料及公用组件，升级版本信息然后保存
        ProdBomDetailPlm updateBomDetailPlm = new ProdBomDetailPlm();
        List<MattersStoreDto> mattersStoreDtos = prodBomPlmDto.getMattersStoreDtos();
        upDateAllMattersAndComponent(prodBomPlmDto, updateBomDetailPlm, mattersStoreDtos);
    }

    /**
     * 递归修改
     *
     * @param prodBomPlmDto
     * @param updateBomDetailPlm
     * @param mattersStoreDtos
     */
    public void upDateAllMattersAndComponent(ProdBomPlmDto prodBomPlmDto, ProdBomDetailPlm updateBomDetailPlm, List<MattersStoreDto> mattersStoreDtos) {
        if (CollectionUtils.isNotEmpty(mattersStoreDtos)) {
            //判断是否是公用组件
            for (MattersStoreDto mattersStoreDto : mattersStoreDtos) {
                if ("1".equals(mattersStoreDto.getIsMidprod())) {
                    Map<String, Object> param = new HashMap<>(2);
                    param.put("companyId", UserHolder.getCompanyId());
                    param.put("paranetNo", mattersStoreDto.getMattersNo());
                    param.put("serial", Long.parseLong(prodBomPlmDto.getVersions()));
                    //查询
                    List<MattersStoreDto> mattersList = prodBomPlmDao.findMattersAndComponentBySerialAndPno(param);
                    upDateAllMattersAndComponent(prodBomPlmDto, updateBomDetailPlm, mattersList);
                } else {
                    //封装物料信息
                    updateBomDetailPlm.setSerial(Long.valueOf(prodBomPlmDto.getVersions())+1L);
                    updateBomDetailPlm.setMattersNo(mattersStoreDto.getMattersNo());
                    updateBomDetailPlm.setParentNo(prodBomPlmDto.getMattersNo());
                    updateBomDetailPlm.setAmount(Double.valueOf(mattersStoreDto.getAmount()));
                    updateBomDetailPlm.setBackupMatter(mattersStoreDto.getBackUpMatterNo());
                    updateBomDetailPlm.setPlmDesc(mattersStoreDto.getPlmDesc());
                    updateBomDetailPlm.setCompanyId(UserHolder.getCompanyId());
                    updateBomDetailPlm.setBomNo(prodBomPlmDto.getBomNo());
                    updateBomDetailPlm.setId(null);
                    prodBomDetailPlmDao.insert(updateBomDetailPlm);
                }
            }
        }
    }

    /**
     * 更新bom前显示数据封装
     *
     * @param prodBomPlmDto
     * @return com.zhiyun.dto.ProdBomPlmDto
     * @author 邓艺
     * @date 2018/10/19 10:43
     */
    @Override
    @Transactional
    public ProdBomPlmDto beforeUpdateBom(ProdBomPlmDto prodBomPlmDto) {
        //查询出之前的所有物料信息
        Map<String, Object> param = new HashMap<>(3);
        param.put("matterNo", prodBomPlmDto.getMattersNo());
        param.put("serial", Long.parseLong(prodBomPlmDto.getVersions()));
        param.put("companyId", UserHolder.getCompanyId());
        prodBomPlmDto.setMakeDate(new Date());
        prodBomPlmDto.setVersions(String.valueOf(Long.parseLong(prodBomPlmDto.getVersions()) + 1L));
        //查询其下所有物料和公用组件
        List<MattersStoreDto> mattersStoreIosLists = prodBomPlmDao.findAllMattersAndComponet(param);
        if (CollectionUtils.isNotEmpty(mattersStoreIosLists)) {
            for (MattersStoreDto mattersStoreIosList : mattersStoreIosLists) {
                //  如果是公用组件
                if ("1".equals(mattersStoreIosList.getIsMidprod())) {
                    param.put("matterNo", mattersStoreIosList.getMattersNo());
                    List<MattersStoreDto> componets = prodBomPlmDao.findAllMattersAndComponet(param);
                    if (CollectionUtils.isEmpty(componets)) {
                        mattersStoreIosList.setLeaf(true);
                    }
                } else {
                    mattersStoreIosList.setLeaf(true);
                }
            }
        }
        prodBomPlmDto.setMattersStoreDtos(mattersStoreIosLists);
        return prodBomPlmDto;
    }

    @Override
    public List<MattersStoreDto> findAllChildByMattersNo(MattersStoreDto mattersStoreDto) {
        Map<String, Object> param = new HashMap<>(3);
        param.put("matterNo", mattersStoreDto.getMattersNo());
        param.put("serial", mattersStoreDto.getSerial());
        param.put("companyId", UserHolder.getCompanyId());
        //查询其下所有物料和公用组件
        List<MattersStoreDto> mattersStoreIosLists = prodBomPlmDao.findAllMattersAndComponet(param);
        if (CollectionUtils.isNotEmpty(mattersStoreIosLists)) {
            for (MattersStoreDto mattersStoreIosList : mattersStoreIosLists) {
                //  如果是公用组件
                if ("1".equals(mattersStoreIosList.getIsMidprod())) {
                    param.put("matterNo", mattersStoreIosList.getMattersNo());
                    List<MattersStoreDto> componets = prodBomPlmDao.findAllMattersAndComponet(param);
                    if (CollectionUtils.isEmpty(componets)) {
                        mattersStoreIosList.setLeaf(true);
                    }
                } else {
                    mattersStoreIosList.setLeaf(true);
                }
            }
        }
        return prodBomPlmDao.findAllChildByMattersNo(mattersStoreDto);
    }

    @Override
    public ProdBomPlmDto beforeUpdateProductBom(ProdBomPlmDto prodBomPlmDto) {
        Map<String, Object> param = new HashMap<>(3);
        param.put("companyId", UserHolder.getCompanyId());
        //通过产品编码查询
        param.put("productNo", prodBomPlmDto.getProdNo());
        //查询bom
        ProdBomPlmDto bomByPno = prodBomPlmDao.findBomByPno(param);
        if (bomByPno != null) {
            //查询半成品封装
            List<MattersStoreDto> mattersStoreIosList = prodBomPlmDao.findAllMattersFroProduct(param);
            List<ProductMidPlmDto> list = prodBomPlmDao.findAllMidProductByPno(param);
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
        }

        return null;
    }

}
