/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dao;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.dto.CrafworkInputMaterPlmDto;
import com.zhiyun.entity.CrafworkInputMaterPlm;

import java.util.List;

/**
 * CrafworkInputMaterPlmDao接口
 *
 * @author auto
 * @version v1.0
 * @date
 */
public interface CrafworkInputMaterPlmDao extends BaseDao<CrafworkInputMaterPlm, Long> {

    List<CrafworkInputMaterPlmDto> findInput(CrafworkInputMaterPlm crafworkInputMaterPlm);
}
