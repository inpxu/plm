package com.zhiyun.form;

import com.zhiyun.dto.ProdMacPlmDto;

import java.util.List;

public class ProdMacPlmForm {

    private List<ProdMacPlmDto> ProdMacPlmDtos;
    // 产品编码
    private String prodNo;
    // 半成品编码
    private String midProdNo;
    // 工艺id
    private Long crafworkId;

    public List<ProdMacPlmDto> getProdMacPlmDtos() {
        return ProdMacPlmDtos;
    }

    public void setProdMacPlmDtos(List<ProdMacPlmDto> prodMacPlmDtos) {
        ProdMacPlmDtos = prodMacPlmDtos;
    }

    public String getMidProdNo() {
        return midProdNo;
    }

    public void setMidProdNo(String midProdNo) {
        this.midProdNo = midProdNo;
    }

    public String getProdNo() {
        return prodNo;
    }

    public void setProdNo(String prodNo) {
        this.prodNo = prodNo;
    }

    public Long getCrafworkId() {
        return crafworkId;
    }

    public void setCrafworkId(Long crafworkId) {
        this.crafworkId = crafworkId;
    }
}