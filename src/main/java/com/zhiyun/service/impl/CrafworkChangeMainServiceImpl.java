/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service.impl;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.model.DataGrid;
import com.zhiyun.base.model.Pager;
import com.zhiyun.base.model.Params;
import com.zhiyun.base.service.BaseServiceImpl;
import com.zhiyun.dao.CrafworkChangeMainDao;
import com.zhiyun.dto.CrafworkChangeMainDto;
import com.zhiyun.entity.CasOrg;
import com.zhiyun.entity.CrafworkChangeMain;
import com.zhiyun.service.CrafworkChangeMainService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 工艺路线变更申请主表Service接口实现类。
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Service("crafworkChangeMainService")
public class CrafworkChangeMainServiceImpl extends BaseServiceImpl<CrafworkChangeMain, Long> implements CrafworkChangeMainService {

    @Resource
    private CrafworkChangeMainDao crafworkChangeMainDao;

    @Override
    protected BaseDao<CrafworkChangeMain, Long> getBaseDao() {
        return this.crafworkChangeMainDao;
    }

    @Override
    public List<CrafworkChangeMain> findVoucher(CrafworkChangeMain crafworkChangeMain) {
        return crafworkChangeMainDao.findVoucher(crafworkChangeMain);
    }

    @Override
    public List<CasOrg> getOrg(CasOrg casOrg) {
        return crafworkChangeMainDao.getOrg(casOrg);
    }

    @Override
    public DataGrid<CrafworkChangeMainDto> changePage(Params params, Pager pager) {
        return crafworkChangeMainDao.changePage(params, pager);
    }
}
