/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service.impl;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.service.BaseServiceImpl;
import com.zhiyun.client.UserHolder;
import com.zhiyun.dao.MattersStoreIosDao;
import com.zhiyun.dao.ProdBomPlmDao;
import com.zhiyun.dto.ProductStorePlmDto;
import com.zhiyun.entity.MattersStoreIos;
import com.zhiyun.entity.ProdBomPlm;
import com.zhiyun.service.ProdBomPlmService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 产品物料构成主表Service接口实现类。
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Service("prodBomPlmService")
public class ProdBomPlmServiceImpl extends BaseServiceImpl<ProdBomPlm, Long> implements ProdBomPlmService {

    @Resource
    private ProdBomPlmDao prodBomPlmDao;
    @Autowired
    private MattersStoreIosDao mattersStoreIosDao;

    @Override
    protected BaseDao<ProdBomPlm, Long> getBaseDao() {
        return this.prodBomPlmDao;
    }

    @Override
    public ProductStorePlmDto searchForProduct(String productName, String bomCode) {
        Map<String, Object> param = new HashMap<>(3);
        param.put("productName", productName);
        param.put("bomCode", bomCode);
        param.put("companyId", UserHolder.getCompanyId());
        return prodBomPlmDao.searchForProduct(param);
    }

    @Override
    public ProdBomPlm findBomByPnoOrMpno(String pNo, String mpno) {
        Map<String, Object> param = new HashMap<>(3);
        param.put("companyId", UserHolder.getCompanyId());
        if (StringUtils.isNotBlank(pNo) && StringUtils.isBlank(mpno)) {
            //通过产品编码查询
            param.put("productNo", pNo);
            return prodBomPlmDao.findBomByPno(param);
        } else if (StringUtils.isBlank(pNo) && StringUtils.isNotBlank(mpno)) {
            //通过半成品编码查询
            param.put("midProductNo", mpno);
            return prodBomPlmDao.findBomByMpno(param);
        }
        return null;
    }

    @Override
    public void addBomNo(ProdBomPlm prodBomPlm) {
        prodBomPlmDao.insert(prodBomPlm);
    }

    @Override
    public void addMatters(MattersStoreIos mattersStoreIos) {
        mattersStoreIosDao.insert(mattersStoreIos);
    }
}
