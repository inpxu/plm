package com.zhiyun.dto;

import com.zhiyun.entity.CrafworkChangeMain;

public class CrafworkChangeMainDto extends CrafworkChangeMain {
    private static final long serialVersionUID = -3145145339795857174L;

    // 部门名称
    private String orgName;
    // 审批状态
    private String label;

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