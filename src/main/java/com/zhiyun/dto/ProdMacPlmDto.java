package com.zhiyun.dto;

import com.zhiyun.entity.ProdMacPlm;

public class ProdMacPlmDto extends ProdMacPlm {

    private static final long serialVersionUID = -8538349217317714544L;

    // 设备信息
    private String macMsg;
    // 部门名称
    private String orgName;
    // 设备名称
    private String macName;
    // 班制名称
    private String totalName;
    // 设备分类
    private String macType;
    // 设备状态
    private String macStatus;
    // 规格参数型号
    private String spec;

    public String getMacMsg() {
        return macMsg;
    }

    public void setMacMsg(String macMsg) {
        this.macMsg = macMsg;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getMacName() {
        return macName;
    }

    public void setMacName(String macName) {
        this.macName = macName;
    }

    public String getTotalName() {
        return totalName;
    }

    public void setTotalName(String totalName) {
        this.totalName = totalName;
    }

    public String getMacType() {
        return macType;
    }

    public void setMacType(String macType) {
        this.macType = macType;
    }

    public String getMacStatus() {
        return macStatus;
    }

    public void setMacStatus(String macStatus) {
        this.macStatus = macStatus;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }
}