/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dao.impl;

import com.zhiyun.base.dao.BaseDaoImpl;
import com.zhiyun.base.model.DataGrid;
import com.zhiyun.base.model.Pager;
import com.zhiyun.base.model.Params;
import com.zhiyun.dao.MattersTypeIosDao;
import com.zhiyun.entity.MattersStoreIos;
import com.zhiyun.entity.MattersTypeIos;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * MattersTypeIosDao接口实现类
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Repository("mattersTypeIosDao")
public class MattersTypeIosDaoImpl extends BaseDaoImpl<MattersTypeIos, Long> implements MattersTypeIosDao {

    @Override
    public DataGrid<MattersTypeIos> select(Params params, Pager pager) {
        return this.selectPage(getMethodName(), params, pager);
    }

}
