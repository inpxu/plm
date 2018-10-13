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
public class FactorySetMes extends BaseEntity<Long> {

    private static final long serialVersionUID = 6911413862890564241L;

    // ~~~~实体属性
    //
    @Max(value = 9223372036854775807L, message = "字段过长")
    private Long factoryId;
    //
    @Pattern(regexp = "[\\S]{0,40}", message = "字段过长")
    private String factoryName;
    //
    @Pattern(regexp = "[\\S]{0,10}", message = "字段过长")
    private String factoryType;
    //
    @Pattern(regexp = "[\\S]{0,200}", message = "字段过长")
    private String factoryDesc;
    //
    @Pattern(regexp = "[\\S]{0,60}", message = "字段过长")
    private String manager;
    //
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date builddate;
    //
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date losedate;
    // 企业标识
    @Max(value = 9223372036854775807L, message = "企业标识字段过长")
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
     *
     */
    public Long getFactoryId() {
        return this.factoryId;
    }

    /**
     *
     */
    public void setFactoryId(Long factoryId) {
        this.factoryId = factoryId;
    }

    /**
     *
     */
    public String getFactoryName() {
        return this.factoryName;
    }

    /**
     *
     */
    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    /**
     *
     */
    public String getFactoryType() {
        return this.factoryType;
    }

    /**
     *
     */
    public void setFactoryType(String factoryType) {
        this.factoryType = factoryType;
    }

    /**
     *
     */
    public String getFactoryDesc() {
        return this.factoryDesc;
    }

    /**
     *
     */
    public void setFactoryDesc(String factoryDesc) {
        this.factoryDesc = factoryDesc;
    }

    /**
     *
     */
    public String getManager() {
        return this.manager;
    }

    /**
     *
     */
    public void setManager(String manager) {
        this.manager = manager;
    }

    /**
     *
     */
    public java.util.Date getBuilddate() {
        return this.builddate;
    }

    /**
     *
     */
    public void setBuilddate(java.util.Date builddate) {
        this.builddate = builddate;
    }

    /**
     *
     */
    public java.util.Date getLosedate() {
        return this.losedate;
    }

    /**
     *
     */
    public void setLosedate(java.util.Date losedate) {
        this.losedate = losedate;
    }

    /**
     * 企业标识
     */
    @Override
    public Long getCompanyId() {
        return this.companyId;
    }

    /**
     * 企业标识
     */
    @Override
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}
