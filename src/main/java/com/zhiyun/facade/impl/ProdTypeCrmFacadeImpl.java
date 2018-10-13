/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.facade.impl;

import com.zhiyun.facade.ProdTypeCrmFacade;
import com.zhiyun.service.ProdTypeCrmService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * ProdTypeCrmFacade接口实现类。
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Service("prodTypeCrmFacade")
public class ProdTypeCrmFacadeImpl implements ProdTypeCrmFacade {

    @Resource
    private ProdTypeCrmService prodTypeCrmService;

}
