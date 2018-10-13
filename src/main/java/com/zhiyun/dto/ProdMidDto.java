/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dto;

import com.zhiyun.entity.ProductMidPlm;

import java.util.List;

/**
 * @author sun
 * @version v1.0
 * @date 2018-09-06 09:35
 */
public class ProdMidDto extends ProductMidPlm {

    private static final long serialVersionUID = 7628696957752347514L;

    // 名称
    private String cosName;

    // 来源名称
    private String sourceName;
    // 生产场地
    private String factoryName;
    // 产品信息
    private String prodMsg;
    private String prodMess;
    // 半成品信息
    private String midMsg;
    private String midProdMsg;
    // 规格参数型号
    private String SyntheticField;
    // 子产品
    private List<ProdMidDto> prodMidDtos;
    // 是否有子节点
    private Boolean leaf;
    // 当前选中
    private String nowChoice;
    // 工艺路线号
    private String pathNo;
    // 状态
    private String status;
    // 版本号
    private String versions;

    public String getMidProdMsg() {
        return midProdMsg;
    }

    public void setMidProdMsg(String midProdMsg) {
        this.midProdMsg = midProdMsg;
    }

    public String getProdMess() {
        return prodMess;
    }

    public void setProdMess(String prodMess) {
        this.prodMess = prodMess;
    }

    public String getPathNo() {
        return pathNo;
    }

    public void setPathNo(String pathNo) {
        this.pathNo = pathNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVersions() {
        return versions;
    }

    public void setVersions(String versions) {
        this.versions = versions;
    }

    public String getNowChoice() {
        return nowChoice;
    }

    public void setNowChoice(String nowChoice) {
        this.nowChoice = nowChoice;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getCosName() {
        return cosName;
    }

    public void setCosName(String cosName) {
        this.cosName = cosName;
    }

    public List<ProdMidDto> getProdMidDtos() {
        return prodMidDtos;
    }

    public void setProdMidDtos(List<ProdMidDto> prodMidDtos) {
        this.prodMidDtos = prodMidDtos;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getProdMsg() {
        return prodMsg;
    }

    public void setProdMsg(String prodMsg) {
        this.prodMsg = prodMsg;
    }

    public String getMidMsg() {
        return midMsg;
    }

    public void setMidMsg(String midMsg) {
        this.midMsg = midMsg;
    }

    public String getSyntheticField() {
        return SyntheticField;
    }

    public void setSyntheticField(String syntheticField) {
        SyntheticField = syntheticField;
    }

    public Boolean getLeaf() {
        return leaf;
    }

    public void setLeaf(Boolean leaf) {
        this.leaf = leaf;
    }
}
