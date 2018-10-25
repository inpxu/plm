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
import com.zhiyun.dao.MattersStoreIosDao;
import com.zhiyun.dao.ProdCrafworkMainPlmDao;
import com.zhiyun.dao.ProductMidPlmDao;
import com.zhiyun.dao.ProductStorePlmDao;
import com.zhiyun.dto.ProdCrafworkMainPlmDto;
import com.zhiyun.dto.ProductStorePlmDto;
import com.zhiyun.entity.MattersStoreIos;
import com.zhiyun.entity.ProductMidPlm;
import com.zhiyun.entity.ProductStorePlm;
import com.zhiyun.service.ProductStorePlmService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 产品库Service接口实现类。
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Service("productStorePlmService")
public class ProductStorePlmServiceImpl extends BaseServiceImpl<ProductStorePlm, Long> implements ProductStorePlmService {

    @Resource
    private ProductStorePlmDao productStorePlmDao;
    @Resource
    private ProductMidPlmDao productMidPlmDao;
    @Resource
    private ProdCrafworkMainPlmDao prodCrafworkMainPlmDao;
    @Resource
    private MattersStoreIosDao mattersStoreIosDao;

    @Override
    protected BaseDao<ProductStorePlm, Long> getBaseDao() {
        return this.productStorePlmDao;
    }

    @Override
    public DataGrid<ProductStorePlmDto> pg(Params params, Pager pager) {
        return productStorePlmDao.pg(params, pager);
    }

    @Override
    public void add(ProductStorePlm productStorePlm) {
        String prodNo = productStorePlm.getProdNo();
        String prodName = productStorePlm.getProdName();
        MattersStoreIos ios = new MattersStoreIos();
        ios.setMattersNo(prodNo);
        ios.setCompanyId(UserHolder.getCompanyId());
        List<MattersStoreIos> storeIosList = mattersStoreIosDao.find(ios);
        if (CollectionUtils.isNotEmpty(storeIosList)) {
            throw new BusinessException("产品编码与物料编码重复！");
        }
        ProductStorePlm p = new ProductStorePlm();
        p.setProdNo(prodNo);
        p.setCompanyId(UserHolder.getCompanyId());
        p.setDeleted("F");
        List<ProductStorePlm> list = productStorePlmDao.find(p);
        if (CollectionUtils.isNotEmpty(list)) {
            throw new BusinessException("产品编码已存在");
        }
        ProductStorePlm ps = new ProductStorePlm();
        ps.setProdName(prodName);
        ps.setCompanyId(UserHolder.getCompanyId());
        ps.setDeleted("F");
        List<ProductStorePlm> list1 = productStorePlmDao.find(ps);
        if (CollectionUtils.isNotEmpty(list1)) {
            throw new BusinessException("产品名称已存在");
        }
        productStorePlmDao.insert(productStorePlm);
    }

    @Override
    public void upd(ProductStorePlm productStorePlm) {
        String prodNo = productStorePlm.getProdNo();
        String prodName = productStorePlm.getProdName();
        ProductStorePlm storePlm = productStorePlmDao.get(productStorePlm.getId());
        String pn = storePlm.getProdNo();
        String pm = storePlm.getProdName();

        ProductMidPlm plm = new ProductMidPlm();
        if (prodNo != null && !prodNo.equals(pn)) {
            plm.setProdNo(pn);
            plm.setCompanyId(UserHolder.getCompanyId());
            plm.setDeleted("F");
            List<ProductMidPlm> li = productMidPlmDao.find(plm);
            if (CollectionUtils.isNotEmpty(li)) {
                throw new BusinessException("产品已被使用，不能修改产品编码！");
            }
            ProductStorePlm p = new ProductStorePlm();
            p.setProdNo(prodNo);
            p.setCompanyId(UserHolder.getCompanyId());
            p.setDeleted("F");
            List<ProductStorePlm> list = productStorePlmDao.find(p);
            if (CollectionUtils.isNotEmpty(list)) {
                throw new BusinessException("产品编码已存在");
            }
        }

        if (prodName != null && !prodName.equals(pm)) {
            plm.setProdName(pm);
            plm.setCompanyId(UserHolder.getCompanyId());
            plm.setDeleted("F");
            List<ProductMidPlm> li = productMidPlmDao.find(plm);
            if (CollectionUtils.isNotEmpty(li)) {
                throw new BusinessException("产品已被使用，不能修改产品名称！");
            }
            ProductStorePlm ps = new ProductStorePlm();
            ps.setProdName(prodName);
            ps.setCompanyId(UserHolder.getCompanyId());
            ps.setDeleted("F");
            List<ProductStorePlm> list1 = productStorePlmDao.find(ps);
            if (CollectionUtils.isNotEmpty(list1)) {
                throw new BusinessException("产品名称已存在");
            }
        }
        productStorePlmDao.update(productStorePlm);
    }

    @Override
    public void del(Long[] ids) {
        for (Long id : ids) {
            ProductMidPlm plm = new ProductMidPlm();
            plm.setProdNo(productStorePlmDao.get(id).getProdNo());
            plm.setCompanyId(UserHolder.getCompanyId());
            plm.setDeleted("F");
            List<ProductMidPlm> list = productMidPlmDao.find(plm);
            if (CollectionUtils.isNotEmpty(list) || list.size() > 0) {
                throw new BusinessException("产品已被使用，不能删除！");
            }
            productStorePlmDao.delete(id);
        }
    }

    @Override
    public ProductStorePlmDto getById(ProductStorePlm productStorePlm) {
        productStorePlm.setCompanyId(UserHolder.getCompanyId());
        return productStorePlmDao.getById(productStorePlm);
    }

    @Override
    public List<ProductStorePlmDto> prodDrop(ProductStorePlm productStorePlm) {
        List<ProductStorePlmDto> list = productStorePlmDao.prodDrop(productStorePlm);
        for (ProductStorePlmDto prod : list) {
            prod.setMidProdNo(prod.getProdNo());
            prod.setProdMess(prod.getProdNo() + "/" + prod.getProdName());
            prod.setMidMsg(prod.getProdNo() + "/" + prod.getProdName());
            prod.setNowChoice(prod.getProdName());
            ProductMidPlm productMidPlm = new ProductMidPlm();
            productMidPlm.setCompanyId(UserHolder.getCompanyId());
            productMidPlm.setProdNo(prod.getProdNo());
            productMidPlm.setDeleted("F");
            List<ProductMidPlm> li = productMidPlmDao.find(productMidPlm);
            if (CollectionUtils.isEmpty(li)) {
                prod.setLeaf(true);
            } else {
                prod.setLeaf(false);
            }
            ProdCrafworkMainPlmDto dto = new ProdCrafworkMainPlmDto();
            dto.setProdNo(prod.getProdNo());
            dto.setCompanyId(UserHolder.getCompanyId());
            String label = prodCrafworkMainPlmDao.getStatus(dto).getLabel();
            if (label == null || "".equals(label) || "ready".equals(label)) {
                prod.setStatus("未发起评审");
            } else {
                prod.setStatus(label);
            }
            String v = prod.getVersions();
            if (v == null || "".equals(v)) {
                prod.setVersions("未发布");
            }
        }
        return list;
    }

    @Override
    public List<ProductStorePlmDto> optionProduct() {
        Map<String, Object> param = new HashMap<>(1);
        param.put("companyId", UserHolder.getCompanyId());
        return productStorePlmDao.optionProduct(param);
    }

}
