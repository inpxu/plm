/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service.impl;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.service.BaseServiceImpl;
import com.zhiyun.dao.ProdCrafworkPathHirstoryPlmDao;
import com.zhiyun.entity.ProdCrafworkPathHirstoryPlm;
import com.zhiyun.service.ProdCrafworkPathHirstoryPlmService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 产品工艺路线表Service接口实现类。
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Service("prodCrafworkPathHirstoryPlmService")
public class ProdCrafworkPathHirstoryPlmServiceImpl extends BaseServiceImpl<ProdCrafworkPathHirstoryPlm, Long> implements ProdCrafworkPathHirstoryPlmService {

    @Resource
    private ProdCrafworkPathHirstoryPlmDao prodCrafworkPathHirstoryPlmDao;

    @Override
    protected BaseDao<ProdCrafworkPathHirstoryPlm, Long> getBaseDao() {
        return this.prodCrafworkPathHirstoryPlmDao;
    }
}
