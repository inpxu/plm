/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service;

import com.zhiyun.base.service.BaseService;
import com.zhiyun.dto.ProductStorePlmDto;
import com.zhiyun.entity.MattersStoreIos;
import com.zhiyun.entity.ProdBomPlm;

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

    ProdBomPlm findBomByPnoOrMpno(String pNo, String mpno);

    void addBomNo(ProdBomPlm prodBomPlm);

    void addMatters(MattersStoreIos mattersStoreIos);
}
