/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dao;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.entity.ProdCrafworkMainPlm;
import com.zhiyun.entity.VoucherMainOa;

/**
 * VoucherMainOaDao接口
 *
 * @author auto
 * @version v1.0
 * @date
 */
public interface VoucherMainOaDao extends BaseDao<VoucherMainOa, Long> {

    VoucherMainOa getIsFinished(ProdCrafworkMainPlm prodCrafworkMainPlm);

    VoucherMainOa getByVoucherNo(String voucherNo, Long companyId);

    void updateByVoucherNo(VoucherMainOa voucherMainOa);

}
