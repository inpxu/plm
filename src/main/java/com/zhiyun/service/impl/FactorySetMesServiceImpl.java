/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service.impl;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.service.BaseServiceImpl;
import com.zhiyun.dao.FactorySetMesDao;
import com.zhiyun.entity.FactorySetMes;
import com.zhiyun.service.FactorySetMesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Service接口实现类。
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Service("factorySetMesService")
public class FactorySetMesServiceImpl extends BaseServiceImpl<FactorySetMes, Long> implements FactorySetMesService {

    @Resource
    private FactorySetMesDao factorySetMesDao;

    @Override
    protected BaseDao<FactorySetMes, Long> getBaseDao() {
        return this.factorySetMesDao;
    }
}
