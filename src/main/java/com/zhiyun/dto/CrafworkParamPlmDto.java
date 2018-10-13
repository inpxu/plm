package com.zhiyun.dto;

import com.zhiyun.entity.CrafworkParamPlm;

public class CrafworkParamPlmDto extends CrafworkParamPlm {

    private static final long serialVersionUID = 7879642074014184343L;
    // 状态
    private String status;
    // 半成品/成品编码
    private String midProdNo;

    public String getMidProdNo() {
        return midProdNo;
    }

    public void setMidProdNo(String midProdNo) {
        this.midProdNo = midProdNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}