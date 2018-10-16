package com.zhiyun.dto;

import com.zhiyun.entity.ProdBomDetailPlm;

public class ProdBomDetailPlmDto extends ProdBomDetailPlm {

    private static final long serialVersionUID = 4252004763733302414L;

    // 产品编码
    private String prodNo;
    // 半成品编码
    private String midNo;
    // 输入半成品编码
    private String midProdNo;
    // 规格参数型号
    private String syntheticField;
    // 状态
    private String status;
    // 物料名
    private String mattersName;
    // 物料类型
    private String typeName;
    // 物料信息
    private String matterMsg;

    private String matterNo;

    public String getMatterNo() {
        return matterNo;
    }

    public void setMatterNo(String matterNo) {
        this.matterNo = matterNo;
    }

    public String getMidNo() {
        return midNo;
    }

    public void setMidNo(String midNo) {
        this.midNo = midNo;
    }

    public String getMatterMsg() {
        return matterMsg;
    }

    public void setMatterMsg(String matterMsg) {
        this.matterMsg = matterMsg;
    }

    public String getProdNo() {
        return prodNo;
    }

    public void setProdNo(String prodNo) {
        this.prodNo = prodNo;
    }

    public String getMidProdNo() {
        return midProdNo;
    }

    public void setMidProdNo(String midProdNo) {
        this.midProdNo = midProdNo;
    }

    public String getSyntheticField() {
        return syntheticField;
    }

    public void setSyntheticField(String syntheticField) {
        this.syntheticField = syntheticField;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMattersName() {
        return mattersName;
    }

    public void setMattersName(String mattersName) {
        this.mattersName = mattersName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}