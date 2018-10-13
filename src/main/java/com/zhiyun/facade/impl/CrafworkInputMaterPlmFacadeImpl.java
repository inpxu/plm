/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.facade.impl;

import com.zhiyun.facade.CrafworkInputMaterPlmFacade;
import com.zhiyun.service.CrafworkInputMaterPlmService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * CrafworkInputMaterPlmFacade接口实现类。
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Service("crafworkInputMaterPlmFacade")
public class CrafworkInputMaterPlmFacadeImpl implements CrafworkInputMaterPlmFacade {

    @Resource
    private CrafworkInputMaterPlmService crafworkInputMaterPlmService;

}
