package com.zhiyun.dto;

import com.zhiyun.entity.ProdCrafworkPathPlm;

public class ProdCrafworkPathPlmDto extends ProdCrafworkPathPlm {

    private static final long serialVersionUID = 4354186085406852029L;

    // 工艺名称
    private String crafworkName;
    private String quartersDesc;
    // 状态
    private String status;
    private Long[] ids;
    private String prodNo;

    public String getQuartersDesc() {
        return quartersDesc;
    }

    public void setQuartersDesc(String quartersDesc) {
        this.quartersDesc = quartersDesc;
    }

    public String getProdNo() {
        return prodNo;
    }

    public void setProdNo(String prodNo) {
        this.prodNo = prodNo;
    }

    public Long[] getIds() {
        return ids;
    }

    public void setIds(Long[] ids) {
        this.ids = ids;
    }

    public String getCrafworkName() {
        return crafworkName;
    }

    public void setCrafworkName(String crafworkName) {
        this.crafworkName = crafworkName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}