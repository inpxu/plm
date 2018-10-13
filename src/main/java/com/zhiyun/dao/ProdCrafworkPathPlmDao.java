/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dao;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.dto.ProdCrafworkPathPlmDto;
import com.zhiyun.entity.ProdCrafworkPathPlm;

import java.util.List;

/**
 * ProdCrafworkPathPlmDao接口
 *
 * @author auto
 * @version v1.0
 * @date
 */
public interface ProdCrafworkPathPlmDao extends BaseDao<ProdCrafworkPathPlm, Long> {
    // 获取工艺信息
    List<ProdCrafworkPathPlmDto> crafworkStruct(ProdCrafworkPathPlm prodCrafworkPathPlm);

    // 获取最大的顺序数
    Long getMaxSeq(String mid);

    List<ProdCrafworkPathPlmDto> getPath(String midProdNo);

    void enDis(ProdCrafworkPathPlm prodCrafworkPathPlm);

    // 获取工艺信息
    List<ProdCrafworkPathPlmDto> pathCraf(ProdCrafworkPathPlm prodCrafworkPathPlm);
}
