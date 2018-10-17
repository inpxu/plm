package com.zhiyun.form;

import com.zhiyun.dto.ProdMacPlmDto;
import lombok.Data;

import java.util.List;

@Data
public class ProdMacPlmForm {

    private List<ProdMacPlmDto> prodMacPlmDtos;
    // 产品编码
    private String prodNo;
    // 半成品编码
    private String midProdNo;
    // 工艺id
    private Long crafworkId;

}