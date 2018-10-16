package com.zhiyun.dto;

import com.zhiyun.entity.ProdBomPlm;
import com.zhiyun.entity.ProductStorePlm;
import lombok.Data;

import java.util.List;

/**
 * @author 邓艺
 * @version v1.0
 * @date 2018-10-07 15:47
 */
@Data
public class ProdBomPlmDto extends ProdBomPlm {
    private String productName;
    private List<MattersStoreDto> mattersStoreDtos;
    private ProductStorePlm productStorePlm;
    private List<ProductMidPlmDto> productMidPlms;
    //默认是有下级
    private Boolean leaf = false;
    private String voucherStatus;
    private String mattersName;
    private String mattersNo;

}
