/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dao.impl;

import com.zhiyun.base.dao.BaseDaoImpl;
import com.zhiyun.dao.CrafworkInputMaterPlmDao;
import com.zhiyun.dto.CrafworkInputMaterPlmDto;
import com.zhiyun.entity.CrafworkInputMaterPlm;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * CrafworkInputMaterPlmDao接口实现类
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Repository("crafworkInputMaterPlmDao")
public class CrafworkInputMaterPlmDaoImpl extends BaseDaoImpl<CrafworkInputMaterPlm, Long> implements CrafworkInputMaterPlmDao {

    @Override
    public List<CrafworkInputMaterPlmDto> findInput(CrafworkInputMaterPlm crafworkInputMaterPlm) {
        return this.selectList(getMethodName(), crafworkInputMaterPlm);
    }
}
