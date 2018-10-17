/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.entity;

import com.zhiyun.base.entity.BaseEntity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;

/**
 * 工艺路线变更申请表实体类
 *
 * @author auto
 * @version v1.0
 * @date
 */
public class CrafworkChangeRecordPlm extends BaseEntity<Long> {

    private static final long serialVersionUID = 4517159861990072628L;

    // ~~~~实体属性
    // 单据号
    @Pattern(regexp = "[\\s\\S]{0,30}", message = "单据号字段过长")
    private String voucherNo;
    // 产品编码
    @Pattern(regexp = "[\\s\\S]{0,30}", message = "产品编码字段过长")
    private String prodNo;
    // 半产品编码
    @Pattern(regexp = "[\\s\\S]{0,30}", message = "半产品编码字段过长")
    private String midProdNo;
    // 工艺id
    @Max(value = 9223372036854775807L, message = "工艺id字段过长")
    private Long crafworkId;
    // 修改类型
    @Pattern(regexp = "[\\s\\S]{0,30}", message = "修改类型字段过长")
    private String changeFlag;
    // 修改项目
    @Pattern(regexp = "[\\s\\S]{0,30}", message = "修改项目字段过长")
    private String changeItem;
    // 旧值
    @Pattern(regexp = "[\\s\\S]{0,30}", message = "旧值字段过长")
    private String oldValue;
    // 新值
    @Pattern(regexp = "[\\s\\S]{0,30}", message = "新值字段过长")
    private String newValue;
    // 修改人
    @Pattern(regexp = "[\\s\\S]{0,30}", message = "修改人字段过长")
    private String changeEmp;
    // 修改日期
    private java.util.Date updDate;
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
     * 修改类型
     */
    public String getChangeFlag() {
        return this.changeFlag;
    }

    /**
     * 修改类型
     */
    public void setChangeFlag(String changeFlag) {
        this.changeFlag = changeFlag;
    }

    /**
     * 修改项目
     */
    public String getChangeItem() {
        return this.changeItem;
    }

    /**
     * 修改项目
     */
    public void setChangeItem(String changeItem) {
        this.changeItem = changeItem;
    }

    /**
     * 旧值
     */
    public String getOldValue() {
        return this.oldValue;
    }

    /**
     * 旧值
     */
    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    /**
     * 新值
     */
    public String getNewValue() {
        return this.newValue;
    }

    /**
     * 新值
     */
    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    /**
     * 修改人
     */
    public String getChangeEmp() {
        return this.changeEmp;
    }

    /**
     * 修改人
     */
    public void setChangeEmp(String changeEmp) {
        this.changeEmp = changeEmp;
    }

    /**
     * 修改日期
     */
    public java.util.Date getUpdDate() {
        return this.updDate;
    }

    /**
     * 修改日期
     */
    public void setUpdDate(java.util.Date updDate) {
        this.updDate = updDate;
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
