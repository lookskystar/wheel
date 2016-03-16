package com.lundui.manage.model;

import java.io.Serializable;

/**
 * 机车类型表
 * @author Administrator
 *
 */
public class JCType implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6497345642349313241L;

	private Long id;
	
	/**
	 * 类型如：DF4、DF4D、DF7、DF7D、SS3、SS3B等
	 */
	private String name;
	
	/**
	 * 机车类别
	 */
	private JCCategory category;

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

	public JCCategory getCategory() {
		return category;
	}

	public void setCategory(JCCategory category) {
		this.category = category;
	}
	
	

}
