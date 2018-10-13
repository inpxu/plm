/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.entity;

import com.zhiyun.base.entity.BaseEntity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;

/**
 * 产品工艺资料(文件、图纸)实体类
 *
 * @author auto
 * @version v1.0
 * @date
 */
public class ProdCrafworkResourceMes extends BaseEntity<Long> {

    private static final long serialVersionUID = 7735702449635085562L;

    // ~~~~实体属性
    // 产品编码
    @Pattern(regexp = "[\\s\\S]{0,30}", message = "产品编码字段过长")
    private String prodNo;
    // 半成品编码
    @Pattern(regexp = "[\\s\\S]{0,30}", message = "半成品编码字段过长")
    private String midProdNo;
    // 工艺id
    @Max(value = 9223372036854775807L, message = "工艺id字段过长")
    private Long crafworkId;
    // 资源类型
    @Pattern(regexp = "[\\s\\S]{0,1}", message = "资源类型字段过长")
    private String resType;
    // 文件名称
    @Pattern(regexp = "[\\s\\S]{0,40}", message = "文件名称字段过长")
    private String fileName;
    // 资源名称
    @Pattern(regexp = "[\\s\\S]{0,40}", message = "资源名称字段过长")
    private String resName;
    // 文件路径
    @Pattern(regexp = "[\\s\\S]{0,256}", message = "文件路径字段过长")
    private String resPath;
    // 图片
    @Pattern(regexp = "[\\s\\S]{0,5000}", message = "图片字段过长")
    private String resource;
    // remark
    @Pattern(regexp = "[\\s\\S]{0,256}", message = "remark字段过长")
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
     * 资源类型
     */
    public String getResType() {
        return this.resType;
    }

    /**
     * 资源类型
     */
    public void setResType(String resType) {
        this.resType = resType;
    }

    /**
     * 文件名称
     */
    public String getFileName() {
        return this.fileName;
    }

    /**
     * 文件名称
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * 资源名称
     */
    public String getResName() {
        return this.resName;
    }

    /**
     * 资源名称
     */
    public void setResName(String resName) {
        this.resName = resName;
    }

    /**
     * 文件路径
     */
    public String getResPath() {
        return this.resPath;
    }

    /**
     * 文件路径
     */
    public void setResPath(String resPath) {
        this.resPath = resPath;
    }

    /**
     * 图片
     */
    public String getResource() {
        return this.resource;
    }

    /**
     * 图片
     */
    public void setResource(String resource) {
        this.resource = resource;
    }

    /**
     * remark
     */
    public String getRemark() {
        return this.remark;
    }

    /**
     * remark
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
