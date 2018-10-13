/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service.impl;

import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.exception.BusinessException;
import com.zhiyun.base.model.DataGrid;
import com.zhiyun.base.model.LabelValue;
import com.zhiyun.base.model.Pager;
import com.zhiyun.base.model.Params;
import com.zhiyun.base.service.BaseServiceImpl;
import com.zhiyun.base.util.StringUtil;
import com.zhiyun.client.UserHolder;
import com.zhiyun.dao.MattersStoreIosDao;
import com.zhiyun.dao.MattersTypeIosDao;
import com.zhiyun.entity.MattersStoreIos;
import com.zhiyun.entity.MattersTypeIos;
import com.zhiyun.service.MattersTypeIosService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 物料分类设置Service接口实现类。
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Service("mattersTypeIosService")
public class MattersTypeIosServiceImpl extends BaseServiceImpl<MattersTypeIos, Long> implements MattersTypeIosService {

    @Resource
    private MattersTypeIosDao mattersTypeIosDao;
    @Resource
    private MattersStoreIosDao mattersStoreIosDao;

    @Override
    protected BaseDao<MattersTypeIos, Long> getBaseDao() {
        return this.mattersTypeIosDao;
    }

    @Transactional
    @Override
    public void insertMatterType(MattersTypeIos mattersTypeIos) {
        if (StringUtil.isBlank(mattersTypeIos.getTypeNo())) {
            throw new BusinessException("编码为空");
        }
        //编码的唯一校验
        MattersTypeIos ios = new MattersTypeIos();
        ios.setTypeNo(mattersTypeIos.getTypeNo());
        List<MattersTypeIos> list = this.mattersTypeIosDao.find(ios);
        if (list != null && list.size() > 0) {
            throw new BusinessException("改编码已存在，请重新输入");
        }
        mattersTypeIos.setCompanyId(UserHolder.getCompanyId());
        this.mattersTypeIosDao.insert(mattersTypeIos);
    }

    @Transactional
    @Override
    public void updateMatterType(MattersTypeIos mattersTypeIos) {
        this.mattersTypeIosDao.update(mattersTypeIos);
    }

    @Transactional
    @Override
    public void deletedMatterType(Long[] id) {
        if (id != null) {
            for (Long ids : id) {
                //校验(物料库已经使用的分类不能删除)
                MattersStoreIos storeIos = new MattersStoreIos();
                storeIos.setTypeId(ids);
                List<MattersStoreIos> list = this.mattersStoreIosDao.find(storeIos);
                if (list != null && list.size() > 0) {
                    throw new BusinessException("该物料分类已被使用，无法删除");
                }
                //删除
                this.mattersTypeIosDao.delete(ids);
            }

        }

    }

    @Override
    public DataGrid<MattersTypeIos> select(Params params, Pager pager) {
        return this.mattersTypeIosDao.select(params, pager);
    }

    @Override
    public List<LabelValue> mattersOptions() {
        List<LabelValue> list = new ArrayList<LabelValue>();
        MattersTypeIos storeIos = new MattersTypeIos();
        //查询所有物料分类
        List<MattersTypeIos> typeIosList = this.mattersTypeIosDao.find(storeIos);
        if (typeIosList != null && typeIosList.size() > 0) {
            for (MattersTypeIos ios : typeIosList) {
                LabelValue labelValue = new LabelValue();
                labelValue.setLabel(ios.getTypeName());
                labelValue.setValue(ios.getId().toString());
                list.add(labelValue);
            }
        }
        return list;
    }
}
