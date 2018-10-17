/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.entity;

import com.zhiyun.base.entity.BaseEntity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;

/**
 * 产品工艺参数实体类
 *
 * @author auto
 * @version v1.0
 * @date
 */
public class ProdCrafworkParamPlm extends BaseEntity<Long> {

    private static final long serialVersionUID = 4410066231611338300L;

    // ~~~~实体属性
    // 产品编码
    @Pattern(regexp = "[\\s\\S]{0,30}", message = "产品编码字段过长")
    private String prodNo;
    // 半产品编码
    @Pattern(regexp = "[\\s\\S]{0,30}", message = "半产品编码字段过长")
    private String midProdNo;
    // 工艺id
    @Max(value = 9223372036854775807L, message = "工艺id字段过长")
    private Long crafworkId;
    // 参数名
    @Pattern(regexp = "[\\s\\S]{0,20}", message = "参数名字段过长")
    private String paramName;
    // 参数值1
    @Pattern(regexp = "[\\s\\S]{0,20}", message = "参数值1字段过长")
    private String values1;
    // 参数值2
    @Pattern(regexp = "[\\s\\S]{0,20}", message = "参数值2字段过长")
    private String values2;
    // 值集
    @Pattern(regexp = "[\\s\\S]{0,999}", message = "值集字段过长")
    private String values3;
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
     * 参数名
     */
    public String getParamName() {
        return this.paramName;
    }

    /**
     * 参数名
     */
    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    /**
     * 参数值1
     */
    public String getValues1() {
        return this.values1;
    }

    /**
     * 参数值1
     */
    public void setValues1(String values1) {
        this.values1 = values1;
    }

    /**
     * 参数值2
     */
    public String getValues2() {
        return this.values2;
    }

    /**
     * 参数值2
     */
    public void setValues2(String values2) {
        this.values2 = values2;
    }

    /**
     * 值集
     */
    public String getValues3() {
        return this.values3;
    }

    /**
     * 值集
     */
    public void setValues3(String values3) {
        this.values3 = values3;
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
