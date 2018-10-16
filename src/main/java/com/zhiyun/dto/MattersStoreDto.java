package com.zhiyun.dto;

import com.zhiyun.entity.MattersStoreIos;

/**
 * @author sun
 * @version v1.0
 * @date 2018-09-07 09:57
 */
public class MattersStoreDto extends MattersStoreIos {
    //物料信息
    private String mattersInfo;
    //规格/型号/参数
    private String allInfo;
    //物料分类名称
    private String typeName;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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
}
