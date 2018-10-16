/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service.impl;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.exception.BusinessException;
import com.zhiyun.base.service.BaseServiceImpl;
import com.zhiyun.client.UserHolder;
import com.zhiyun.dao.FormulaBomPlmDao;
import com.zhiyun.dao.ProdBomPlmDao;
import com.zhiyun.dto.FormulaDto;
import com.zhiyun.entity.FormulaBomPlm;
import com.zhiyun.service.FormulaBomPlmService;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Service接口实现类。
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Service("formulaBomPlmService")
public class FormulaBomPlmServiceImpl extends BaseServiceImpl<FormulaBomPlm, Long> implements FormulaBomPlmService {

    @Resource
    private FormulaBomPlmDao formulaBomPlmDao;
    @Autowired
    private ProdBomPlmDao prodBomPlmDao;

    @Override
    protected BaseDao<FormulaBomPlm, Long> getBaseDao() {
        return this.formulaBomPlmDao;
    }

    @Override
    @Transactional
    public void addOrUpdateFormula(FormulaBomPlm formulaBomPlm) {
        if (formulaBomPlm.getId() == null) {
            //新增
            formulaBomPlm.setCompanyId(UserHolder.getCompanyId());
            formulaBomPlmDao.insert(formulaBomPlm);
        } else {
            formulaBomPlmDao.update(formulaBomPlm);
        }

    }

    @Override
    @Transactional
    public void deleteByIds(Long[] ids) {
        if (ArrayUtils.isNotEmpty(ids)) {
            for (Long id : ids) {
                formulaBomPlmDao.delete(id);
            }
        }
    }

    @Override
    public FormulaDto findBomByPnoOrMpno(String pNo) {
        Map<String, Object> param = new HashMap<>(2);
        param.put("companyId", UserHolder.getCompanyId());
        param.put("productNo", pNo);
        FormulaDto bomByPnoForFormula = prodBomPlmDao.findBomByPnoForFormula(param);
        if (bomByPnoForFormula == null) {
            throw new BusinessException("请先为产品添加bom");
        }
        return bomByPnoForFormula;
    }
}
