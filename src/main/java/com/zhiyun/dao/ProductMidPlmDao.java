/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dao;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.model.DataGrid;
import com.zhiyun.base.model.Pager;
import com.zhiyun.base.model.Params;
import com.zhiyun.dto.ProdMidDto;
import com.zhiyun.entity.ProductMidPlm;

import java.util.List;
import java.util.Map;

/**
 * ProductMidPlmDao接口
 *
 * @author auto
 * @version v1.0
 * @date
 */
public interface ProductMidPlmDao extends BaseDao<ProductMidPlm, Long> {
    DataGrid<ProdMidDto> pageMid(Params params, Pager pager);

    List<ProdMidDto> findMid(ProdMidDto productMidPlm);

    // 产品工艺路线侧边产品,半成品展开
    List<ProdMidDto> prodDropDown(ProductMidPlm productMidPlm);

    List<ProductMidPlm> getMidMess(ProductMidPlm productMidPlm);

    List<ProductMidPlm> findAllMidProductByMidId(Map<String, Object> param);
}
