/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dao;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.dto.ProdCrafworkPathPlmAudDto;
import com.zhiyun.entity.ProdCrafworkPathPlm;
import com.zhiyun.entity.ProdCrafworkPathPlmAud;

import java.util.List;

/**
 * ProdCrafworkPathPlmAudDao接口
 *
 * @author auto
 * @version v1.0
 * @date
 */
public interface ProdCrafworkPathPlmAudDao extends BaseDao<ProdCrafworkPathPlmAud, Long> {

    // 获取工艺信息
    List<ProdCrafworkPathPlmAudDto> pathCraf(ProdCrafworkPathPlm prodCrafworkPathPlm);
}
