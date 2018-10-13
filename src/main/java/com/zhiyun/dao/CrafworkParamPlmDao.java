/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dao;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.dto.CrafworkParamPlmDto;
import com.zhiyun.entity.CrafworkParamPlm;

import java.util.List;

/**
 * CrafworkParamPlmDao接口
 *
 * @author auto
 * @version v1.0
 * @date
 */
public interface CrafworkParamPlmDao extends BaseDao<CrafworkParamPlm, Long> {
    // 工艺路线参数添加查询
    List<CrafworkParamPlm> addParamGet(CrafworkParamPlmDto crafworkParamPlmDto);

}
