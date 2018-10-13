/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dao;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.entity.ProdTypeCrm;

import java.util.List;

/**
 * ProdTypeCrmDao接口
 *
 * @author auto
 * @version v1.0
 * @date
 */
public interface ProdTypeCrmDao extends BaseDao<ProdTypeCrm, Long> {

    //    产品分类模糊查询下拉
    List<ProdTypeCrm> getType(ProdTypeCrm prodTypeCrm);
}
