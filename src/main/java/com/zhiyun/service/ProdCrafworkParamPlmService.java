/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service;

import com.zhiyun.base.service.BaseService;
import com.zhiyun.dto.ProdCrafworkParamPlmDto;
import com.zhiyun.entity.ProdCrafworkParamPlm;

import java.util.List;

/**
 * 产品工艺参数Service接口。
 *
 * @author auto
 * @version v1.0
 * @date
 */
public interface ProdCrafworkParamPlmService extends BaseService<ProdCrafworkParamPlm, Long> {

    void delParam(ProdCrafworkParamPlmDto prodCrafworkParamPlmDto);

    void addParam(List<ProdCrafworkParamPlm> prodCrafworkParamPlms);
}
