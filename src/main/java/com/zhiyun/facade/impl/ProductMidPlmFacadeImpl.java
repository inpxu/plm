/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.facade.impl;

import com.zhiyun.facade.ProductMidPlmFacade;
import com.zhiyun.service.ProductMidPlmService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * ProductMidPlmFacade接口实现类。
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Service("productMidPlmFacade")
public class ProductMidPlmFacadeImpl implements ProductMidPlmFacade {

    @Resource
    private ProductMidPlmService productMidPlmService;

}
