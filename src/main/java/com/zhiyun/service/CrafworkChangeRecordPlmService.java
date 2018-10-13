/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service;

import com.zhiyun.base.service.BaseService;
import com.zhiyun.dto.CrafworkChangeRecordPlmDto;
import com.zhiyun.entity.CrafworkChangeRecordPlm;
import com.zhiyun.form.ChangeRecordForm;

import java.util.List;

/**
 * 工艺路线变更申请表Service接口。
 *
 * @author auto
 * @version v1.0
 * @date
 */
public interface CrafworkChangeRecordPlmService extends BaseService<CrafworkChangeRecordPlm, Long> {

    ChangeRecordForm getRecord(CrafworkChangeRecordPlmDto crafworkChangeRecordPlmDto);

    List<CrafworkChangeRecordPlmDto> getDetail(CrafworkChangeRecordPlm crafworkChangeRecordPlm);

    List<CrafworkChangeRecordPlmDto> getRecordDetail(CrafworkChangeRecordPlmDto crafworkChangeRecordPlmDto);

}
