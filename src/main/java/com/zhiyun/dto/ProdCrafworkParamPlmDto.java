package com.zhiyun.dto;

import com.zhiyun.entity.ProdCrafworkParamPlm;

public class ProdCrafworkParamPlmDto extends ProdCrafworkParamPlm {

    private static final long serialVersionUID = 7065869353880305042L;
    // 状态
    private String status;
    // id数组
    private Long ids;

    private ProdCrafworkPathPlmDto[] crafworkPathDtos;

    public ProdCrafworkPathPlmDto[] getCrafworkPathDtos() {
        return crafworkPathDtos;
    }

    public void setCrafworkPathDtos(ProdCrafworkPathPlmDto[] crafworkPathDtos) {
        this.crafworkPathDtos = crafworkPathDtos;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getIds() {
        return ids;
    }

    public void setIds(Long ids) {
        this.ids = ids;
    }
}