package com.zhiyun.util;

/**
 * @Auther: sunyuntao
 * @Date: 2018/6/25 10:35
 * @Description:
 */
public enum VoucherEnum {

    APPROVAL_STATUS_SUCCESS("AS001", "已通过"),
    APPROVAL_STATUS_PROCESS("AS002", "审核中"),
    APPROVAL_STATUS_FAILURE("AS003", "已驳回");

    private String id;

    private String label;

    private VoucherEnum(String id, String label) {
        this.id = id;
        this.label = label;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
