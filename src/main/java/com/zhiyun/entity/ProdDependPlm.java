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
 * 产品配件库实体类
 *
 * @author auto
 * @version v1.0
 * @date
 */
public class ProdDependPlm extends BaseEntity<Long> {

    private static final long serialVersionUID = 1251789792309965910L;

    // ~~~~实体属性
    // 主产品编码
    @Pattern(regexp = "[\\s\\S]{0,30}", message = "主产品编码字段过长")
    private String prodNo;
    // 配件编码
    @Pattern(regexp = "[\\s\\S]{0,30}", message = "配件编码字段过长")
    private String dependNo;
    // 配件来源
    @Pattern(regexp = "[\\s\\S]{0,30}", message = "配件来源字段过长")
    private String dependSrc;
    // 配件名称
    @Pattern(regexp = "[\\s\\S]{0,30}", message = "配件名称字段过长")
    private String dependName;
    // 规格
    @Pattern(regexp = "[\\s\\S]{0,40}", message = "规格字段过长")
    private String norms;
    // 参数
    @Pattern(regexp = "[\\s\\S]{0,400}", message = "参数字段过长")
    private String param;
    // 型号
    @Pattern(regexp = "[\\s\\S]{0,30}", message = "型号字段过长")
    private String modelDesc;
    // 应配数量
    private java.math.BigDecimal depNumber;
    // 计量单位
    @Pattern(regexp = "[\\s\\S]{0,10}", message = "计量单位字段过长")
    private String unit;
    // 生产地
    @Pattern(regexp = "[\\s\\S]{0,30}", message = "生产地字段过长")
    private String facAddr;
    // 生产厂商
    @Pattern(regexp = "[\\s\\S]{0,30}", message = "生产厂商字段过长")
    private String factory;
    // 材质
    @Pattern(regexp = "[\\s\\S]{0,20}", message = "材质字段过长")
    private String material;
    // 等级
    @Pattern(regexp = "[\\s\\S]{0,30}", message = "等级字段过长")
    private String level;
    // 许可号
    @Pattern(regexp = "[\\s\\S]{0,30}", message = "许可号字段过长")
    private String allowNo;
    // 专利号
    @Pattern(regexp = "[\\s\\S]{0,30}", message = "专利号字段过长")
    private String patentNo;
    // 发行号
    @Pattern(regexp = "[\\s\\S]{0,30}", message = "发行号字段过长")
    private String sellNo;
    // 版本号
    @Pattern(regexp = "[\\s\\S]{0,20}", message = "版本号字段过长")
    private String versionNo;
    // 保质期限
    @Pattern(regexp = "[\\s\\S]{0,20}", message = "保质期限字段过长")
    private String qualtyTime;
    // 下架日期
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date loseDate;
    // 产品状态
    @Pattern(regexp = "[\\s\\S]{0,20}", message = "产品状态字段过长")
    private String prodStatus;
    // 当前库存量
    private java.math.BigDecimal curAmount;
    // 生产场地
    @Max(value = 9223372036854775807L, message = "生产场地字段过长")
    private Long factoryId;
    // 备注
    @Pattern(regexp = "[\\s\\S]{0,40}", message = "备注字段过长")
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
     * 主产品编码
     */
    public String getProdNo() {
        return this.prodNo;
    }

    /**
     * 主产品编码
     */
    public void setProdNo(String prodNo) {
        this.prodNo = prodNo;
    }

    /**
     * 配件编码
     */
    public String getDependNo() {
        return this.dependNo;
    }

    /**
     * 配件编码
     */
    public void setDependNo(String dependNo) {
        this.dependNo = dependNo;
    }

    /**
     * 配件来源
     */
    public String getDependSrc() {
        return this.dependSrc;
    }

    /**
     * 配件来源
     */
    public void setDependSrc(String dependSrc) {
        this.dependSrc = dependSrc;
    }

    /**
     * 配件名称
     */
    public String getDependName() {
        return this.dependName;
    }

    /**
     * 配件名称
     */
    public void setDependName(String dependName) {
        this.dependName = dependName;
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
     * 应配数量
     */
    public java.math.BigDecimal getDepNumber() {
        return this.depNumber;
    }

    /**
     * 应配数量
     */
    public void setDepNumber(java.math.BigDecimal depNumber) {
        this.depNumber = depNumber;
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
     * 生产地
     */
    public String getFacAddr() {
        return this.facAddr;
    }

    /**
     * 生产地
     */
    public void setFacAddr(String facAddr) {
        this.facAddr = facAddr;
    }

    /**
     * 生产厂商
     */
    public String getFactory() {
        return this.factory;
    }

    /**
     * 生产厂商
     */
    public void setFactory(String factory) {
        this.factory = factory;
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
     * 下架日期
     */
    public java.util.Date getLoseDate() {
        return this.loseDate;
    }

    /**
     * 下架日期
     */
    public void setLoseDate(java.util.Date loseDate) {
        this.loseDate = loseDate;
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
