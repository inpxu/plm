/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service.impl;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.exception.BusinessException;
import com.zhiyun.base.model.DataGrid;
import com.zhiyun.base.model.Pager;
import com.zhiyun.base.model.Params;
import com.zhiyun.base.service.BaseServiceImpl;
import com.zhiyun.dao.CrafworkStructPlmDao;
import com.zhiyun.dto.CrafworkStructDto;
import com.zhiyun.entity.CrafworkStructPlm;
import com.zhiyun.service.CrafworkStructPlmService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 工艺池Service接口实现类。
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Service("crafworkStructPlmService")
public class CrafworkStructPlmServiceImpl extends BaseServiceImpl<CrafworkStructPlm, Long> implements CrafworkStructPlmService {

    @Resource
    private CrafworkStructPlmDao crafworkStructPlmDao;

    @Override
    protected BaseDao<CrafworkStructPlm, Long> getBaseDao() {
        return this.crafworkStructPlmDao;
    }

    @Override
    public DataGrid<CrafworkStructDto> pageCrafwork(Params params, Pager pager) {
        DataGrid<CrafworkStructDto> crafworkStructPlmDataGrid = crafworkStructPlmDao.pageCrafwork(params, pager);
        for (CrafworkStructDto crafworkStructPlm : crafworkStructPlmDataGrid.getItems()) {
            if (crafworkStructPlm.getIsCheck() == 1) {
                crafworkStructPlm.setCheckof("是");
            } else {
                crafworkStructPlm.setCheckof("否");
            }
        }
        return crafworkStructPlmDataGrid;
    }

    @Override
    public List<CrafworkStructDto> addGet(CrafworkStructDto crafworkStructDto) {
        String status = crafworkStructDto.getStatus();
        if ("已通过".equals(status)) {
            throw new BusinessException("产品工艺路线审核已通过，不能在此处更改！");
        } else if ("审核中".equals(status)) {
            throw new BusinessException("产品工艺路线审核中，不能更改！");
        }
        List<CrafworkStructDto> list = crafworkStructPlmDao.addGet(crafworkStructDto);
        return list;
    }

    @Override
    public List<CrafworkStructDto> findCraf(CrafworkStructDto crafworkStructDto) {
        return crafworkStructPlmDao.findCraf(crafworkStructDto);
    }
}
