/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service;

import com.zhiyun.base.model.DataGrid;
import com.zhiyun.base.model.Pager;
import com.zhiyun.base.model.Params;
import com.zhiyun.base.service.BaseService;
import com.zhiyun.entity.ProdTypeCrm;

import java.util.List;

/**
 * 产品分类设置Service接口。
 *
 * @author auto
 * @version v1.0
 * @date
 */
public interface ProdTypeCrmService extends BaseService<ProdTypeCrm, Long> {

    // 新增
    int add(ProdTypeCrm prodTypeCrm);

    // 修改
    void up(ProdTypeCrm prodTypeCrm);

    void del(Long[] ids);

    //    产品分类模糊查询下拉
    List<ProdTypeCrm> getType(ProdTypeCrm prodTypeCrm);

    DataGrid<ProdTypeCrm> pages(Params params, Pager pager);

}
