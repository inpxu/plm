package com.zhiyun.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhiyun.base.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 实体对象：
 */
public class CasUser extends BaseEntity<Long> {

    private static final long serialVersionUID = 4627487348106318170L;

    // ~~~~实体属性
    // 头像URL
    private String headImg;
    // 部门主键
    private Long orgId;
    // 登录账号
    private String account;
    // 姓名
    private String name;
    //员工编号
    private String empNo;
    // 电话
    private String phone;
    // 邮件
    private String email;
    // 密码
    private String password;
    // 加密key
    private String slat;
    // 职位
    private String position;
    // 是否启用
    private Boolean isAble;
    // 是否管理员
    private Boolean isAdmin;
    // 是否联系人
    private Boolean isContacts;
    // 企业标识
    private Long companyId;
    // 启用时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date ableTime;
    // 停用时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date disableTime;

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public Boolean getAble() {
        return isAble;
    }

    public void setAble(Boolean able) {
        isAble = able;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public Boolean getContacts() {
        return isContacts;
    }

    public void setContacts(Boolean contacts) {
        isContacts = contacts;
    }

    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public void setId(Long id) {
        super.setId(id);
    }

    /**
     * 头像URL
     */
    public String getHeadImg() {
        return this.headImg;
    }

    /**
     * 头像URL
     */
    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    /**
     * 部门主键
     */
    public Long getOrgId() {
        return this.orgId;
    }

    /**
     * 部门主键
     */
    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    /**
     * 登录账号
     */
    public String getAccount() {
        return this.account;
    }

    /**
     * 登录账号
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 姓名
     */
    public String getName() {
        return this.name;
    }

    /**
     * 姓名
     */
    public void setName(String name) {
        this.name = name;
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
     * 邮件
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * 邮件
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 密码
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 加密key
     */
    public String getSlat() {
        return this.slat;
    }

    /**
     * 加密key
     */
    public void setSlat(String slat) {
        this.slat = slat;
    }

    /**
     * 职位
     */
    public String getPosition() {
        return this.position;
    }

    /**
     * 职位
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * 是否启用
     */
    public Boolean getIsAble() {
        return this.isAble;
    }

    /**
     * 是否启用
     */
    public void setIsAble(Boolean isAble) {
        this.isAble = isAble;
    }

    /**
     * 是否管理员
     */
    public Boolean getIsAdmin() {
        return this.isAdmin;
    }

    /**
     * 是否管理员
     */
    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    /**
     * 是否联系人
     */
    public Boolean getIsContacts() {
        return this.isContacts;
    }

    /**
     * 是否联系人
     */
    public void setIsContacts(Boolean isContacts) {
        this.isContacts = isContacts;
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

    /**
     * 启用时间
     */
    public java.util.Date getAbleTime() {
        return this.ableTime;
    }

    /**
     * 启用时间
     */
    public void setAbleTime(java.util.Date ableTime) {
        this.ableTime = ableTime;
    }

    /**
     * 停用时间
     */
    public java.util.Date getDisableTime() {
        return this.disableTime;
    }

    /**
     * 停用时间
     */
    public void setDisableTime(java.util.Date disableTime) {
        this.disableTime = disableTime;
    }
}
