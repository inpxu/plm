/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dao.impl;

import com.zhiyun.base.dao.BaseDaoImpl;
import com.zhiyun.client.UserHolder;
import com.zhiyun.dao.ProdCrafworkPathPlmDao;
import com.zhiyun.dto.ProdCrafworkPathPlmDto;
import com.zhiyun.entity.ProdCrafworkPathPlm;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ProdCrafworkPathPlmDao接口实现类
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Repository("prodCrafworkPathPlmDao")
public class ProdCrafworkPathPlmDaoImpl extends BaseDaoImpl<ProdCrafworkPathPlm, Long> implements ProdCrafworkPathPlmDao {

    @Override
    public List<ProdCrafworkPathPlmDto> crafworkStruct(ProdCrafworkPathPlm prodCrafworkPathPlm) {
        return this.selectList(getMethodName(), prodCrafworkPathPlm);
    }

    @Override
    public Long getMaxSeq(String mid) {
        ProdCrafworkPathPlm prodCrafworkPathPlm = new ProdCrafworkPathPlm();
        prodCrafworkPathPlm.setMidProdNo(mid);
        prodCrafworkPathPlm.setCompanyId(UserHolder.getCompanyId());
        return this.selectOne(getMethodName(), prodCrafworkPathPlm);
    }

    @Override
    public List<ProdCrafworkPathPlmDto> getPath(String midProdNo) {
        ProdCrafworkPathPlm prodCrafworkPathPlm = new ProdCrafworkPathPlm();
        prodCrafworkPathPlm.setMidProdNo(midProdNo);
        prodCrafworkPathPlm.setCompanyId(UserHolder.getCompanyId());
        return selectList(getMethodName(), prodCrafworkPathPlm);
    }

    @Override
    public void enDis(ProdCrafworkPathPlm prodCrafworkPathPlm) {
        this.update(getMethodName(), prodCrafworkPathPlm);
    }

    @Override
    public List<ProdCrafworkPathPlmDto> pathCraf(ProdCrafworkPathPlm prodCrafworkPathPlm) {
        return this.selectList(getMethodName(),prodCrafworkPathPlm);
    }
}
