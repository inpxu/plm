/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dto;

import com.zhiyun.entity.ProductStorePlm;

import java.util.List;

/**
 * @author sun
 * @version v1.0
 * @date 2018-09-06 12:59
 */
public class ProdDto extends ProductStorePlm {

    private static final long serialVersionUID = 7623039040997431134L;
    // 名称
    private String cosName;
    // 半成品
    private List<ProdMidDto> prodMidDtos;

    public List<ProdMidDto> getProdMidDtos() {
        return prodMidDtos;
    }

    public void setProdMidDtos(List<ProdMidDto> prodMidDtos) {
        this.prodMidDtos = prodMidDtos;
    }

    public String getCosName() {
        return cosName;
    }

    public void setCosName(String cosName) {
        this.cosName = cosName;
    }
}
