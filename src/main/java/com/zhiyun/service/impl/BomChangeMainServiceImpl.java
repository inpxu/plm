/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service.impl;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.service.BaseServiceImpl;
import com.zhiyun.dao.BomChangeMainDao;
import com.zhiyun.entity.BomChangeMain;
import com.zhiyun.service.BomChangeMainService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * bom变更申请主表Service接口实现类。
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Service("bomChangeMainService")
public class BomChangeMainServiceImpl extends BaseServiceImpl<BomChangeMain, Long> implements BomChangeMainService {

    @Resource
    private BomChangeMainDao bomChangeMainDao;

    @Override
    protected BaseDao<BomChangeMain, Long> getBaseDao() {
        return this.bomChangeMainDao;
    }
}
