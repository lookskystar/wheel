package com.lundui.manage.model;

import java.io.Serializable;


/**
 * 机车类别表
 * @author Administrator
 *
 */
public class JCCategory implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	/**
	 * 类别名,如：内燃车、电力车、和谐车等
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
