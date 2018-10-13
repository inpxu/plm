/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service;

import com.zhiyun.base.service.BaseService;
import com.zhiyun.dto.CrafworkParamPlmDto;
import com.zhiyun.entity.CrafworkParamPlm;

import java.util.List;

/**
 * 工艺参数Service接口。
 *
 * @author auto
 * @version v1.0
 * @date
 */
public interface CrafworkParamPlmService extends BaseService<CrafworkParamPlm, Long> {
    // 工艺路线参数添加查询
    List<CrafworkParamPlm> addParamGet(CrafworkParamPlmDto crafworkParamPlmDto);

    List<CrafworkParamPlm> addParam(List<CrafworkParamPlm> crafworkParamPlm);
}
