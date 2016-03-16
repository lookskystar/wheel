package com.lundui.manage.model;

import java.io.Serializable;

/**
 * 用户资源表
 * @author Administrator
 *
 */
public class RoleResource implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Role role;
	
	private Resource resource;
	
	/**
	 * 是否可以访问，0不能访问，1可以访问
	 */
	private Short operator=0;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public Short getOperator() {
		return operator;
	}

	public void setOperator(Short operator) {
		this.operator = operator;
	}
	
	

}
