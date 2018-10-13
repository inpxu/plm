/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dto;

import com.zhiyun.base.entity.BaseEntity;

import java.util.Date;

public class JqmDto extends BaseEntity<Long> {

    /**
     *
     */
    private static final long serialVersionUID = -4294553660828306196L;
    //设备主键
    private String devId;
    //模型名称
    private String modelName;
    //模型类型
    private String modelType;
    //设备名称
    private String devName;
    //投运编码
    private String commcode;
    //参数别名
    private String alias;
    //参数类型
    private String fieldType;
    //实时数据
    private String realValue;
    //实时数据
    private Date uploadTime;

    public String getDevId() {
        return devId;
    }

    public void setDevId(String devId) {
        this.devId = devId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelType() {
        return modelType;
    }

    public void setModelType(String modelType) {
        this.modelType = modelType;
    }

    public String getDevName() {
        return devName;
    }

    public void setDevName(String devName) {
        this.devName = devName;
    }

    public String getCommcode() {
        return commcode;
    }

    public void setCommcode(String commcode) {
        this.commcode = commcode;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getRealValue() {
        return realValue;
    }

    public void setRealValue(String realValue) {
        this.realValue = realValue;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

}
