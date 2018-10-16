package com.zhiyun.dto;

import com.zhiyun.entity.FormulaBomPlm;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author 邓艺
 * @version v1.0
 * @date 2018-10-14 18:34
 */
@Data
public class FormulaDto implements Serializable {

    private static final long serialVersionUID = 6143366408785568926L;
    private String bomNo;
    private String productName;
    private String versions;
    private String bomStatus;
    private List<FormulaBomPlm> formulaBomPlms;

}
