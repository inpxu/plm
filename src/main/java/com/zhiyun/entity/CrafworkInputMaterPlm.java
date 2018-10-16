/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.entity;

import com.zhiyun.base.entity.BaseEntity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;

/**
 * 工艺输入物料实体类
 *
 * @author auto
 * @version v1.0
 * @date
 */
public class CrafworkInputMaterPlm extends BaseEntity<Long> {

    private static final long serialVersionUID = 4588744056971911304L;

    // ~~~~实体属性
    // 产品编码
    @Pattern(regexp = "[\\S]{0,30}", message = "产品编码字段过长")
    private String prodNo;
    // 半成品编码
    @Pattern(regexp = "[\\S]{0,30}", message = "半成品编码字段过长")
    private String midPordNo;
    // 工艺id
    @Max(value = 9223372036854775807L, message = "工艺id字段过长")
    private Long crafworkId;
    // 输入物料
    @Pattern(regexp = "[\\S]{0,30}", message = "输入物料字段过长")
    private String materNo;
    // 输入半成品编码
    @Pattern(regexp = "[\\S]{0,30}", message = "输入半成品编码字段过长")
    private String inProdNo;
    // 输入数量
    private java.math.BigDecimal inputAmt;
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
    public String getMidPordNo() {
        return this.midPordNo;
    }

    /**
     * 半成品编码
     */
    public void setMidPordNo(String midPordNo) {
        this.midPordNo = midPordNo;
    }

    /**
     * 工艺id
     */
    public Long getCrafworkId() {
        return this.crafworkId;
    }

    /**
     * 工艺id
     */
    public void setCrafworkId(Long crafworkId) {
        this.crafworkId = crafworkId;
    }

    /**
     * 输入物料
     */
    public String getMaterNo() {
        return this.materNo;
    }

    /**
     * 输入物料
     */
    public void setMaterNo(String materNo) {
        this.materNo = materNo;
    }

    /**
     * 输入半成品编码
     */
    public String getInProdNo() {
        return this.inProdNo;
    }

    /**
     * 输入半成品编码
     */
    public void setInProdNo(String inProdNo) {
        this.inProdNo = inProdNo;
    }

    /**
     * 输入数量
     */
    public java.math.BigDecimal getInputAmt() {
        return this.inputAmt;
    }

    /**
     * 输入数量
     */
    public void setInputAmt(java.math.BigDecimal inputAmt) {
        this.inputAmt = inputAmt;
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
