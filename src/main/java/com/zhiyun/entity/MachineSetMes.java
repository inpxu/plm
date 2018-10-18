/*
 * 嘉兴飞戎智云软件有限公司版权所有
 * Copyright (c) 2018. zhiyun and/or its affiliates. All rights reserved.
 */

package com.zhiyun.entity;

import com.zhiyun.base.entity.BaseEntity;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Max;

/**
 * 实体类
 *
 * @author auto
 * @version v1.0
 * @date
 */
public class MachineSetMes extends BaseEntity<Long> {

	private static final long serialVersionUID = 2422424017721269803L;

	// ~~~~实体属性
	// 设备编码
	@Pattern(regexp="[\\S]{0,40}", message="设备编码字段过长")
	private String macNo;
	// 设备名称
	@Pattern(regexp="[\\S]{0,40}", message="设备名称字段过长")
	private String macName;
	// 所属场地id
	@Max(value=9223372036854775807L,message="所属场地id字段过长")
	private Long factoryId;
	// 所属部门
	@Max(value=9223372036854775807L,message="所属部门字段过长")
	private Long orgId;
	// 设备类型
	@Pattern(regexp="[\\S]{0,20}", message="设备类型字段过长")
	private String macType;
	// 工艺id
	@Max(value=9223372036854775807L,message="工艺id字段过长")
	private Long crafworkId;
	// 设备责任人
	@Pattern(regexp="[\\S]{0,30}", message="设备责任人字段过长")
	private String liable;
	// 最小开机量
	private java.math.BigDecimal minAmount;
	// 最小开机人数
	@Max(value=99999999999L,message="最小开机人数字段过长")
	private Integer minEmp;
	// 是否品检
	@Pattern(regexp="[\\S]{0,1}", message="是否品检字段过长")
	private String isInspection;
	// 品牌
	@Pattern(regexp="[\\S]{0,40}", message="品牌字段过长")
	private String brand;
	// 参数规格
	@Pattern(regexp="[\\S]{0,65535}", message="参数规格字段过长")
	private String spec;
	// 正常维修保养中停用
	@Pattern(regexp="[\\S]{0,10}", message="正常维修保养中停用字段过长")
	private String macStatus;
	// 0否/1是
	@Max(value=99999999999L,message="0否/1是字段过长")
	private Integer isFinish;
	// 机联网编码
	@Pattern(regexp="[\\S]{0,30}", message="机联网编码字段过长")
	private String barcode;
	// 二维码
	@Pattern(regexp="[\\S]{0,30}", message="二维码字段过长")
	private String qrcode;
	// 排列位置
	@Pattern(regexp="[\\S]{0,40}", message="排列位置字段过长")
	private String position;
	// 排序号
	@Max(value=99999999999L,message="排序号字段过长")
	private Integer serial;
	// 建立日期
	private java.util.Date builddate;
	// 失效日期
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
	 * 设备编码
	 */
	public String getMacNo() {
		return this.macNo;
	}

	/**
	 * 设备编码
	 */
	public void setMacNo(String macNo) {
		this.macNo = macNo;
	}
	
	/**
	 * 设备名称
	 */
	public String getMacName() {
		return this.macName;
	}

	/**
	 * 设备名称
	 */
	public void setMacName(String macName) {
		this.macName = macName;
	}
	
	/**
	 * 所属场地id
	 */
	public Long getFactoryId() {
		return this.factoryId;
	}

	/**
	 * 所属场地id
	 */
	public void setFactoryId(Long factoryId) {
		this.factoryId = factoryId;
	}
	
	/**
	 * 所属部门
	 */
	public Long getOrgId() {
		return this.orgId;
	}

	/**
	 * 所属部门
	 */
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	
	/**
	 * 设备类型
	 */
	public String getMacType() {
		return this.macType;
	}

	/**
	 * 设备类型
	 */
	public void setMacType(String macType) {
		this.macType = macType;
	}
	
	/**
	 * 工艺id
	 */
	public Long getCrafworkId() {
		return this.crafworkId;
	}

	/**
	 * 工艺id
	 */
	public void setCrafworkId(Long crafworkId) {
		this.crafworkId = crafworkId;
	}
	
	/**
	 * 设备责任人
	 */
	public String getLiable() {
		return this.liable;
	}

	/**
	 * 设备责任人
	 */
	public void setLiable(String liable) {
		this.liable = liable;
	}
	
	/**
	 * 最小开机量
	 */
	public java.math.BigDecimal getMinAmount() {
		return this.minAmount;
	}

	/**
	 * 最小开机量
	 */
	public void setMinAmount(java.math.BigDecimal minAmount) {
		this.minAmount = minAmount;
	}
	
	/**
	 * 最小开机人数
	 */
	public Integer getMinEmp() {
		return this.minEmp;
	}

	/**
	 * 最小开机人数
	 */
	public void setMinEmp(Integer minEmp) {
		this.minEmp = minEmp;
	}
	
	/**
	 * 是否品检
	 */
	public String getIsInspection() {
		return this.isInspection;
	}

	/**
	 * 是否品检
	 */
	public void setIsInspection(String isInspection) {
		this.isInspection = isInspection;
	}
	
	/**
	 * 品牌
	 */
	public String getBrand() {
		return this.brand;
	}

	/**
	 * 品牌
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	/**
	 * 参数规格
	 */
	public String getSpec() {
		return this.spec;
	}

	/**
	 * 参数规格
	 */
	public void setSpec(String spec) {
		this.spec = spec;
	}
	
	/**
	 * 正常维修保养中停用
	 */
	public String getMacStatus() {
		return this.macStatus;
	}

	/**
	 * 正常维修保养中停用
	 */
	public void setMacStatus(String macStatus) {
		this.macStatus = macStatus;
	}
	
	/**
	 * 0否/1是
	 */
	public Integer getIsFinish() {
		return this.isFinish;
	}

	/**
	 * 0否/1是
	 */
	public void setIsFinish(Integer isFinish) {
		this.isFinish = isFinish;
	}
	
	/**
	 * 机联网编码
	 */
	public String getBarcode() {
		return this.barcode;
	}

	/**
	 * 机联网编码
	 */
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	
	/**
	 * 二维码
	 */
	public String getQrcode() {
		return this.qrcode;
	}

	/**
	 * 二维码
	 */
	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}
	
	/**
	 * 排列位置
	 */
	public String getPosition() {
		return this.position;
	}

	/**
	 * 排列位置
	 */
	public void setPosition(String position) {
		this.position = position;
	}
	
	/**
	 * 排序号
	 */
	public Integer getSerial() {
		return this.serial;
	}

	/**
	 * 排序号
	 */
	public void setSerial(Integer serial) {
		this.serial = serial;
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
