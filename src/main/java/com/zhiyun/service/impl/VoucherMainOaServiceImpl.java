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
import com.zhiyun.dao.CrafworkChangeMainDao;
import com.zhiyun.dao.CrafworkChangeRecordPlmDao;
import com.zhiyun.dao.ProdCrafworkMainPlmDao;
import com.zhiyun.dao.VoucherMainOaDao;
import com.zhiyun.dto.CrafworkChangeRecordPlmDto;
import com.zhiyun.entity.*;
import com.zhiyun.internal.uniqueid.UniqueIdService;
import com.zhiyun.service.ProdCrafworkPathPlmService;
import com.zhiyun.service.VoucherMainOaService;
import com.zhiyun.util.ProcessDto;
import com.zhiyun.util.ResponseStatusConsts;
import com.zhiyun.util.VoucherEnum;
import com.zhiyun.util.WorkFlowStateConsts;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 单据主表Service接口实现类。
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Service("voucherMainOaService")
public class VoucherMainOaServiceImpl extends BaseServiceImpl<VoucherMainOa, Long> implements VoucherMainOaService {

    private final static String UNIQUE_ID_HEAD = "PCWM";
    private final static Long APPROVER_USER_ID = 232526L;
    private final static Long DEFAULT_WORKFLOW_ID = 1L;

    @Resource
    private VoucherMainOaDao voucherMainOaDao;
    @Resource
    private ProdCrafworkMainPlmDao prodCrafworkMainPlmDao;
    @Resource
    private UniqueIdService uniqueIdService;
    @Resource
    private ProdCrafworkPathPlmService prodCrafworkPathPlmService;
    @Resource
    private CrafworkChangeMainDao crafworkChangeMainDao;
    @Resource
    private CrafworkChangeRecordPlmDao crafworkChangeRecordPlmDao;

    @Override
    protected BaseDao<VoucherMainOa, Long> getBaseDao() {
        return this.voucherMainOaDao;
    }

    @Override
    @Transactional
    public void submit(ProdCrafworkMainPlm prodCrafworkMainPlm) {
        String pathNo = prodCrafworkMainPlm.getPathNo();
        if (pathNo == null) {
            throw new BusinessException("工艺路线未生成，不能提交审核");
        }
        VoucherMainOa voucher = voucherMainOaDao.getIsFinished(prodCrafworkMainPlm);
        if (voucher != null) {
            String isFinished = voucher.getIsFinished();
            if (isFinished != null) {
                if (isFinished.equals(VoucherEnum.APPROVAL_STATUS_PROCESS.getId()) || isFinished.equals(VoucherEnum.APPROVAL_STATUS_SUCCESS.getId())) {
                    throw new BusinessException("请勿重复发起审核");
                }
            }
        }
        ProdCrafworkPathPlm path = new ProdCrafworkPathPlm();
        path.setPathNo(pathNo);
        path.setCompanyId(UserHolder.getCompanyId());
        List<ProdCrafworkPathPlm> li = prodCrafworkPathPlmService.find(path);
        if (CollectionUtils.isEmpty(li)) {
            throw new BusinessException("该工艺路线号下不存在工艺信息，不能提交审批");
        }

        //生成单据号
        String voucherNo = uniqueIdService.mixedId(UNIQUE_ID_HEAD, 20, UserHolder.getCompanyId());
        //创建流程
        //        ProcessDto processDto = processService.startProcess(processKey, String.valueOf(UserHolder.getId()));
        //        if (!ResponseStatusConsts.OK.equals(processDto.getStatus())) {
        //            throw new BusinessException("审核流程创建失败！");
        //        }
        //ProcessDto processDto = processService.startProcess(processKey, String.valueOf(UserHolder.getId()));
        ProcessDto processDto = null;
        //插入审核信息
        VoucherMainOa voucherMainOa = new VoucherMainOa();
        voucherMainOa.setVoucherNo(voucherNo);
        voucherMainOa.setIsFinished(VoucherEnum.APPROVAL_STATUS_PROCESS.getId());
        voucherMainOa.setCompanyId(UserHolder.getCompanyId());
        if (processDto != null && ResponseStatusConsts.OK.equals(processDto.getStatus())) {
            //            voucherMainOa.setApproverUserId(Long.valueOf(processDto.getData().getTasks().get(0).getAssignee()));
            voucherMainOa.setWkflowId(Long.valueOf(processDto.getData().getTasks().get(0).getTaskId()));
            //            voucherMainOa.setRaiserUserId(UserHolder.getId());
        }
        //FIX ME
        if (processDto == null) {
            //            voucherMainOa.setApproverUserId(APPROVER_USER_ID);
            voucherMainOa.setWkflowId(DEFAULT_WORKFLOW_ID);
            //            voucherMainOa.setRaiserUserId(UserHolder.getId());
        }
        voucherMainOaDao.insert(voucherMainOa);
        // 工艺路线主表添加单据号
        ProdCrafworkMainPlm main = new ProdCrafworkMainPlm();
        main.setPathNo(pathNo);
        main.setCompanyId(UserHolder.getCompanyId());
        List<ProdCrafworkMainPlm> list = prodCrafworkMainPlmDao.find(main);
        if (CollectionUtils.isNotEmpty(list)) {
            for (ProdCrafworkMainPlm mainPlm : list) {
                ProdCrafworkMainPlm m = new ProdCrafworkMainPlm();
                m.setId(mainPlm.getId());
                m.setVoucherNo(voucherNo);
                prodCrafworkMainPlmDao.update(m);
            }
        } else {
            throw new BusinessException("工艺路线输入错误");
        }
        // 工艺路线添加单据号
        if (CollectionUtils.isNotEmpty(li)) {
            for (ProdCrafworkPathPlm Plm : li) {
                ProdCrafworkPathPlm p = new ProdCrafworkPathPlm();
                p.setId(Plm.getId());
                p.setVoucherNo(voucherNo);
                prodCrafworkPathPlmService.update(p);
            }
        }
    }


    @Override
    @Transactional
    public void audit(CrafworkChangeMain crafworkChangeMain) {
        String pathNo = crafworkChangeMain.getPathNo();
        CrafworkChangeRecordPlmDto change = new CrafworkChangeRecordPlmDto();
        change.setCompanyId(UserHolder.getCompanyId());
        change.setPathNo(pathNo);
        List<CrafworkChangeRecordPlmDto> list = crafworkChangeRecordPlmDao.getRecordDetail(change);
        if (CollectionUtils.isEmpty(list)) {
            throw new BusinessException("审核已提交或您对该产品的工艺路线没有进行任何修改！");
        }
        //生成单据号
        String voucherNo = uniqueIdService.mixedId(UNIQUE_ID_HEAD, 20, UserHolder.getCompanyId());
        //创建流程
        //        ProcessDto processDto = processService.startProcess(processKey, String.valueOf(UserHolder.getId()));
        //        if (!ResponseStatusConsts.OK.equals(processDto.getStatus())) {
        //            throw new BusinessException("审核流程创建失败！");
        //        }
        //ProcessDto processDto = processService.startProcess(processKey, String.valueOf(UserHolder.getId()));
        ProcessDto processDto = null;
        //插入审核信息
        VoucherMainOa voucherMainOa = new VoucherMainOa();
        voucherMainOa.setVoucherNo(voucherNo);
        voucherMainOa.setIsFinished(VoucherEnum.APPROVAL_STATUS_PROCESS.getId());
        voucherMainOa.setCompanyId(UserHolder.getCompanyId());
        if (processDto != null && ResponseStatusConsts.OK.equals(processDto.getStatus())) {
            //            voucherMainOa.setApproverUserId(Long.valueOf(processDto.getData().getTasks().get(0).getAssignee()));
            voucherMainOa.setWkflowId(Long.valueOf(processDto.getData().getTasks().get(0).getTaskId()));
            //            voucherMainOa.setRaiserUserId(UserHolder.getId());
        }
        //FIX ME
        if (processDto == null) {
            //            voucherMainOa.setApproverUserId(APPROVER_USER_ID);
            voucherMainOa.setWkflowId(DEFAULT_WORKFLOW_ID);
            //            voucherMainOa.setRaiserUserId(UserHolder.getId());
        }
        voucherMainOaDao.insert(voucherMainOa);
        // 工艺路线变更申请主表添加单据号
        CrafworkChangeMain main = new CrafworkChangeMain();
        main.setPathNo(pathNo);
        main.setCompanyId(UserHolder.getCompanyId());
        main.setVoucherNo(voucherNo);
        main.setOrgId(UserHolder.getOrgId());
        main.setRaiseUser(UserHolder.getUserName());
        main.setRaiseDate(new Date());
        crafworkChangeMainDao.insert(main);
        // 工艺路线变更申请详情表添加单据号
        if (CollectionUtils.isNotEmpty(list)){
            for (CrafworkChangeRecordPlmDto dto : list) {
                CrafworkChangeRecordPlm plm = new CrafworkChangeRecordPlm();
                plm.setId(dto.getId());
                plm.setVoucherNo(voucherNo);
                crafworkChangeRecordPlmDao.update(plm);
            }
        }
    }

    @Transactional
    @Override
    synchronized public void examine(String voucherNo, boolean isPass) {

        VoucherMainOa voucherMainOa = voucherMainOaDao.getByVoucherNo(voucherNo, UserHolder.getCompanyId());
        if (VoucherEnum.APPROVAL_STATUS_SUCCESS.getId().equals(voucherMainOa.getIsFinished()) || VoucherEnum.APPROVAL_STATUS_FAILURE.getId()
                .equals(voucherMainOa.getIsFinished())) {
            throw new BusinessException("请勿重复审核！");
        }

        //审核走流程
        //        ProcessDto processDto = processService.processTask(String.valueOf(voucherMainOa.getWkflowId()), isPass);
        //        if (!ResponseStatusConsts.OK.equals(processDto.getStatus())) {
        //            throw new BusinessException("审核流程创建失败！");
        //        }
        //ProcessDto processDto = processService.processTask(String.valueOf(voucherMainOa.getWkflowId()), isPass);
        ProcessDto processDto = null;
        //修改审核状态
        voucherMainOa = new VoucherMainOa();
        voucherMainOa.setVoucherNo(voucherNo);
        voucherMainOa.setCompanyId(UserHolder.getCompanyId());

        if (isPass || processDto != null && WorkFlowStateConsts.FINISHED.equals(processDto.getData().getFlowState())) {
            voucherMainOa.setIsFinished(VoucherEnum.APPROVAL_STATUS_SUCCESS.getId());
        } else {
            if (processDto != null && ResponseStatusConsts.OK.equals(processDto.getStatus())) {
                voucherMainOa.setIsFinished(VoucherEnum.APPROVAL_STATUS_FAILURE.getId());
                voucherMainOa.setWkflowId(Long.valueOf(processDto.getData().getTasks().get(0).getTaskId()));
            }
            //FIX ME
            if(processDto == null){
                voucherMainOa.setIsFinished(VoucherEnum.APPROVAL_STATUS_FAILURE.getId());
                voucherMainOa.setWkflowId(DEFAULT_WORKFLOW_ID);
            }
        }

        voucherMainOaDao.updateByVoucherNo(voucherMainOa);
    }
}
