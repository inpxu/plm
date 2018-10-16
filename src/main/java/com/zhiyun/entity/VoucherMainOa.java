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
 * 单据主表实体类
 *
 * @author auto
 * @version v1.0
 * @date
 */
public class VoucherMainOa extends BaseEntity<Long> {

    private static final long serialVersionUID = 4603097012576107728L;

    // ~~~~实体属性
    // 单据号
    @Pattern(regexp = "[\\s\\S]{0,30}", message = "单据号字段过长")
    private String voucherNo;
    // 提交人
    @Pattern(regexp = "[\\s\\S]{0,30}", message = "提交人字段过长")
    private String raiser;
    // 提交日期
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date raiseDate;
    // 希望日期
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date hopeDate;
    // 事件描述
    @Pattern(regexp = "[\\s\\S]{0,100}", message = "事件描述字段过长")
    private String eventDesc;
    // 单据类型
    @Pattern(regexp = "[\\s\\S]{0,8}", message = "单据类型字段过长")
    private String voucherType;
    // 审批流程id
    @Max(value = 9223372036854775807L, message = "审批流程id字段过长")
    private Long wkflowId;
    // 意见
    @Pattern(regexp = "[\\s\\S]{0,200}", message = "意见字段过长")
    private String checkAdvice;
    // 审批流
    @Pattern(regexp = "[\\s\\S]{0,100}", message = "审批流字段过长")
    private String checks;
    // 单据状态
    @Pattern(regexp = "[\\s\\S]{0,1}", message = "单据状态字段过长")
    private String isFinished;
    // 备注
    @Pattern(regexp = "[\\s\\S]{0,100}", message = "备注字段过长")
    private String remark;
    // company_id
    @Max(value = 9223372036854775807L, message = "company_id字段过长")
    private Long companyId;
    // creat_time
    private java.util.Date creatTime;
    // creat_by
    @Pattern(regexp = "[\\s\\S]{0,20}", message = "creat_by字段过长")
    private String creatBy;

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
     * 提交人
     */
    public String getRaiser() {
        return this.raiser;
    }

    /**
     * 提交人
     */
    public void setRaiser(String raiser) {
        this.raiser = raiser;
    }

    /**
     * 提交日期
     */
    public java.util.Date getRaiseDate() {
        return this.raiseDate;
    }

    /**
     * 提交日期
     */
    public void setRaiseDate(java.util.Date raiseDate) {
        this.raiseDate = raiseDate;
    }

    /**
     * 希望日期
     */
    public java.util.Date getHopeDate() {
        return this.hopeDate;
    }

    /**
     * 希望日期
     */
    public void setHopeDate(java.util.Date hopeDate) {
        this.hopeDate = hopeDate;
    }

    /**
     * 事件描述
     */
    public String getEventDesc() {
        return this.eventDesc;
    }

    /**
     * 事件描述
     */
    public void setEventDesc(String eventDesc) {
        this.eventDesc = eventDesc;
    }

    /**
     * 单据类型
     */
    public String getVoucherType() {
        return this.voucherType;
    }

    /**
     * 单据类型
     */
    public void setVoucherType(String voucherType) {
        this.voucherType = voucherType;
    }

    /**
     * 审批流程id
     */
    public Long getWkflowId() {
        return this.wkflowId;
    }

    /**
     * 审批流程id
     */
    public void setWkflowId(Long wkflowId) {
        this.wkflowId = wkflowId;
    }

    /**
     * 意见
     */
    public String getCheckAdvice() {
        return this.checkAdvice;
    }

    /**
     * 意见
     */
    public void setCheckAdvice(String checkAdvice) {
        this.checkAdvice = checkAdvice;
    }

    /**
     * 审批流
     */
    public String getChecks() {
        return this.checks;
    }

    /**
     * 审批流
     */
    public void setChecks(String checks) {
        this.checks = checks;
    }

    /**
     * 单据状态
     */
    public String getIsFinished() {
        return this.isFinished;
    }

    /**
     * 单据状态
     */
    public void setIsFinished(String isFinished) {
        this.isFinished = isFinished;
    }

    /**
     * 备注
     */
    public String getRemark() {
        return this.remark;
    }

    /**
     * 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
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
}
