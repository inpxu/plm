/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.entity;

import com.zhiyun.base.entity.BaseEntity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;

/**
 * 工艺池实体类
 *
 * @author auto
 * @version v1.0
 * @date
 */
public class CrafworkStructPlm extends BaseEntity<Long> {

    private static final long serialVersionUID = 6618734531266885371L;

    // ~~~~实体属性
    // 工艺名称
    @Pattern(regexp = "[\\s\\S]{0,30}", message = "工艺名称字段过长")
    private String crafworkName;
    // 主生产设备
    @Pattern(regexp = "[\\s\\S]{0,60}", message = "主生产设备字段过长")
    private String macType;
    // 是否需要品检
    @Max(value = 99999999999L, message = "是否需要品检字段过长")
    private Integer isCheck;
    // 工艺岗位
    @Pattern(regexp = "[\\s\\S]{0,30}", message = "工艺岗位字段过长")
    private String quartersEmp;
    // 技能等级要求
    @Pattern(regexp = "[\\s\\S]{0,30}", message = "技能等级要求字段过长")
    private String gradeEmp;
    // 计量单位
    @Pattern(regexp = "[\\s\\S]{0,10}", message = "计量单位字段过长")
    private String unit;
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
     * 工艺名称
     */
    public String getCrafworkName() {
        return this.crafworkName;
    }

    /**
     * 工艺名称
     */
    public void setCrafworkName(String crafworkName) {
        this.crafworkName = crafworkName;
    }

    /**
     * 主生产设备
     */
    public String getMacType() {
        return this.macType;
    }

    /**
     * 主生产设备
     */
    public void setMacType(String macType) {
        this.macType = macType;
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
     * 技能等级要求
     */
    public String getGradeEmp() {
        return this.gradeEmp;
    }

    /**
     * 技能等级要求
     */
    public void setGradeEmp(String gradeEmp) {
        this.gradeEmp = gradeEmp;
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
