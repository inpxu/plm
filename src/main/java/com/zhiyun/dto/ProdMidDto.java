/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.dto;

import com.zhiyun.entity.ProductMidPlm;
import lombok.Data;

import java.util.List;

/**
 * @author sun
 * @version v1.0
 * @date 2018-09-06 09:35
 */
@Data
public class ProdMidDto extends ProductMidPlm {

    private static final long serialVersionUID = 7628696957752347514L;

    // 名称
    private String cosName;

    // 来源名称
    private String sourceName;
    // 生产场地
    private String factoryName;
    // 产品信息
    private String prodMsg;
    private String prodMess;
    // 半成品信息
    private String midMsg;
    private String midProdMsg;
    // 规格参数型号
    private String syntheticField;
    // 子产品
    private List<ProdMidDto> prodMidDtos;
    // 是否有子节点
    private Boolean leaf;
    // 当前选中
    private String nowChoice;
    // 工艺路线号
    private String pathNo;
    // 状态
    private String status;
    // 版本号
    private String versions;

}
