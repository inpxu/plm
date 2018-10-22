/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.service.impl;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.zhiyun.base.dao.BaseDao;
import com.zhiyun.base.exception.BusinessException;
import com.zhiyun.base.service.BaseServiceImpl;
import com.zhiyun.base.util.BeanCopyUtil;
import com.zhiyun.client.UserHolder;
import com.zhiyun.dao.*;
import com.zhiyun.dto.CrafworkChangeMainDto;
import com.zhiyun.dto.CrafworkChangeRecordPlmDto;
import com.zhiyun.dto.ProdCrafworkParamPlmDto;
import com.zhiyun.dto.ProdCrafworkPathPlmDto;
import com.zhiyun.entity.*;
import com.zhiyun.internal.uniqueid.UniqueIdService;
import com.zhiyun.service.ProdCrafworkPathPlmService;
import com.zhiyun.service.VoucherMainOaService;
import com.zhiyun.util.ProcessDto;
import com.zhiyun.util.ResponseStatusConsts;
import com.zhiyun.util.VoucherEnum;
import com.zhiyun.util.WorkFlowStateConsts;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
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
    @Resource
    private ProdCrafworkPathPlmDao prodCrafworkPathPlmDao;
    @Resource
    private ProductMidPlmDao productMidPlmDao;

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
                m.setStatus("ready");
                m.setVersions("1.0");
                prodCrafworkMainPlmDao.update(m);
            }
        } else {
            throw new BusinessException("工艺路线输入错误");
        }
        // 工艺路线添加单据号
        if (CollectionUtils.isNotEmpty(li)) {
            for (ProdCrafworkPathPlm plm : li) {
                ProdCrafworkPathPlm p = new ProdCrafworkPathPlm();
                p.setId(plm.getId());
                p.setVoucherNo(voucherNo);
                prodCrafworkPathPlmService.update(p);
            }
        }
    }

    @Override
    @Transactional
    public void audit(CrafworkChangeMainDto crafworkChangeMainDto) {
        List<ProdCrafworkPathPlmDto> paths = crafworkChangeMainDto.getProdCrafworkPathPlmDtos();
        List<ProdCrafworkParamPlmDto> prodCrafworkParamPlmDtos = crafworkChangeMainDto.getProdCrafworkParamPlmDtos();
        if (CollectionUtils.isEmpty(paths) && CollectionUtils.isEmpty(prodCrafworkParamPlmDtos)) {
            throw new BusinessException("审核已提交或您对该产品的工艺路线没有进行任何修改！");
        }
        String pathNo = paths.get(0).getPathNo();
        CrafworkChangeRecordPlmDto change = new CrafworkChangeRecordPlmDto();
        change.setCompanyId(UserHolder.getCompanyId());
        change.setPathNo(pathNo);
//        List<CrafworkChangeRecordPlmDto> list = crafworkChangeRecordPlmDao.getRecordDetail(change);
//        if (CollectionUtils.isEmpty(list)) {
//            throw new BusinessException("审核已提交或您对该产品的工艺路线没有进行任何修改！");
//        }
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
//        if (CollectionUtils.isNotEmpty(list)) {
//            for (CrafworkChangeRecordPlmDto dto : list) {
//                CrafworkChangeRecordPlm plm = new CrafworkChangeRecordPlm();
//                plm.setId(dto.getId());
//                plm.setVoucherNo(voucherNo);
//                crafworkChangeRecordPlmDao.update(plm);
//            }
//        }
        if (CollectionUtils.isNotEmpty(paths)) {
            for (ProdCrafworkPathPlmDto prodCrafworkPathPlm : paths) {
                // 获取调用方法的字段
                String auditMethod = prodCrafworkPathPlm.getAuditMethod();
                if ("新增".equals(auditMethod)) {
                    // 判断如果是新增
                    String prodNo = prodCrafworkPathPlm.getProdNo();
                    String midProdNo = prodCrafworkPathPlm.getMidProdNo();
                    Long crafworkId = prodCrafworkPathPlm.getCrafworkId();
                    CrafworkChangeRecordPlm sub = new CrafworkChangeRecordPlm();
                    sub.setProdNo(prodNo);
                    sub.setMidProdNo(midProdNo);
                    sub.setCrafworkId(crafworkId);
                    sub.setCompanyId(UserHolder.getCompanyId());
                    sub.setChangeEmp(UserHolder.getUserName());
                    sub.setUpdDate(new Date());
                    sub.setChangeFlag("新增产品工艺");
                    sub.setVoucherNo(voucherNo);
                    crafworkChangeRecordPlmDao.insert(sub);
                } else if ("编辑".equals(auditMethod)) {
                    // 判断如果是编辑
                    Long id = prodCrafworkPathPlm.getId();
                    Long crafworkId = prodCrafworkPathPlm.getCrafworkId();
                    String prodNo = prodCrafworkPathPlm.getProdNo();
                    String midProdNo = prodCrafworkPathPlm.getMidProdNo();
                    ProdCrafworkPathPlm aud = prodCrafworkPathPlmDao.get(id);
                    Long oldSeq = aud.getCarfSeq();
                    Integer oldMac = aud.getMacMinutes();
                    Integer oldEmp = aud.getEmpMinutes();
                    BigDecimal oldDay = aud.getDayAmount();
                    Long seq = prodCrafworkPathPlm.getCarfSeq();
                    Integer mac = prodCrafworkPathPlm.getMacMinutes();
                    Integer emp = prodCrafworkPathPlm.getEmpMinutes();
                    BigDecimal day = prodCrafworkPathPlm.getDayAmount();
                    CrafworkChangeRecordPlm sub = new CrafworkChangeRecordPlm();
                    sub.setProdNo(prodNo);
                    sub.setMidProdNo(midProdNo);
                    sub.setCrafworkId(crafworkId);
                    sub.setCompanyId(UserHolder.getCompanyId());
                    sub.setChangeEmp(UserHolder.getUserName());
                    sub.setUpdDate(new Date());
                    if (mac == null && emp == null && day == null) {
                        if (!oldSeq.equals(seq)) {
                            sub.setId(null);
                            sub.setOldValue(oldSeq + "");
                            sub.setNewValue(seq + "");
                            sub.setChangeFlag("调整工艺顺序");
                            sub.setChangeItem("工艺顺序");
                            sub.setVoucherNo(voucherNo);
                            crafworkChangeRecordPlmDao.insert(sub);
                        }
                    } else {
                        if (!oldMac.equals(mac)) {
                            sub.setId(null);
                            sub.setOldValue(oldMac + "");
                            sub.setNewValue(mac + "");
                            sub.setChangeFlag("编辑产品工艺");
                            sub.setChangeItem("设备单耗标准工时");
                            sub.setVoucherNo(voucherNo);
                            crafworkChangeRecordPlmDao.insert(sub);
                        }
                        if (!oldEmp.equals(emp)) {
                            sub.setId(null);
                            sub.setOldValue(oldEmp + "");
                            sub.setNewValue(emp + "");
                            sub.setChangeFlag("编辑产品工艺");
                            sub.setChangeItem("人员单耗标准工时");
                            sub.setVoucherNo(voucherNo);
                            crafworkChangeRecordPlmDao.insert(sub);
                        }
                        if (!oldDay.equals(day)) {
                            sub.setId(null);
                            sub.setOldValue(oldDay + "");
                            sub.setNewValue(day + "");
                            sub.setChangeFlag("编辑产品工艺");
                            sub.setChangeItem("每班标准产量");
                            sub.setVoucherNo(voucherNo);
                            crafworkChangeRecordPlmDao.insert(sub);
                        }
                    }
                } else if ("删除".equals(auditMethod)) {
                    Long id = prodCrafworkPathPlm.getId();
                    ProdCrafworkPathPlm aud = prodCrafworkPathPlmDao.get(id);
                    Long craf = aud.getCrafworkId();
                    String mid = aud.getMidProdNo();
                    String path = aud.getPathNo();
                    ProdCrafworkMainPlm mn = new ProdCrafworkMainPlm();
                    mn.setPathNo(path);
                    mn.setCompanyId(UserHolder.getCompanyId());
                    List<ProdCrafworkMainPlm> list = prodCrafworkMainPlmDao.find(mn);
                    if (CollectionUtils.isNotEmpty(list)) {
                        String prodNo = list.get(0).getProdNo();
                        CrafworkChangeRecordPlm sub = new CrafworkChangeRecordPlm();
                        sub.setProdNo(prodNo);
                        sub.setMidProdNo(mid);
                        sub.setCrafworkId(craf);
                        sub.setCompanyId(UserHolder.getCompanyId());
                        sub.setChangeEmp(UserHolder.getUserName());
                        sub.setUpdDate(new Date());
                        sub.setChangeFlag("删除产品工艺");
                        sub.setVoucherNo(voucherNo);
                        crafworkChangeRecordPlmDao.insert(sub);
                    }
                }
            }
        }

        // 调整工艺顺序
        if (CollectionUtils.isNotEmpty(prodCrafworkParamPlmDtos)){
            for (ProdCrafworkParamPlmDto plmDto : prodCrafworkParamPlmDtos) {
                ProdCrafworkPathPlmDto[] crafworkPathDtos = plmDto.getCrafworkPathDtos();
                    Long afterId = crafworkPathDtos[0].getId();
                    Long uqe1 = crafworkPathDtos[0].getCarfSeq();
                    ProdCrafworkPathPlm after = prodCrafworkPathPlmDao.get(afterId);
                    Long beforeId = crafworkPathDtos[1].getId();
                    Long uqe2 = crafworkPathDtos[1].getCarfSeq();
                    ProdCrafworkPathPlm before = prodCrafworkPathPlmDao.get(beforeId);
//                    after.setId(beforeId);
//                    before.setId(afterId);
//                    prodCrafworkPathPlmDao.update(BeanCopyUtil.copy(after, ProdCrafworkPathPlmDto.class));
//                    prodCrafworkPathPlmDao.update(BeanCopyUtil.copy(before, ProdCrafworkPathPlmDto.class));
                    CrafworkChangeRecordPlm sub = new CrafworkChangeRecordPlm();
                    ProductMidPlm mid = new ProductMidPlm();
                    mid.setMidProdNo(after.getMidProdNo());
                    mid.setCompanyId(UserHolder.getCompanyId());
                    List<ProductMidPlm> midList = productMidPlmDao.find(mid);
                    if (CollectionUtils.isNotEmpty(midList)) {
                        String prodNo = midList.get(0).getProdNo();
                        sub.setProdNo(prodNo);
                    }
                    sub.setMidProdNo(after.getMidProdNo());
                    sub.setCompanyId(UserHolder.getCompanyId());
                    sub.setChangeEmp(UserHolder.getUserName());
                    sub.setUpdDate(new Date());
                    if (!uqe1.equals(uqe2)) {
                        sub.setCrafworkId(after.getCrafworkId());
                        sub.setId(null);
                        sub.setOldValue(uqe1 + "");
                        sub.setNewValue(uqe2 + "");
                        sub.setChangeFlag("调整工艺顺序");
                        sub.setChangeItem("工艺顺序");
                        sub.setVoucherNo(voucherNo);
                        crafworkChangeRecordPlmDao.insert(sub);
                        sub.setCrafworkId(before.getCrafworkId());
                        sub.setId(null);
                        sub.setOldValue(uqe2 + "");
                        sub.setNewValue(uqe1 + "");
                        sub.setChangeFlag("调整工艺顺序");
                        sub.setChangeItem("工艺顺序");
                        sub.setVoucherNo(voucherNo);
                        crafworkChangeRecordPlmDao.insert(sub);
                    }
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
            if (processDto == null) {
                voucherMainOa.setIsFinished(VoucherEnum.APPROVAL_STATUS_FAILURE.getId());
                voucherMainOa.setWkflowId(DEFAULT_WORKFLOW_ID);
            }
        }

        voucherMainOaDao.updateByVoucherNo(voucherMainOa);
    }
}
