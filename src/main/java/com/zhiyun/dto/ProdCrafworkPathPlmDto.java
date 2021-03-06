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
    // 是否品检
    private String check;

    private String leaf;

    // 调用的方法
    private String auditMethod;

    public String getLeaf() {
        return leaf;
    }

    public void setLeaf(String leaf) {
        this.leaf = leaf;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public String getAuditMethod() {
        return auditMethod;
    }

    public void setAuditMethod(String auditMethod) {
        this.auditMethod = auditMethod;
    }

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