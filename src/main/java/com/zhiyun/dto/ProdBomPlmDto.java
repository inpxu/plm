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
    private String materielMsg;
    private String prodMes;

    public String getProdMes() {
        return prodMes;
    }

    public void setProdMes(String prodMes) {
        this.prodMes = prodMes;
    }

    public String getVoucherStatus() {
        return voucherStatus;
    }

    public void setVoucherStatus(String voucherStatus) {
        this.voucherStatus = voucherStatus;
    }

    public String getMattersName() {
        return mattersName;
    }

    public void setMattersName(String mattersName) {
        this.mattersName = mattersName;
    }

    public String getMattersNo() {
        return mattersNo;
    }

    public void setMattersNo(String mattersNo) {
        this.mattersNo = mattersNo;
    }

    public String getMaterielMsg() {
        return materielMsg;
    }

    public void setMaterielMsg(String materielMsg) {
        this.materielMsg = materielMsg;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public List<MattersStoreDto> getMattersStoreDtos() {
        return mattersStoreDtos;
    }

    public void setMattersStoreDtos(List<MattersStoreDto> mattersStoreDtos) {
        this.mattersStoreDtos = mattersStoreDtos;
    }

    public ProductStorePlm getProductStorePlm() {
        return productStorePlm;
    }

    public void setProductStorePlm(ProductStorePlm productStorePlm) {
        this.productStorePlm = productStorePlm;
    }

    public List<ProductMidPlmDto> getProductMidPlms() {
        return productMidPlms;
    }

    public void setProductMidPlms(List<ProductMidPlmDto> productMidPlms) {
        this.productMidPlms = productMidPlms;
    }
}
