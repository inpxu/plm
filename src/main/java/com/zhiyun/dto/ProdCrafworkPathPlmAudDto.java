package com.zhiyun.dto;

import com.zhiyun.entity.ProdCrafworkPathPlmAud;

public class ProdCrafworkPathPlmAudDto extends ProdCrafworkPathPlmAud {
    private static final long serialVersionUID = -3842411751520228617L;

    private String prodNo;
    // 工艺名称
    private String crafworkName;
    private String quartersDesc;
    // 状态
    private String status;

    public String getCrafworkName() {
        return crafworkName;
    }

    public void setCrafworkName(String crafworkName) {
        this.crafworkName = crafworkName;
    }

    public String getQuartersDesc() {
        return quartersDesc;
    }

    public void setQuartersDesc(String quartersDesc) {
        this.quartersDesc = quartersDesc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProdNo() {
        return prodNo;
    }

    public void setProdNo(String prodNo) {
        this.prodNo = prodNo;
    }
}