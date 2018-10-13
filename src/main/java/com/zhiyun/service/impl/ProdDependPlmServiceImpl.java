/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service.impl;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.service.BaseServiceImpl;
import com.zhiyun.dao.ProdDependPlmDao;
import com.zhiyun.entity.ProdDependPlm;
import com.zhiyun.service.ProdDependPlmService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 产品配件库Service接口实现类。
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Service("prodDependPlmService")
public class ProdDependPlmServiceImpl extends BaseServiceImpl<ProdDependPlm, Long> implements ProdDependPlmService {

    @Resource
    private ProdDependPlmDao prodDependPlmDao;

    @Override
    protected BaseDao<ProdDependPlm, Long> getBaseDao() {
        return this.prodDependPlmDao;
    }
}
