/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dao;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.dto.ProductStorePlmDto;
import com.zhiyun.entity.ProdBomPlm;

import java.util.Map;

/**
 * ProdBomPlmDao接口
 *
 * @author auto
 * @version v1.0
 * @date
 */
public interface ProdBomPlmDao extends BaseDao<ProdBomPlm, Long> {

    ProductStorePlmDto searchForProduct(Map<String, Object> param);

    ProdBomPlm findBomByPno(Map<String, Object> param);

    ProdBomPlm findBomByMpno(Map<String, Object> param);
}
