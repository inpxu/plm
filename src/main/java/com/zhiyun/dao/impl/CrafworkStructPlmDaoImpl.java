/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dao.impl;

import com.zhiyun.base.dao.BaseDaoImpl;
import com.zhiyun.base.model.DataGrid;
import com.zhiyun.base.model.Pager;
import com.zhiyun.base.model.Params;
import com.zhiyun.dao.CrafworkStructPlmDao;
import com.zhiyun.dto.CrafworkStructDto;
import com.zhiyun.entity.CrafworkStructPlm;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * CrafworkStructPlmDao接口实现类
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Repository("crafworkStructPlmDao")
public class CrafworkStructPlmDaoImpl extends BaseDaoImpl<CrafworkStructPlm, Long> implements CrafworkStructPlmDao {

    @Override
    public DataGrid<CrafworkStructDto> pageCrafwork(Params params, Pager pager) {
        return this.selectPage(getMethodName(), params, pager);
    }

    @Override
    public List<CrafworkStructDto> addGet(CrafworkStructDto crafworkStructDto) {
        return this.selectList(getMethodName(), crafworkStructDto);
    }

    @Override
    public List<CrafworkStructDto> findCraf(CrafworkStructDto crafworkStructDto) {
        return this.selectList(getMethodName(), crafworkStructDto);
    }
}
