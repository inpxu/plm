/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dao.impl;

import com.zhiyun.base.dao.BaseDaoImpl;
import com.zhiyun.dao.ProdCrafworkPathPlmAudDao;
import com.zhiyun.dto.ProdCrafworkPathPlmAudDto;
import com.zhiyun.entity.ProdCrafworkPathPlm;
import com.zhiyun.entity.ProdCrafworkPathPlmAud;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ProdCrafworkPathPlmAudDao接口实现类
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Repository("prodCrafworkPathPlmAudDao")
public class ProdCrafworkPathPlmAudDaoImpl extends BaseDaoImpl<ProdCrafworkPathPlmAud, Long> implements ProdCrafworkPathPlmAudDao {

    @Override
    public List<ProdCrafworkPathPlmAudDto> pathCraf(ProdCrafworkPathPlm prodCrafworkPathPlm) {
        return this.selectList(getMethodName(), prodCrafworkPathPlm);
    }
}
