/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dao.impl;

import com.zhiyun.base.dao.BaseDaoImpl;
import com.zhiyun.client.UserHolder;
import com.zhiyun.dao.ProdCrafworkMainPlmDao;
import com.zhiyun.dto.ProdCrafworkMainPlmDto;
import com.zhiyun.entity.ProdCrafworkMainPlm;
import com.zhiyun.entity.ProductMidPlm;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ProdCrafworkMainPlmDao接口实现类
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Repository("prodCrafworkMainPlmDao")
public class ProdCrafworkMainPlmDaoImpl extends BaseDaoImpl<ProdCrafworkMainPlm, Long> implements ProdCrafworkMainPlmDao {

    @Override
    public ProdCrafworkMainPlm getVoucher(ProductMidPlm productMidPlm) {
        return this.selectOne(getMethodName(), productMidPlm);
    }

    @Override
    public ProdCrafworkMainPlmDto getMain(String prodNo) {
        ProdCrafworkMainPlm prodCrafworkMainPlm = new ProdCrafworkMainPlm();
        prodCrafworkMainPlm.setProdNo(prodNo);
        prodCrafworkMainPlm.setCompanyId(UserHolder.getCompanyId());
        return this.selectOne(getMethodName(), prodCrafworkMainPlm);
    }

    @Override
    public ProdCrafworkMainPlmDto getStatus(ProdCrafworkMainPlmDto prodCrafworkMainPlmDto) {
        return this.selectOne(getMethodName(), prodCrafworkMainPlmDto);
    }

    @Override
    public void enDis(ProdCrafworkMainPlm prodCrafworkMainPlm) {
        this.update(getMethodName(), prodCrafworkMainPlm);
    }

    @Override
    public List<ProdCrafworkMainPlmDto> getPathMsg(ProdCrafworkMainPlmDto prodCrafworkMainPlm) {
        return this.selectList(getMethodName(), prodCrafworkMainPlm);
    }

    @Override
    public List<ProdCrafworkMainPlmDto> getProdMsg(ProdCrafworkMainPlmDto prodCrafworkMainPlmDto) {
        return this.selectList(getMethodName(), prodCrafworkMainPlmDto);
    }

    @Override
    public List<ProdCrafworkMainPlmDto> findMainMess(ProdCrafworkMainPlmDto prodCrafworkMainPlmDto) {
        return this.selectList(getMethodName(), prodCrafworkMainPlmDto);
    }
}
