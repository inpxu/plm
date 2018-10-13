package com.zhiyun.form;

import com.zhiyun.dto.CrafworkChangeRecordPlmDto;

import java.util.List;

public class ChangeRecordForm {
    private String nowChoice;
    private String prodMsg;
    private String midProdMsg;
    private String pathNo;
    private List<CrafworkChangeRecordPlmDto> crafworkChangeRecordPlmDtos;

    public String getNowChoice() {
        return nowChoice;
    }

    public void setNowChoice(String nowChoice) {
        this.nowChoice = nowChoice;
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

    public String getPathNo() {
        return pathNo;
    }

    public void setPathNo(String pathNo) {
        this.pathNo = pathNo;
    }

    public List<CrafworkChangeRecordPlmDto> getCrafworkChangeRecordPlmDtos() {
        return crafworkChangeRecordPlmDtos;
    }

    public void setCrafworkChangeRecordPlmDtos(List<CrafworkChangeRecordPlmDto> crafworkChangeRecordPlmDtos) {
        this.crafworkChangeRecordPlmDtos = crafworkChangeRecordPlmDtos;
    }
}