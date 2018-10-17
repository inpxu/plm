/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service.impl;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.exception.BusinessException;
import com.zhiyun.base.service.BaseServiceImpl;
import com.zhiyun.base.util.BeanCopyUtil;
import com.zhiyun.client.UserHolder;
import com.zhiyun.dao.*;
import com.zhiyun.dto.ProdCrafworkMainPlmDto;
import com.zhiyun.dto.ProdCrafworkPathPlmAudDto;
import com.zhiyun.dto.ProdCrafworkPathPlmDto;
import com.zhiyun.dto.ProdMidDto;
import com.zhiyun.entity.*;
import com.zhiyun.service.ProdCrafworkMainPlmService;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 工艺路线设置主表Service接口实现类。
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Service("prodCrafworkMainPlmService")
public class ProdCrafworkMainPlmServiceImpl extends BaseServiceImpl<ProdCrafworkMainPlm, Long> implements ProdCrafworkMainPlmService {

    @Resource
    private ProdCrafworkMainPlmDao prodCrafworkMainPlmDao;
    @Resource
    private ProdCrafworkPathPlmDao prodCrafworkPathPlmDao;
    @Resource
    private ProdCrafworkPathPlmAudDao prodCrafworkPathPlmAudDao;
    @Resource
    private ProductMidPlmDao productMidPlmDao;
    @Resource
    private CrafworkChangeRecordPlmDao crafworkChangeRecordPlmDao;

    @Override
    protected BaseDao<ProdCrafworkMainPlm, Long> getBaseDao() {
        return this.prodCrafworkMainPlmDao;
    }

    @Override
    @Transactional
    public void addPathNo(ProdCrafworkMainPlm prodCrafworkMainPlm) {
        ProdCrafworkMainPlm plm = new ProdCrafworkMainPlm();
        plm.setCompanyId(UserHolder.getCompanyId());
        plm.setProdNo(prodCrafworkMainPlm.getProdNo());
        List<ProdCrafworkMainPlm> list1 = prodCrafworkMainPlmDao.find(plm);
        if (CollectionUtils.isNotEmpty(list1)) {
            throw new BusinessException("该产品已有工艺路线号");
        }
        ProdCrafworkMainPlm pl = new ProdCrafworkMainPlm();
        pl.setCompanyId(UserHolder.getCompanyId());
        pl.setPathNo(prodCrafworkMainPlm.getPathNo());
        List<ProdCrafworkMainPlm> list = prodCrafworkMainPlmDao.find(pl);
        if (CollectionUtils.isNotEmpty(list)) {
            throw new BusinessException("工艺路线号已存在");
        }

        prodCrafworkMainPlm.setRaiseUser(UserHolder.getUserName());
        prodCrafworkMainPlm.setRaiseDate(new Date());
        prodCrafworkMainPlm.setOrgId(UserHolder.getOrgId());
        prodCrafworkMainPlmDao.insert(prodCrafworkMainPlm);
    }

    @Override
    public ProdCrafworkMainPlm getSV(ProdCrafworkMainPlm prodCrafworkMainPlm) {
        List<ProdCrafworkMainPlm> plm = prodCrafworkMainPlmDao.find(prodCrafworkMainPlm);
        if (CollectionUtils.isEmpty(plm)) {
            throw new BusinessException("工艺路线号错误");
        }
        ProdCrafworkMainPlm mainPlm = new ProdCrafworkMainPlm();
        ProdCrafworkMainPlmDto dto1 = new ProdCrafworkMainPlmDto();
        dto1.setProdNo(prodCrafworkMainPlm.getProdNo());
        dto1.setCompanyId(UserHolder.getCompanyId());
        String label = prodCrafworkMainPlmDao.getStatus(dto1).getLabel();
        if (label == null || "".equals(label)) {
            mainPlm.setStatus("未发起评审");
        } else {
            mainPlm.setStatus(label);
        }
        String version = plm.get(0).getVersions();
        if (version == null || "".equals(version)) {
            mainPlm.setVersions("未发布");
        } else {
            mainPlm.setVersions(version);
        }
        return mainPlm;
    }

    @Override
    public ProdCrafworkMainPlmDto getMsg(ProdMidDto prodMidDto) {
        String pathNo = prodMidDto.getPathNo();
        if (pathNo == null) {
            throw new BusinessException("该产品尚未添加工艺路线");
        }
        String prodNo = prodMidDto.getProdNo();
        String midProdNo = prodMidDto.getMidProdNo();
        if (prodNo == null || midProdNo == null) {
            throw new BusinessException("操作错误！");
        }
        ProdCrafworkMainPlmDto mainPlmDto = prodCrafworkMainPlmDao.getMain(prodNo);
        String prodName = mainPlmDto.getProdName();
        String midProdName = mainPlmDto.getMidProdName();
        String isFinished = mainPlmDto.getIsFinished();
        if (isFinished == null) {
            throw new BusinessException("该产品的工艺路线未发起评审");
        }
        if (prodNo.equals(midProdNo)) {
            mainPlmDto.setMidProdName(null);
            mainPlmDto.setMidProdNo(null);
            mainPlmDto.setNowChoice(prodName);
        } else {
            mainPlmDto.setProdName(null);
            mainPlmDto.setProdNo(null);
            mainPlmDto.setNowChoice(prodName + "/" + midProdName);
        }
        String v = mainPlmDto.getVersions();
        if (v == null || v == "") {
            mainPlmDto.setVersions("未发布");
        }
        String status = mainPlmDto.getStatus();
        if ("true".equals(status)) {
            mainPlmDto.setEnDis("true");
        } else {
            mainPlmDto.setEnDis("false");
        }
        List<ProdCrafworkPathPlmDto> pathDtos = prodCrafworkPathPlmDao.getPath(midProdNo);
        if (CollectionUtils.isNotEmpty(pathDtos)) {
            mainPlmDto.setProdCrafworkPathPlmDtos(pathDtos);
        }
        return mainPlmDto;
    }

    @Override
    public void enDis(ProdCrafworkMainPlmDto prodCrafworkMainPlmDto) {
        String pathNo = prodCrafworkMainPlmDto.getPathNo();
        ProdCrafworkMainPlm path = new ProdCrafworkMainPlm();
        String t = prodCrafworkMainPlmDto.getEnDis();
//        ProdCrafworkMainPlm plm = new ProdCrafworkMainPlm();
//        plm.setCompanyId(UserHolder.getCompanyId());
//        plm.setPathNo(pathNo);
//        List<ProdCrafworkMainPlm> list = prodCrafworkMainPlmDao.find(plm);
//        if (CollectionUtils.isNotEmpty(list)) {
//            String status = list.get(0).getStatus();
//            if (status == null || status.equals("")) {
//                ProdCrafworkPathPlm pp = new ProdCrafworkPathPlm();
//                pp.setCompanyId(UserHolder.getCompanyId());
//                pp.setPathNo(pathNo);
//                List<ProdCrafworkPathPlm> lp = prodCrafworkPathPlmDao.find(pp);
//                if (CollectionUtils.isNotEmpty(lp)) {
//                    for (ProdCrafworkPathPlm l : lp) {
//                        l.setId(null);
//                        l.setVoucherNo(null);
//                        ProdCrafworkPathPlmAud aud = new ProdCrafworkPathPlmAud();
//                        BeanUtils.copyProperties(l, aud);
//                        prodCrafworkPathPlmAudDao.insert(aud);
//                    }
//                }
//            }
//        }
        path.setStatus(t);
        path.setPathNo(pathNo);
        path.setCompanyId(UserHolder.getCompanyId());
        prodCrafworkMainPlmDao.enDis(path);

    }

    @Override
    public List<ProdCrafworkMainPlmDto> getPathMsg(ProdCrafworkMainPlmDto prodCrafworkMainPlm) {
        prodCrafworkMainPlm.setCompanyId(UserHolder.getCompanyId());
        return prodCrafworkMainPlmDao.getPathMsg(prodCrafworkMainPlm);
    }

    @Override
    public List<ProdCrafworkMainPlmDto> getProdMsg(ProdCrafworkMainPlmDto prodCrafworkMainPlmDto) {
        prodCrafworkMainPlmDto.setCompanyId(UserHolder.getCompanyId());
        return prodCrafworkMainPlmDao.getProdMsg(prodCrafworkMainPlmDto);
    }

    @Override
    public List<ProdCrafworkMainPlmDto> findMainMess(ProdCrafworkMainPlmDto prodCrafworkMainPlmDto) {
        prodCrafworkMainPlmDto.setCompanyId(UserHolder.getCompanyId());
        return prodCrafworkMainPlmDao.findMainMess(prodCrafworkMainPlmDto);
    }

    @Override
    public List<ProdMidDto> prodDown(ProductMidPlm productMidPlm) {
        productMidPlm.setCompanyId(UserHolder.getCompanyId());
        productMidPlm.setParentNo(productMidPlm.getMidProdNo());
        /**
         * 查询全部半成品
         */
        List<ProdMidDto> list = productMidPlmDao.prodDropDown(productMidPlm);
        /**
         * 遍历所有半成品,判断半成品是否存在工艺,存在则设置leaf=false
         */
        for (ProdMidDto dto : list) {
            ProductMidPlm midPlm = new ProductMidPlm();
            midPlm.setParentNo(dto.getMidProdNo());
            midPlm.setCompanyId(UserHolder.getCompanyId());
            midPlm.setDeleted("F");
            List<ProductMidPlm> list1 = productMidPlmDao.find(midPlm);
            // 是否有子节点
            if (CollectionUtils.isEmpty(list1)) {
                dto.setLeaf(true);
            } else {
                dto.setLeaf(false);
            }

            /**
             * 设置显示名
             */
            dto.setProdMess(dto.getProdNo() + "/" + dto.getProdName());
            dto.setMidProdMsg(dto.getMidProdNo() + "/" + dto.getMidProdName());
            ProdCrafworkPathPlm aud = new ProdCrafworkPathPlm();
            aud.setMidProdNo(productMidPlm.getMidProdNo());
            aud.setPathNo(dto.getPathNo());
            aud.setCompanyId(UserHolder.getCompanyId());
            List<ProdCrafworkPathPlm> list2 = prodCrafworkPathPlmDao.find(aud);
            if (CollectionUtils.isNotEmpty(list2)) {
                dto.setLeaf(false);
            }
        }
        return list;
    }

    @Override
    @Transactional
    public void switchSequence(ProdCrafworkPathPlmDto[] prodCrafworkPathPlmDtos) {
        if (ArrayUtils.isNotEmpty(prodCrafworkPathPlmDtos)) {
            Long afterId = prodCrafworkPathPlmDtos[0].getId();
            Long uqe1 = prodCrafworkPathPlmDtos[0].getCarfSeq();
            ProdCrafworkPathPlm after = prodCrafworkPathPlmDao.get(afterId);
            Long beforeId = prodCrafworkPathPlmDtos[1].getId();
            Long uqe2 = prodCrafworkPathPlmDtos[1].getCarfSeq();
            ProdCrafworkPathPlm before = prodCrafworkPathPlmDao.get(beforeId);
            after.setId(beforeId);
            before.setId(afterId);
            prodCrafworkPathPlmDao.update(BeanCopyUtil.copy(after, ProdCrafworkPathPlmDto.class));
            prodCrafworkPathPlmDao.update(BeanCopyUtil.copy(before, ProdCrafworkPathPlmDto.class));
            CrafworkChangeRecordPlm sub = new CrafworkChangeRecordPlm();
            ProductMidPlm mid = new ProductMidPlm();
            mid.setMidProdNo(after.getMidProdNo());
            mid.setCompanyId(UserHolder.getCompanyId());
            List<ProductMidPlm> midList = productMidPlmDao.find(mid);
            if (CollectionUtils.isNotEmpty(midList)) {
                String prodNo = midList.get(0).getProdNo();
                sub.setProdNo(prodNo);
            }
            sub.setMidProdNo(after.getMidProdNo());
            sub.setCompanyId(UserHolder.getCompanyId());
            sub.setChangeEmp(UserHolder.getUserName());
            sub.setUpdDate(new Date());
            if (!uqe1.equals(uqe2)) {
                sub.setCrafworkId(after.getCrafworkId());
                sub.setId(null);
                sub.setOldValue(uqe1 + "");
                sub.setNewValue(uqe2 + "");
                sub.setChangeFlag("调整工艺顺序");
                sub.setChangeItem("工艺顺序");
                crafworkChangeRecordPlmDao.insert(sub);
                sub.setCrafworkId(before.getCrafworkId());
                sub.setId(null);
                sub.setOldValue(uqe2 + "");
                sub.setNewValue(uqe1 + "");
                sub.setChangeFlag("调整工艺顺序");
                sub.setChangeItem("工艺顺序");
                crafworkChangeRecordPlmDao.insert(sub);
            }
        }
    }
}
