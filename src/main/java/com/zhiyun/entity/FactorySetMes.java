/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhiyun.base.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Max;

/**
 * 实体类
 *
 * @author auto
 * @version v1.0
 * @date
 */
public class FactorySetMes extends BaseEntity<Long> {

	private static final long serialVersionUID = 3203930800799187953L;

	// ~~~~实体属性
	// 场地id
	@Max(value=9223372036854775807L,message="场地id字段过长")
	private Long factoryId;
	// 场地名称
	@Pattern(regexp="[\\S]{0,40}", message="场地名称字段过长")
	private String factoryName;
	// 本厂外协厂
	@Pattern(regexp="[\\S]{0,10}", message="本厂外协厂字段过长")
	private String factoryType;
	// 场地描述
	@Pattern(regexp="[\\S]{0,65535}", message="场地描述字段过长")
	private String factoryDesc;
	// 管理者
	@Pattern(regexp="[\\S]{0,60}", message="管理者字段过长")
	private String manager;
	// 建立日期
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date builddate;
	// 失效日期
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date losedate;
	// company_id
	@Max(value=9223372036854775807L,message="company_id字段过长")
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
	 * 场地id
	 */
	public Long getFactoryId() {
		return this.factoryId;
	}

	/**
	 * 场地id
	 */
	public void setFactoryId(Long factoryId) {
		this.factoryId = factoryId;
	}
	
	/**
	 * 场地名称
	 */
	public String getFactoryName() {
		return this.factoryName;
	}

	/**
	 * 场地名称
	 */
	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}
	
	/**
	 * 本厂外协厂
	 */
	public String getFactoryType() {
		return this.factoryType;
	}

	/**
	 * 本厂外协厂
	 */
	public void setFactoryType(String factoryType) {
		this.factoryType = factoryType;
	}
	
	/**
	 * 场地描述
	 */
	public String getFactoryDesc() {
		return this.factoryDesc;
	}

	/**
	 * 场地描述
	 */
	public void setFactoryDesc(String factoryDesc) {
		this.factoryDesc = factoryDesc;
	}
	
	/**
	 * 管理者
	 */
	public String getManager() {
		return this.manager;
	}

	/**
	 * 管理者
	 */
	public void setManager(String manager) {
		this.manager = manager;
	}
	
	/**
	 * 建立日期
	 */
	public java.util.Date getBuilddate() {
		return this.builddate;
	}

	/**
	 * 建立日期
	 */
	public void setBuilddate(java.util.Date builddate) {
		this.builddate = builddate;
	}
	
	/**
	 * 失效日期
	 */
	public java.util.Date getLosedate() {
		return this.losedate;
	}

	/**
	 * 失效日期
	 */
	public void setLosedate(java.util.Date losedate) {
		this.losedate = losedate;
	}
	
	/**
	 * company_id
	 */
	public Long getCompanyId() {
		return this.companyId;
	}

	/**
	 * company_id
	 */
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
}
