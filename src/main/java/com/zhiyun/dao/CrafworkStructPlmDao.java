/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dao;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.model.DataGrid;
import com.zhiyun.base.model.Pager;
import com.zhiyun.base.model.Params;
import com.zhiyun.dto.CrafworkStructDto;
import com.zhiyun.entity.CrafworkStructPlm;

import java.util.List;

/**
 * CrafworkStructPlmDao接口
 *
 * @author auto
 * @version v1.0
 * @date
 */
public interface CrafworkStructPlmDao extends BaseDao<CrafworkStructPlm, Long> {
    DataGrid<CrafworkStructDto> pageCrafwork(Params params, Pager pager);

    // 查询除产品任务池外的工艺
    List<CrafworkStructDto> addGet(CrafworkStructDto crafworkStructDto);

    // 除产品任务池外的工艺
    List<CrafworkStructDto> findCraf(CrafworkStructDto crafworkStructDto);
}
