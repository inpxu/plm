package com.zhiyun.form;

import com.zhiyun.dto.CrafworkInputMaterPlmDto;

import java.util.List;

public class CrafworkInputMaterPlmForm {

    private List<CrafworkInputMaterPlmDto> crafworkInputMaterPlmsDtos;

    private String prodNo;
    // 半成品编码
    private String midPordNo;
    // 工艺id
    private Long crafworkId;

    public String getProdNo() {
        return prodNo;
    }

    public void setProdNo(String prodNo) {
        this.prodNo = prodNo;
    }

    public String getMidPordNo() {
        return midPordNo;
    }

    public void setMidPordNo(String midPordNo) {
        this.midPordNo = midPordNo;
    }

    public Long getCrafworkId() {
        return crafworkId;
    }

    public void setCrafworkId(Long crafworkId) {
        this.crafworkId = crafworkId;
    }

    public List<CrafworkInputMaterPlmDto> getCrafworkInputMaterPlmsDtos() {
        return crafworkInputMaterPlmsDtos;
    }

    public void setCrafworkInputMaterPlmsDtos(List<CrafworkInputMaterPlmDto> crafworkInputMaterPlmsDtos) {
        this.crafworkInputMaterPlmsDtos = crafworkInputMaterPlmsDtos;
    }
}