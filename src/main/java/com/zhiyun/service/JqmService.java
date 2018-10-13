/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service;

import com.zhiyun.base.service.BaseService;
import com.zhiyun.dto.JqmDto;
import com.zhiyun.entity.DevInfoJqm;

import java.util.List;

/**
 * 服务接口。
 */
public interface JqmService extends BaseService<JqmDto, Long> {

    //	//k断纱轮询
    //	List<Map<String, String>> broken();
    //	//整经机开机率
    //	List<Map<String, String>> openRate();
    //	//k班次效率
    //	List<Map<String, String>> bcRate();
    //	//查询设备 devName可模糊查询编码及名称
    //	List<DevInfoJqm> findDev(DevInfoJqm devInfoJqm);
    //	//查询设备 devName可模糊查询编码及名称,关联set表
    //	List<DevInfoJqm> findDevSet(DevInfoJqm devInfoJqm);
    //查询设备类型
    List<DevInfoJqm> findType(DevInfoJqm devInfoJqm);
}
