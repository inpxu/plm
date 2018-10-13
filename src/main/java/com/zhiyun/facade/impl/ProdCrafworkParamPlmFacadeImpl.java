/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.facade.impl;

import com.zhiyun.facade.ProdCrafworkParamPlmFacade;
import com.zhiyun.service.ProdCrafworkParamPlmService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * ProdCrafworkParamPlmFacade接口实现类。
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Service("prodCrafworkParamPlmFacade")
public class ProdCrafworkParamPlmFacadeImpl implements ProdCrafworkParamPlmFacade {

    @Resource
    private ProdCrafworkParamPlmService prodCrafworkParamPlmService;

}
