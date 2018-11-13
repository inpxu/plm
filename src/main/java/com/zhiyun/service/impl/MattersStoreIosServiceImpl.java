/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service.impl;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.exception.BusinessException;
import com.zhiyun.base.model.DataGrid;
import com.zhiyun.base.model.Pager;
import com.zhiyun.base.model.Params;
import com.zhiyun.base.service.BaseServiceImpl;
import com.zhiyun.base.util.StringUtil;
import com.zhiyun.client.UserHolder;
import com.zhiyun.dao.MattersStoreIosDao;
import com.zhiyun.dao.ProductMidPlmDao;
import com.zhiyun.dao.ProductStorePlmDao;
import com.zhiyun.dto.MattersStoreDto;
import com.zhiyun.entity.MattersStoreIos;
import com.zhiyun.entity.ProductMidPlm;
import com.zhiyun.entity.ProductStorePlm;
import com.zhiyun.service.MattersStoreIosService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 物料库Service接口实现类。
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Service("mattersStoreIosService")
public class MattersStoreIosServiceImpl extends BaseServiceImpl<MattersStoreIos, Long> implements MattersStoreIosService {

    @Resource
    private MattersStoreIosDao mattersStoreIosDao;
    @Resource
    private ProductMidPlmDao productMidPlmDao;
    @Resource
    private ProductStorePlmDao productStorePlmDao;

    @Override
    protected BaseDao<MattersStoreIos, Long> getBaseDao() {
        return this.mattersStoreIosDao;
    }

    @Transactional
    @Override
    public void insertStore(MattersStoreDto mattersStoreDto) {
        String mattersNo = mattersStoreDto.getMattersNo();
        if (StringUtil.isBlank(mattersNo)) {
            throw new BusinessException("物料编码不能为空");
        }
        ProductMidPlm midPlm = new ProductMidPlm();
        midPlm.setMidProdNo(mattersNo);
        midPlm.setCompanyId(UserHolder.getCompanyId());
        List<ProductMidPlm> midPlmList = productMidPlmDao.find(midPlm);
        if (CollectionUtils.isNotEmpty(midPlmList)) {
            throw new BusinessException("物料编码与半成品编码重复！");
        }
        ProductStorePlm storePlm = new ProductStorePlm();
        storePlm.setProdNo(mattersNo);
        storePlm.setCompanyId(UserHolder.getCompanyId());
        List<ProductStorePlm> storePlms = productStorePlmDao.find(storePlm);
        if (CollectionUtils.isNotEmpty(storePlms)) {
            throw new BusinessException("物料编码与产品编码重复！");
        }
        //唯一校验
        MattersStoreIos storeIos = new MattersStoreIos();
        storeIos.setMattersNo(mattersStoreDto.getMattersNo());
        List<MattersStoreIos> mattersStoreIosList = this.mattersStoreIosDao.find(storeIos);
        if (mattersStoreIosList != null && mattersStoreIosList.size() > 0) {
            throw new BusinessException("该物料编码已重复，请重新输入");
        }
        mattersStoreDto.setCompanyId(UserHolder.getCompanyId());
        mattersStoreDto.setStatus(String.valueOf(mattersStoreDto.getStatusId()));
        //新增
        this.mattersStoreIosDao.insert(mattersStoreDto);
    }

    @Transactional
    @Override
    public void updateStore(MattersStoreDto mattersStoreIos) {
        if (mattersStoreIos != null) {
            if (mattersStoreIos.getId() != null) {
                //查询选中物料库信息
                MattersStoreIos storeIos = this.mattersStoreIosDao.get(mattersStoreIos.getId());
                //根据编码关联查询
                ProductMidPlm midPlm = new ProductMidPlm();
                midPlm.setMidProdNo(storeIos.getMattersNo());
                List<ProductMidPlm> list = this.productMidPlmDao.find(midPlm);
                //同步数据
                if (list != null && list.size() == 1) {
                    for (ProductMidPlm plm : list) {
                        ProductMidPlm mid = new ProductMidPlm();
                        mid.setId(plm.getId());
                        //规格
                        if (mattersStoreIos.getNorms() != null) {
                            mid.setNorms(mattersStoreIos.getNorms());
                        }
                        //参数
                        if (mattersStoreIos.getParam() != null) {
                            mid.setParam(mattersStoreIos.getParam());
                        }
                        //型号
                        if (mattersStoreIos.getModelDesc() != null) {
                            mid.setModelDesc(mattersStoreIos.getModelDesc());
                        }
                        //名称
                        if (mattersStoreIos.getMattersName() != null) {
                            mid.setMidProdName(mattersStoreIos.getMattersName());
                        }
                        //单位
                        if (mattersStoreIos.getUnit() != null) {
                            mid.setUnit(mattersStoreIos.getUnit());
                        }
                        //修改半成品库
                        this.productMidPlmDao.update(mid);
                    }
                }
                //                mattersStoreIos.setStatus(mattersStoreIos.getStatusId());
                //                mattersStoreIos.setIsMidprod(mattersStoreIos.getIsMidprodId());
                //修改物料库
                mattersStoreIos.setStatus(String.valueOf(mattersStoreIos.getStatusId()));
                this.mattersStoreIosDao.update(mattersStoreIos);
            }
        } else {
            throw new BusinessException("请选择修改对象");
        }
    }

    @Transactional
    @Override
    public void deletedStore(Long[] id) {
        if (id != null) {
            for (Long ids : id) {
                //查询该物料库信息
                MattersStoreIos storeIos = this.mattersStoreIosDao.get(ids);
                //根据编码关联查询
                ProductMidPlm midPlm = new ProductMidPlm();
                midPlm.setMidProdNo(storeIos.getMattersNo());
                List<ProductMidPlm> list = this.productMidPlmDao.find(midPlm);
                //同步删除
                if ("2".equals(storeIos.getIsMidprod())) {
                    if (list != null && list.size() == 1) {
                        for (ProductMidPlm plm : list) {
                            productMidPlmDao.delete(plm.getId());
                        }
                    }
                }
                this.delete(ids);
            }
        }
    }

    @Override
    public DataGrid<MattersStoreDto> select(Params params, Pager pager) {
        return this.mattersStoreIosDao.select(params, pager);
    }

    @Override
    public List<MattersStoreDto> getMatter(MattersStoreIos mattersStoreIos) {
        return mattersStoreIosDao.getMatter(mattersStoreIos);
    }

    @Override
    public List<MattersStoreIos> mattersOption() {
        Map<String, Object> param = new HashMap<>(1);
        param.put("companyId", UserHolder.getCompanyId());
        return mattersStoreIosDao.mattersOption(param);
    }

    @Override
    public List<MattersStoreDto> findMatter() {
        MattersStoreIos mattersStoreIos = new MattersStoreIos();
        mattersStoreIos.setCompanyId(UserHolder.getCompanyId());
        return mattersStoreIosDao.findMatter(mattersStoreIos);
    }
}
