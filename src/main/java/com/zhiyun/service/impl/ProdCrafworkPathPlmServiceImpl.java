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
import com.zhiyun.dto.ProdCrafworkMainPlmDto;
import com.zhiyun.dto.ProdCrafworkPathPlmDto;
import com.zhiyun.entity.*;
import com.zhiyun.form.ProdCrafworkPathPlmForm;
import com.zhiyun.service.ProdCrafworkPathPlmService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 产品工艺路线表Service接口实现类。
 *
 * @author auto
 * @version v1.0
 * @date
 */
@Service("prodCrafworkPathPlmService")
public class ProdCrafworkPathPlmServiceImpl extends BaseServiceImpl<ProdCrafworkPathPlm, Long> implements ProdCrafworkPathPlmService {

    @Resource
    private ProdCrafworkPathPlmDao prodCrafworkPathPlmDao;
    @Resource
    private CrafworkStructPlmDao crafworkStructPlmDao;
    @Resource
    private ProdCrafworkMainPlmDao prodCrafworkMainPlmDao;
    @Resource
    private ProdCrafworkParamPlmDao prodCrafworkParamPlmDao;
    @Resource
    private CrafworkInputMaterPlmDao crafworkInputMaterPlmDao;
    @Resource
    private CarfworkOutputMaterPlmDao carfworkOutputMaterPlmDao;
    @Resource
    private ProdMacPlmDao prodMacPlmDao;
    @Resource
    private ProdCrafworkResourceMesDao prodCrafworkResourceMesDao;
    @Resource
    private CrafworkChangeRecordPlmDao crafworkChangeRecordPlmDao;

    @Override
    protected BaseDao<ProdCrafworkPathPlm, Long> getBaseDao() {
        return this.prodCrafworkPathPlmDao;
    }

    @Override
    public List<ProdCrafworkPathPlmDto> crafworkStruct(ProdCrafworkPathPlm prodCrafworkPathPlm) {
        String path = prodCrafworkPathPlm.getPathNo();
        if (path == null || path == "") {
            throw new BusinessException("请先输入工艺路线号");
        }
        List<ProdCrafworkPathPlmDto> list = prodCrafworkPathPlmDao.crafworkStruct(prodCrafworkPathPlm);
        for (ProdCrafworkPathPlmDto dto : list) {
            ProdCrafworkMainPlmDto dto1 = new ProdCrafworkMainPlmDto();
            dto1.setPathNo(path);
            dto1.setCompanyId(UserHolder.getCompanyId());
            String label = prodCrafworkMainPlmDao.getStatus(dto1).getLabel();
            if (label == null || label == "" || "ready".equals(label)) {
                dto.setStatus("未发起评审");
            } else {
                dto.setStatus(label);
            }

        }
        return list;
    }

    @Override
    @Transactional
    public void delStruct(ProdCrafworkPathPlmDto prodCrafworkPathPlmDto) {
        String status = prodCrafworkPathPlmDto.getStatus();
        if ("已通过".equals(status)) {
            throw new BusinessException("工艺路线审核已通过，不能在此处更改！");
        } else if ("审批中".equals(status)) {
            throw new BusinessException("工艺路线审批中，不能更改！");
        }
        Long[] ids = prodCrafworkPathPlmDto.getIds();
        for (Long id : ids) {
            ProdCrafworkPathPlm p = prodCrafworkPathPlmDao.get(id);
            String midProdNo = p.getMidProdNo();
            Long craf = p.getCrafworkId();
            Long companyId = UserHolder.getCompanyId();
            prodCrafworkPathPlmDao.delete(id);
            // 更改工艺顺序
            ProdCrafworkPathPlm pap = new ProdCrafworkPathPlm();
            pap.setCompanyId(UserHolder.getCompanyId());
            pap.setMidProdNo(midProdNo);
            List<ProdCrafworkPathPlmDto> paps = prodCrafworkPathPlmDao.crafworkStruct(pap);
            if (CollectionUtils.isNotEmpty(paps)) {
                Long seq = 0L;
                for (ProdCrafworkPathPlmDto dto : paps) {
                    Long se = seq + 1;
                    Long sid = dto.getId();
                    ProdCrafworkPathPlm prodCrafworkPathPlm = new ProdCrafworkPathPlm();
                    prodCrafworkPathPlm.setId(sid);
                    prodCrafworkPathPlm.setCarfSeq(se);
                    prodCrafworkPathPlm.setCompanyId(UserHolder.getCompanyId());
                    prodCrafworkPathPlmDao.update(prodCrafworkPathPlm);
                    seq = se;
                }
            }
            // 删除工艺参数
            ProdCrafworkParamPlm param = new ProdCrafworkParamPlm();
            param.setCompanyId(companyId);
            param.setCrafworkId(craf);
            param.setMidProdNo(midProdNo);
            List<ProdCrafworkParamPlm> listP = prodCrafworkParamPlmDao.find(param);
            if (CollectionUtils.isNotEmpty(listP)) {
                for (ProdCrafworkParamPlm tp : listP) {
                    prodCrafworkParamPlmDao.delete(tp.getId());
                }
            }
            // 删除物料输入
            CrafworkInputMaterPlm in = new CrafworkInputMaterPlm();
            in.setCompanyId(companyId);
            in.setCrafworkId(craf);
            in.setMidPordNo(midProdNo);
            List<CrafworkInputMaterPlm> lin = crafworkInputMaterPlmDao.find(in);
            if (CollectionUtils.isNotEmpty(lin)) {
                for (CrafworkInputMaterPlm lt : lin) {
                    crafworkInputMaterPlmDao.delete(lt.getId());
                }
            }
            // 删除工艺设备
            ProdMacPlm ma = new ProdMacPlm();
            ma.setCompanyId(companyId);
            ma.setCrafworkId(craf);
            ma.setMidProdNo(midProdNo);
            List<ProdMacPlm> pt = prodMacPlmDao.find(ma);
            if (CollectionUtils.isNotEmpty(pt)) {
                for (ProdMacPlm n : pt) {
                    prodMacPlmDao.delete(n.getId());
                }
            }
            // 删除工艺输出
            CarfworkOutputMaterPlm mp = new CarfworkOutputMaterPlm();
            mp.setCompanyId(companyId);
            mp.setCrafworkNo(craf);
            mp.setMidProdNo(midProdNo);
            List<CarfworkOutputMaterPlm> ou = carfworkOutputMaterPlmDao.find(mp);
            if (CollectionUtils.isNotEmpty(ou)) {
                for (CarfworkOutputMaterPlm o : ou) {
                    carfworkOutputMaterPlmDao.delete(o.getId());
                }
            }
            // 删除工艺资料
            ProdCrafworkResourceMes rm = new ProdCrafworkResourceMes();
            rm.setCompanyId(companyId);
            rm.setCrafworkId(craf);
            rm.setMidProdNo(midProdNo);
            List<ProdCrafworkResourceMes> re = prodCrafworkResourceMesDao.find(rm);
            if (CollectionUtils.isNotEmpty(re)) {
                for (ProdCrafworkResourceMes e : re) {
                    prodCrafworkResourceMesDao.delete(e.getId());
                }
            }
        }
    }

    @Override
    @Transactional
    public List<ProdCrafworkPathPlm> addStruct(ProdCrafworkPathPlmForm prodCrafworkPathPlmForm) {
        List<ProdCrafworkPathPlm> prodCrafworkPathPlms = prodCrafworkPathPlmForm.getProdCrafworkPathPlms();
        String status = prodCrafworkPathPlmForm.getStatus();
        if (CollectionUtils.isEmpty(prodCrafworkPathPlms)) {
            throw new BusinessException("请选择需要添加的工艺！");
        }
        if ("已通过".equals(status)) {
            throw new BusinessException("工艺路线审核已通过，不能在此处更改！");
        } else if ("审批中".equals(status)) {
            throw new BusinessException("工艺路线审批中，不能更改！");
        }
        for (ProdCrafworkPathPlm plm : prodCrafworkPathPlms) {
            Long id = plm.getCrafworkId();
            if (id != null) {
                ProdCrafworkPathPlm prod = new ProdCrafworkPathPlm();
                prod.setCompanyId(UserHolder.getCompanyId());
                prod.setMidProdNo(plm.getMidProdNo());
                List<ProdCrafworkPathPlm> list = prodCrafworkPathPlmDao.find(prod);
                if (CollectionUtils.isNotEmpty(list)) {
                    for (ProdCrafworkPathPlm pathPlm : list) {
                        Long id1 = pathPlm.getCrafworkId();
                        if (id.equals(id1)) {
                            String name = crafworkStructPlmDao.get(id1).getCrafworkName();
                            throw new BusinessException("工艺：" + name + "已添加！");
                        }
                    }
                }
            }
        }
        for (ProdCrafworkPathPlm prodCrafworkPathPlm : prodCrafworkPathPlms) {
            // 获取当前最大的顺序
            Long max = prodCrafworkPathPlmDao.getMaxSeq(prodCrafworkPathPlm.getMidProdNo());
            if (max == null) {
                max = 0L;
            }
            prodCrafworkPathPlm.setCarfSeq(max + 1);
            prodCrafworkPathPlm.setCompanyId(UserHolder.getCompanyId());
            prodCrafworkPathPlm.setId(null);
            prodCrafworkPathPlmDao.insert(prodCrafworkPathPlm);
        }
        return prodCrafworkPathPlms;
    }

    @Override
    @Transactional
    public void upStruct(ProdCrafworkPathPlmForm prodCrafworkPathPlmForm) {
        Long[] ids = prodCrafworkPathPlmForm.getIds();
        String status = prodCrafworkPathPlmForm.getStatus();
        if ("已通过".equals(status)) {
            throw new BusinessException("工艺路线审核已通过，不能在此处更改！");
        } else if ("审批中".equals(status)) {
            throw new BusinessException("工艺路线审批中，不能更改！");
        }
        Long seq = 0L;
        for (Long id : ids) {
            Long seq1 = seq + 1;
            ProdCrafworkPathPlm prodCrafworkPathPlm = new ProdCrafworkPathPlm();
            prodCrafworkPathPlm.setId(id);
            prodCrafworkPathPlm.setCarfSeq(seq1);
            prodCrafworkPathPlm.setCompanyId(UserHolder.getCompanyId());
            prodCrafworkPathPlmDao.update(prodCrafworkPathPlm);
            seq = seq1;
        }
    }

    @Override
    public List<ProdCrafworkPathPlmDto> pathCraf(ProdCrafworkPathPlm prodCrafworkPathPlm) {
        prodCrafworkPathPlm.setCompanyId(UserHolder.getCompanyId());
        List<ProdCrafworkPathPlmDto> list = prodCrafworkPathPlmDao.pathCraf(prodCrafworkPathPlm);
        //        for (ProdCrafworkPathPlmDto dto : list) {
        //            int ischeck = dto.getIsCheck();
        //            if (ischeck == 0) {
        //                dto.setCheck("否");
        //            } else if (ischeck == 1) {
        //                dto.setCheck("是");
        //            }
        //        }
        return list;
    }

    @Override
    @Transactional
    public ProdCrafworkPathPlmDto add(ProdCrafworkPathPlmDto prodCrafworkPathPlm) {
        // 获取当前最大的顺序
        Long max = prodCrafworkPathPlmDao.getMaxSeq(prodCrafworkPathPlm.getMidProdNo());
        if (max == null) {
            max = 0L;
        }
        prodCrafworkPathPlm.setCarfSeq(max + 1);
        prodCrafworkPathPlm.setCompanyId(UserHolder.getCompanyId());
        prodCrafworkPathPlm.setId(null);
        prodCrafworkPathPlmDao.insert(prodCrafworkPathPlm);
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
        crafworkChangeRecordPlmDao.insert(sub);
        return prodCrafworkPathPlm;
    }

    @Override
    @Transactional
    public ProdCrafworkPathPlm upd(ProdCrafworkPathPlmDto prodCrafworkPathPlm) {
        if (prodCrafworkPathPlm == null) {
            throw new BusinessException("修改参数为空！");
        }
        Long id = prodCrafworkPathPlm.getId();
        Long crafworkId = prodCrafworkPathPlm.getCrafworkId();
        String prodNo = prodCrafworkPathPlm.getProdNo();
        String midProdNo = prodCrafworkPathPlm.getMidProdNo();
        ProdCrafworkPathPlm aud = prodCrafworkPathPlmDao.get(id);
        Long oldSeq = aud.getCarfSeq();
        Integer oldMac = aud.getMacMinutes();
        Integer oldEmp = aud.getEmpMinutes();
        BigDecimal oldDay = aud.getDayAmount();
        prodCrafworkPathPlm.setCompanyId(UserHolder.getCompanyId());
        prodCrafworkPathPlmDao.update(prodCrafworkPathPlm);
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
                crafworkChangeRecordPlmDao.insert(sub);
            }
        } else {
            if (!oldMac.equals(mac)) {
                sub.setId(null);
                sub.setOldValue(oldMac + "");
                sub.setNewValue(mac + "");
                sub.setChangeFlag("编辑产品工艺");
                sub.setChangeItem("设备单耗标准工时");
                crafworkChangeRecordPlmDao.insert(sub);
            }
            if (!oldEmp.equals(emp)) {
                sub.setId(null);
                sub.setOldValue(oldEmp + "");
                sub.setNewValue(emp + "");
                sub.setChangeFlag("编辑产品工艺");
                sub.setChangeItem("人员单耗标准工时");
                crafworkChangeRecordPlmDao.insert(sub);
            }
            if (!oldDay.equals(day)) {
                sub.setId(null);
                sub.setOldValue(oldDay + "");
                sub.setNewValue(day + "");
                sub.setChangeFlag("编辑产品工艺");
                sub.setChangeItem("每班标准产量");
                crafworkChangeRecordPlmDao.insert(sub);
            }
        }
        return prodCrafworkPathPlm;
    }

    @Override
    @Transactional
    public List<ProdCrafworkPathPlmDto> del(ProdCrafworkPathPlm prodCrafworkPathPlm) {
        Long id = prodCrafworkPathPlm.getId();
        ProdCrafworkPathPlm aud = prodCrafworkPathPlmDao.get(id);
        Long craf = aud.getCrafworkId();
        String mid = aud.getMidProdNo();
        String path = aud.getPathNo();
        ProdCrafworkMainPlm main = new ProdCrafworkMainPlm();
        main.setPathNo(path);
        main.setCompanyId(UserHolder.getCompanyId());
        List<ProdCrafworkMainPlm> list = prodCrafworkMainPlmDao.find(main);
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
            crafworkChangeRecordPlmDao.insert(sub);
        }
        prodCrafworkPathPlmDao.delete(id);
        ProdCrafworkPathPlm pa = new ProdCrafworkPathPlm();
        pa.setPathNo(path);
        pa.setMidProdNo(mid);
        pa.setCompanyId(UserHolder.getCompanyId());
        List<ProdCrafworkPathPlmDto> lia = prodCrafworkPathPlmDao.pathCraf(pa);
        if (CollectionUtils.isNotEmpty(lia)) {
            Long seq = 0L;
            for (ProdCrafworkPathPlmDto plmAud : lia) {
                Long se = seq + 1;
                Long pId = plmAud.getId();
                ProdCrafworkPathPlm prod = new ProdCrafworkPathPlm();
                prod.setId(pId);
                prod.setCarfSeq(se);
                prod.setCompanyId(UserHolder.getCompanyId());
                prodCrafworkPathPlmDao.update(prod);
                seq = se;
            }
        }
        return lia;
    }

}
