/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dao.impl;

import com.zhiyun.base.dao.BaseDaoImpl;
import com.zhiyun.dao.ProdMacPlmDao;
import com.zhiyun.dto.ProdMacPlmDto;
import com.zhiyun.entity.ProdMacPlm;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ProdMacPlmDao接口实现类
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Repository("prodMacPlmDao")
public class ProdMacPlmDaoImpl extends BaseDaoImpl<ProdMacPlm, Long> implements ProdMacPlmDao {

    @Override
    public List<ProdMacPlmDto> findMacPlm(ProdMacPlm prodMacPlm) {
        return this.selectList(getMethodName(), prodMacPlm);
    }
}
