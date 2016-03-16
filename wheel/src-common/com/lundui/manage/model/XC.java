package com.lundui.manage.model;

import java.io.Serializable;

/**
 * 轮对修程表
 * @author Administrator
 *
 */
public class XC implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	/**
	 * 修程，如：段修、段做厂修、临修、A2修等
	 */
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
