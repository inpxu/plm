package com.zhiyun.dto;

import com.zhiyun.entity.CrafworkInputMaterPlm;

public class CrafworkInputMaterPlmDto extends CrafworkInputMaterPlm {

    private static final long serialVersionUID = 4588743456971911304L;

    // 物料名称
    private String mattersName;
    // 物料信息
    private String matterMsg;
    // 状态
    private String status;
    // 分类
    private String typeName;
    // 参数/型号/规格
    private String syntheticField;
    // 单位
    private String unit;
    // 版本
    private String version;

    private String midProdMsg;

    public String getMidProdMsg() {
        return midProdMsg;
    }

    public void setMidProdMsg(String midProdMsg) {
        this.midProdMsg = midProdMsg;
    }

    public String getMattersName() {
        return mattersName;
    }

    public void setMattersName(String mattersName) {
        this.mattersName = mattersName;
    }

    public String getMatterMsg() {
        return matterMsg;
    }

    public void setMatterMsg(String matterMsg) {
        this.matterMsg = matterMsg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getSyntheticField() {
        return syntheticField;
    }

    public void setSyntheticField(String syntheticField) {
        this.syntheticField = syntheticField;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}