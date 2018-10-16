/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dao.impl;

import com.zhiyun.base.dao.BaseDaoImpl;
import com.zhiyun.dao.ProdBomPlmDao;
import com.zhiyun.dto.ProductStorePlmDto;
import com.zhiyun.entity.ProdBomPlm;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * ProdBomPlmDao接口实现类
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Repository("prodBomPlmDao")
public class ProdBomPlmDaoImpl extends BaseDaoImpl<ProdBomPlm, Long> implements ProdBomPlmDao {

    @Override
    public ProductStorePlmDto searchForProduct(Map<String, Object> param) {
        return this.selectOne(getMethodName(), param);
    }

    @Override
    public ProdBomPlm findBomByPno(Map<String, Object> param) {
        return this.selectOne(getMethodName(), param);
    }

    @Override
    public ProdBomPlm findBomByMpno(Map<String, Object> param) {
        return this.selectOne(getMethodName(), param);
    }
}
