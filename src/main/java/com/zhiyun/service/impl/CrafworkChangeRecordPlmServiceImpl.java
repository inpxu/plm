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
import com.zhiyun.dao.CrafworkChangeRecordPlmDao;
import com.zhiyun.dao.ProdCrafworkMainPlmDao;
import com.zhiyun.dto.CrafworkChangeRecordPlmDto;
import com.zhiyun.dto.ProdCrafworkMainPlmDto;
import com.zhiyun.entity.CrafworkChangeRecordPlm;
import com.zhiyun.form.ChangeRecordForm;
import com.zhiyun.service.CrafworkChangeRecordPlmService;
import com.zhiyun.util.VoucherEnum;
import freemarker.cache.StrongCacheStorage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 工艺路线变更申请表Service接口实现类。
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Service("crafworkChangeRecordPlmService")
public class CrafworkChangeRecordPlmServiceImpl extends BaseServiceImpl<CrafworkChangeRecordPlm, Long> implements CrafworkChangeRecordPlmService {

    @Resource
    private CrafworkChangeRecordPlmDao crafworkChangeRecordPlmDao;
    @Resource
    private ProdCrafworkMainPlmDao prodCrafworkMainPlmDao;

    @Override
    protected BaseDao<CrafworkChangeRecordPlm, Long> getBaseDao() {
        return this.crafworkChangeRecordPlmDao;
    }

    @Override
    public ChangeRecordForm getRecord(CrafworkChangeRecordPlmDto crafworkChangeRecordPlmDto) {
        String pathNo = crafworkChangeRecordPlmDto.getPathNo();
        if (pathNo == null){
            throw new BusinessException("该产品尚未添加工艺路线");
        }
        String prodNo = crafworkChangeRecordPlmDto.getProdNo();
        String midProdNo = crafworkChangeRecordPlmDto.getMidProdNo();
        if (prodNo == null || midProdNo == null){
            throw new BusinessException("操作错误！");
        }
        ProdCrafworkMainPlmDto mainPlmDto = prodCrafworkMainPlmDao.getMain(prodNo);
        String isFinished = mainPlmDto.getIsFinished();
        String prodName = mainPlmDto.getProdName();
        String midProdName = mainPlmDto.getMidProdName();
        if (isFinished == null) {
            throw new BusinessException("该产品的工艺路线未发起评审");
        }
        ChangeRecordForm change = new ChangeRecordForm();
        if (prodNo.equals(midProdNo)){
            change.setProdMsg(prodNo +"/"+ prodName);
            change.setNowChoice(prodName);
        } else {
            change.setMidProdMsg(midProdNo +"/"+ midProdName);
            change.setNowChoice(prodName +"/"+ midProdName);
        }
        change.setPathNo(pathNo);
        crafworkChangeRecordPlmDto.setIsFinished(VoucherEnum.APPROVAL_STATUS_SUCCESS.getId());
        crafworkChangeRecordPlmDto.setCompanyId(UserHolder.getCompanyId());
        List<CrafworkChangeRecordPlmDto> list = crafworkChangeRecordPlmDao.getChange(crafworkChangeRecordPlmDto);
        for (CrafworkChangeRecordPlmDto dto : list) {
            String midName = dto.getMidProdName();
            if (midName == null){
                dto.setMidProdMsg(dto.getProdMsg());
            }
        }
        change.setCrafworkChangeRecordPlmDtos(list);
        return change;
    }

    @Override
    public List<CrafworkChangeRecordPlmDto> getDetail(CrafworkChangeRecordPlm crafworkChangeRecordPlm) {
        crafworkChangeRecordPlm.setCompanyId(UserHolder.getCompanyId());
        return crafworkChangeRecordPlmDao.getDetail(crafworkChangeRecordPlm);
    }

    @Override
    public List<CrafworkChangeRecordPlmDto> getRecordDetail(CrafworkChangeRecordPlmDto crafworkChangeRecordPlmDto) {
        crafworkChangeRecordPlmDto.setCompanyId(UserHolder.getCompanyId());
        List<CrafworkChangeRecordPlmDto> list = crafworkChangeRecordPlmDao.getRecordDetail(crafworkChangeRecordPlmDto);
        if (CollectionUtils.isNotEmpty(list)) {
            for (CrafworkChangeRecordPlmDto dto : list) {
                String prod = dto.getProdNo();
                String mid = dto.getMidProdNo();
                if (prod.equals(mid)){
                    dto.setMidProdMsg("");
                }
            }
        }
        return list;
    }
}
