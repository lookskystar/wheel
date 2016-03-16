package com.lundui.manage.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 轮对支出
 * @author Administrator
 *
 */
public class Outlay implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2085407030004378430L;

	private Long id;
	
	/**
	 * 轮对卡片编号
	 */
	private WheelRecord record;
	
	/**
	 * 收入段
	 */
	private String depotCode;
	
	/**
	 * 收入段名称
	 */
	private String depotName;
	
	/**
	 * 支出时间
	 */
	private Date time;
	
	/**
	 * 支出人员工号
	 */
	private String  userNum;
	
	/**
	 * 支出人员姓名
	 */
	private String userName;
	
	/**
	 * 支出原因（1上车，2送修，3报废,4其他,5修竣支出）
	 */
	private Short reason;
	
	/**
	 * 备注，装车修程，送修原因，报废原因
	 */
	private String remark;
	
	/**
	 * 检修单位(送修单位、接受单位)
	 */
	private String targetUnit;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public WheelRecord getRecord() {
		return record;
	}

	public void setRecord(WheelRecord record) {
		this.record = record;
	}

	public String getDepotCode() {
		return depotCode;
	}

	public void setDepotCode(String depotCode) {
		this.depotCode = depotCode;
	}

	public String getDepotName() {
		return depotName;
	}

	public void setDepotName(String depotName) {
		this.depotName = depotName;
	}

	public Short getReason() {
		return reason;
	}

	public void setReason(Short reason) {
		this.reason = reason;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTargetUnit() {
		return targetUnit;
	}

	public void setTargetUnit(String targetUnit) {
		this.targetUnit = targetUnit;
	}


	

}
