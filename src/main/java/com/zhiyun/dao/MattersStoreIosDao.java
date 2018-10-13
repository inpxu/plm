/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dao;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.model.DataGrid;
import com.zhiyun.base.model.Pager;
import com.zhiyun.base.model.Params;
import com.zhiyun.dto.MattersStoreDto;
import com.zhiyun.entity.MattersStoreIos;

import java.util.List;

/**
 * MattersStoreIosDao接口
 *
 * @author auto
 * @version v1.0
 * @date
 */
public interface MattersStoreIosDao extends BaseDao<MattersStoreIos, Long> {
    DataGrid<MattersStoreDto> select(Params params, Pager pager);

    // 模糊查询物料信息
    List<MattersStoreDto> getMatter(MattersStoreIos mattersStoreIos);
}
