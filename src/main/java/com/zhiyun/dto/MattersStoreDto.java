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

}
