/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dao;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.model.DataGrid;
import com.zhiyun.base.model.Pager;
import com.zhiyun.base.model.Params;
import com.zhiyun.dto.*;
import com.zhiyun.entity.ProdBomPlm;

import java.util.List;
import java.util.Map;

/**
 * ProdBomPlmDao接口
 *
 * @author auto
 * @version v1.0
 * @date
 */
public interface ProdBomPlmDao extends BaseDao<ProdBomPlm, Long> {

    ProductStorePlmDto searchForProduct(Map<String, Object> param);

    ProdBomPlmDto findBomByPno(Map<String, Object> param);

    ProdBomPlmDto findBomByMpno(Map<String, Object> param);

    void updateBom(Map<String, Object> param);

    void startOrStopBom(Map<String, Object> bomCode);

    ProdBomPlm selectBeforeUpdate(Map<String, Object> param);

    ProdBomPlm searchForCompnent(Map<String, Object> param);

    List<MattersStoreDto> optionBomCodeAndProdName(Map<String, Object> param);

    List<ProductMidPlmDto> findAllMidProductByPno(Map<String, Object> param);

    List<MattersStoreDto> findAllMattersFroProduct(Map<String, Object> param);

    List<MattersStoreDto> SearchBeforeAddMatters(Map<String, Object> codeOrName);

    List<ProductMidPlmDto> findMidProduct(Map<String, Object> param);

    ProdBomPlmDto findCommonBomByMatterName(Map<String, Object> param);

    ProdBomPlmDto findCommonBomByBomCode(Map<String, Object> param);

    ProdBomPlmDto findCommonBomByProdNo(Map<String, Object> param);

    List<MattersStoreDto> SearchBeforeAddMattersForCom(Map<String, Object> param);

    List<MattersStoreDto> optionComponent(Map<String, Object> param);

    ProdBomPlmDto findCommonBomByMatterNo(Map<String, Object> param);

    DataGrid<Object> customPage(Params entity, Pager pager);

    FormulaDto findBomByPnoForFormula(Map<String, Object> param);

    List<ProdBomPlm> uniqueBom(Map<String, Object> param);

    List<MattersStoreDto> findAllMattersAndComponet(Map<String, Object> param);
}
