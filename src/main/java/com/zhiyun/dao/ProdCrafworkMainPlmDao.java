/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dao;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.dto.ProdCrafworkMainPlmDto;
import com.zhiyun.entity.ProdCrafworkMainPlm;
import com.zhiyun.entity.ProductMidPlm;

import java.util.List;

/**
 * ProdCrafworkMainPlmDao接口
 *
 * @author auto
 * @version v1.0
 * @date
 */
public interface ProdCrafworkMainPlmDao extends BaseDao<ProdCrafworkMainPlm, Long> {

    ProdCrafworkMainPlm getVoucher(ProductMidPlm productMidPlm);

    ProdCrafworkMainPlmDto getMain(String prodNo);

    ProdCrafworkMainPlmDto getStatus(ProdCrafworkMainPlmDto prodCrafworkMainPlmDto);

    void enDis(ProdCrafworkMainPlm prodCrafworkMainPlm);

    List<ProdCrafworkMainPlmDto> getPathMsg(ProdCrafworkMainPlmDto prodCrafworkMainPlm);

    List<ProdCrafworkMainPlmDto> getProdMsg(ProdCrafworkMainPlmDto prodCrafworkMainPlmDto);

    List<ProdCrafworkMainPlmDto> findMainMess(ProdCrafworkMainPlmDto prodCrafworkMainPlmDto);

}
