/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dto;

import com.zhiyun.entity.CrafworkStructPlm;

/**
 * @author sun
 * @version v1.0
 * @date 2018-09-07 13:25
 */
public class CrafworkStructDto extends CrafworkStructPlm {

    private static final long serialVersionUID = 7205993746592286384L;
    // 是否品检
    private String checkof;

    // 工艺岗位名称
    private String quarterName;
    // 半成品编码
    private String midProdNo;
    // 状态
    private String status;

    private String pathNo;

    public String getPathNo() {
        return pathNo;
    }

    public void setPathNo(String pathNo) {
        this.pathNo = pathNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMidProdNo() {
        return midProdNo;
    }

    public void setMidProdNo(String midProdNo) {
        this.midProdNo = midProdNo;
    }

    public String getQuarterName() {
        return quarterName;
    }

    public void setQuarterName(String quarterName) {
        this.quarterName = quarterName;
    }

    public String getCheckof() {
        return checkof;
    }

    public void setCheckof(String checkof) {
        this.checkof = checkof;
    }
}
