/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service;

import com.zhiyun.base.service.BaseService;
import com.zhiyun.dto.ProdCrafworkPathPlmDto;
import com.zhiyun.entity.ProdCrafworkPathPlm;
import com.zhiyun.form.ProdCrafworkPathPlmForm;

import java.util.List;

/**
 * 产品工艺路线表Service接口。
 *
 * @author auto
 * @version v1.0
 * @date
 */
public interface ProdCrafworkPathPlmService extends BaseService<ProdCrafworkPathPlm, Long> {

    // 获取工艺信息
    List<ProdCrafworkPathPlmDto> crafworkStruct(ProdCrafworkPathPlm prodCrafworkPathPlm);

    // 删除工艺池工艺
    void delStruct(ProdCrafworkPathPlmDto prodCrafworkPathPlmDto);

    // 添加工艺池工艺
    List<ProdCrafworkPathPlm> addStruct(ProdCrafworkPathPlmForm prodCrafworkPathPlmForm);

    // 调整工艺顺序
    void upStruct(ProdCrafworkPathPlmForm prodCrafworkPathPlmForm);

    // 获取工艺信息
    List<ProdCrafworkPathPlmDto> pathCraf(ProdCrafworkPathPlm prodCrafworkPathPlm);

    ProdCrafworkPathPlmDto add(ProdCrafworkPathPlmDto prodCrafworkPathPlm);

    ProdCrafworkPathPlm upd(ProdCrafworkPathPlmDto prodCrafworkPathPlm);

    List<ProdCrafworkPathPlmDto> del(ProdCrafworkPathPlm prodCrafworkPathPlm);

}
