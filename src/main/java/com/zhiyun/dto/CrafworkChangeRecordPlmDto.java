package com.zhiyun.dto;

import com.zhiyun.entity.CrafworkChangeRecordPlm;

public class CrafworkChangeRecordPlmDto extends CrafworkChangeRecordPlm {
    private static final long serialVersionUID = -291656499684560899L;

    private String midProdMsg;
    private String midProdName;
    private String prodName;
    private String pathNo;
    private String isFinished;
    private String prodMsg;
    private String crafworkName;

    public String getCrafworkName() {
        return crafworkName;
    }

    public void setCrafworkName(String crafworkName) {
        this.crafworkName = crafworkName;
    }

    public String getProdMsg() {
        return prodMsg;
    }

    public void setProdMsg(String prodMsg) {
        this.prodMsg = prodMsg;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getMidProdName() {
        return midProdName;
    }

    public void setMidProdName(String midProdName) {
        this.midProdName = midProdName;
    }

    public String getPathNo() {
        return pathNo;
    }

    public void setPathNo(String pathNo) {
        this.pathNo = pathNo;
    }

    public String getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(String isFinished) {
        this.isFinished = isFinished;
    }

    public String getMidProdMsg() {
        return midProdMsg;
    }

    public void setMidProdMsg(String midProdMsg) {
        this.midProdMsg = midProdMsg;
    }
}