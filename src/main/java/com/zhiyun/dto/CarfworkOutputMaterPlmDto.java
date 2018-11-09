package com.zhiyun.dto;

import com.zhiyun.entity.CarfworkOutputMaterPlm;

public class CarfworkOutputMaterPlmDto extends CarfworkOutputMaterPlm {

    private static final long serialVersionUID = 8886140008645103230L;
    // 产品信息
    private String prodMsg;
    // 半成品信息
    private String midProdMsg;
    // 单位
    private String unit;
    // 参数/型号/规格
    private String syntheticField;
    // 参数/型号/规格
    private String allInfo;

    public String getAllInfo() {
        return allInfo;
    }

    public void setAllInfo(String allInfo) {
        this.allInfo = allInfo;
    }

    public String getProdMsg() {
        return prodMsg;
    }

    public void setProdMsg(String prodMsg) {
        this.prodMsg = prodMsg;
    }

    public String getMidProdMsg() {
        return midProdMsg;
    }

    public void setMidProdMsg(String midProdMsg) {
        this.midProdMsg = midProdMsg;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getSyntheticField() {
        return syntheticField;
    }

    public void setSyntheticField(String syntheticField) {
        this.syntheticField = syntheticField;
    }
}