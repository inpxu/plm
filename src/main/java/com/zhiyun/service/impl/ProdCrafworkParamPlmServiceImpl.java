/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service.impl;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.exception.BusinessException;
import com.zhiyun.base.service.BaseServiceImpl;
import com.zhiyun.client.UserHolder;
import com.zhiyun.dao.ProdCrafworkParamPlmDao;
import com.zhiyun.dto.ProdCrafworkParamPlmDto;
import com.zhiyun.entity.ProdCrafworkParamPlm;
import com.zhiyun.service.ProdCrafworkParamPlmService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 产品工艺参数Service接口实现类。
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Service("prodCrafworkParamPlmService")
public class ProdCrafworkParamPlmServiceImpl extends BaseServiceImpl<ProdCrafworkParamPlm, Long> implements ProdCrafworkParamPlmService {

    @Resource
    private ProdCrafworkParamPlmDao prodCrafworkParamPlmDao;

    @Override
    protected BaseDao<ProdCrafworkParamPlm, Long> getBaseDao() {
        return this.prodCrafworkParamPlmDao;
    }

    @Override
    @Transactional
    public void delParam(ProdCrafworkParamPlmDto prodCrafworkParamPlmDto) {
        String status = prodCrafworkParamPlmDto.getStatus();
        if ("已通过".equals(status)) {
            throw new BusinessException("工艺路线审核已通过，不能在此处更改！");
        } else if ("审批中".equals(status)) {
            throw new BusinessException("工艺路线审批中，不能更改！");
        }
        Long ids = prodCrafworkParamPlmDto.getIds();
        prodCrafworkParamPlmDao.delete(ids);
    }

    @Override
    @Transactional
    public void addParam(List<ProdCrafworkParamPlm> prodCrafworkParamPlms) {
        for (ProdCrafworkParamPlm paramPlm : prodCrafworkParamPlms) {
            String value1 = paramPlm.getValues1();
            String value3 = paramPlm.getValues3();
            String name = paramPlm.getParamName();
            if (value1 == null && value3 == null) {
                throw new BusinessException(name + "的值不能为空！");
            }
        }
        for (ProdCrafworkParamPlm plm : prodCrafworkParamPlms) {
            plm.setCompanyId(UserHolder.getCompanyId());
            prodCrafworkParamPlmDao.insert(plm);
        }
    }
}
