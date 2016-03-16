package com.lundui.manage.model;

import java.io.Serializable;

/**
 * 轴型表
 * @author Administrator
 *
 */
public class AxleType implements Serializable{

	private static final long serialVersionUID = 5447054330173069784L;

	private Long id;
	
	/**
	 * 轴型编号
	 */
	private String axleNum;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAxleNum() {
		return axleNum;
	}

	public void setAxleNum(String axleNum) {
		this.axleNum = axleNum;
	}
	
	
}
