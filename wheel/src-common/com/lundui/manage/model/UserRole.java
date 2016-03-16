package com.lundui.manage.model;

import java.io.Serializable;

/**
 * 用户与角色对应
 * @author Administrator
 *
 */
public class UserRole implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	/**
	 * 用户
	 */
	private User user;
	
	/**
	 * 角色
	 */
	private Role role;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
}
