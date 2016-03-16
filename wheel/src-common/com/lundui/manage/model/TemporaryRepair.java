package com.lundui.manage.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 轮对临修表
 * @author Administrator
 *
 */
public class TemporaryRepair implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	/**
	 * 轮对卡片编号,对应一个轮对
	 */
	private WheelRecord record;
	
	/**
	 * 所在段代码
	 */
	private String depotCode;
	
	/**
	 * 段名
	 */
	private String depotName;
	
	/**
	 * 机车号
	 */
	private String jcNum;
	
	/**
	 * 车位
	 */
	private String position;
	
	/**
	 * 故障描述
	 */
	private String hitchDesc;
	
	/**
	 * 处理方式，包括换轮
	 * 1.换轮，2.其他
	 */
	private Short treatment;
	
	/**
	 * 支出轮对
	 */
	private WheelRecord outRecord;
	
	/**
	 * 处理人员
	 */
	private String handlerUserNum;
	
	private String handlerUserName;
	
	/**
	 * 确认人员
	 */
	private String comfirmUserNum;
	
	private String comfirmUserName;

	/**
	 * 处理时间
	 */
	private Date time;
	
	/**
	 * 距离前次检修行走公里
	 */
	private Integer distance;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getHitchDesc() {
		return hitchDesc;
	}

	public void setHitchDesc(String hitchDesc) {
		this.hitchDesc = hitchDesc;
	}

	public WheelRecord getRecord() {
		return record;
	}

	public void setRecord(WheelRecord record) {
		this.record = record;
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

	public String getJcNum() {
		return jcNum;
	}

	public void setJcNum(String jcNum) {
		this.jcNum = jcNum;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Short getTreatment() {
		return treatment;
	}

	public void setTreatment(Short treatment) {
		this.treatment = treatment;
	}

	public WheelRecord getOutRecord() {
		return outRecord;
	}

	public void setOutRecord(WheelRecord outRecord) {
		this.outRecord = outRecord;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getHandlerUserNum() {
		return handlerUserNum;
	}

	public void setHandlerUserNum(String handlerUserNum) {
		this.handlerUserNum = handlerUserNum;
	}

	public String getHandlerUserName() {
		return handlerUserName;
	}

	public void setHandlerUserName(String handlerUserName) {
		this.handlerUserName = handlerUserName;
	}

	public String getComfirmUserNum() {
		return comfirmUserNum;
	}

	public void setComfirmUserNum(String comfirmUserNum) {
		this.comfirmUserNum = comfirmUserNum;
	}

	public String getComfirmUserName() {
		return comfirmUserName;
	}

	public void setComfirmUserName(String comfirmUserName) {
		this.comfirmUserName = comfirmUserName;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}
	
}
