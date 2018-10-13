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
import com.zhiyun.client.UserHolder;
import com.zhiyun.dao.ProdCrafworkMainPlmDao;
import com.zhiyun.dao.ProductMidPlmDao;
import com.zhiyun.dto.ProdCrafworkMainPlmDto;
import com.zhiyun.dto.ProdMidDto;
import com.zhiyun.entity.ProdCrafworkMainPlm;
import com.zhiyun.entity.ProductMidPlm;
import com.zhiyun.service.ProductMidPlmService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 半成品编码Service接口实现类。
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Service("productMidPlmService")
public class ProductMidPlmServiceImpl extends BaseServiceImpl<ProductMidPlm, Long> implements ProductMidPlmService {

    @Resource
    private ProductMidPlmDao productMidPlmDao;
    @Resource
    private ProdCrafworkMainPlmDao prodCrafworkMainPlmDao;

    @Override
    protected BaseDao<ProductMidPlm, Long> getBaseDao() {
        return this.productMidPlmDao;
    }

    @Override
    public DataGrid<ProdMidDto> pageMid(Params params, Pager pager) {
        return productMidPlmDao.pageMid(params, pager);
    }

    @Override
    public List<ProdMidDto> findMid(ProdMidDto productMidPlm) {
        return productMidPlmDao.findMid(productMidPlm);
    }

    @Override
    public List<ProdMidDto> prodDropDown(ProductMidPlm productMidPlm) {
        ProdCrafworkMainPlm prodCrafworkMainPlm = new ProdCrafworkMainPlm();
        prodCrafworkMainPlm.setCompanyId(UserHolder.getCompanyId());
        prodCrafworkMainPlm.setProdNo(productMidPlm.getProdNo());
        prodCrafworkMainPlm.setDeleted("F");
        List<ProdCrafworkMainPlm> plms = prodCrafworkMainPlmDao.find(prodCrafworkMainPlm);
        if (CollectionUtils.isEmpty(plms)) {
            throw new BusinessException("请先输入工艺路线号");
        }
        productMidPlm.setParentNo(productMidPlm.getMidProdNo());
        List<ProdMidDto> list = productMidPlmDao.prodDropDown(productMidPlm);
        for (ProdMidDto dto : list) {
            ProductMidPlm midPlm = new ProductMidPlm();
            midPlm.setParentNo(dto.getMidProdNo());
            midPlm.setCompanyId(UserHolder.getCompanyId());
            midPlm.setDeleted("F");
            List<ProductMidPlm> list1 = productMidPlmDao.find(midPlm);
            // 是否有子节点
            if (CollectionUtils.isEmpty(list1)) {
                dto.setLeaf(true);
            } else {
                dto.setLeaf(false);
            }
            ProdCrafworkMainPlmDto dto1 = new ProdCrafworkMainPlmDto();
            dto1.setProdNo(productMidPlm.getProdNo());
            dto1.setCompanyId(UserHolder.getCompanyId());
            String label = prodCrafworkMainPlmDao.getStatus(dto1).getLabel();
            if (label == null || label == "") {
                dto.setStatus("未发起评审");
            } else {
                dto.setStatus(label);
            }
            dto.setProdMess(dto.getProdNo() + "/" + dto.getProdName());
            dto.setMidMsg(dto.getMidProdNo() + "/" + dto.getMidProdName());
            dto.setNowChoice(dto.getProdName() + "/" + dto.getMidProdName());
        }
        return list;
    }

    @Override
    public List<ProdMidDto> getChild(ProductMidPlm productMidPlm) {
        productMidPlm.setParentNo(productMidPlm.getMidProdNo());
        List<ProdMidDto> list = productMidPlmDao.prodDropDown(productMidPlm);
        return list;
    }

    @Override
    public List<ProductMidPlm> getMidMess(ProductMidPlm productMidPlm) {
        return productMidPlmDao.getMidMess(productMidPlm);
    }

}
