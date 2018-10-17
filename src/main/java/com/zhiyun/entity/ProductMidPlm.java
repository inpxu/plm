/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.entity;

import com.zhiyun.base.entity.BaseEntity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;

/**
 * 半成品编码实体类
 *
 * @author auto
 * @version v1.0
 * @date
 */
public class ProductMidPlm extends BaseEntity<Long> {

    private static final long serialVersionUID = 5943246506340824242L;

    // ~~~~实体属性
    // 产品编码
    @Pattern(regexp = "[\\s\\S]{0,30}", message = "产品编码字段过长")
    private String prodNo;
    // 产品名称
    @Pattern(regexp = "[\\s\\S]{0,30}", message = "产品名称字段过长")
    private String prodName;
    // 半成品编码
    @Pattern(regexp = "[\\s\\S]{0,30}", message = "半成品编码字段过长")
    private String midProdNo;
    // 半成品名
    @Pattern(regexp = "[\\s\\S]{0,30}", message = "半成品名字段过长")
    private String midProdName;
    // 上级编码
    @Pattern(regexp = "[\\s\\S]{0,30}", message = "上级编码字段过长")
    private String parentNo;
    // 层级
    @Max(value = 9223372036854775807L, message = "层级字段过长")
    private Long hierarchy;
    // 规格
    @Pattern(regexp = "[\\s\\S]{0,40}", message = "规格字段过长")
    private String norms;
    // 参数
    @Pattern(regexp = "[\\s\\S]{0,400}", message = "参数字段过长")
    private String param;
    // 型号
    @Pattern(regexp = "[\\s\\S]{0,30}", message = "型号字段过长")
    private String modelDesc;
    // 计量单位
    @Pattern(regexp = "[\\s\\S]{0,20}", message = "计量单位字段过长")
    private String unit;
    // 数量
    private Double amount;
    // 仓库id
    @Max(value = 9223372036854775807L, message = "仓库id字段过长")
    private Long storeId;
    // 仓储位置
    @Pattern(regexp = "[\\s\\S]{0,30}", message = "仓储位置字段过长")
    private String rockPosition;
    // 生产场地
    @Max(value = 9223372036854775807L, message = "生产场地字段过长")
    private Long factoryId;
    // 来源
    @Pattern(regexp = "[\\s\\S]{0,20}", message = "来源字段过长")
    private String source;
    // 备注
    @Pattern(regexp = "[\\s\\S]{0,40}", message = "备注字段过长")
    private String remark;
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
     * 产品名称
     */
    public String getProdName() {
        return this.prodName;
    }

    /**
     * 产品名称
     */
    public void setProdName(String prodName) {
        this.prodName = prodName;
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
     * 半成品名
     */
    public String getMidProdName() {
        return this.midProdName;
    }

    /**
     * 半成品名
     */
    public void setMidProdName(String midProdName) {
        this.midProdName = midProdName;
    }

    /**
     * 上级编码
     */
    public String getParentNo() {
        return this.parentNo;
    }

    /**
     * 上级编码
     */
    public void setParentNo(String parentNo) {
        this.parentNo = parentNo;
    }

    /**
     * 层级
     */
    public Long getHierarchy() {
        return this.hierarchy;
    }

    /**
     * 层级
     */
    public void setHierarchy(Long hierarchy) {
        this.hierarchy = hierarchy;
    }

    /**
     * 规格
     */
    public String getNorms() {
        return this.norms;
    }

    /**
     * 规格
     */
    public void setNorms(String norms) {
        this.norms = norms;
    }

    /**
     * 参数
     */
    public String getParam() {
        return this.param;
    }

    /**
     * 参数
     */
    public void setParam(String param) {
        this.param = param;
    }

    /**
     * 型号
     */
    public String getModelDesc() {
        return this.modelDesc;
    }

    /**
     * 型号
     */
    public void setModelDesc(String modelDesc) {
        this.modelDesc = modelDesc;
    }

    /**
     * 计量单位
     */
    public String getUnit() {
        return this.unit;
    }

    /**
     * 计量单位
     */
    public void setUnit(String unit) {
        this.unit = unit;
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
     * 仓库id
     */
    public Long getStoreId() {
        return this.storeId;
    }

    /**
     * 仓库id
     */
    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    /**
     * 仓储位置
     */
    public String getRockPosition() {
        return this.rockPosition;
    }

    /**
     * 仓储位置
     */
    public void setRockPosition(String rockPosition) {
        this.rockPosition = rockPosition;
    }

    /**
     * 生产场地
     */
    public Long getFactoryId() {
        return this.factoryId;
    }

    /**
     * 生产场地
     */
    public void setFactoryId(Long factoryId) {
        this.factoryId = factoryId;
    }

    /**
     * 来源
     */
    public String getSource() {
        return this.source;
    }

    /**
     * 来源
     */
    public void setSource(String source) {
        this.source = source;
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
}
