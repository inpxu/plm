/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service;

import com.zhiyun.base.service.BaseService;
import com.zhiyun.dto.ProdCrafworkResourceMesDto;
import com.zhiyun.entity.ProdCrafworkResourceMes;

import java.util.List;

/**
 * 产品工艺资料(文件、图纸)Service接口。
 *
 * @author auto
 * @version v1.0
 * @date
 */
public interface ProdCrafworkResourceMesService extends BaseService<ProdCrafworkResourceMes, Long> {

    ProdCrafworkResourceMesDto add(ProdCrafworkResourceMesDto prodCrafworkResourceMesDto);

    List<ProdCrafworkResourceMesDto> get(ProdCrafworkResourceMes prodCrafworkResourceMes);

    ProdCrafworkResourceMesDto upd(ProdCrafworkResourceMesDto prodCrafworkResourceMesDto);

}
