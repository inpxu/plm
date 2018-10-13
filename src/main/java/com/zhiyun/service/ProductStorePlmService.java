/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service;

import com.zhiyun.base.model.DataGrid;
import com.zhiyun.base.model.Pager;
import com.zhiyun.base.model.Params;
import com.zhiyun.base.service.BaseService;
import com.zhiyun.dto.ProductStorePlmDto;
import com.zhiyun.entity.ProductStorePlm;

import java.util.List;

/**
 * 产品库Service接口。
 *
 * @author auto
 * @version v1.0
 * @date
 */
public interface ProductStorePlmService extends BaseService<ProductStorePlm, Long> {

    // 分页查询
    DataGrid<ProductStorePlmDto> pg(Params params, Pager pager);

    // 新增
    void add(ProductStorePlm productStorePlm);

    // 编辑
    void upd(ProductStorePlm productStorePlm);

    // 删除
    void del(Long[] ids);

    // 详情
    ProductStorePlmDto getById(ProductStorePlm productStorePlm);

    // 产品工艺路线侧边产品查询显示
    List<ProductStorePlmDto> prodDrop(ProductStorePlm productStorePlm);
}
