/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.entity;

import com.zhiyun.base.entity.BaseEntity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;

/**
 * 岗位设置实体类
 *
 * @author auto
 * @version v1.0
 * @date
 */
public class QuartersHcm extends BaseEntity<Long> {

    private static final long serialVersionUID = 5182092691219010835L;

    // ~~~~实体属性
    // 岗位no
    @Pattern(regexp = "[\\S]{0,30}", message = "岗位no字段过长")
    private String quartersNo;
    // 岗位名称
    @Pattern(regexp = "[\\S]{0,40}", message = "岗位名称字段过长")
    private String quartersDesc;
    // 岗位说明
    @Pattern(regexp = "[\\S]{0,255}", message = "岗位说明字段过长")
    private String funDesc;
    // 岗位定员数
    @Max(value = 99999999999L, message = "岗位定员数字段过长")
    private Integer hires;
    // 岗位要求
    @Pattern(regexp = "[\\S]{0,40}", message = "岗位要求字段过长")
    private String quQuire;
    // 学历要求
    @Pattern(regexp = "[\\S]{0,30}", message = "学历要求字段过长")
    private String educational;
    // 技能要求
    @Pattern(regexp = "[\\S]{0,30}", message = "技能要求字段过长")
    private String skill;
    // 公司id
    @Max(value = 9223372036854775807L, message = "公司id字段过长")
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
     * 岗位no
     */
    public String getQuartersNo() {
        return this.quartersNo;
    }

    /**
     * 岗位no
     */
    public void setQuartersNo(String quartersNo) {
        this.quartersNo = quartersNo;
    }

    /**
     * 岗位名称
     */
    public String getQuartersDesc() {
        return this.quartersDesc;
    }

    /**
     * 岗位名称
     */
    public void setQuartersDesc(String quartersDesc) {
        this.quartersDesc = quartersDesc;
    }

    /**
     * 岗位说明
     */
    public String getFunDesc() {
        return this.funDesc;
    }

    /**
     * 岗位说明
     */
    public void setFunDesc(String funDesc) {
        this.funDesc = funDesc;
    }

    /**
     * 岗位定员数
     */
    public Integer getHires() {
        return this.hires;
    }

    /**
     * 岗位定员数
     */
    public void setHires(Integer hires) {
        this.hires = hires;
    }

    /**
     * 岗位要求
     */
    public String getQuQuire() {
        return this.quQuire;
    }

    /**
     * 岗位要求
     */
    public void setQuQuire(String quQuire) {
        this.quQuire = quQuire;
    }

    /**
     * 学历要求
     */
    public String getEducational() {
        return this.educational;
    }

    /**
     * 学历要求
     */
    public void setEducational(String educational) {
        this.educational = educational;
    }

    /**
     * 技能要求
     */
    public String getSkill() {
        return this.skill;
    }

    /**
     * 技能要求
     */
    public void setSkill(String skill) {
        this.skill = skill;
    }

    /**
     * 公司id
     */
    @Override
    public Long getCompanyId() {
        return this.companyId;
    }

    /**
     * 公司id
     */
    @Override
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}
