/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dao;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.dto.CrafworkChangeRecordPlmDto;
import com.zhiyun.entity.CrafworkChangeRecordPlm;

import java.util.List;

/**
 * CrafworkChangeRecordPlmDao接口
 *
 * @author auto
 * @version v1.0
 * @date
 */
public interface CrafworkChangeRecordPlmDao extends BaseDao<CrafworkChangeRecordPlm, Long> {

    List<CrafworkChangeRecordPlmDto> getChange(CrafworkChangeRecordPlmDto crafworkChangeRecordPlmDto);

    List<CrafworkChangeRecordPlmDto> getDetail(CrafworkChangeRecordPlm crafworkChangeRecordPlm);

    List<CrafworkChangeRecordPlmDto> getRecordDetail(CrafworkChangeRecordPlmDto crafworkChangeRecordPlmDto);
}
