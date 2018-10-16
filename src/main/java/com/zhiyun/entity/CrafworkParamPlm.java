/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.entity;

import com.zhiyun.base.entity.BaseEntity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;

/**
 * 工艺参数实体类
 *
 * @author auto
 * @version v1.0
 * @date
 */
public class CrafworkParamPlm extends BaseEntity<Long> {

    private static final long serialVersionUID = 5090684729699840051L;

    // ~~~~实体属性
    // 工艺id
    @Max(value = 9223372036854775807L, message = "工艺id字段过长")
    private Long crafworkId;
    // 工艺参数名
    @Pattern(regexp = "[\\s\\S]{0,20}", message = "工艺参数名字段过长")
    private String param;
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
     * 工艺参数名
     */
    public String getParam() {
        return this.param;
    }

    /**
     * 工艺参数名
     */
    public void setParam(String param) {
        this.param = param;
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
