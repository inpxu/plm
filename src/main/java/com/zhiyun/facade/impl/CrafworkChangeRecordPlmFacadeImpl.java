/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.facade.impl;

import com.zhiyun.facade.CrafworkChangeRecordPlmFacade;
import com.zhiyun.service.CrafworkChangeRecordPlmService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * CrafworkChangeRecordPlmFacade接口实现类。
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Service("crafworkChangeRecordPlmFacade")
public class CrafworkChangeRecordPlmFacadeImpl implements CrafworkChangeRecordPlmFacade {

    @Resource
    private CrafworkChangeRecordPlmService crafworkChangeRecordPlmService;

}
