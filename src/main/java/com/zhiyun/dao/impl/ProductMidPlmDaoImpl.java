/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dao.impl;

import com.zhiyun.base.dao.BaseDaoImpl;
import com.zhiyun.base.model.DataGrid;
import com.zhiyun.base.model.Pager;
import com.zhiyun.base.model.Params;
import com.zhiyun.client.UserHolder;
import com.zhiyun.dao.ProductMidPlmDao;
import com.zhiyun.dto.ProdMidDto;
import com.zhiyun.entity.ProductMidPlm;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ProductMidPlmDao接口实现类
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Repository("productMidPlmDao")
public class ProductMidPlmDaoImpl extends BaseDaoImpl<ProductMidPlm, Long> implements ProductMidPlmDao {

    @Override
    public DataGrid<ProdMidDto> pageMid(Params params, Pager pager) {
        return this.selectPage(getMethodName(), params, pager);
    }

    @Override
    public List<ProdMidDto> findMid(ProdMidDto productMidPlm) {
        return this.selectList(getMethodName(), productMidPlm);
    }

    @Override
    public List<ProdMidDto> prodDropDown(ProductMidPlm productMidPlm) {
        productMidPlm.setCompanyId(UserHolder.getCompanyId());
        return this.selectList(getMethodName(), productMidPlm);
    }

    @Override
    public List<ProductMidPlm> getMidMess(ProductMidPlm productMidPlm) {
        productMidPlm.setCompanyId(UserHolder.getCompanyId());
        return this.selectList(getMethodName(), productMidPlm);
    }
}
