/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dao;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.dto.ProdMacPlmDto;
import com.zhiyun.entity.ProdMacPlm;

import java.util.List;

/**
 * ProdMacPlmDao接口
 *
 * @author auto
 * @version v1.0
 * @date
 */
public interface ProdMacPlmDao extends BaseDao<ProdMacPlm, Long> {

    List<ProdMacPlmDto> findMacPlm(ProdMacPlm prodMacPlm);

}
