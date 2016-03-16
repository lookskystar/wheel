package com.lundui.manage.model;

import java.io.Serializable;

/**
 * 轮型表
 * @author Administrator
 *
 */
public class WheelType implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -106356262575342953L;

	private Long id;
	
	/**
	 * 轮型编号
	 */
	private String wheelNum;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWheelNum() {
		return wheelNum;
	}

	public void setWheelNum(String wheelNum) {
		this.wheelNum = wheelNum;
	}

}
