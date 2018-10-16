/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service;

import com.zhiyun.base.service.BaseService;
import com.zhiyun.entity.CrafworkChangeMain;
import com.zhiyun.entity.ProdCrafworkMainPlm;
import com.zhiyun.entity.VoucherMainOa;

/**
 * 单据主表Service接口。
 *
 * @author auto
 * @version v1.0
 * @date
 */
public interface VoucherMainOaService extends BaseService<VoucherMainOa, Long> {

    // 工艺路线审核
    void submit(ProdCrafworkMainPlm prodCrafworkMainPlm);
    // 路线变更审核
    void audit(CrafworkChangeMain crafworkChangeMain);

    void examine(String voucherNo, boolean isPass);

}
