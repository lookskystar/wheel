package com.lundui.manage.model;

import java.io.Serializable;

/**
 * 轮对库存表
 * @author Administrator
 *
 */
public class WheelStock implements Serializable{
	
	private static final long serialVersionUID = -1633859209712116350L;
	
	private Long id;
	
	/**
	 * 轴型
	 */
	private String axleType;

	/**
	 * 轮型
	 */
	private String wheelType;

	/**
	 * 库存量
	 */
	private Integer inventory;
	
	/**
	 * 良好数量
	 */
	private Integer goodNum;
	
	/**
	 * 不良好数量
	 */
	private Integer notGoodNum;
	
	/**
	 * 最小库存量
	 */
	private Integer minStock=0;
	
	
	/**
	 * 不良库存上限
	 */
	private Integer maxBadStock=0;
	
	/**
	 * 送修数量
	 */
	private Integer sxNum=0;
	
	/**
	 * 装车数量
	 */
	private Integer zcNum=0;
	
	/**
	 * 所在地编码
	 */
	private String depotCode;
	
	/**
	 * 所在地
	 */
	private String depotName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getInventory() {
		return inventory;
	}

	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}

	public Integer getGoodNum() {
		return goodNum;
	}

	public void setGoodNum(Integer goodNum) {
		this.goodNum = goodNum;
	}

	public Integer getNotGoodNum() {
		return notGoodNum;
	}

	public void setNotGoodNum(Integer notGoodNum) {
		this.notGoodNum = notGoodNum;
	}

	public Integer getMinStock() {
		return minStock;
	}

	public void setMinStock(Integer minStock) {
		this.minStock = minStock;
	}
	public String getAxleType() {
		return axleType;
	}

	public void setAxleType(String axleType) {
		this.axleType = axleType;
	}

	public String getWheelType() {
		return wheelType;
	}

	public void setWheelType(String wheelType) {
		this.wheelType = wheelType;
	}

	public Integer getMaxBadStock() {
		return maxBadStock;
	}

	public void setMaxBadStock(Integer maxBadStock) {
		this.maxBadStock = maxBadStock;
	}

	public String getDepotCode() {
		return depotCode;
	}

	public void setDepotCode(String depotCode) {
		this.depotCode = depotCode;
	}

	public String getDepotName() {
		return depotName;
	}

	public void setDepotName(String depotName) {
		this.depotName = depotName;
	}

	public Integer getSxNum() {
		return sxNum;
	}

	public void setSxNum(Integer sxNum) {
		this.sxNum = sxNum;
	}

	public Integer getZcNum() {
		return zcNum;
	}

	public void setZcNum(Integer zcNum) {
		this.zcNum = zcNum;
	}

	

}
