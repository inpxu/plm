/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service;

import com.zhiyun.base.service.BaseService;
import com.zhiyun.dto.ProdCrafworkMainPlmDto;
import com.zhiyun.dto.ProdCrafworkPathPlmAudDto;
import com.zhiyun.dto.ProdCrafworkPathPlmDto;
import com.zhiyun.dto.ProdMidDto;
import com.zhiyun.entity.ProdCrafworkMainPlm;
import com.zhiyun.entity.ProductMidPlm;

import java.util.List;

/**
 * 工艺路线设置主表Service接口。
 *
 * @author auto
 * @version v1.0
 * @date
 */
public interface ProdCrafworkMainPlmService extends BaseService<ProdCrafworkMainPlm, Long> {

    void  addPathNo(ProdCrafworkMainPlm prodCrafworkMainPlm);

    ProdCrafworkMainPlm  getSV(ProdCrafworkMainPlm prodCrafworkMainPlm);

    ProdCrafworkMainPlmDto getMsg(ProdMidDto prodMidDto);

    void enDis(ProdCrafworkMainPlmDto prodCrafworkMainPlmDto);

    List<ProdCrafworkMainPlmDto> getPathMsg(ProdCrafworkMainPlmDto prodCrafworkMainPlm);

    List<ProdCrafworkMainPlmDto> getProdMsg(ProdCrafworkMainPlmDto prodCrafworkMainPlmDto);

    List<ProdCrafworkMainPlmDto> findMainMess(ProdCrafworkMainPlmDto prodCrafworkMainPlmDto);

    // 产品,半成品展开
    List<ProdMidDto> prodDown(ProductMidPlm productMidPlm);

    void switchSequence(ProdCrafworkPathPlmDto[] prodCrafworkPathPlmDtos);
}
