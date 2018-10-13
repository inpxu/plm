package com.zhiyun.dto;

import com.zhiyun.entity.MachineSetMes;

public class MachineSetMesDto extends MachineSetMes {

    private static final long serialVersionUID = 7335243132874809738L;
    // 设备信息
    private String macMsg;
    // 部门名称
    private String orgName;

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
}