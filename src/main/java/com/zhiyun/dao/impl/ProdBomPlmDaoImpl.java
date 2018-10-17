/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dao.impl;

import com.zhiyun.base.dao.BaseDaoImpl;
import com.zhiyun.base.model.DataGrid;
import com.zhiyun.base.model.Pager;
import com.zhiyun.base.model.Params;
import com.zhiyun.dao.ProdBomPlmDao;
import com.zhiyun.dto.*;
import com.zhiyun.entity.ProdBomPlm;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * ProdBomPlmDao接口实现类
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Repository("prodBomPlmDao")
public class ProdBomPlmDaoImpl extends BaseDaoImpl<ProdBomPlm, Long> implements ProdBomPlmDao {

    @Override
    public ProductStorePlmDto searchForProduct(Map<String, Object> param) {
        return this.selectOne(getMethodName(), param);
    }

    @Override
    public ProdBomPlmDto findBomByPno(Map<String, Object> param) {
        return this.selectOne(getMethodName(), param);
    }

    @Override
    public ProdBomPlmDto findBomByMpno(Map<String, Object> param) {
        return this.selectOne(getMethodName(), param);
    }

    @Override
    public void updateBom(Map<String, Object> param) {
        this.update(getMethodName(), param);
    }

    @Override
    public void startOrStopBom(Map<String, Object> bomCode) {
        this.update(getMethodName(), bomCode);

    }

    @Override
    public ProdBomPlm selectBeforeUpdate(Map<String, Object> param) {
        return this.selectOne(getMethodName(), param);
    }

    @Override
    public ProdBomPlm searchForCompnent(Map<String, Object> param) {
        return this.selectOne(getMethodName(), param);
    }

    @Override
    public List<MattersStoreDto> optionBomCodeAndProdName(Map<String, Object> param) {
        return this.selectList(getMethodName(), param);
    }

    @Override
    public List<ProductMidPlmDto> findAllMidProductByPno(Map<String, Object> param) {
        return this.selectList(getMethodName(), param);
    }

    @Override
    public List<MattersStoreDto> findAllMattersFroProduct(Map<String, Object> param) {
        return this.selectList(getMethodName(), param);
    }

    @Override
    public List<MattersStoreDto> SearchBeforeAddMatters(Map<String, Object> param) {
        return this.selectList(getMethodName(), param);
    }

    @Override
    public List<ProductMidPlmDto> findMidProduct(Map<String, Object> param) {
        return this.selectList(getMethodName(), param);
    }

    @Override
    public ProdBomPlmDto findCommonBomByMatterName(Map<String, Object> param) {
        return this.selectOne(getMethodName(), param);
    }

    @Override
    public ProdBomPlmDto findCommonBomByBomCode(Map<String, Object> param) {
        return this.selectOne(getMethodName(), param);
    }

    @Override
    public ProdBomPlmDto findCommonBomByProdNo(Map<String, Object> param) {
        return this.selectOne(getMethodName(), param);
    }

    @Override
    public List<MattersStoreDto> SearchBeforeAddMattersForCom(Map<String, Object> param) {
        return this.selectList(getMethodName(), param);
    }

    @Override
    public List<MattersStoreDto> optionComponent(Map<String, Object> param) {
        return this.selectList(getMethodName(), param);
    }

    @Override
    public ProdBomPlmDto findCommonBomByMatterNo(Map<String, Object> param) {
        return this.selectOne(getMethodName(), param);
    }

    @Override
    public DataGrid<Object> customPage(Params entity, Pager pager) {
        return this.selectPage(getMethodName(), entity, pager);
    }

    @Override
    public FormulaDto findBomByPnoForFormula(Map<String, Object> param) {
        return this.selectOne(getMethodName(), param);
    }

    @Override
    public List<ProdBomPlm> uniqueBom(Map<String, Object> param) {
        return this.selectList(getMethodName(), param);
    }

    @Override
    public List<MattersStoreDto> findAllMattersAndComponet(Map<String, Object> param) {
        return this.selectList(getMethodName(), param);
    }
}
