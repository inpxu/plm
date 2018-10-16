/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dao.impl;

import com.zhiyun.base.dao.BaseDaoImpl;
import com.zhiyun.dao.ProdBomDetailPlmDao;
import com.zhiyun.dto.ProdBomDetailPlmDto;
import com.zhiyun.entity.ProdBomDetailPlm;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ProdBomDetailPlmDao接口实现类
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Repository("prodBomDetailPlmDao")
public class ProdBomDetailPlmDaoImpl extends BaseDaoImpl<ProdBomDetailPlm, Long> implements ProdBomDetailPlmDao {

    @Override
    public List<ProdBomDetailPlmDto> getMatter(ProdBomDetailPlmDto prodBomDetailPlmDto) {
        return this.selectList(getMethodName(), prodBomDetailPlmDto);
    }
}
