/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service;

import com.zhiyun.base.service.BaseService;
import com.zhiyun.dto.CrafworkChangeMainDto;
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

    // 工艺路线提交审核
    void submit(ProdCrafworkMainPlm prodCrafworkMainPlm);

    // 路线变更提交审核
    void audit(CrafworkChangeMainDto crafworkChangeMainDto);

    // 路线审批
    void examine(String voucherNo, boolean isPass);

    void pass(String voucherNo, boolean isPass);

    // 路线变更审批
    void changeExamine(String voucherNo, boolean isPass);

}
