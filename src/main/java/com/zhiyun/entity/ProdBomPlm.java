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
 * 产品物料构成主表实体类
 *
 * @author auto
 * @version v1.0
 * @date
 */
public class ProdBomPlm extends BaseEntity<Long> {

    private static final long serialVersionUID = 2973933986312283504L;

    // ~~~~实体属性
    // 单据号
    @Pattern(regexp = "[\\s\\S]{0,30}", message = "单据号字段过长")
    private String voucherNo;
    // bom编号
    @Pattern(regexp = "[\\s\\S]{0,30}", message = "bom编号字段过长")
    private String bomNo;
    // 产品编码
    @Pattern(regexp = "[\\s\\S]{0,30}", message = "产品编码字段过长")
    private String prodNo;
    // 半成品编码
    @Pattern(regexp = "[\\s\\S]{0,30}", message = "半成品编码字段过长")
    private String midProdNo;
    // 制订日期
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date makeDate;
    // 版本号
    @Pattern(regexp = "[\\s\\S]{0,30}", message = "版本号字段过长")
    private String versions;
    // 制订人
    @Pattern(regexp = "[\\s\\S]{0,30}", message = "制订人字段过长")
    private String makeEmp;
    // 单据状态
    @Pattern(regexp = "[\\s\\S]{0,1}", message = "单据状态字段过长")
    private String status;
    // bom状态
    @Pattern(regexp = "[\\s\\S]{0,10}", message = "bom状态字段过长")
    private String bomStatus;
    // 生效日期
    private java.util.Date startDate;
    // 失效日期
    private java.util.Date loseDate;
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
     * bom编号
     */
    public String getBomNo() {
        return this.bomNo;
    }

    /**
     * bom编号
     */
    public void setBomNo(String bomNo) {
        this.bomNo = bomNo;
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
     * 半成品编码
     */
    public String getMidProdNo() {
        return this.midProdNo;
    }

    /**
     * 半成品编码
     */
    public void setMidProdNo(String midProdNo) {
        this.midProdNo = midProdNo;
    }

    /**
     * 制订日期
     */
    public java.util.Date getMakeDate() {
        return this.makeDate;
    }

    /**
     * 制订日期
     */
    public void setMakeDate(java.util.Date makeDate) {
        this.makeDate = makeDate;
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
     * 制订人
     */
    public String getMakeEmp() {
        return this.makeEmp;
    }

    /**
     * 制订人
     */
    public void setMakeEmp(String makeEmp) {
        this.makeEmp = makeEmp;
    }

    /**
     * 单据状态
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * 单据状态
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * bom状态
     */
    public String getBomStatus() {
        return this.bomStatus;
    }

    /**
     * bom状态
     */
    public void setBomStatus(String bomStatus) {
        this.bomStatus = bomStatus;
    }

    /**
     * 生效日期
     */
    public java.util.Date getStartDate() {
        return this.startDate;
    }

    /**
     * 生效日期
     */
    public void setStartDate(java.util.Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 失效日期
     */
    public java.util.Date getLoseDate() {
        return this.loseDate;
    }

    /**
     * 失效日期
     */
    public void setLoseDate(java.util.Date loseDate) {
        this.loseDate = loseDate;
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
