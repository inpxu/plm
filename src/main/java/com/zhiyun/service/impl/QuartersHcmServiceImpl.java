/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service.impl;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.service.BaseServiceImpl;
import com.zhiyun.dao.QuartersHcmDao;
import com.zhiyun.entity.QuartersHcm;
import com.zhiyun.service.QuartersHcmService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 岗位设置Service接口实现类。
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Service("quartersHcmService")
public class QuartersHcmServiceImpl extends BaseServiceImpl<QuartersHcm, Long> implements QuartersHcmService {

    @Resource
    private QuartersHcmDao quartersHcmDao;

    @Override
    protected BaseDao<QuartersHcm, Long> getBaseDao() {
        return this.quartersHcmDao;
    }
}
