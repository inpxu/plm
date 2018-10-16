/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service.impl;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.exception.BusinessException;
import com.zhiyun.base.service.BaseServiceImpl;
import com.zhiyun.client.UserHolder;
import com.zhiyun.dao.CrafworkInputMaterPlmDao;
import com.zhiyun.dto.CrafworkInputMaterPlmDto;
import com.zhiyun.entity.CrafworkInputMaterPlm;
import com.zhiyun.form.CrafworkInputMaterPlmForm;
import com.zhiyun.service.CrafworkInputMaterPlmService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * 工艺输入物料Service接口实现类。
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Service("crafworkInputMaterPlmService")
public class CrafworkInputMaterPlmServiceImpl extends BaseServiceImpl<CrafworkInputMaterPlm, Long> implements CrafworkInputMaterPlmService {

    @Resource
    private CrafworkInputMaterPlmDao crafworkInputMaterPlmDao;

    @Override
    protected BaseDao<CrafworkInputMaterPlm, Long> getBaseDao() {
        return this.crafworkInputMaterPlmDao;
    }

    @Override
    public List<CrafworkInputMaterPlmDto> findInput(CrafworkInputMaterPlm crafworkInputMaterPlm) {
        List<CrafworkInputMaterPlmDto> dtos = crafworkInputMaterPlmDao.findInput(crafworkInputMaterPlm);
        if (CollectionUtils.isNotEmpty(dtos)) {
            for (CrafworkInputMaterPlmDto dto : dtos) {
                String prodNo = dto.getProdNo();
                String midPordNo = dto.getMidPordNo();
                String in = dto.getInProdNo();
                if (prodNo.equals(midPordNo) && prodNo.equals(in)) {
                    dto.setInProdNo(null);
                }
            }
        }
        return dtos;
    }

    @Override
    @Transactional
    public List<CrafworkInputMaterPlmDto> addInput(CrafworkInputMaterPlmForm crafworkInputMaterPlmForm) {
        List<CrafworkInputMaterPlmDto> crafworkInputMaterPlmsDtos = crafworkInputMaterPlmForm.getCrafworkInputMaterPlmsDtos();
        String prodNo = crafworkInputMaterPlmForm.getProdNo();
        String midPordNo = crafworkInputMaterPlmForm.getMidPordNo();
        Long crafworkId = crafworkInputMaterPlmForm.getCrafworkId();
        if (CollectionUtils.isNotEmpty(crafworkInputMaterPlmsDtos)) {
            for (CrafworkInputMaterPlmDto materPlm : crafworkInputMaterPlmsDtos) {
                String inputNo = materPlm.getInProdNo();
                if (inputNo == null || inputNo == "") {
                    if (prodNo != null && prodNo.equals(midPordNo)) {
                        materPlm.setInProdNo(midPordNo);
                    } else {
                        materPlm.setInProdNo(null);
                    }
                }
                String materNo = materPlm.getMaterNo();
                if (materNo == null || materNo == "") {
                    materPlm.setMaterNo(null);
                }
                BigDecimal inputAmt = materPlm.getInputAmt();
                if (inputNo == null && materNo == null) {
                    throw new BusinessException("请选择需要输入的半成品或者物料");
                }
                if (inputAmt == null) {
                    throw new BusinessException("工艺输入数量不能为空");
                }
            }
            for (int i = 0; i < crafworkInputMaterPlmsDtos.size(); i++) {
                String inputNo = crafworkInputMaterPlmsDtos.get(i).getInProdNo();
                String materNo = crafworkInputMaterPlmsDtos.get(i).getMaterNo();
                for (int j = i + 1; j < crafworkInputMaterPlmsDtos.size(); j++) {
                    String inputNo1 = crafworkInputMaterPlmsDtos.get(j).getInProdNo();
                    String materNo1 = crafworkInputMaterPlmsDtos.get(j).getMaterNo();
                    if (inputNo == inputNo1 && materNo == materNo1) {
                        throw new BusinessException("输入信息重复！");
                    }
                }
            }
        }
        CrafworkInputMaterPlm plm = new CrafworkInputMaterPlm();
        plm.setCompanyId(UserHolder.getCompanyId());
        plm.setProdNo(prodNo);
        plm.setMidPordNo(midPordNo);
        plm.setCrafworkId(crafworkId);
        plm.setCompanyId(UserHolder.getCompanyId());
        List<CrafworkInputMaterPlm> plms = crafworkInputMaterPlmDao.find(plm);
        for (CrafworkInputMaterPlm p : plms) {
            crafworkInputMaterPlmDao.delete(p.getId());
        }
        if (CollectionUtils.isNotEmpty(crafworkInputMaterPlmsDtos)) {
            for (CrafworkInputMaterPlm m : crafworkInputMaterPlmsDtos) {
                m.setId(null);
                m.setCompanyId(UserHolder.getCompanyId());
                m.setProdNo(prodNo);
                m.setMidPordNo(midPordNo);
                m.setCrafworkId(crafworkId);
                crafworkInputMaterPlmDao.insert(m);
            }
        }
        return crafworkInputMaterPlmsDtos;
    }
}
