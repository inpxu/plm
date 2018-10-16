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
import com.zhiyun.dao.CarfworkOutputMaterPlmDao;
import com.zhiyun.dao.ProductMidPlmDao;
import com.zhiyun.dao.ProductStorePlmDao;
import com.zhiyun.dto.CarfworkOutputMaterPlmDto;
import com.zhiyun.entity.CarfworkOutputMaterPlm;
import com.zhiyun.entity.ProductMidPlm;
import com.zhiyun.entity.ProductStorePlm;
import com.zhiyun.service.CarfworkOutputMaterPlmService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * 工艺输出半成品Service接口实现类。
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Service("carfworkOutputMaterPlmService")
public class CarfworkOutputMaterPlmServiceImpl extends BaseServiceImpl<CarfworkOutputMaterPlm, Long> implements CarfworkOutputMaterPlmService {

    @Resource
    private CarfworkOutputMaterPlmDao carfworkOutputMaterPlmDao;
    @Resource
    private ProductMidPlmDao productMidPlmDao;
    @Resource
    private ProductStorePlmDao productStorePlmDao;

    @Override
    protected BaseDao<CarfworkOutputMaterPlm, Long> getBaseDao() {
        return this.carfworkOutputMaterPlmDao;
    }

    @Override
    public CarfworkOutputMaterPlmDto findOutput(CarfworkOutputMaterPlm carfworkOutputMaterPlm) {
        CarfworkOutputMaterPlm plm = new CarfworkOutputMaterPlm();
        String prodNo = carfworkOutputMaterPlm.getProdNo();
        String mid = carfworkOutputMaterPlm.getMidProdNo();
        Long craId = carfworkOutputMaterPlm.getCrafworkNo();
        plm.setProdNo(prodNo);
        plm.setMidProdNo(mid);
        plm.setCrafworkNo(craId);
        plm.setCompanyId(UserHolder.getCompanyId());
        List<CarfworkOutputMaterPlm> lt = carfworkOutputMaterPlmDao.find(plm);
        if (CollectionUtils.isNotEmpty(lt)) {
            if (prodNo == null || mid == null) {
                throw new BusinessException("编码不能为空");
            }
            if (prodNo.equals(mid)) {
                return carfworkOutputMaterPlmDao.getProdOut(carfworkOutputMaterPlm);
            } else {
                return carfworkOutputMaterPlmDao.getMidOut(carfworkOutputMaterPlm);
            }
        } else {
            if (prodNo.equals(mid)) {
                CarfworkOutputMaterPlmDto dto = new CarfworkOutputMaterPlmDto();
                ProductStorePlm st = new ProductStorePlm();
                st.setProdNo(prodNo);
                st.setCompanyId(UserHolder.getCompanyId());
                List<ProductStorePlm> store = productStorePlmDao.find(st);
                if (CollectionUtils.isNotEmpty(store)) {
                    ProductStorePlm p = store.get(0);
                    String sy = (p.getParam() + "/" + p.getNorms() + "/" + p.getModelDesc());
                    String prodMsg = (p.getProdNo() + "/" + p.getProdName());
                    dto.setSyntheticField(sy);
                    dto.setMidProdMsg(prodMsg);
                    dto.setProdMsg(prodMsg);
                    dto.setUnit(p.getUnit());
                }
                return dto;
            } else {
                CarfworkOutputMaterPlmDto dto = new CarfworkOutputMaterPlmDto();
                ProductMidPlm st = new ProductMidPlm();
                st.setProdNo(prodNo);
                st.setMidProdNo(mid);
                st.setCompanyId(UserHolder.getCompanyId());
                List<ProductMidPlm> store = productMidPlmDao.find(st);
                if (CollectionUtils.isNotEmpty(store)) {
                    ProductMidPlm p = store.get(0);
                    String sy = (p.getParam() + "/" + p.getNorms() + "/" + p.getModelDesc());
                    String prodMsg = (p.getProdNo() + "/" + p.getProdName());
                    String midMsg = (p.getMidProdNo() + "/" + p.getMidProdName());
                    dto.setSyntheticField(sy);
                    dto.setMidProdMsg(midMsg);
                    dto.setProdMsg(prodMsg);
                    dto.setUnit(p.getUnit());
                }
                return dto;
            }
        }
    }

    @Override
    public CarfworkOutputMaterPlmDto addOutput(CarfworkOutputMaterPlmDto carfworkOutputMaterPlmDto) {
        CarfworkOutputMaterPlm carfworkOutputMaterPlm = new CarfworkOutputMaterPlm();
        BigDecimal outputAmt = carfworkOutputMaterPlmDto.getOutputAmt();
        if (outputAmt == null) {
            throw new BusinessException("输出数量不能为空");
        }
        BeanUtils.copyProperties(carfworkOutputMaterPlmDto, carfworkOutputMaterPlm);
        Long id = carfworkOutputMaterPlmDto.getId();
        if (id == null) {
            carfworkOutputMaterPlm.setOutMidPordNo(carfworkOutputMaterPlm.getMidProdNo());
            carfworkOutputMaterPlmDao.insert(carfworkOutputMaterPlm);
        } else {
            carfworkOutputMaterPlmDao.update(carfworkOutputMaterPlm);
        }
        return carfworkOutputMaterPlmDto;
    }
}
