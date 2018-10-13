/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.entity;

import com.zhiyun.base.entity.BaseEntity;

/**
 * 实体类
 *
 * @author auto
 * @version v1.0
 * @date
 */
public class DevInfoJqm extends BaseEntity<Long> {

    private static final long serialVersionUID = 4519565249956788609L;

    // ~~~~实体属性
    // 类型
    private String modelName;
    // 涉笔名称
    private String devName;
    // 设备号
    private String code;
    // 型号
    private String spec;
    // 品牌
    private String manufactory;
    // 状态
    private String status;
    // 公司id
    private Long companyId;
    // 设备及编码
    private String devInfo;
    //
    private String commCode;

    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public void setId(Long id) {
        super.setId(id);
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getDevName() {
        return devName;
    }

    public void setDevName(String devName) {
        this.devName = devName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getManufactory() {
        return manufactory;
    }

    public void setManufactory(String manufactory) {
        this.manufactory = manufactory;
    }

    @Override
    public Long getCompanyId() {
        return companyId;
    }

    @Override
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getDevInfo() {
        return devInfo;
    }

    public void setDevInfo(String devInfo) {
        this.devInfo = devInfo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCommCode() {
        return commCode;
    }

    public void setCommCode(String commCode) {
        this.commCode = commCode;
    }

}
