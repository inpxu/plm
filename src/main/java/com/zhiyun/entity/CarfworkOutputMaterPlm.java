/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.entity;

import com.zhiyun.base.entity.BaseEntity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;

/**
 * 工艺输出半成品实体类
 *
 * @author auto
 * @version v1.0
 * @date
 */
public class CarfworkOutputMaterPlm extends BaseEntity<Long> {

    private static final long serialVersionUID = 3539661704076519228L;

    // ~~~~实体属性
    // 产品编码
    @Pattern(regexp = "[\\S]{0,30}", message = "产品编码字段过长")
    private String prodNo;
    // 半成品编码
    @Pattern(regexp = "[\\S]{0,30}", message = "半成品编码字段过长")
    private String midProdNo;
    // 工艺id
    @Max(value = 9223372036854775807L, message = "工艺id字段过长")
    private Long crafworkNo;
    // 输出半成品
    @Pattern(regexp = "[\\S]{0,30}", message = "输出半成品字段过长")
    private String outMidPordNo;
    // 输出数量
    private java.math.BigDecimal outputAmt;
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
     * 工艺id
     */
    public Long getCrafworkNo() {
        return this.crafworkNo;
    }

    /**
     * 工艺id
     */
    public void setCrafworkNo(Long crafworkNo) {
        this.crafworkNo = crafworkNo;
    }

    /**
     * 输出半成品
     */
    public String getOutMidPordNo() {
        return this.outMidPordNo;
    }

    /**
     * 输出半成品
     */
    public void setOutMidPordNo(String outMidPordNo) {
        this.outMidPordNo = outMidPordNo;
    }

    /**
     * 输出数量
     */
    public java.math.BigDecimal getOutputAmt() {
        return this.outputAmt;
    }

    /**
     * 输出数量
     */
    public void setOutputAmt(java.math.BigDecimal outputAmt) {
        this.outputAmt = outputAmt;
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
