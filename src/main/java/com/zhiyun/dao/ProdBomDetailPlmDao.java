/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dao;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.dto.ProdBomDetailPlmDto;
import com.zhiyun.entity.ProdBomDetailPlm;

import java.util.List;

/**
 * ProdBomDetailPlmDao接口
 *
 * @author auto
 * @version v1.0
 * @date
 */
public interface ProdBomDetailPlmDao extends BaseDao<ProdBomDetailPlm, Long> {

    List<ProdBomDetailPlmDto> getMatter(ProdBomDetailPlmDto prodBomDetailPlmDto);

}
