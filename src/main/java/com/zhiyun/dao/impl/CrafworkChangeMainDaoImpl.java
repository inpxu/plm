/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dao.impl;

import com.zhiyun.base.dao.BaseDaoImpl;
import com.zhiyun.base.model.DataGrid;
import com.zhiyun.base.model.Pager;
import com.zhiyun.base.model.Params;
import com.zhiyun.dao.CrafworkChangeMainDao;
import com.zhiyun.dto.CrafworkChangeMainDto;
import com.zhiyun.entity.CasOrg;
import com.zhiyun.entity.CrafworkChangeMain;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * CrafworkChangeMainDao接口实现类
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Repository("crafworkChangeMainDao")
public class CrafworkChangeMainDaoImpl extends BaseDaoImpl<CrafworkChangeMain, Long> implements CrafworkChangeMainDao {

    @Override
    public List<CrafworkChangeMain> findVoucher(CrafworkChangeMain crafworkChangeMain) {
        return this.selectList(getMethodName(), crafworkChangeMain);
    }

    @Override
    public List<CasOrg> getOrg(CasOrg casOrg) {
        return this.selectList(getMethodName(), casOrg);
    }

    @Override
    public DataGrid<CrafworkChangeMainDto> changePage(Params params, Pager pager) {
        return this.selectPage(getMethodName(), params, pager);
    }
}
