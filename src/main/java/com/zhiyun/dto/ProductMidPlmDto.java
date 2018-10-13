package com.zhiyun.dto;

import com.zhiyun.entity.ProductMidPlm;
import lombok.Data;

/**
 * @author 邓艺
 * @version v1.0
 * @date 2018-10-09 12:33
 */
@Data
public class ProductMidPlmDto extends ProductMidPlm {
    private Boolean leaf = false;

}
