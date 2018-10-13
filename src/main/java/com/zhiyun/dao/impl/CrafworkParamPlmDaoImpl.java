/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dao.impl;

import com.zhiyun.base.dao.BaseDaoImpl;
import com.zhiyun.client.UserHolder;
import com.zhiyun.dao.CrafworkParamPlmDao;
import com.zhiyun.dto.CrafworkParamPlmDto;
import com.zhiyun.entity.CrafworkParamPlm;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * CrafworkParamPlmDao接口实现类
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Repository("crafworkParamPlmDao")
public class CrafworkParamPlmDaoImpl extends BaseDaoImpl<CrafworkParamPlm, Long> implements CrafworkParamPlmDao {

    @Override
    public List<CrafworkParamPlm> addParamGet(CrafworkParamPlmDto crafworkParamPlmDto) {
        crafworkParamPlmDto.setCompanyId(UserHolder.getCompanyId());
        return this.selectList(getMethodName(), crafworkParamPlmDto);
    }
}
