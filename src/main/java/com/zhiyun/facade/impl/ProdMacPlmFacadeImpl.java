/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.facade.impl;

import com.zhiyun.facade.ProdMacPlmFacade;
import com.zhiyun.service.ProdMacPlmService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * ProdMacPlmFacade接口实现类。
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Service("prodMacPlmFacade")
public class ProdMacPlmFacadeImpl implements ProdMacPlmFacade {

    @Resource
    private ProdMacPlmService prodMacPlmService;

}
