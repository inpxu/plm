/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service.impl;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.service.BaseServiceImpl;
import com.zhiyun.dao.JqmDao;
import com.zhiyun.dto.JqmDto;
import com.zhiyun.entity.DevInfoJqm;
import com.zhiyun.service.JqmService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("jqmService")
public class JqmServiceImpl extends BaseServiceImpl<JqmDto, Long> implements JqmService {

    @Resource
    private JqmDao jqmDao;

    @Override
    protected BaseDao<JqmDto, Long> getBaseDao() {
        return this.jqmDao;
    }

    //	@Override
    //	public List<Map<String, String>> broken() {
    //		return jqmDao.broken();
    //	}
    //
    //
    //	@Override
    //	public List<Map<String, String>> openRate() {
    //		return jqmDao.openRate();
    //	}
    //
    //
    //	@Override
    //	public List<Map<String, String>> bcRate() {
    //		return jqmDao.bcRate();
    //	}
    //
    //
    //	@Override
    //	public List<DevInfoJqm> findDev(DevInfoJqm devInfoJqm) {
    //		return jqmDao.findDev(devInfoJqm);
    //	}

    @Override
    public List<DevInfoJqm> findType(DevInfoJqm devInfoJqm) {
        return jqmDao.findType(devInfoJqm);
    }

    //	@Override
    //	public List<DevInfoJqm> findDevSet(DevInfoJqm devInfoJqm) {
    //		return jqmDao.findDevSet(devInfoJqm);
    //	}

}
