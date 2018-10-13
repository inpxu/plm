/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service;

import com.zhiyun.base.service.BaseService;
import com.zhiyun.dto.*;
import com.zhiyun.entity.ProdBomDetailPlm;
import com.zhiyun.entity.ProdBomPlm;

import java.util.List;

/**
 * 产品物料构成主表Service接口。
 *
 * @author auto
 * @version v1.0
 * @date
 */
public interface ProdBomPlmService extends BaseService<ProdBomPlm, Long> {

    /**
     * 通过产品名或bom编码搜索
     *
     * @param productName
     * @param bomCode
     * @return com.zhiyun.entity.ProductStorePlm
     * @author 邓艺
     * @date 2018/10/7 13:36
     */
    ProductStorePlmDto searchForProduct(String productName, String bomCode);

    ProdBomPlmDto findBomByPnoOrMpno(String pNo, String mpno);

    void addBomNo(ProdBomPlm prodBomPlm);

    List<ProdBomDetailPlmDto> addMatters(ProdBomDetailPlm[] mattersStoreIos);

    void commit2Approve(String bomCode);

    void deleteMatters(Long[] ids);

    void startOrStopBom(String bomCode);

    ProdBomPlm searchForCompnent(String productName, String bomCode);

    List<MattersStoreDto> optionBomCodeAndProdName();

    List<MattersStoreDto> SearchBeforeAddMatters(String codeOrName);

    void updateMatter(ProdBomDetailPlm prodBomDetailPlm);

    List<ProductMidPlmDto> findMidProduct(String parentNo);

    void changeNumber(boolean isMidProduct, Long id, Long number, Long numberBefore);

    ProdBomPlmDto findCommonBom(String matterName, String bomCode);

    ProdBomPlmDto findCommonBomByProdNo(String prodNo);

    List<MattersStoreDto> SearchBeforeAddMattersForCom(String codeOrName);

    /**
     * 下拉查询组件
     *
     * @param
     * @return java.util.List<com.zhiyun.dto.MattersStoreDto>
     * @author 邓艺
     * @date 2018/10/12 9:50
     */
    List<MattersStoreDto> optionComponent();

    void approve(String bomCode);
}
