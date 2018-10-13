package com.zhiyun.dto;

import com.zhiyun.entity.ProductMidPlm;
import com.zhiyun.entity.ProductStorePlm;

import java.util.List;

public class ProductStorePlmDto extends ProductStorePlm {

    private static final long serialVersionUID = 6596522284766947381L;

    // 分类名称
    private String typeDesc;
    // 规格、参数、型号
    private String prodMess;
    // 仓库名称
    private String storeName;
    // 生产场地
    private String factoryName;
    // 当前选中
    private String nowChoice;
    // 是否有子节点
    private Boolean leaf;
    // 工艺路线号
    private String pathNo;
    // 状态
    private String status;
    // 版本号
    private String versions;
    // 半成品编码
    private String midProdNo;
    // 产品信息
    private String midMsg;
    private List<ProductMidPlm> midProducts;

    public List<ProductMidPlm> getMidProducts() {
        return midProducts;
    }

    public void setMidProducts(List<ProductMidPlm> midProducts) {
        this.midProducts = midProducts;
    }

    public String getMidMsg() {
        return midMsg;
    }

    public void setMidMsg(String midMsg) {
        this.midMsg = midMsg;
    }

    public String getMidProdNo() {
        return midProdNo;
    }

    public void setMidProdNo(String midProdNo) {
        this.midProdNo = midProdNo;
    }

    public String getPathNo() {
        return pathNo;
    }

    public void setPathNo(String pathNo) {
        this.pathNo = pathNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVersions() {
        return versions;
    }

    public void setVersions(String versions) {
        this.versions = versions;
    }

    public Boolean getLeaf() {
        return leaf;
    }

    public void setLeaf(Boolean leaf) {
        this.leaf = leaf;
    }

    public String getNowChoice() {
        return nowChoice;
    }

    public void setNowChoice(String nowChoice) {
        this.nowChoice = nowChoice;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    public String getProdMess() {
        return prodMess;
    }

    public void setProdMess(String prodMess) {
        this.prodMess = prodMess;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }
}