/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service;

import com.zhiyun.base.service.BaseService;
import com.zhiyun.dto.CrafworkInputMaterPlmDto;
import com.zhiyun.entity.CrafworkInputMaterPlm;
import com.zhiyun.form.CrafworkInputMaterPlmForm;

import java.util.List;

/**
 * 工艺输入物料Service接口。
 *
 * @author auto
 * @version v1.0
 * @date
 */
public interface CrafworkInputMaterPlmService extends BaseService<CrafworkInputMaterPlm, Long> {

    List<CrafworkInputMaterPlmDto> findInput(CrafworkInputMaterPlm crafworkInputMaterPlm);

    List<CrafworkInputMaterPlmDto> addInput(CrafworkInputMaterPlmForm crafworkInputMaterPlmForm);

}
