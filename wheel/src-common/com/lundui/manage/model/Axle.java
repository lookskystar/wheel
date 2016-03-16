package com.lundui.manage.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 车轴表
 * @author Administrator
 *
 */
public class Axle implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3162963977837657172L;

	private Long id;
	
	/**
	 * 轴号
	 */
	private String axleNum;
	
	/**
	 * 轴型
	 */
	private AxleType type;
	
	/**
	 * 制造日期
	 */
	private Date makeDate;
	
	/**
	 * 制造厂
	 */
	private String makeCompany;
	
	/**
	 * 状态
	 */
	private Short status;

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

	public AxleType getType() {
		return type;
	}

	public void setType(AxleType type) {
		this.type = type;
	}

	public Date getMakeDate() {
		return makeDate;
	}

	public void setMakeDate(Date makeDate) {
		this.makeDate = makeDate;
	}

	public String getMakeCompany() {
		return makeCompany;
	}

	public void setMakeCompany(String makeCompany) {
		this.makeCompany = makeCompany;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

}
