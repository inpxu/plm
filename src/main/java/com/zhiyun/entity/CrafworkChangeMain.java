/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhiyun.base.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;

/**
 * 工艺路线变更申请主表实体类
 *
 * @author auto
 * @version v1.0
 * @date
 */
public class CrafworkChangeMain extends BaseEntity<Long> {

    private static final long serialVersionUID = 7791208682559287295L;

    // ~~~~实体属性
    // 单据号
    @Pattern(regexp = "[\\s\\S]{0,30}", message = "单据号字段过长")
    private String voucherNo;
    // 申请日期
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date raiseDate;
    // 申请人
    @Pattern(regexp = "[\\s\\S]{0,30}", message = "申请人字段过长")
    private String raiseUser;
    // 变更部门
    @Max(value = 9223372036854775807L, message = "变更部门字段过长")
    private Long orgId;
    // 路线号
    @Pattern(regexp = "[\\s\\S]{0,30}", message = "路线号字段过长")
    private String pathNo;
    // creat_time
    private java.util.Date creatTime;
    // creat_by
    @Pattern(regexp = "[\\s\\S]{0,20}", message = "creat_by字段过长")
    private String creatBy;
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
     * 路线号
     */
    public String getPathNo() {
        return this.pathNo;
    }

    /**
     * 路线号
     */
    public void setPathNo(String pathNo) {
        this.pathNo = pathNo;
    }

    /**
     * creat_time
     */
    public java.util.Date getCreatTime() {
        return this.creatTime;
    }

    /**
     * creat_time
     */
    public void setCreatTime(java.util.Date creatTime) {
        this.creatTime = creatTime;
    }

    /**
     * creat_by
     */
    public String getCreatBy() {
        return this.creatBy;
    }

    /**
     * creat_by
     */
    public void setCreatBy(String creatBy) {
        this.creatBy = creatBy;
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
