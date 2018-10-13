package com.zhiyun.form;

import com.zhiyun.base.entity.BaseEntity;
import com.zhiyun.dto.ProdCrafworkPathPlmDto;
import com.zhiyun.entity.ProdCrafworkPathPlm;

import java.util.List;

public class ProdCrafworkPathPlmForm extends BaseEntity<Long> {

    private static final long serialVersionUID = 4354186085206853229L;

    private List<ProdCrafworkPathPlm> prodCrafworkPathPlms;
    private List<ProdCrafworkPathPlmDto> prodCrafworkPathPlmDtos;
    private String status;
    private Long[] ids;

    public List<ProdCrafworkPathPlmDto> getProdCrafworkPathPlmDtos() {
        return prodCrafworkPathPlmDtos;
    }

    public void setProdCrafworkPathPlmDtos(List<ProdCrafworkPathPlmDto> prodCrafworkPathPlmDtos) {
        this.prodCrafworkPathPlmDtos = prodCrafworkPathPlmDtos;
    }

    public Long[] getIds() {
        return ids;
    }

    public void setIds(Long[] ids) {
        this.ids = ids;
    }

    public List<ProdCrafworkPathPlm> getProdCrafworkPathPlms() {
        return prodCrafworkPathPlms;
    }

    public void setProdCrafworkPathPlms(List<ProdCrafworkPathPlm> prodCrafworkPathPlms) {
        this.prodCrafworkPathPlms = prodCrafworkPathPlms;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}