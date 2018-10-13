/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dao.impl;

import com.zhiyun.base.dao.BaseDaoImpl;
import com.zhiyun.dao.MachineSetMesDao;
import com.zhiyun.dto.MachineSetMesDto;
import com.zhiyun.entity.MachineSetMes;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * MachineSetMesDao接口实现类
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Repository("machineSetMesDao")
public class MachineSetMesDaoImpl extends BaseDaoImpl<MachineSetMes, Long> implements MachineSetMesDao {

    @Override
    public List<MachineSetMesDto> getMac(MachineSetMes machineSetMes) {
        return this.selectList(getMethodName(), machineSetMes);
    }
}
