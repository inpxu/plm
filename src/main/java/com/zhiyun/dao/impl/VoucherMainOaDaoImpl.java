/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dao.impl;

import com.zhiyun.base.dao.BaseDaoImpl;
import com.zhiyun.base.model.Params;
import com.zhiyun.dao.VoucherMainOaDao;
import com.zhiyun.entity.ProdCrafworkMainPlm;
import com.zhiyun.entity.VoucherMainOa;
import org.springframework.stereotype.Repository;

/**
 * VoucherMainOaDao接口实现类
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Repository("voucherMainOaDao")
public class VoucherMainOaDaoImpl extends BaseDaoImpl<VoucherMainOa, Long> implements VoucherMainOaDao {

    @Override
    public VoucherMainOa getIsFinished(ProdCrafworkMainPlm prodCrafworkMainPlm) {
        return this.selectOne(getMethodName(), prodCrafworkMainPlm);
    }

    @Override
    public VoucherMainOa getByVoucherNo(String voucherNo, Long companyId) {
        Params params = Params.create();
        params.add("voucherNo",voucherNo);
        params.add("companyId",companyId);

        return this.selectOne(this.getMethodName(),params);
    }

    @Override
    public void updateByVoucherNo(VoucherMainOa voucherMainOa) {
        this.update(this.getMethodName(),voucherMainOa);
    }
}
