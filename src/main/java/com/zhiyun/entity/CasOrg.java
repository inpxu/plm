package com.zhiyun.entity;

import com.zhiyun.base.entity.BaseEntity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;
import java.util.HashMap;
import java.util.Map;

/**
 * 实体对象：
 */
public class CasOrg extends BaseEntity<Long> {

    private static final long serialVersionUID = 3206702822881326116L;

    // ~~~~实体属性
    // 部门编码
    @Pattern(regexp = "[\\S]{0,40}", message = "部门编码长度不能超过40")
    private String orgCode;
    // 部门名称
    @Pattern(regexp = "[\\S]{0,20}", message = "部门名称长度不能超过20")
    private String orgName;
    // 父主建
    private Long parentId;
    // 是否叶节点
    private Boolean isLeaf;
    //
    @Pattern(regexp = "[\\S]{0,255}", message = "长度不能超过255")
    private String orgIdPath;
    // 部门树
    @Pattern(regexp = "[\\S]{0,100}", message = "部门树长度不能超过100")
    private String orgPath;
    // 部门权责描述
    //@Pattern(regexp = "[\\S]{0,255}", message = "部门权责描述长度不能超过255")
    private String description;
    // 部门第二负责人
    @Pattern(regexp = "[\\S]{0,255}", message = "部门第二负责人长度不能超过255")
    private String secondleader;
    // 部门负责人
    @Pattern(regexp = "[\\S]{0,255}", message = "部门负责人长度不能超过255")
    private String leader;
    // 目标雇员数
    @Max(value = 100000, message = "目标雇员数人数不能超过100000")
    private Long hires;
    // 排序
    @Max(value = 10, message = "排序长度不能超过10")
    private Long orders;
    // 企业标识
    @Max(value = 19, message = "企业标识长度不能超过19")
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
     * 部门编码
     */
    public String getOrgCode() {
        return this.orgCode;
    }

    /**
     * 部门编码
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    /**
     * 部门名称
     */
    public String getOrgName() {
        return this.orgName;
    }

    /**
     * 部门名称
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    /**
     * 父主键
     */
    public Long getParentId() {
        return this.parentId;
    }

    /**
     * 父主键
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 是否叶节点
     */
    public Boolean getIsLeaf() {
        return this.isLeaf;
    }

    /**
     * 是否叶节点
     */
    public void setIsLeaf(Boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    /**
     *
     */
    public String getOrgIdPath() {
        return this.orgIdPath;
    }

    /**
     *
     */
    public void setOrgIdPath(String orgIdPath) {
        this.orgIdPath = orgIdPath;
    }

    /**
     * 部门树
     */
    public String getOrgPath() {
        return this.orgPath;
    }

    /**
     * 部门树
     */
    public void setOrgPath(String orgPath) {
        this.orgPath = orgPath;
    }

    /**
     * 部门权责描述
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * 部门权责描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 部门第二负责人
     */
    public String getSecondleader() {
        return this.secondleader;
    }

    /**
     * 部门第二负责人
     */
    public void setSecondleader(String secondleader) {
        this.secondleader = secondleader;
    }

    /**
     * 部门负责人
     */
    public String getLeader() {
        return this.leader;
    }

    /**
     * 部门负责人
     */
    public void setLeader(String leader) {
        this.leader = leader;
    }

    /**
     * 目标雇员数
     */
    public Long getHires() {
        return this.hires;
    }

    /**
     * 目标雇员数
     */
    public void setHires(Long hires) {
        this.hires = hires;
    }

    /**
     * 排序
     */
    public Long getOrders() {
        return this.orders;
    }

    /**
     * 排序
     */
    public void setOrders(Long orders) {
        this.orders = orders;
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

    public Map<String, Object> modelAttributes() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", this.getId());
        map.put("orgCode", this.getOrgCode());
        map.put("orgName", this.getOrgName());
        map.put("parentId", this.getParentId());
        map.put("isLeaf", this.getIsLeaf());
        map.put("orgIdPath", this.getOrgIdPath());
        map.put("orgPath", this.getParentId());
        map.put("description", this.getDescription());
        map.put("secondleader", this.getSecondleader());
        map.put("leader", this.getLeader());
        map.put("hires", this.getHires());
        map.put("orders", this.getOrders());
        map.put("companyId", this.getCompanyId());
        return map;
    }
}
