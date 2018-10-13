/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service.impl;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.service.BaseServiceImpl;
import com.zhiyun.dao.MachineSetMesDao;
import com.zhiyun.dto.MachineSetMesDto;
import com.zhiyun.entity.MachineSetMes;
import com.zhiyun.service.MachineSetMesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Service接口实现类。
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Service("machineSetMesService")
public class MachineSetMesServiceImpl extends BaseServiceImpl<MachineSetMes, Long> implements MachineSetMesService {

    @Resource
    private MachineSetMesDao machineSetMesDao;

    @Override
    protected BaseDao<MachineSetMes, Long> getBaseDao() {
        return this.machineSetMesDao;
    }

    @Override
    public List<MachineSetMesDto> getMac(MachineSetMes machineSetMes) {
        return machineSetMesDao.getMac(machineSetMes);
    }
}
