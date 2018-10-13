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
 * 产品工艺路线表实体类
 *
 * @author auto
 * @version v1.0
 * @date
 */
public class ProdCrafworkPathPlmAud extends BaseEntity<Long> {

    private static final long serialVersionUID = 3152313700549917446L;

    // ~~~~实体属性
    // 单据号
    @Pattern(regexp = "[\\S]{0,30}", message = "单据号字段过长")
    private String voucherNo;
    // 工艺路线号
    @Pattern(regexp = "[\\S]{0,30}", message = "工艺路线号字段过长")
    private String pathNo;
    // 半产品编码
    @Pattern(regexp = "[\\S]{0,30}", message = "半产品编码字段过长")
    private String midProdNo;
    // 工艺id
    @Max(value = 9223372036854775807L, message = "工艺id字段过长")
    private Long crafworkId;
    // 工艺要求
    @Pattern(regexp = "[\\S]{0,500}", message = "工艺要求字段过长")
    private String crafworkDesc;
    // 工艺工价
    private java.math.BigDecimal price;
    // 工艺岗位
    @Pattern(regexp = "[\\S]{0,30}", message = "工艺岗位字段过长")
    private String quartersEmp;
    // 模具号
    @Pattern(regexp = "[\\S]{0,40}", message = "模具号字段过长")
    private String modelNo;
    // 前置工艺
    @Pattern(regexp = "[\\S]{0,80}", message = "前置工艺字段过长")
    private String befCrafwork;
    // 工艺顺序
    @Max(value = 9223372036854775807L, message = "工艺顺序字段过长")
    private Long carfSeq;
    // 计量单位
    @Pattern(regexp = "[\\S]{0,10}", message = "计量单位字段过长")
    private String unit;
    // 设备单耗标准工时
    @Max(value = 99999999999L, message = "设备单耗标准工时字段过长")
    private Integer macMinutes;
    // 人员单耗标准工时
    @Max(value = 99999999999L, message = "人员单耗标准工时字段过长")
    private Integer empMinutes;
    // 是否需要品检
    @Max(value = 99999999999L, message = "是否需要品检字段过长")
    private Integer isCheck;
    // 每班标准产量
    private java.math.BigDecimal dayAmount;
    // 生效日期
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date startDate;
    // 失效日期
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date endDate;
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
     * 工艺路线号
     */
    public String getPathNo() {
        return this.pathNo;
    }

    /**
     * 工艺路线号
     */
    public void setPathNo(String pathNo) {
        this.pathNo = pathNo;
    }

    /**
     * 半产品编码
     */
    public String getMidProdNo() {
        return this.midProdNo;
    }

    /**
     * 半产品编码
     */
    public void setMidProdNo(String midProdNo) {
        this.midProdNo = midProdNo;
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
     * 工艺要求
     */
    public String getCrafworkDesc() {
        return this.crafworkDesc;
    }

    /**
     * 工艺要求
     */
    public void setCrafworkDesc(String crafworkDesc) {
        this.crafworkDesc = crafworkDesc;
    }

    /**
     * 工艺工价
     */
    public java.math.BigDecimal getPrice() {
        return this.price;
    }

    /**
     * 工艺工价
     */
    public void setPrice(java.math.BigDecimal price) {
        this.price = price;
    }

    /**
     * 工艺岗位
     */
    public String getQuartersEmp() {
        return this.quartersEmp;
    }

    /**
     * 工艺岗位
     */
    public void setQuartersEmp(String quartersEmp) {
        this.quartersEmp = quartersEmp;
    }

    /**
     * 模具号
     */
    public String getModelNo() {
        return this.modelNo;
    }

    /**
     * 模具号
     */
    public void setModelNo(String modelNo) {
        this.modelNo = modelNo;
    }

    /**
     * 前置工艺
     */
    public String getBefCrafwork() {
        return this.befCrafwork;
    }

    /**
     * 前置工艺
     */
    public void setBefCrafwork(String befCrafwork) {
        this.befCrafwork = befCrafwork;
    }

    /**
     * 工艺顺序
     */
    public Long getCarfSeq() {
        return this.carfSeq;
    }

    /**
     * 工艺顺序
     */
    public void setCarfSeq(Long carfSeq) {
        this.carfSeq = carfSeq;
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
     * 设备单耗标准工时
     */
    public Integer getMacMinutes() {
        return this.macMinutes;
    }

    /**
     * 设备单耗标准工时
     */
    public void setMacMinutes(Integer macMinutes) {
        this.macMinutes = macMinutes;
    }

    /**
     * 人员单耗标准工时
     */
    public Integer getEmpMinutes() {
        return this.empMinutes;
    }

    /**
     * 人员单耗标准工时
     */
    public void setEmpMinutes(Integer empMinutes) {
        this.empMinutes = empMinutes;
    }

    /**
     * 是否需要品检
     */
    public Integer getIsCheck() {
        return this.isCheck;
    }

    /**
     * 是否需要品检
     */
    public void setIsCheck(Integer isCheck) {
        this.isCheck = isCheck;
    }

    /**
     * 每班标准产量
     */
    public java.math.BigDecimal getDayAmount() {
        return this.dayAmount;
    }

    /**
     * 每班标准产量
     */
    public void setDayAmount(java.math.BigDecimal dayAmount) {
        this.dayAmount = dayAmount;
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
    public java.util.Date getEndDate() {
        return this.endDate;
    }

    /**
     * 失效日期
     */
    public void setEndDate(java.util.Date endDate) {
        this.endDate = endDate;
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
