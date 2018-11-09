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
import com.zhiyun.client.UserHolder;
import com.zhiyun.dao.ProdTypeCrmDao;
import com.zhiyun.dao.ProductStorePlmDao;
import com.zhiyun.entity.ProdTypeCrm;
import com.zhiyun.entity.ProductStorePlm;
import com.zhiyun.service.ProdTypeCrmService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 产品分类设置Service接口实现类。
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Service("prodTypeCrmService")
public class ProdTypeCrmServiceImpl extends BaseServiceImpl<ProdTypeCrm, Long> implements ProdTypeCrmService {

    @Resource
    private ProdTypeCrmDao prodTypeCrmDao;
    @Resource
    private ProductStorePlmDao productStorePlmDao;

    @Override
    protected BaseDao<ProdTypeCrm, Long> getBaseDao() {
        return this.prodTypeCrmDao;
    }

    @Override
    @Transactional
    public int add(ProdTypeCrm prodTypeCrm) {
        ProdTypeCrm pro = new ProdTypeCrm();
        pro.setCompanyId(UserHolder.getCompanyId());
        pro.setDeleted("F");
        pro.setTypeDesc(prodTypeCrm.getTypeDesc());
        List<ProdTypeCrm> lists = prodTypeCrmDao.find(prodTypeCrm);
        if (CollectionUtils.isNotEmpty(lists) || lists.size() > 0) {
            throw new BusinessException("该分类名称已存在！");
        }
        prodTypeCrm.setStartDate(new Date());
        return prodTypeCrmDao.insert(prodTypeCrm);
    }

    @Override
    @Transactional
    public void up(ProdTypeCrm prodTypeCrm) {
        ProdTypeCrm pro = new ProdTypeCrm();
        pro.setCompanyId(UserHolder.getCompanyId());
        pro.setDeleted("F");
        pro.setTypeDesc(prodTypeCrm.getTypeDesc());
        List<ProdTypeCrm> lists = prodTypeCrmDao.find(pro);
        if (CollectionUtils.isNotEmpty(lists) || lists.size() > 0) {
            throw new BusinessException("该分类名称已存在！");
        }
        prodTypeCrmDao.update(prodTypeCrm);
    }

    @Override
    @Transactional
    public void del(Long[] ids) {
        for (Long id : ids) {
            ProductStorePlm plm = new ProductStorePlm();
            plm.setTypeId(id);
            plm.setCompanyId(UserHolder.getCompanyId());
            plm.setDeleted("F");
            List<ProductStorePlm> list = productStorePlmDao.find(plm);
            if (CollectionUtils.isNotEmpty(list) || list.size() > 0) {
                throw new BusinessException("分类已被使用，不能删除！");
            }
        }
        for (Long id : ids) {
            ProdTypeCrm pro = new ProdTypeCrm();
            pro.setId(id);
            pro.setCompanyId(UserHolder.getCompanyId());
            pro.setLoseDate(new Date());
            prodTypeCrmDao.update(pro);
            prodTypeCrmDao.delete(id);
        }
    }

    @Override
    public List<ProdTypeCrm> getType(ProdTypeCrm prodTypeCrm) {
        prodTypeCrm.setCompanyId(UserHolder.getCompanyId());
        return prodTypeCrmDao.getType(prodTypeCrm);
    }

    @Override
    public DataGrid<ProdTypeCrm> pages(Params params, Pager pager) {
        return prodTypeCrmDao.pages(params, pager);
    }
}
