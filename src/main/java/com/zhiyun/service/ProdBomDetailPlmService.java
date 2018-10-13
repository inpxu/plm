/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service;

import com.zhiyun.base.service.BaseService;
import com.zhiyun.dto.ProdBomDetailPlmDto;
import com.zhiyun.entity.ProdBomDetailPlm;

import java.util.List;

/**
 * 产品物料构成明细表（BOM）Service接口。
 *
 * @author auto
 * @version v1.0
 * @date
 */
public interface ProdBomDetailPlmService extends BaseService<ProdBomDetailPlm, Long> {

    List<ProdBomDetailPlmDto> getMatter(ProdBomDetailPlmDto prodBomDetailPlmDto);
}
