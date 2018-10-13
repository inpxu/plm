/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dao;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.model.DataGrid;
import com.zhiyun.base.model.Pager;
import com.zhiyun.base.model.Params;
import com.zhiyun.dto.ProductStorePlmDto;
import com.zhiyun.entity.ProductStorePlm;

import java.util.List;

/**
 * ProductStorePlmDao接口
 *
 * @author auto
 * @version v1.0
 * @date
 */
public interface ProductStorePlmDao extends BaseDao<ProductStorePlm, Long> {

    // 分页查询
    DataGrid<ProductStorePlmDto> pg(Params params, Pager pager);

    // 详情
    ProductStorePlmDto getById(ProductStorePlm productStorePlm);

    // 产品工艺路线侧边产品查询显示
    List<ProductStorePlmDto> prodDrop(ProductStorePlm productStorePlm);

}
