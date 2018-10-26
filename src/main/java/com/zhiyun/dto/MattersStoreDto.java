package com.zhiyun.dto;

import com.zhiyun.entity.MattersStoreIos;
import lombok.Data;

import java.io.Serializable;

/**
 * @author sun
 * @version v1.0
 * @date 2018-09-07 09:57
 */
@Data
public class MattersStoreDto extends MattersStoreIos implements Serializable {

    private static final long serialVersionUID = -5593525942442066915L;
    //物料信息
    private String mattersInfo;
    //规格/型号/参数
    private String allInfo;
    //物料分类名称
    private String typeName;
    private String backMattersInfo;
    private String plmDesc;
    private String backUpMatterNo;
    private Long amount;
    private Boolean leaf = false;
    private Long serial;
    private String backupMatter;
    private Integer statusId;

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getBackupMatter() {
        return backupMatter;
    }

    public void setBackupMatter(String backupMatter) {
        this.backupMatter = backupMatter;
    }

    public String getMattersInfo() {
        return mattersInfo;
    }

    public void setMattersInfo(String mattersInfo) {
        this.mattersInfo = mattersInfo;
    }

    public String getAllInfo() {
        return allInfo;
    }

    public void setAllInfo(String allInfo) {
        this.allInfo = allInfo;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getBackMattersInfo() {
        return backMattersInfo;
    }

    public void setBackMattersInfo(String backMattersInfo) {
        this.backMattersInfo = backMattersInfo;
    }

    public String getPlmDesc() {
        return plmDesc;
    }

    public void setPlmDesc(String plmDesc) {
        this.plmDesc = plmDesc;
    }

    public String getBackUpMatterNo() {
        return backUpMatterNo;
    }

    public void setBackUpMatterNo(String backUpMatterNo) {
        this.backUpMatterNo = backUpMatterNo;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Boolean getLeaf() {
        return leaf;
    }

    public void setLeaf(Boolean leaf) {
        this.leaf = leaf;
    }

    public Long getSerial() {
        return serial;
    }

    public void setSerial(Long serial) {
        this.serial = serial;
    }
}
