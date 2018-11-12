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
import com.zhiyun.dao.*;
import com.zhiyun.dto.CrafworkChangeMainDto;
import com.zhiyun.dto.CrafworkChangeRecordPlmDto;
import com.zhiyun.dto.ProdCrafworkPathPlmDto;
import com.zhiyun.entity.*;
import com.zhiyun.internal.uniqueid.UniqueIdService;
import com.zhiyun.service.ProdCrafworkPathPlmService;
import com.zhiyun.service.VoucherMainOaService;
import com.zhiyun.util.ProcessDto;
import com.zhiyun.util.ResponseStatusConsts;
import com.zhiyun.util.VoucherEnum;
import com.zhiyun.util.WorkFlowStateConsts;
import org.springframework.beans.BeanUtils;
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

    private final static String UNIQUE_ID_HEAD = "PCWM-UN";
    private final static Long APPROVER_USER_ID = 232526L;
    private final static Long DEFAULT_WORKFLOW_ID = 1L;
    private final static String AUDIT_ID_HEAD = "PCWM-AU";

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
    @Resource
    private ProdCrafworkPathHirstoryPlmDao prodCrafworkPathHirstoryPlmDao;
    @Resource
    private CrafworkStructPlmDao crafworkStructPlmDao;

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
        String voucherNo = uniqueIdService.mixedId(UNIQUE_ID_HEAD, 18, UserHolder.getCompanyId());
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
                m.setVersions("1");
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
        if (CollectionUtils.isEmpty(paths)) {
            throw new BusinessException("审核已提交或您对该产品的工艺路线没有进行任何修改！");
        }
        String pathNo = crafworkChangeMainDto.getPathNo();
        CrafworkChangeRecordPlmDto change = new CrafworkChangeRecordPlmDto();
        change.setCompanyId(UserHolder.getCompanyId());
        change.setPathNo(pathNo);
        //        List<CrafworkChangeRecordPlmDto> list = crafworkChangeRecordPlmDao.getRecordDetail(change);
        //        if (CollectionUtils.isEmpty(list)) {
        //            throw new BusinessException("审核已提交或您对该产品的工艺路线没有进行任何修改！");
        //        }
        //生成单据号
        String voucherNo = uniqueIdService.mixedId(AUDIT_ID_HEAD, 18, UserHolder.getCompanyId());
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
                    Long seq = prodCrafworkPathPlm.getCarfSeq();
                    Integer mac = prodCrafworkPathPlm.getMacMinutes();
                    Integer emp = prodCrafworkPathPlm.getEmpMinutes();
                    BigDecimal day = prodCrafworkPathPlm.getDayAmount();
                    sub.setNewValue(seq + "," + mac + "," + emp + "," + day);
                    crafworkChangeRecordPlmDao.insert(sub);
                } else if ("编辑".equals(auditMethod)) {
                    // 判断如果是编辑
                    Long id = prodCrafworkPathPlm.getId();
                    Long crafworkId = prodCrafworkPathPlm.getCrafworkId();
                    String prodNo = prodCrafworkPathPlm.getProdNo();
                    String midProdNo = prodCrafworkPathPlm.getMidProdNo();
                    if (prodNo == null) {
                        ProductMidPlm midPlm = new ProductMidPlm();
                        midPlm.setMidProdNo(midProdNo);
                        midPlm.setCompanyId(UserHolder.getCompanyId());
                        List<ProductMidPlm> list = productMidPlmDao.find(midPlm);
                        if (CollectionUtils.isEmpty(list)) {
                            prodNo = midProdNo;
                        } else {
                            prodNo = list.get(0).getProdNo();
                        }
                    }
                    CrafworkChangeRecordPlm plm = new CrafworkChangeRecordPlm();
                    plm.setCompanyId(UserHolder.getCompanyId());
                    plm.setMidProdNo(midProdNo);
                    plm.setProdNo(prodNo);
                    plm.setCrafworkId(crafworkId);
                    plm.setChangeFlag("新增产品工艺");
                    Long oldSeq = null;
                    Integer oldMac = null;
                    Integer oldEmp = null;
                    BigDecimal oldDay = null;
                    // 判断id是否为空
                    if (id == 0) {
                        // 为空表示是新增的数据
                        String value = crafworkChangeRecordPlmDao.getMes(plm).get(0).getNewValue();
                        String[] values = value.split(",");
                        for (int i = 0; i < values.length; i++) {
                            if (values[i] != null && values[i] != "") {
                                if (i == 0) {
                                    oldSeq = Long.valueOf(values[i]);
                                } else if (i == 1) {
                                    oldMac = Integer.valueOf(values[i]);
                                } else if (i == 2) {
                                    oldEmp = Integer.valueOf(values[i]);
                                } else if (i == 3) {
                                    oldDay = BigDecimal.valueOf(Long.parseLong(values[i]));
                                }
                            }
                        }
                        // 新增数据是否被编辑过
                        plm.setChangeFlag("编辑产品工艺");
                        plm.setChangeItem("设备单耗标准工时");
                        List<CrafworkChangeRecordPlm> list1 = crafworkChangeRecordPlmDao.getMes(plm);
                        if (CollectionUtils.isNotEmpty(list1)) {
                            oldMac = Integer.valueOf(list1.get(0).getNewValue());
                        }
                        plm.setChangeItem("人员单耗标准工时");
                        List<CrafworkChangeRecordPlm> list2 = crafworkChangeRecordPlmDao.getMes(plm);
                        if (CollectionUtils.isNotEmpty(list2)) {
                            oldEmp = Integer.valueOf(list2.get(0).getNewValue());
                        }
                        plm.setChangeItem("每班标准产量");
                        List<CrafworkChangeRecordPlm> list3 = crafworkChangeRecordPlmDao.getMes(plm);
                        if (CollectionUtils.isNotEmpty(list3)) {
                            oldDay = BigDecimal.valueOf(Long.parseLong(list3.get(0).getNewValue()));
                        }
                        plm.setChangeFlag("调整工艺顺序");
                        plm.setChangeItem("工艺顺序");
                        List<CrafworkChangeRecordPlm> list4 = crafworkChangeRecordPlmDao.getMes(plm);
                        if (CollectionUtils.isNotEmpty(list4)) {
                            oldSeq = Long.valueOf(list4.get(0).getNewValue());
                        }
                    } else {
                        // 不为空直接通过id查询
                        ProdCrafworkPathPlm aud = prodCrafworkPathPlmDao.get(id);
                        oldSeq = aud.getCarfSeq();
                        oldMac = aud.getMacMinutes();
                        oldEmp = aud.getEmpMinutes();
                        oldDay = aud.getDayAmount();
                    }
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
                    if (seq != null && !seq.equals(oldSeq)) {
                        sub.setId(null);
                        sub.setOldValue(oldSeq + "");
                        sub.setNewValue(seq + "");
                        sub.setChangeFlag("调整工艺顺序");
                        sub.setChangeItem("工艺顺序");
                        sub.setVoucherNo(voucherNo);
                        crafworkChangeRecordPlmDao.insert(sub);
                    }
                    if (mac != null && !mac.equals(oldMac)) {
                        sub.setId(null);
                        sub.setOldValue(oldMac + "");
                        sub.setNewValue(mac + "");
                        sub.setChangeFlag("编辑产品工艺");
                        sub.setChangeItem("设备单耗标准工时");
                        sub.setVoucherNo(voucherNo);
                        crafworkChangeRecordPlmDao.insert(sub);
                    }
                    if (emp != null && !emp.equals(oldEmp)) {
                        sub.setId(null);
                        sub.setOldValue(oldEmp + "");
                        sub.setNewValue(emp + "");
                        sub.setChangeFlag("编辑产品工艺");
                        sub.setChangeItem("人员单耗标准工时");
                        sub.setVoucherNo(voucherNo);
                        crafworkChangeRecordPlmDao.insert(sub);
                    }
                    if (day != null && !day.equals(oldDay)) {
                        sub.setId(null);
                        sub.setOldValue(oldDay + "");
                        sub.setNewValue(day + "");
                        sub.setChangeFlag("编辑产品工艺");
                        sub.setChangeItem("每班标准产量");
                        sub.setVoucherNo(voucherNo);
                        crafworkChangeRecordPlmDao.insert(sub);
                    }
                } else if ("删除".equals(auditMethod)) {
                    Long crafworkId = prodCrafworkPathPlm.getCrafworkId();
                    String midProdNo = prodCrafworkPathPlm.getMidProdNo();
                    ProdCrafworkPathPlm plm = new ProdCrafworkPathPlm();
                    plm.setCompanyId(UserHolder.getCompanyId());
                    plm.setMidProdNo(midProdNo);
                    plm.setCrafworkId(crafworkId);
                    ProdCrafworkPathPlm aud = prodCrafworkPathPlmDao.find(plm).get(0);
                    String path = aud.getPathNo();
                    ProdCrafworkMainPlm mn = new ProdCrafworkMainPlm();
                    mn.setPathNo(path);
                    mn.setCompanyId(UserHolder.getCompanyId());
                    List<ProdCrafworkMainPlm> list = prodCrafworkMainPlmDao.find(mn);
                    if (CollectionUtils.isNotEmpty(list)) {
                        String prodNo = list.get(0).getProdNo();
                        CrafworkChangeRecordPlm sub = new CrafworkChangeRecordPlm();
                        sub.setProdNo(prodNo);
                        sub.setMidProdNo(midProdNo);
                        sub.setCrafworkId(crafworkId);
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

    }

    @Override
    @Transactional
    public void examine(String voucherNo, boolean isPass) {
        pass(voucherNo, isPass);
        // 添加版本号
        ProdCrafworkMainPlm mainPlm = new ProdCrafworkMainPlm();
        mainPlm.setCompanyId(UserHolder.getCompanyId());
        mainPlm.setVoucherNo(voucherNo);
        mainPlm.setVersions("1");
        prodCrafworkMainPlmDao.update(mainPlm);
    }

    @Override
    @Transactional
    synchronized public void pass(String voucherNo, boolean isPass) {

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

    @Override
    @Transactional
    public void changeExamine(String voucherNo, boolean isPass) {
        CrafworkChangeRecordPlm changeRecordPlm = new CrafworkChangeRecordPlm();
        changeRecordPlm.setVoucherNo(voucherNo);
        changeRecordPlm.setCompanyId(UserHolder.getCompanyId());
        List<CrafworkChangeRecordPlm> list = crafworkChangeRecordPlmDao.find(changeRecordPlm);
        if (CollectionUtils.isEmpty(list)) {
            throw new BusinessException("数据有误，审批未成功！");
        }
        // 产品编码获取路线号
        String prodNo = list.get(0).getProdNo();
        ProdCrafworkMainPlm prodCrafworkMainPlm = new ProdCrafworkMainPlm();
        prodCrafworkMainPlm.setProdNo(prodNo);
        prodCrafworkMainPlm.setCompanyId(UserHolder.getCompanyId());
        List<ProdCrafworkMainPlm> mains = prodCrafworkMainPlmDao.find(prodCrafworkMainPlm);
        if (CollectionUtils.isEmpty(mains)) {
            throw new BusinessException("数据错误，审批未成功！");
        }
        String pathNo = mains.get(0).getPathNo();
        String version = mains.get(0).getVersions();
        // 之前的单据号
        String voucher = mains.get(0).getVoucherNo();
        ProdCrafworkPathPlm prodCrafworkPathPlm = new ProdCrafworkPathPlm();
        prodCrafworkPathPlm.setCompanyId(UserHolder.getCompanyId());
        prodCrafworkPathPlm.setPathNo(pathNo);
        List<ProdCrafworkPathPlm> paths = prodCrafworkPathPlmDao.find(prodCrafworkPathPlm);
        if (CollectionUtils.isEmpty(paths)) {
            throw new BusinessException("数据错误，审批失败！");
        }
        // 把原来的数据添加到历史表中
        for (ProdCrafworkPathPlm path : paths) {
            ProdCrafworkPathHirstoryPlm hirstoryPlm = new ProdCrafworkPathHirstoryPlm();
            BeanUtils.copyProperties(path, hirstoryPlm);
            hirstoryPlm.setVersions(version);
            hirstoryPlm.setId(null);
            prodCrafworkPathHirstoryPlmDao.insert(hirstoryPlm);
        }
        // 对原来的数据遍历更改
        ProdCrafworkPathPlm pathPlm = new ProdCrafworkPathPlm();
        pathPlm.setCompanyId(UserHolder.getCompanyId());
        pathPlm.setPathNo(pathNo);
        for (CrafworkChangeRecordPlm recordPlm : list) {
            String midProdNo = recordPlm.getMidProdNo();
            Long crafWorkId = recordPlm.getCrafworkId();
            String changeFlag = recordPlm.getChangeFlag();
            String changeItem = recordPlm.getChangeItem();
            pathPlm.setMidProdNo(midProdNo);
            pathPlm.setCrafworkId(crafWorkId);
            // 变更新值
            String value = recordPlm.getNewValue();
            if ("新增产品工艺".equals(changeFlag)) {
                String[] values = value.split(",");
                for (int i = 0; i < values.length; i++) {
                    if (values[i] != null && values[i] != "") {
                        if (i == 0) {
                            pathPlm.setCarfSeq(Long.valueOf(values[i]));
                        } else if (i == 1) {
                            pathPlm.setMacMinutes(Integer.valueOf(values[i]));
                        } else if (i == 2) {
                            pathPlm.setEmpMinutes(Integer.valueOf(values[i]));
                        } else if (i == 3) {
                            pathPlm.setDayAmount(BigDecimal.valueOf(Long.parseLong(values[i])));
                        }
                    }
                }
                CrafworkStructPlm structPlm = crafworkStructPlmDao.get(crafWorkId);
                pathPlm.setQuartersEmp(structPlm.getQuartersEmp());
                pathPlm.setUnit(structPlm.getUnit());
                pathPlm.setIsCheck(structPlm.getIsCheck());
                pathPlm.setId(null);
                prodCrafworkPathPlmDao.insert(pathPlm);
            }

            // 获取id
            ProdCrafworkPathPlm palm = new ProdCrafworkPathPlm();
            palm.setCompanyId(UserHolder.getCompanyId());
            palm.setPathNo(pathNo);
            palm.setMidProdNo(midProdNo);
            palm.setCrafworkId(crafWorkId);
            List<ProdCrafworkPathPlm> plmList = prodCrafworkPathPlmDao.find(palm);
            if (CollectionUtils.isEmpty(plmList)) {
                throw new BusinessException("数据有误，审批失败！");
            }

            Long id = plmList.get(0).getId();
            if ("编辑产品工艺".equals(changeFlag)) {
                if ("设备单耗标准工时".equals(changeItem)) {
                    pathPlm.setMacMinutes(Integer.valueOf(value));
                }
                if ("人员单耗标准工时".equals(changeItem)) {
                    pathPlm.setEmpMinutes(Integer.valueOf(value));
                }
                if ("每班标准产量".equals(changeItem)) {
                    pathPlm.setDayAmount(BigDecimal.valueOf(Long.parseLong(value)));
                }
                pathPlm.setId(id);
                prodCrafworkPathPlmDao.update(pathPlm);
            }
            if ("调整工艺顺序".equals(changeFlag) && "工艺顺序".equals(changeItem)) {
                pathPlm.setCarfSeq(Long.valueOf(value));
                pathPlm.setId(id);
                prodCrafworkPathPlmDao.update(pathPlm);
            }
            if ("删除产品工艺".equals(changeFlag)) {
                prodCrafworkPathPlmDao.delete(id);
            }
        }
        // 路线主表更改单据号
        Long mainId = mains.get(0).getId();
        ProdCrafworkMainPlm mainPlm = new ProdCrafworkMainPlm();
        mainPlm.setCompanyId(UserHolder.getCompanyId());
        mainPlm.setVoucherNo(voucherNo);
        mainPlm.setId(mainId);
        mainPlm.setVersions(String.valueOf(Long.valueOf(version) + 1L));
        prodCrafworkMainPlmDao.update(mainPlm);

        // 路线详情表更改单据号
        ProdCrafworkPathPlm plm = new ProdCrafworkPathPlm();
        plm.setCompanyId(UserHolder.getCompanyId());
        plm.setVoucherNo(voucher);
        List<ProdCrafworkPathPlm> prodCrafworkPath = prodCrafworkPathPlmDao.find(plm);
        if (CollectionUtils.isNotEmpty(prodCrafworkPath)) {
            for (ProdCrafworkPathPlm crafworkPathPlm : prodCrafworkPath) {
                plm.setVoucherNo(voucherNo);
                plm.setId(crafworkPathPlm.getId());
                prodCrafworkPathPlmDao.update(plm);
            }
        }
        // 单据号审批
        pass(voucherNo, isPass);
    }
}
