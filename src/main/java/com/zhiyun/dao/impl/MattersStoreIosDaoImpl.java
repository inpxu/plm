/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dao.impl;

import com.zhiyun.base.dao.BaseDaoImpl;
import com.zhiyun.base.model.DataGrid;
import com.zhiyun.base.model.Pager;
import com.zhiyun.base.model.Params;
import com.zhiyun.dao.MattersStoreIosDao;
import com.zhiyun.dto.MattersStoreDto;
import com.zhiyun.entity.MattersStoreIos;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * MattersStoreIosDao接口实现类
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Repository("mattersStoreIosDao")
public class MattersStoreIosDaoImpl extends BaseDaoImpl<MattersStoreIos, Long> implements MattersStoreIosDao {

    @Override
    public DataGrid<MattersStoreDto> select(Params params, Pager pager) {
        return this.selectPage(getMethodName(), params, pager);
    }

    @Override
    public List<MattersStoreDto> getMatter(MattersStoreIos mattersStoreIos) {
        return this.selectList(getMethodName(), mattersStoreIos);
    }

    @Override
    public List<MattersStoreIos> mattersOption(Map<String, Object> param) {
        return this.selectList(getMethodName(), param);
    }
}
