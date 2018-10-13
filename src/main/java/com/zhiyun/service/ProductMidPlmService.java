/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service;

import com.zhiyun.base.model.DataGrid;
import com.zhiyun.base.model.Pager;
import com.zhiyun.base.model.Params;
import com.zhiyun.base.service.BaseService;
import com.zhiyun.dto.ProdMidDto;
import com.zhiyun.entity.ProductMidPlm;

import java.util.List;

/**
 * 半成品编码Service接口。
 *
 * @author auto
 * @version v1.0
 * @date
 */
public interface ProductMidPlmService extends BaseService<ProductMidPlm, Long> {
    DataGrid<ProdMidDto> pageMid(Params params, Pager pager);

    List<ProdMidDto> findMid(ProdMidDto productMidPlm);

    // 产品工艺路线侧边产品,半成品展开
    List<ProdMidDto> prodDropDown(ProductMidPlm productMidPlm);

    // 产品工艺路线侧边产品,半成品展开
    List<ProdMidDto> getChild(ProductMidPlm productMidPlm);

    List<ProductMidPlm> getMidMess(ProductMidPlm productMidPlm);
}
