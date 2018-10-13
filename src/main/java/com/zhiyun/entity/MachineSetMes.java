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
 * 实体类
 *
 * @author auto
 * @version v1.0
 * @date
 */
public class MachineSetMes extends BaseEntity<Long> {

    private static final long serialVersionUID = 7680704249383267350L;

    // ~~~~实体属性
    // 设备编号
    @Pattern(regexp = "[\\S]{0,40}", message = "设备编号字段过长")
    private String macNo;
    // 设备名称
    @Pattern(regexp = "[\\S]{0,40}", message = "设备名称字段过长")
    private String macName;
    // 场地ID
    @Max(value = 9223372036854775807L, message = "场地ID字段过长")
    private Long factoryId;
    // 所属部门
    @Max(value = 9223372036854775807L, message = "所属部门字段过长")
    private Long orgId;
    // 设备类型
    @Pattern(regexp = "[\\S]{0,20}", message = "设备类型字段过长")
    private String macType;
    // 工艺ID
    @Max(value = 9223372036854775807L, message = "工艺ID字段过长")
    private Long crafworkId;
    // 设备责任人
    @Pattern(regexp = "[\\S]{0,30}", message = "设备责任人字段过长")
    private String liable;
    // 最小开机量
    private java.math.BigDecimal minAmount;
    // 最小开机人数
    @Max(value = 99999999999L, message = "最小开机人数字段过长")
    private Integer minEmp;
    // 是否品检
    private Boolean isInspection;
    // 品牌
    @Pattern(regexp = "[\\S]{0,40}", message = "品牌字段过长")
    private String brand;
    // 参数规格
    @Pattern(regexp = "[\\S]{0,20}", message = "参数规格字段过长")
    private String spec;
    // 保养间隔时数
    @Max(value = 99999999999L, message = "保养间隔时数字段过长")
    private Integer fixHour;
    // 设备状态
    @Pattern(regexp = "[\\S]{0,10}", message = "设备状态字段过长")
    private String macStatus;
    // 条形码
    @Pattern(regexp = "[\\S]{0,30}", message = "条形码字段过长")
    private String barcode;
    // 二维码
    @Pattern(regexp = "[\\S]{0,30}", message = "二维码字段过长")
    private String qrcode;
    // 建立日期
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date builddate;
    // 失效日期
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date losedate;
    //
    @Max(value = 9223372036854775807L, message = "字段过长")
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
     * 设备编号
     */
    public String getMacNo() {
        return this.macNo;
    }

    /**
     * 设备编号
     */
    public void setMacNo(String macNo) {
        this.macNo = macNo;
    }

    /**
     * 设备名称
     */
    public String getMacName() {
        return this.macName;
    }

    /**
     * 设备名称
     */
    public void setMacName(String macName) {
        this.macName = macName;
    }

    /**
     * 场地ID
     */
    public Long getFactoryId() {
        return this.factoryId;
    }

    /**
     * 场地ID
     */
    public void setFactoryId(Long factoryId) {
        this.factoryId = factoryId;
    }

    /**
     * 所属部门
     */
    public Long getOrgId() {
        return this.orgId;
    }

    /**
     * 所属部门
     */
    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    /**
     * 设备类型
     */
    public String getMacType() {
        return this.macType;
    }

    /**
     * 设备类型
     */
    public void setMacType(String macType) {
        this.macType = macType;
    }

    /**
     * 工艺ID
     */
    public Long getCrafworkId() {
        return this.crafworkId;
    }

    /**
     * 工艺ID
     */
    public void setCrafworkId(Long crafworkId) {
        this.crafworkId = crafworkId;
    }

    /**
     * 设备责任人
     */
    public String getLiable() {
        return this.liable;
    }

    /**
     * 设备责任人
     */
    public void setLiable(String liable) {
        this.liable = liable;
    }

    /**
     * 最小开机量
     */
    public java.math.BigDecimal getMinAmount() {
        return this.minAmount;
    }

    /**
     * 最小开机量
     */
    public void setMinAmount(java.math.BigDecimal minAmount) {
        this.minAmount = minAmount;
    }

    /**
     * 最小开机人数
     */
    public Integer getMinEmp() {
        return this.minEmp;
    }

    /**
     * 最小开机人数
     */
    public void setMinEmp(Integer minEmp) {
        this.minEmp = minEmp;
    }

    /**
     * 是否品检
     */
    public Boolean getIsInspection() {
        return this.isInspection;
    }

    /**
     * 是否品检
     */
    public void setIsInspection(Boolean isInspection) {
        this.isInspection = isInspection;
    }

    /**
     * 品牌
     */
    public String getBrand() {
        return this.brand;
    }

    /**
     * 品牌
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * 参数规格
     */
    public String getSpec() {
        return this.spec;
    }

    /**
     * 参数规格
     */
    public void setSpec(String spec) {
        this.spec = spec;
    }

    /**
     * 保养间隔时数
     */
    public Integer getFixHour() {
        return this.fixHour;
    }

    /**
     * 保养间隔时数
     */
    public void setFixHour(Integer fixHour) {
        this.fixHour = fixHour;
    }

    /**
     * 设备状态
     */
    public String getMacStatus() {
        return this.macStatus;
    }

    /**
     * 设备状态
     */
    public void setMacStatus(String macStatus) {
        this.macStatus = macStatus;
    }

    /**
     * 条形码
     */
    public String getBarcode() {
        return this.barcode;
    }

    /**
     * 条形码
     */
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    /**
     * 二维码
     */
    public String getQrcode() {
        return this.qrcode;
    }

    /**
     * 二维码
     */
    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    /**
     * 建立日期
     */
    public java.util.Date getBuilddate() {
        return this.builddate;
    }

    /**
     * 建立日期
     */
    public void setBuilddate(java.util.Date builddate) {
        this.builddate = builddate;
    }

    /**
     * 失效日期
     */
    public java.util.Date getLosedate() {
        return this.losedate;
    }

    /**
     * 失效日期
     */
    public void setLosedate(java.util.Date losedate) {
        this.losedate = losedate;
    }

    /**
     *
     */
    @Override
    public Long getCompanyId() {
        return this.companyId;
    }

    /**
     *
     */
    @Override
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}
