/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dao.impl;

import com.zhiyun.base.dao.BaseDaoImpl;
import com.zhiyun.dao.CarfworkOutputMaterPlmDao;
import com.zhiyun.dto.CarfworkOutputMaterPlmDto;
import com.zhiyun.entity.CarfworkOutputMaterPlm;
import org.springframework.stereotype.Repository;

/**
 * CarfworkOutputMaterPlmDao接口实现类
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Repository("carfworkOutputMaterPlmDao")
public class CarfworkOutputMaterPlmDaoImpl extends BaseDaoImpl<CarfworkOutputMaterPlm, Long> implements CarfworkOutputMaterPlmDao {

    @Override
    public CarfworkOutputMaterPlmDto getMidOut(CarfworkOutputMaterPlm carfworkOutputMaterPlm) {
        return this.selectOne(getMethodName(), carfworkOutputMaterPlm);
    }

    @Override
    public CarfworkOutputMaterPlmDto getProdOut(CarfworkOutputMaterPlm carfworkOutputMaterPlm) {
        return this.selectOne(getMethodName(), carfworkOutputMaterPlm);
    }
}
