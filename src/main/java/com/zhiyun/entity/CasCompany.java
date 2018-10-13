package com.zhiyun.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhiyun.base.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 实体对象：
 */
public class CasCompany extends BaseEntity<Long> {

    private static final long serialVersionUID = 2986591709751626448L;

    // ~~~~实体属性
    // 商铺头像
    private String heardImg;
    // 商铺名称
    private String shopName;
    // 注册时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date registerTime;
    // 企业编码
    private String companyCode;
    // 类型
    private String companyType;
    // 企业/个人名称
    private String companyName;
    // 省份
    private String province;
    // 市
    private String city;
    // 区/县
    private String country;
    // 地址
    private String address;
    // 电话
    private String phone;
    // 代理企业主键
    private Long proxyCompanyId;
    // 行业
    private String industry;
    // 法人姓名
    private String legalPerson;
    // 淘宝账号
    private String taobaoAccount;
    // 银联账号
    private String unionAccount;
    // 实名认证流程主键
    private Long authProcessId;
    // 认证状态
    private String authStatus;
    // 入住流程主键
    private Long settledProcessId;
    // 入住状态
    private String settledStatus;
    // 是否永久有效
    private Boolean isForeverValid;
    // 截止有效期
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date closeDate;
    // 是否冻结
    private Boolean isFrozen;
    // 解冻时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date unfrozenTime;
    // 是否访问控制
    private Boolean isAccessControl;
    // 身份证正面
    private String idCardPositiveUrl;
    // 身份证反面
    private String idCardNegativeUrl;
    // 营业执照
    private String busiLicenseUrl;
    // 税务登记证
    private String taxRegistrationUrl;
    // 企业标识
    private Long companyId;

    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public void setId(Long id) {
        super.setId(id);
    }

    /**
     * 商铺头像
     */
    public String getHeardImg() {
        return this.heardImg;
    }

    /**
     * 商铺头像
     */
    public void setHeardImg(String heardImg) {
        this.heardImg = heardImg;
    }

    /**
     * 商铺名称
     */
    public String getShopName() {
        return this.shopName;
    }

    /**
     * 商铺名称
     */
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    /**
     * 注册时间
     */
    public java.util.Date getRegisterTime() {
        return this.registerTime;
    }

    /**
     * 注册时间
     */
    public void setRegisterTime(java.util.Date registerTime) {
        this.registerTime = registerTime;
    }

    /**
     * 企业编码
     */
    public String getCompanyCode() {
        return this.companyCode;
    }

    /**
     * 企业编码
     */
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    /**
     * 类型
     */
    public String getCompanyType() {
        return this.companyType;
    }

    /**
     * 类型
     */
    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    /**
     * 企业/个人名称
     */
    public String getCompanyName() {
        return this.companyName;
    }

    /**
     * 企业/个人名称
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * 省份
     */
    public String getProvince() {
        return this.province;
    }

    /**
     * 省份
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 市
     */
    public String getCity() {
        return this.city;
    }

    /**
     * 市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 区/县
     */
    public String getCountry() {
        return this.country;
    }

    /**
     * 区/县
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * 地址
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * 地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 电话
     */
    public String getPhone() {
        return this.phone;
    }

    /**
     * 电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 代理企业主键
     */
    public Long getProxyCompanyId() {
        return this.proxyCompanyId;
    }

    /**
     * 代理企业主键
     */
    public void setProxyCompanyId(Long proxyCompanyId) {
        this.proxyCompanyId = proxyCompanyId;
    }

    /**
     * 行业
     */
    public String getIndustry() {
        return this.industry;
    }

    /**
     * 行业
     */
    public void setIndustry(String industry) {
        this.industry = industry;
    }

    /**
     * 法人姓名
     */
    public String getLegalPerson() {
        return this.legalPerson;
    }

    /**
     * 法人姓名
     */
    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    /**
     * 淘宝账号
     */
    public String getTaobaoAccount() {
        return this.taobaoAccount;
    }

    /**
     * 淘宝账号
     */
    public void setTaobaoAccount(String taobaoAccount) {
        this.taobaoAccount = taobaoAccount;
    }

    /**
     * 银联账号
     */
    public String getUnionAccount() {
        return this.unionAccount;
    }

    /**
     * 银联账号
     */
    public void setUnionAccount(String unionAccount) {
        this.unionAccount = unionAccount;
    }

    /**
     * 实名认证流程主键
     */
    public Long getAuthProcessId() {
        return this.authProcessId;
    }

    /**
     * 实名认证流程主键
     */
    public void setAuthProcessId(Long authProcessId) {
        this.authProcessId = authProcessId;
    }

    /**
     * 认证状态
     */
    public String getAuthStatus() {
        return this.authStatus;
    }

    /**
     * 认证状态
     */
    public void setAuthStatus(String authStatus) {
        this.authStatus = authStatus;
    }

    /**
     * 入住流程主键
     */
    public Long getSettledProcessId() {
        return this.settledProcessId;
    }

    /**
     * 入住流程主键
     */
    public void setSettledProcessId(Long settledProcessId) {
        this.settledProcessId = settledProcessId;
    }

    /**
     * 入住状态
     */
    public String getSettledStatus() {
        return this.settledStatus;
    }

    /**
     * 入住状态
     */
    public void setSettledStatus(String settledStatus) {
        this.settledStatus = settledStatus;
    }

    /**
     * 是否永久有效
     */
    public Boolean getIsForeverValid() {
        return this.isForeverValid;
    }

    /**
     * 是否永久有效
     */
    public void setIsForeverValid(Boolean isForeverValid) {
        this.isForeverValid = isForeverValid;
    }

    /**
     * 截止有效期
     */
    public java.util.Date getCloseDate() {
        return this.closeDate;
    }

    /**
     * 截止有效期
     */
    public void setCloseDate(java.util.Date closeDate) {
        this.closeDate = closeDate;
    }

    /**
     * 是否冻结
     */
    public Boolean getIsFrozen() {
        return this.isFrozen;
    }

    /**
     * 是否冻结
     */
    public void setIsFrozen(Boolean isFrozen) {
        this.isFrozen = isFrozen;
    }

    /**
     * 解冻时间
     */
    public java.util.Date getUnfrozenTime() {
        return this.unfrozenTime;
    }

    /**
     * 解冻时间
     */
    public void setUnfrozenTime(java.util.Date unfrozenTime) {
        this.unfrozenTime = unfrozenTime;
    }

    /**
     * 是否访问控制
     */
    public Boolean getIsAccessControl() {
        return this.isAccessControl;
    }

    /**
     * 是否访问控制
     */
    public void setIsAccessControl(Boolean isAccessControl) {
        this.isAccessControl = isAccessControl;
    }

    /**
     * 身份证正面
     */
    public String getIdCardPositiveUrl() {
        return this.idCardPositiveUrl;
    }

    /**
     * 身份证正面
     */
    public void setIdCardPositiveUrl(String idCardPositiveUrl) {
        this.idCardPositiveUrl = idCardPositiveUrl;
    }

    /**
     * 身份证反面
     */
    public String getIdCardNegativeUrl() {
        return this.idCardNegativeUrl;
    }

    /**
     * 身份证反面
     */
    public void setIdCardNegativeUrl(String idCardNegativeUrl) {
        this.idCardNegativeUrl = idCardNegativeUrl;
    }

    /**
     * 营业执照
     */
    public String getBusiLicenseUrl() {
        return this.busiLicenseUrl;
    }

    /**
     * 营业执照
     */
    public void setBusiLicenseUrl(String busiLicenseUrl) {
        this.busiLicenseUrl = busiLicenseUrl;
    }

    /**
     * 税务登记证
     */
    public String getTaxRegistrationUrl() {
        return this.taxRegistrationUrl;
    }

    /**
     * 税务登记证
     */
    public void setTaxRegistrationUrl(String taxRegistrationUrl) {
        this.taxRegistrationUrl = taxRegistrationUrl;
    }

    /**
     * 企业标识
     */
    @Override
    public Long getCompanyId() {
        return this.companyId;
    }

    /**
     * 企业标识
     */
    @Override
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}
