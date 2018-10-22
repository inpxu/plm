package com.zhiyun.dto;

import com.zhiyun.entity.CrafworkChangeMain;

import java.util.List;

public class CrafworkChangeMainDto extends CrafworkChangeMain {
    private static final long serialVersionUID = -3145145339795857174L;

    // 部门名称
    private String orgName;
    // 审批状态
    private String label;
    // 新增编辑删除
    private List<ProdCrafworkPathPlmDto> prodCrafworkPathPlmDtos;
    // 调整顺序
    private List<ProdCrafworkParamPlmDto> prodCrafworkParamPlmDtos;

    public List<ProdCrafworkParamPlmDto> getProdCrafworkParamPlmDtos() {
        return prodCrafworkParamPlmDtos;
    }

    public void setProdCrafworkParamPlmDtos(List<ProdCrafworkParamPlmDto> prodCrafworkParamPlmDtos) {
        this.prodCrafworkParamPlmDtos = prodCrafworkParamPlmDtos;
    }

    public List<ProdCrafworkPathPlmDto> getProdCrafworkPathPlmDtos() {
        return prodCrafworkPathPlmDtos;
    }

    public void setProdCrafworkPathPlmDtos(List<ProdCrafworkPathPlmDto> prodCrafworkPathPlmDtos) {
        this.prodCrafworkPathPlmDtos = prodCrafworkPathPlmDtos;
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