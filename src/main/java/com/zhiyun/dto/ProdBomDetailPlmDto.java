package com.zhiyun.dto;

import com.zhiyun.entity.ProdBomDetailPlm;
import lombok.Data;

@Data
public class ProdBomDetailPlmDto extends ProdBomDetailPlm {

    private static final long serialVersionUID = 4252004763733302414L;

    // 产品编码
    private String prodNo;
    // 半成品编码
    private String midProdNo;
    // 输入半成品编码
    private String matterNo;
    // 规格参数型号
    private String syntheticField;
    // 状态
    private String status;
    // 物料名
    private String mattersName;
    // 物料类型
    private String typeName;
    // 物料信息
    private String matterMsg;
    private String mattersInfo;
    private String allInfo;
    private Long weight;
    private String plmDesc;
    private String backMattersInfo;
    private String backUpMatterNo;

}