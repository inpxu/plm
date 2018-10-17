/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service;

import com.zhiyun.base.model.DataGrid;
import com.zhiyun.base.model.Pager;
import com.zhiyun.base.model.Params;
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

    List<MattersStoreDto> searchBeforeAddMatters(String codeOrName, String parentNo);

    void updateMatter(ProdBomDetailPlm prodBomDetailPlm);

    List<ProductMidPlmDto> findMidProduct(String parentNo);

    void changeNumber(boolean isMidProduct, Long id, Long number, Long numberBefore);

    ProdBomPlmDto findCommonBom(String matterNo);

    ProdBomPlmDto findCommonBomByProdNo(String prodNo);

    List<MattersStoreDto> searchBeforeAddMattersForCom(String codeOrName);

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

    /**
     * 变更申请自定义分页查询
     *
     * @param entity
     * @param pager
     * @return com.zhiyun.base.model.DataGrid<java.lang.Object>
     * @author 邓艺
     * @date 2018/10/14 17:20
     */
    DataGrid<Object> customPage(Params entity, Pager pager);

    /**
     * 产品bom唯一性校验，如果存在将会抛出业务异常
     *
     * @param bomNo
     * @return void
     * @author 邓艺
     * @date 2018/10/14 20:13
     */
    void uniqueBomNo(String bomNo);
}
