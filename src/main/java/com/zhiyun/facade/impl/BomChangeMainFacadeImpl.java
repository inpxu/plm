/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.facade.impl;

import com.zhiyun.facade.BomChangeMainFacade;
import com.zhiyun.service.BomChangeMainService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * BomChangeMainFacade接口实现类。
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Service("bomChangeMainFacade")
public class BomChangeMainFacadeImpl implements BomChangeMainFacade {

    @Resource
    private BomChangeMainService bomChangeMainService;

}
