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
 * 产品分类设置实体类
 *
 * @author auto
 * @version v1.0
 * @date
 */
public class ProdTypeCrm extends BaseEntity<Long> {

    private static final long serialVersionUID = 6089211747323597504L;

    // ~~~~实体属性
    // 分类名称
    @Pattern(regexp = "[\\s\\S]{0,30}", message = "分类名称字段过长")
    private String typeDesc;
    // 开始日期
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date startDate;
    // 失效日期
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date loseDate;
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
     * 分类名称
     */
    public String getTypeDesc() {
        return this.typeDesc;
    }

    /**
     * 分类名称
     */
    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    /**
     * 开始日期
     */
    public java.util.Date getStartDate() {
        return this.startDate;
    }

    /**
     * 开始日期
     */
    public void setStartDate(java.util.Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 失效日期
     */
    public java.util.Date getLoseDate() {
        return this.loseDate;
    }

    /**
     * 失效日期
     */
    public void setLoseDate(java.util.Date loseDate) {
        this.loseDate = loseDate;
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
