package com.lundui.manage.model;

import java.io.Serializable;

/**
 * 角色
 * @author Administrator
 *
 */
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	/**
	 * 角色名
	 */
	private String roleName;
	
	/**
	 * 角色标识
	 */
	private String roleMark;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleMark() {
		return roleMark;
	}

	public void setRoleMark(String roleMark) {
		this.roleMark = roleMark;
	}
	
	

}
