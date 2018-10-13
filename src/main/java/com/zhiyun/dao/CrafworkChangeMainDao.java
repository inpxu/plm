/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dao;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.model.DataGrid;
import com.zhiyun.base.model.Pager;
import com.zhiyun.base.model.Params;
import com.zhiyun.dto.CrafworkChangeMainDto;
import com.zhiyun.entity.CasOrg;
import com.zhiyun.entity.CrafworkChangeMain;

import java.util.List;

/**
 * CrafworkChangeMainDao接口
 *
 * @author auto
 * @version v1.0
 * @date
 */
public interface CrafworkChangeMainDao extends BaseDao<CrafworkChangeMain, Long> {

    // 单据号模糊查询下拉
    List<CrafworkChangeMain> findVoucher(CrafworkChangeMain crafworkChangeMain);

    // 变更部门模糊查询下拉
    List<CasOrg> getOrg(CasOrg casOrg);

    // 分页查询
    DataGrid<CrafworkChangeMainDto> changePage(Params params, Pager pager);
}
