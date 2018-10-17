/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dao.impl;

import com.zhiyun.base.dao.BaseDaoImpl;
import com.zhiyun.dao.CrafworkChangeRecordPlmDao;
import com.zhiyun.dto.CrafworkChangeRecordPlmDto;
import com.zhiyun.entity.CrafworkChangeRecordPlm;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * CrafworkChangeRecordPlmDao接口实现类
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Repository("crafworkChangeRecordPlmDao")
public class CrafworkChangeRecordPlmDaoImpl extends BaseDaoImpl<CrafworkChangeRecordPlm, Long> implements CrafworkChangeRecordPlmDao {

    @Override
    public List<CrafworkChangeRecordPlmDto> getChange(CrafworkChangeRecordPlmDto crafworkChangeRecordPlmDto) {
        return this.selectList(getMethodName(), crafworkChangeRecordPlmDto);
    }

    @Override
    public List<CrafworkChangeRecordPlmDto> getDetail(CrafworkChangeRecordPlm crafworkChangeRecordPlm) {
        return this.selectList(getMethodName(), crafworkChangeRecordPlm);
    }

    @Override
    public List<CrafworkChangeRecordPlmDto> getRecordDetail(CrafworkChangeRecordPlmDto crafworkChangeRecordPlmDto) {
        return this.selectList(getMethodName(), crafworkChangeRecordPlmDto);
    }
}
