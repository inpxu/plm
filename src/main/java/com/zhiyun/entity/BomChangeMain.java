/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.entity;

import com.zhiyun.base.entity.BaseEntity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;

/**
 * bom变更申请主表实体类
 *
 * @author auto
 * @version v1.0
 * @date
 */
public class BomChangeMain extends BaseEntity<Long> {

    private static final long serialVersionUID = 5747470465201909673L;

    // ~~~~实体属性
    // 单据号
    @Pattern(regexp = "[\\s\\S]{0,30}", message = "单据号字段过长")
    private String voucherNo;
    // 申请日期
    private java.util.Date raiseDate;
    // 申请人
    @Pattern(regexp = "[\\s\\S]{0,30}", message = "申请人字段过长")
    private String raiseUser;
    // 变更部门
    @Max(value = 9223372036854775807L, message = "变更部门字段过长")
    private Long orgId;
    // bom号
    @Pattern(regexp = "[\\s\\S]{0,30}", message = "bom号字段过长")
    private String bomNo;
    // company_id
    @Max(value = 9223372036854775807L, message = "company_id字段过长")
    private Long companyId;

    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public void setId(Long id) {
        super.setId(id);
    }

    /**
     * 单据号
     */
    public String getVoucherNo() {
        return this.voucherNo;
    }

    /**
     * 单据号
     */
    public void setVoucherNo(String voucherNo) {
        this.voucherNo = voucherNo;
    }

    /**
     * 申请日期
     */
    public java.util.Date getRaiseDate() {
        return this.raiseDate;
    }

    /**
     * 申请日期
     */
    public void setRaiseDate(java.util.Date raiseDate) {
        this.raiseDate = raiseDate;
    }

    /**
     * 申请人
     */
    public String getRaiseUser() {
        return this.raiseUser;
    }

    /**
     * 申请人
     */
    public void setRaiseUser(String raiseUser) {
        this.raiseUser = raiseUser;
    }

    /**
     * 变更部门
     */
    public Long getOrgId() {
        return this.orgId;
    }

    /**
     * 变更部门
     */
    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    /**
     * bom号
     */
    public String getBomNo() {
        return this.bomNo;
    }

    /**
     * bom号
     */
    public void setBomNo(String bomNo) {
        this.bomNo = bomNo;
    }

    /**
     * company_id
     */
    @Override
    public Long getCompanyId() {
        return this.companyId;
    }

    /**
     * company_id
     */
    @Override
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}
