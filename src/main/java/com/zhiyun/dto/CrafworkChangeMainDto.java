package com.zhiyun.dto;

import com.zhiyun.entity.CrafworkChangeMain;

import java.util.List;

public class CrafworkChangeMainDto extends CrafworkChangeMain {
    private static final long serialVersionUID = -3145145339795857174L;

    // 部门名称
    private String orgName;
    // 审批状态
    private String label;

    private List<ProdCrafworkPathPlmDto> prodCrafworkPathPlmDtos;

    private ProdCrafworkPathPlmDto[] crafworkPathDtos;

    public List<ProdCrafworkPathPlmDto> getProdCrafworkPathPlmDtos() {
        return prodCrafworkPathPlmDtos;
    }

    public void setProdCrafworkPathPlmDtos(List<ProdCrafworkPathPlmDto> prodCrafworkPathPlmDtos) {
        this.prodCrafworkPathPlmDtos = prodCrafworkPathPlmDtos;
    }

    public ProdCrafworkPathPlmDto[] getCrafworkPathDtos() {
        return crafworkPathDtos;
    }

    public void setCrafworkPathDtos(ProdCrafworkPathPlmDto[] crafworkPathDtos) {
        this.crafworkPathDtos = crafworkPathDtos;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}