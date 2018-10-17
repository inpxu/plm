/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.entity;

import com.zhiyun.base.entity.BaseEntity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;

/**
 * 实体类
 *
 * @author auto
 * @version v1.0
 * @date
 */
public class FormulaBomPlm extends BaseEntity<Long> {

    private static final long serialVersionUID = 2831710335270641811L;

    // ~~~~实体属性
    // bom编码
    @Pattern(regexp = "[\\s\\S]{0,30}", message = "bom编码字段过长")
    private String bomNo;
    // 配方编码
    @Pattern(regexp = "[\\s\\S]{0,30}", message = "配方编码字段过长")
    private String formulaNo;
    // 配方名
    @Pattern(regexp = "[\\s\\S]{0,30}", message = "配方名字段过长")
    private String formulaName;
    // 物料编码
    @Pattern(regexp = "[\\s\\S]{0,30}", message = "物料编码字段过长")
    private String matterNo;
    // 数量
    private Double amount;
    // 单位
    @Pattern(regexp = "[\\s\\S]{0,10}", message = "单位字段过长")
    private String unit;
    // 参数
    @Pattern(regexp = "[\\s\\S]{0,60}", message = "参数字段过长")
    private String norms;
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
     * bom编码
     */
    public String getBomNo() {
        return this.bomNo;
    }

    /**
     * bom编码
     */
    public void setBomNo(String bomNo) {
        this.bomNo = bomNo;
    }

    /**
     * 配方编码
     */
    public String getFormulaNo() {
        return this.formulaNo;
    }

    /**
     * 配方编码
     */
    public void setFormulaNo(String formulaNo) {
        this.formulaNo = formulaNo;
    }

    /**
     * 配方名
     */
    public String getFormulaName() {
        return this.formulaName;
    }

    /**
     * 配方名
     */
    public void setFormulaName(String formulaName) {
        this.formulaName = formulaName;
    }

    /**
     * 物料编码
     */
    public String getMatterNo() {
        return this.matterNo;
    }

    /**
     * 物料编码
     */
    public void setMatterNo(String matterNo) {
        this.matterNo = matterNo;
    }

    /**
     * 数量
     */
    public Double getAmount() {
        return this.amount;
    }

    /**
     * 数量
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * 单位
     */
    public String getUnit() {
        return this.unit;
    }

    /**
     * 单位
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * 参数
     */
    public String getNorms() {
        return this.norms;
    }

    /**
     * 参数
     */
    public void setNorms(String norms) {
        this.norms = norms;
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
