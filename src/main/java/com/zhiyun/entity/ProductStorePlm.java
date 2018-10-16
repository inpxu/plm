/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.entity;

import com.zhiyun.base.entity.BaseEntity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;

/**
 * 产品库实体类
 *
 * @author auto
 * @version v1.0
 * @date
 */
public class ProductStorePlm extends BaseEntity<Long> {

    private static final long serialVersionUID = 3255885144690672734L;

    // ~~~~实体属性
    // 产品编码
    @Pattern(regexp = "[\\S]{0,30}", message = "产品编码字段过长")
    private String prodNo;
    // 产品名称
    @Pattern(regexp = "[\\S]{0,30}", message = "产品名称字段过长")
    private String prodName;
    // 规格
    @Pattern(regexp = "[\\S]{0,40}", message = "规格字段过长")
    private String norms;
    // 参数
    @Pattern(regexp = "[\\S]{0,400}", message = "参数字段过长")
    private String param;
    // 型号
    @Pattern(regexp = "[\\S]{0,30}", message = "型号字段过长")
    private String modelDesc;
    // 所属分类
    @Max(value = 9223372036854775807L, message = "所属分类字段过长")
    private Long typeId;
    // 计量单位
    @Pattern(regexp = "[\\S]{0,20}", message = "计量单位字段过长")
    private String unit;
    // 许可号
    @Pattern(regexp = "[\\S]{0,30}", message = "许可号字段过长")
    private String allowNo;
    // 专利号
    @Pattern(regexp = "[\\S]{0,30}", message = "专利号字段过长")
    private String patentNo;
    // 发行号
    @Pattern(regexp = "[\\S]{0,30}", message = "发行号字段过长")
    private String sellNo;
    // 版本号
    @Pattern(regexp = "[\\S]{0,20}", message = "版本号字段过长")
    private String versionNo;
    // 等级
    @Pattern(regexp = "[\\S]{0,20}", message = "等级字段过长")
    private String level;
    // 材质
    @Pattern(regexp = "[\\S]{0,20}", message = "材质字段过长")
    private String material;
    // 保质期限
    @Pattern(regexp = "[\\S]{0,20}", message = "保质期限字段过长")
    private String qualtyTime;
    // 产品状态
    @Pattern(regexp = "[\\S]{0,20}", message = "产品状态字段过长")
    private String prodStatus;
    // 仓储位置
    @Pattern(regexp = "[\\S]{0,30}", message = "仓储位置字段过长")
    private String rockPosition;
    // 仓库id
    @Max(value = 9223372036854775807L, message = "仓库id字段过长")
    private Long storeId;
    // 当前库存量
    private java.math.BigDecimal curAmount;
    // 生产场地
    @Max(value = 9223372036854775807L, message = "生产场地字段过长")
    private Long factoryId;
    // 商品编码
    @Pattern(regexp = "[\\S]{0,30}", message = "商品编码字段过长")
    private String waresNo;
    // 克重分类
    @Pattern(regexp = "[\\S]{0,1}", message = "克重分类字段过长")
    private String gram;
    // 毛长
    @Pattern(regexp = "[\\S]{0,1}", message = "毛长字段过长")
    private String hairLen;
    // 弹性
    @Pattern(regexp = "[\\S]{0,1}", message = "弹性字段过长")
    private String flex;
    // 布种
    @Pattern(regexp = "[\\S]{0,1}", message = "布种字段过长")
    private String clothType;
    // 梳节数
    @Pattern(regexp = "[\\S]{0,1}", message = "梳节数字段过长")
    private String comb;
    // 备注
    @Pattern(regexp = "[\\S]{0,40}", message = "备注字段过长")
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
     * 所属分类
     */
    public Long getTypeId() {
        return this.typeId;
    }

    /**
     * 所属分类
     */
    public void setTypeId(Long typeId) {
        this.typeId = typeId;
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
     * 许可号
     */
    public String getAllowNo() {
        return this.allowNo;
    }

    /**
     * 许可号
     */
    public void setAllowNo(String allowNo) {
        this.allowNo = allowNo;
    }

    /**
     * 专利号
     */
    public String getPatentNo() {
        return this.patentNo;
    }

    /**
     * 专利号
     */
    public void setPatentNo(String patentNo) {
        this.patentNo = patentNo;
    }

    /**
     * 发行号
     */
    public String getSellNo() {
        return this.sellNo;
    }

    /**
     * 发行号
     */
    public void setSellNo(String sellNo) {
        this.sellNo = sellNo;
    }

    /**
     * 版本号
     */
    public String getVersionNo() {
        return this.versionNo;
    }

    /**
     * 版本号
     */
    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo;
    }

    /**
     * 等级
     */
    public String getLevel() {
        return this.level;
    }

    /**
     * 等级
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * 材质
     */
    public String getMaterial() {
        return this.material;
    }

    /**
     * 材质
     */
    public void setMaterial(String material) {
        this.material = material;
    }

    /**
     * 保质期限
     */
    public String getQualtyTime() {
        return this.qualtyTime;
    }

    /**
     * 保质期限
     */
    public void setQualtyTime(String qualtyTime) {
        this.qualtyTime = qualtyTime;
    }

    /**
     * 产品状态
     */
    public String getProdStatus() {
        return this.prodStatus;
    }

    /**
     * 产品状态
     */
    public void setProdStatus(String prodStatus) {
        this.prodStatus = prodStatus;
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
     * 当前库存量
     */
    public java.math.BigDecimal getCurAmount() {
        return this.curAmount;
    }

    /**
     * 当前库存量
     */
    public void setCurAmount(java.math.BigDecimal curAmount) {
        this.curAmount = curAmount;
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
     * 商品编码
     */
    public String getWaresNo() {
        return this.waresNo;
    }

    /**
     * 商品编码
     */
    public void setWaresNo(String waresNo) {
        this.waresNo = waresNo;
    }

    /**
     * 克重分类
     */
    public String getGram() {
        return this.gram;
    }

    /**
     * 克重分类
     */
    public void setGram(String gram) {
        this.gram = gram;
    }

    /**
     * 毛长
     */
    public String getHairLen() {
        return this.hairLen;
    }

    /**
     * 毛长
     */
    public void setHairLen(String hairLen) {
        this.hairLen = hairLen;
    }

    /**
     * 弹性
     */
    public String getFlex() {
        return this.flex;
    }

    /**
     * 弹性
     */
    public void setFlex(String flex) {
        this.flex = flex;
    }

    /**
     * 布种
     */
    public String getClothType() {
        return this.clothType;
    }

    /**
     * 布种
     */
    public void setClothType(String clothType) {
        this.clothType = clothType;
    }

    /**
     * 梳节数
     */
    public String getComb() {
        return this.comb;
    }

    /**
     * 梳节数
     */
    public void setComb(String comb) {
        this.comb = comb;
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
