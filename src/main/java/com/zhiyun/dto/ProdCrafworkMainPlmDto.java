package com.zhiyun.dto;

import com.zhiyun.entity.ProdCrafworkMainPlm;

import java.util.List;

public class ProdCrafworkMainPlmDto extends ProdCrafworkMainPlm {
    private static final long serialVersionUID = 146330942260175109L;
    List<ProdCrafworkPathPlmDto> prodCrafworkPathPlmDtos;
    // 启用\停用
    private String enDis;
    // 产品名称
    private String prodName;
    // 半成品 编码
    private String midProdNo;
    // 半成品名称
    private String midProdName;
    private String prodMsg;
    private String midProdMsg;
    // 部门
    private String orgName;
    // 单据状态
    private String isFinished;
    // 当前选中
    private String nowChoice;
    private String label;

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

    public String getEnDis() {
        return enDis;
    }

    public void setEnDis(String enDis) {
        this.enDis = enDis;
    }

    public String getNowChoice() {
        return nowChoice;
    }

    public void setNowChoice(String nowChoice) {
        this.nowChoice = nowChoice;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(String isFinished) {
        this.isFinished = isFinished;
    }

    public List<ProdCrafworkPathPlmDto> getProdCrafworkPathPlmDtos() {
        return prodCrafworkPathPlmDtos;
    }

    public void setProdCrafworkPathPlmDtos(List<ProdCrafworkPathPlmDto> prodCrafworkPathPlmDtos) {
        this.prodCrafworkPathPlmDtos = prodCrafworkPathPlmDtos;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getMidProdNo() {
        return midProdNo;
    }

    public void setMidProdNo(String midProdNo) {
        this.midProdNo = midProdNo;
    }

    public String getMidProdName() {
        return midProdName;
    }

    public void setMidProdName(String midProdName) {
        this.midProdName = midProdName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}