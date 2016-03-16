package com.lundui.manage.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 轮对履历修改记录表
 * @author Administrator
 *
 */
public class WheelRec implements Serializable{
	
	
	private static final long serialVersionUID = -6685482650784562048L;
	
	/**
	 * 主健ID
	 */
	private Long recId;
	/**
	 * 轴号
	 */
	private String axleNum;
	
	/**
	 * 原轴号
	 */
	private String oldaxleNum;
	
	/**
	 * 修改人
	 */
	private String userName;
	

	/**
	 * 修改时间
	 */
	private Date userTime;
	
	/**
	 * 所属段
	 */
	private String depotCode;


	public Long getRecId() {
		return recId;
	}


	public void setRecId(Long recId) {
		this.recId = recId;
	}


	public String getAxleNum() {
		return axleNum;
	}


	public void setAxleNum(String axleNum) {
		this.axleNum = axleNum;
	}


	public String getOldaxleNum() {
		return oldaxleNum;
	}


	public void setOldaxleNum(String oldaxleNum) {
		this.oldaxleNum = oldaxleNum;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public Date getUserTime() {
		return userTime;
	}


	public void setUserTime(Date userTime) {
		this.userTime = userTime;
	}


	public String getDepotCode() {
		return depotCode;
	}


	public void setDepotCode(String depotCode) {
		this.depotCode = depotCode;
	}
	

	
}
