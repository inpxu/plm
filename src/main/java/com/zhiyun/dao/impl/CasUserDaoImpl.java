/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dao.impl;

import com.zhiyun.base.dao.BaseDaoImpl;
import com.zhiyun.base.model.Params;
import com.zhiyun.dao.CasUserDao;
import com.zhiyun.entity.CasUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("casUserDao")
public class CasUserDaoImpl extends BaseDaoImpl<CasUser, Long> implements CasUserDao {

    @Override
    public Long insertSpace(Long userId) {
        Params params = Params.create().add("userId", userId);
        return (long) this.delete(getMethodName(), params);
    }

    @Override
    public List<CasUser> customeFind(CasUser casUser) {
        return this.selectList(getMethodName(), casUser);
    }

}
