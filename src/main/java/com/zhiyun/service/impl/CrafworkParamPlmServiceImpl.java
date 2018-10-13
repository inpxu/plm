/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service.impl;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.exception.BusinessException;
import com.zhiyun.base.service.BaseServiceImpl;
import com.zhiyun.client.UserHolder;
import com.zhiyun.dao.CrafworkParamPlmDao;
import com.zhiyun.dto.CrafworkParamPlmDto;
import com.zhiyun.entity.CrafworkParamPlm;
import com.zhiyun.service.CrafworkParamPlmService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 工艺参数Service接口实现类。
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Service("crafworkParamPlmService")
public class CrafworkParamPlmServiceImpl extends BaseServiceImpl<CrafworkParamPlm, Long> implements CrafworkParamPlmService {

    @Resource
    private CrafworkParamPlmDao crafworkParamPlmDao;

    @Override
    protected BaseDao<CrafworkParamPlm, Long> getBaseDao() {
        return this.crafworkParamPlmDao;
    }

    @Override
    public List<CrafworkParamPlm> addParamGet(CrafworkParamPlmDto crafworkParamPlmDto) {
        return crafworkParamPlmDao.addParamGet(crafworkParamPlmDto);
    }

    @Override
    @Transactional
    public List<CrafworkParamPlm> addParam(List<CrafworkParamPlm> crafworkParamPlm) {
        if (CollectionUtils.isEmpty(crafworkParamPlm)) {
            throw new BusinessException("参数名不能为空");
        }
        for (int i = 0; i < crafworkParamPlm.size(); i++) {
            String temp = crafworkParamPlm.get(i).getParam();
            for (int j = i + 1; j < crafworkParamPlm.size(); j++) {
                if (temp.equals(crafworkParamPlm.get(j).getParam())) {
                    throw new BusinessException("同一工艺下的参数名不能相同");
                }
            }
        }
        // 增加新的工艺参数
        for (CrafworkParamPlm car : crafworkParamPlm) {
            car.setCompanyId(UserHolder.getCompanyId());
            List<CrafworkParamPlm> list = crafworkParamPlmDao.find(car);
            if (CollectionUtils.isNotEmpty(list)) {
                throw new BusinessException("同一工艺下的参数名不能相同");
            }
            crafworkParamPlmDao.insert(car);
        }
        return crafworkParamPlm;
    }
}
