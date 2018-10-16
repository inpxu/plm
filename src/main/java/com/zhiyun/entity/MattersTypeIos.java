/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.entity;

import com.zhiyun.base.entity.BaseEntity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;

/**
 * 物料分类设置实体类
 *
 * @author auto
 * @version v1.0
 * @date
 */
public class MattersTypeIos extends BaseEntity<Long> {

    private static final long serialVersionUID = 2386248326367162811L;

    // ~~~~实体属性
    // 分类编码
    @Pattern(regexp = "[\\s\\S]{0,30}", message = "分类编码字段过长")
    private String typeNo;
    // 分类名称
    @Pattern(regexp = "[\\s\\S]{0,40}", message = "分类名称字段过长")
    private String typeName;
    // 备注
    @Pattern(regexp = "[\\s\\S]{0,40}", message = "备注字段过长")
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
     * 分类编码
     */
    public String getTypeNo() {
        return this.typeNo;
    }

    /**
     * 分类编码
     */
    public void setTypeNo(String typeNo) {
        this.typeNo = typeNo;
    }

    /**
     * 分类名称
     */
    public String getTypeName() {
        return this.typeName;
    }

    /**
     * 分类名称
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
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
    public Long getCompanyId() {
        return this.companyId;
    }

    /**
     * company_id
     */
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}
