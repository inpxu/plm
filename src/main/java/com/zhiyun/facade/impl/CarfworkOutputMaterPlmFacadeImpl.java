/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.facade.impl;

import com.zhiyun.facade.CarfworkOutputMaterPlmFacade;
import com.zhiyun.service.CarfworkOutputMaterPlmService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * CarfworkOutputMaterPlmFacade接口实现类。
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Service("carfworkOutputMaterPlmFacade")
public class CarfworkOutputMaterPlmFacadeImpl implements CarfworkOutputMaterPlmFacade {

    @Resource
    private CarfworkOutputMaterPlmService carfworkOutputMaterPlmService;

}
