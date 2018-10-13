/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dao;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.dto.MachineSetMesDto;
import com.zhiyun.entity.MachineSetMes;

import java.util.List;

/**
 * MachineSetMesDao接口
 *
 * @author auto
 * @version v1.0
 * @date
 */
public interface MachineSetMesDao extends BaseDao<MachineSetMes, Long> {

    List<MachineSetMesDto> getMac(MachineSetMes machineSetMes);
}
