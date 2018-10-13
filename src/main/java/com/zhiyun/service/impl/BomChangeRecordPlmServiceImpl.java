/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service.impl;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.service.BaseServiceImpl;
import com.zhiyun.dao.BomChangeRecordPlmDao;
import com.zhiyun.entity.BomChangeRecordPlm;
import com.zhiyun.service.BomChangeRecordPlmService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * BOM变更申请表Service接口实现类。
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Service("bomChangeRecordPlmService")
public class BomChangeRecordPlmServiceImpl extends BaseServiceImpl<BomChangeRecordPlm, Long> implements BomChangeRecordPlmService {

    @Resource
    private BomChangeRecordPlmDao bomChangeRecordPlmDao;

    @Override
    protected BaseDao<BomChangeRecordPlm, Long> getBaseDao() {
        return this.bomChangeRecordPlmDao;
    }
}
