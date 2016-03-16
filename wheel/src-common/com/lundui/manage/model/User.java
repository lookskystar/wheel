package com.lundui.manage.model;

import java.io.Serializable;
/**
 * 用户表
 * @author long
 *
 */
public class User implements Serializable {

	private static final long serialVersionUID = -2699030436204817211L;
	
	private Long id;
	
	/**
	 * 工号
	 */
	private String jobNum;
	
	/**
	 * 登录名
	 */
	private String loginName;
	
	/**
	 * 登录密码
	 */
	private String loginPwd;
	
	/**
	 * 人员名
	 */
	private String username;
	
	/**
	 * 所属段
	 */
	private Depot depot;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getJobNum() {
		return jobNum;
	}

	public void setJobNum(String jobNum) {
		this.jobNum = jobNum;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Depot getDepot() {
		return depot;
	}

	public void setDepot(Depot depot) {
		this.depot = depot;
	}

	

}
