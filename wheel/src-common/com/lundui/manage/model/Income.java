package com.lundui.manage.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 轮对收入表
 * @author Administrator
 *
 */
public class Income implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1825017116548801116L;

	private Long id;
	
	/**
	 * 轮对卡片编号
	 */
	private WheelRecord record;
	
	/**
	 * 收入段代码
	 */
	private String depotCode;
	
	/**
	 * 收入段名称
	 */
	private String depotName;
	
	/**
	 * 收入时间
	 */
	private Date time;
	
	/**
	 * 收入原因(1.新送来的，2.检修的，3.临修收入的,4其他,5修竣接收,6段修收入,7厂修收入)
	 */
	private Short reason;
	
	/**
	 * 收入人员工号
	 */
	private String userNum;
	
	/**
	 * 收入人员姓名
	 */
	private String userName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public WheelRecord getRecord() {
		return record;
	}

	public void setRecord(WheelRecord record) {
		this.record = record;
	}
	
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Short getReason() {
		return reason;
	}

	public void setReason(Short reason) {
		this.reason = reason;
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
	
	
	
}
