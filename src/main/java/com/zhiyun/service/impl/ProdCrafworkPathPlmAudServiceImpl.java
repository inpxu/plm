/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service.impl;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.service.BaseServiceImpl;
import com.zhiyun.dao.ProdCrafworkPathPlmAudDao;
import com.zhiyun.entity.ProdCrafworkPathPlmAud;
import com.zhiyun.service.ProdCrafworkPathPlmAudService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 产品工艺路线表Service接口实现类。
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Service("prodCrafworkPathPlmAudService")
public class ProdCrafworkPathPlmAudServiceImpl extends BaseServiceImpl<ProdCrafworkPathPlmAud, Long> implements ProdCrafworkPathPlmAudService {

    @Resource
    private ProdCrafworkPathPlmAudDao prodCrafworkPathPlmAudDao;

    @Override
    protected BaseDao<ProdCrafworkPathPlmAud, Long> getBaseDao() {
        return this.prodCrafworkPathPlmAudDao;
    }
}
