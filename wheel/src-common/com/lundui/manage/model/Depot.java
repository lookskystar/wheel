package com.lundui.manage.model;

import java.io.Serializable;

/**
 * 车辆段/厂/车间/集团
 * @author Administrator
 *
 */
public class Depot implements Serializable{
	
	private static final long serialVersionUID = 8356521242855424100L;

	private Long id;
	
	/**
	 * 车辆段/厂/车间名称
	 */
	private String depotName;
	
	/**
	 * 编码
	 */
	private String depotCode;
	
	/**
	 * 类别
	 * 1:集团,2:车辆厂/段 3：运用车间，4检修车间
	 * 运用车间，检修车间都是3(廖,20131205)
	 */
	private Short type;
	
	/**
	 * 父类
	 */
	private Depot parent;
	
	/**
	 * 所在地
	 */
	private String location;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDepotName() {
		return depotName;
	}

	public void setDepotName(String depotName) {
		this.depotName = depotName;
	}

	public String getDepotCode() {
		return depotCode;
	}

	public void setDepotCode(String depotCode) {
		this.depotCode = depotCode;
	}

	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public Depot getParent() {
		return parent;
	}

	public void setParent(Depot parent) {
		this.parent = parent;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	

}
