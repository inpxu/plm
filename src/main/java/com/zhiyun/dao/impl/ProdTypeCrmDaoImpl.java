/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dao.impl;

import com.zhiyun.base.dao.BaseDaoImpl;
import com.zhiyun.base.model.DataGrid;
import com.zhiyun.base.model.Pager;
import com.zhiyun.base.model.Params;
import com.zhiyun.dao.ProdTypeCrmDao;
import com.zhiyun.entity.ProdTypeCrm;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ProdTypeCrmDao接口实现类
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Repository("prodTypeCrmDao")
public class ProdTypeCrmDaoImpl extends BaseDaoImpl<ProdTypeCrm, Long> implements ProdTypeCrmDao {

    @Override
    public List<ProdTypeCrm> getType(ProdTypeCrm prodTypeCrm) {
        return this.selectList(getMethodName(), prodTypeCrm);
    }

    @Override
    public DataGrid<ProdTypeCrm> pages(Params params, Pager pager) {
        return this.selectPage(getMethodName(), params, pager);
    }
}
