package com.lundui.manage.model;

import java.io.Serializable;

public class JC implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7357281077841955773L;

	private Long id;
	
	private Integer jcNum;
	
	/**
	 * 机车所在地，长沙车辆段、广州车辆厂
	 */
	private String area;
	
	/**
	 * 机车状态 运行中、小辅修、中修
	 */
	private Short jcStatus; 
	
	/**
	 * 机车类型
	 */
	private JCType type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getJcNum() {
		return jcNum;
	}

	public void setJcNum(Integer jcNum) {
		this.jcNum = jcNum;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Short getJcStatus() {
		return jcStatus;
	}

	public void setJcStatus(Short jcStatus) {
		this.jcStatus = jcStatus;
	}

	public JCType getType() {
		return type;
	}

	public void setType(JCType type) {
		this.type = type;
	}
	
	

}
