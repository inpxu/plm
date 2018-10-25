package com.zhiyun.dto;

import com.zhiyun.base.entity.BaseEntity;

import java.util.List;

public class BomPlmDto extends BaseEntity<Long> {

    private static final long serialVersionUID = 4354186085406852129L;

    private List<ProdBomPlmDto> prodBomPlmDtos;
    private String bomNo;
    private String bomStatus;
    private String versions;

    public String getVersions() {
        return versions;
    }

    public void setVersions(String versions) {
        this.versions = versions;
    }

    public List<ProdBomPlmDto> getProdBomPlmDtos() {
        return prodBomPlmDtos;
    }

    public void setProdBomPlmDtos(List<ProdBomPlmDto> prodBomPlmDtos) {
        this.prodBomPlmDtos = prodBomPlmDtos;
    }

    public String getBomNo() {
        return bomNo;
    }

    public void setBomNo(String bomNo) {
        this.bomNo = bomNo;
    }

    public String getBomStatus() {
        return bomStatus;
    }

    public void setBomStatus(String bomStatus) {
        this.bomStatus = bomStatus;
    }
}