/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.entity;

import com.zhiyun.base.entity.BaseEntity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;

/**
 * 产品-设备登记实体类
 *
 * @author auto
 * @version v1.0
 * @date
 */
public class ProdMacPlm extends BaseEntity<Long> {

    private static final long serialVersionUID = 1925736975180249196L;

    // ~~~~实体属性
    // 产品编码
    @Pattern(regexp = "[\\S]{0,30}", message = "产品编码字段过长")
    private String prodNo;
    // 半成品编码
    @Pattern(regexp = "[\\S]{0,30}", message = "半成品编码字段过长")
    private String midProdNo;
    // 工艺id
    @Max(value = 9223372036854775807L, message = "工艺id字段过长")
    private Long crafworkId;
    // 设备编码
    @Pattern(regexp = "[\\S]{0,30}", message = "设备编码字段过长")
    private String macNo;
    // 单件加工时间
    @Max(value = 99999999999L, message = "单件加工时间字段过长")
    private Integer periodTime;
    // 前置时间(s)
    @Max(value = 99999999999L, message = "前置时间(s)字段过长")
    private Integer beforeTime;
    // 后置时间(s)
    @Max(value = 99999999999L, message = "后置时间(s)字段过长")
    private Integer behindTime;
    // 设备班制
    @Max(value = 99999999999L, message = "设备班制字段过长")
    private Integer runTotal;
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
     * 设备编码
     */
    public String getMacNo() {
        return this.macNo;
    }

    /**
     * 设备编码
     */
    public void setMacNo(String macNo) {
        this.macNo = macNo;
    }

    /**
     * 单件加工时间
     */
    public Integer getPeriodTime() {
        return this.periodTime;
    }

    /**
     * 单件加工时间
     */
    public void setPeriodTime(Integer periodTime) {
        this.periodTime = periodTime;
    }

    /**
     * 前置时间(s)
     */
    public Integer getBeforeTime() {
        return this.beforeTime;
    }

    /**
     * 前置时间(s)
     */
    public void setBeforeTime(Integer beforeTime) {
        this.beforeTime = beforeTime;
    }

    /**
     * 后置时间(s)
     */
    public Integer getBehindTime() {
        return this.behindTime;
    }

    /**
     * 后置时间(s)
     */
    public void setBehindTime(Integer behindTime) {
        this.behindTime = behindTime;
    }

    /**
     * 设备班制
     */
    public Integer getRunTotal() {
        return this.runTotal;
    }

    /**
     * 设备班制
     */
    public void setRunTotal(Integer runTotal) {
        this.runTotal = runTotal;
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
