/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service;

import com.zhiyun.base.service.BaseService;
import com.zhiyun.dto.ProdMacPlmDto;
import com.zhiyun.entity.ProdMacPlm;
import com.zhiyun.form.ProdMacPlmForm;

import java.util.List;

/**
 * 产品-设备登记Service接口。
 *
 * @author auto
 * @version v1.0
 * @date
 */
public interface ProdMacPlmService extends BaseService<ProdMacPlm, Long> {

    List<ProdMacPlmDto> findMacPlm(ProdMacPlm prodMacPlm);

    List<ProdMacPlmDto> addMacPlm(ProdMacPlmForm prodMacPlmForm);
}
