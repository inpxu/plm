/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service;

import com.zhiyun.base.model.DataGrid;
import com.zhiyun.base.model.LabelValue;
import com.zhiyun.base.model.Pager;
import com.zhiyun.base.model.Params;
import com.zhiyun.base.service.BaseService;
import com.zhiyun.entity.MattersTypeIos;

import java.util.List;

/**
 * 物料分类设置Service接口。
 *
 * @author auto
 * @version v1.0
 * @date
 */
public interface MattersTypeIosService extends BaseService<MattersTypeIos, Long> {
    //新增物料分类
    public void insertMatterType(MattersTypeIos mattersTypeIos);

    //修改物料分类
    public void updateMatterType(MattersTypeIos mattersTypeIos);

    //删除物料分类
    public void deletedMatterType(Long[] id);

    //查询物料分类
    public DataGrid<MattersTypeIos> select(Params params, Pager pager);

    //物料分类下拉
    public List<LabelValue> mattersOptions();

}
