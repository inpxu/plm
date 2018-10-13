/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.entity;

import com.zhiyun.base.entity.BaseEntity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;

/**
 * 工艺路线设置主表实体类
 *
 * @author auto
 * @version v1.0
 * @date
 */
public class ProdCrafworkMainPlm extends BaseEntity<Long> {

    private static final long serialVersionUID = 1124418783922664595L;

    // ~~~~实体属性
    // 单据号
    @Pattern(regexp = "[\\s\\S]{0,30}", message = "单据号字段过长")
    private String voucherNo;
    // 工艺路线号
    @Pattern(regexp = "[\\s\\S]{0,30}", message = "工艺路线号字段过长")
    private String pathNo;
    // 申请人
    @Pattern(regexp = "[\\s\\S]{0,30}", message = "申请人字段过长")
    private String raiseUser;
    // 申请日期
    private java.util.Date raiseDate;
    // 部门id
    @Max(value = 9223372036854775807L, message = "部门id字段过长")
    private Long orgId;
    // 产品编码
    @Pattern(regexp = "[\\s\\S]{0,30}", message = "产品编码字段过长")
    private String prodNo;
    // 状态
    @Pattern(regexp = "[\\s\\S]{0,20}", message = "状态字段过长")
    private String status;
    // 版本号
    @Pattern(regexp = "[\\s\\S]{0,10}", message = "版本号字段过长")
    private String versions;
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
     * 工艺路线号
     */
    public String getPathNo() {
        return this.pathNo;
    }

    /**
     * 工艺路线号
     */
    public void setPathNo(String pathNo) {
        this.pathNo = pathNo;
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
     * 部门id
     */
    public Long getOrgId() {
        return this.orgId;
    }

    /**
     * 部门id
     */
    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    /**
     * 产品编码
     */
    public String getProdNo() {
        return this.prodNo;
    }

    /**
     * 产品编码
     */
    public void setProdNo(String prodNo) {
        this.prodNo = prodNo;
    }

    /**
     * 状态
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * 状态
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 版本号
     */
    public String getVersions() {
        return this.versions;
    }

    /**
     * 版本号
     */
    public void setVersions(String versions) {
        this.versions = versions;
    }

    /**
     * company_id
     */
    public Long getCompanyId() {
        return this.companyId;
    }

    /**
     * company_id
     */
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}
