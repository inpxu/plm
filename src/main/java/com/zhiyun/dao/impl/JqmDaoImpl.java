/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dao.impl;

import com.zhiyun.base.dao.BaseDaoImpl;
import com.zhiyun.dao.JqmDao;
import com.zhiyun.dto.JqmDto;
import com.zhiyun.entity.DevInfoJqm;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("jqmDao")
public class JqmDaoImpl extends BaseDaoImpl<JqmDto, Long> implements JqmDao {

    //	public List<Map<String, String>> broken() {
    //		return this.selectList(getMethodName(), null);
    //	}
    //
    //	@Override
    //	public List<Map<String, String>> openRate() {
    //		return this.selectList(getMethodName(), null);
    //	}
    //
    //	@Override
    //	public List<Map<String, String>> bcRate() {
    //		return this.selectList(getMethodName(), null);
    //	}
    //
    //	@Override
    //	public List<DevInfoJqm> findDev(DevInfoJqm devInfoJqm) {
    //		return this.selectList(getMethodName(), devInfoJqm);
    //	}

    @Override
    public List<DevInfoJqm> findType(DevInfoJqm devInfoJqm) {
        return this.selectList(getMethodName(), devInfoJqm);
    }

    //	@Override
    //	public List<DevInfoJqm> findDevSet(DevInfoJqm devInfoJqm) {
    //		return this.selectList(getMethodName(), devInfoJqm);
    //	}

}
