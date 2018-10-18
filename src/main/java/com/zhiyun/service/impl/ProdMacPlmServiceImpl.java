/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service.impl;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.exception.BusinessException;
import com.zhiyun.base.service.BaseServiceImpl;
import com.zhiyun.client.UserHolder;
import com.zhiyun.dao.ProdMacPlmDao;
import com.zhiyun.dto.ProdMacPlmDto;
import com.zhiyun.entity.ProdMacPlm;
import com.zhiyun.form.ProdMacPlmForm;
import com.zhiyun.service.ProdMacPlmService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 产品-设备登记Service接口实现类。
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Service("prodMacPlmService")
public class ProdMacPlmServiceImpl extends BaseServiceImpl<ProdMacPlm, Long> implements ProdMacPlmService {

    @Resource
    private ProdMacPlmDao prodMacPlmDao;

    @Override
    protected BaseDao<ProdMacPlm, Long> getBaseDao() {
        return this.prodMacPlmDao;
    }

    @Override
    public List<ProdMacPlmDto> findMacPlm(ProdMacPlm prodMacPlm) {
        return prodMacPlmDao.findMacPlm(prodMacPlm);
    }

    @Override
    @Transactional
    public List<ProdMacPlmDto> addMacPlm(ProdMacPlmForm prodMacPlmForm) {
        List<ProdMacPlmDto> prodMacPlmDtos = prodMacPlmForm.getProdMacPlmDtos();
        String prodNo = prodMacPlmForm.getProdNo();
        String mid = prodMacPlmForm.getMidProdNo();
        Long crafId = prodMacPlmForm.getCrafworkId();
        if (CollectionUtils.isNotEmpty(prodMacPlmDtos)) {
            for (ProdMacPlmDto materPlm : prodMacPlmDtos) {
                String macNo = materPlm.getMacNo();
                if (macNo == null) {
                    throw new BusinessException("设备信息不能为空");
                }
                String total = materPlm.getTotalName();
                if (total == null) {
                    throw new BusinessException("设备班制不能为空");
                }
                Integer before = materPlm.getBeforeTime();
                if (before == null) {
                    throw new BusinessException("前置时间不能为空");
                }
                Integer behind = materPlm.getBehindTime();
                if (behind == null) {
                    throw new BusinessException("后置时间不能为空");
                }
                Integer period = materPlm.getPeriodTime();
                if (period == null) {
                    throw new BusinessException("单件加工时间不能为空");
                }

            }
            for (int i = 0; i < prodMacPlmDtos.size(); i++) {
                String inputNo = prodMacPlmDtos.get(i).getMacNo();
                for (int j = i + 1; j < prodMacPlmDtos.size(); j++) {
                    String inputNo1 = prodMacPlmDtos.get(j).getMacNo();
                    if (inputNo == inputNo1) {
                        throw new BusinessException("设备信息信息重复！");
                    }
                }
            }
        }
        ProdMacPlm plm = new ProdMacPlm();
        plm.setCompanyId(UserHolder.getCompanyId());
        plm.setProdNo(prodNo);
        plm.setMidProdNo(mid);
        plm.setCrafworkId(crafId);
        plm.setCompanyId(UserHolder.getCompanyId());
        List<ProdMacPlm> plms = prodMacPlmDao.find(plm);
        for (ProdMacPlm p : plms) {
            prodMacPlmDao.delete(p.getId());
        }
        if (CollectionUtils.isNotEmpty(prodMacPlmDtos)) {
            for (ProdMacPlmDto m : prodMacPlmDtos) {
                m.setId(null);
                m.setCompanyId(UserHolder.getCompanyId());
                m.setProdNo(prodNo);
                m.setMidProdNo(mid);
                m.setCrafworkId(crafId);
                Integer runTotal = m.getRunTotal();
                if(runTotal == null) {
                    m.setRunTotal(Integer.valueOf(m.getTotalName()));
                }
                prodMacPlmDao.insert(m);
            }
        }
        return prodMacPlmDtos;
    }
}
