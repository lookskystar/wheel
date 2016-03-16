package com.lundui.manage.model;

import java.util.Date;

/**
 * 段修记录
 * @author Administrator
 *
 */
public class DepotRepair {
	
	private Long id;

	/**
	 * 上车号
	 */
	private String jcNum;
	
	/**
	 * 原轮对列表
	 */
	private WheelRecord srcRecord1;
	
	private WheelRecord srcRecord2;
	
	private WheelRecord srcRecord3;
	
	private WheelRecord srcRecord4;
	
	/**
	 * 替换的轮对列表
	 */
	private WheelRecord destRecrord1;
	
	private WheelRecord destRecrord2;
	
	private WheelRecord destRecrord3;
	
	private WheelRecord destRecrord4;
	
	/**
	 * 处理地
	 */
	private String depotName;
	
	private String depotCode;
	
	
	/**
	 * 用来替换的上车号
	 */
	private String jcNumRep;
	
	/**
	 * 检修时间
	 */
	private Date repairDate;
	
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getJcNum() {
		return jcNum;
	}

	public void setJcNum(String jcNum) {
		this.jcNum = jcNum;
	}

	public WheelRecord getSrcRecord1() {
		return srcRecord1;
	}

	public void setSrcRecord1(WheelRecord srcRecord1) {
		this.srcRecord1 = srcRecord1;
	}

	public WheelRecord getSrcRecord2() {
		return srcRecord2;
	}

	public void setSrcRecord2(WheelRecord srcRecord2) {
		this.srcRecord2 = srcRecord2;
	}

	public WheelRecord getSrcRecord3() {
		return srcRecord3;
	}

	public void setSrcRecord3(WheelRecord srcRecord3) {
		this.srcRecord3 = srcRecord3;
	}

	public WheelRecord getSrcRecord4() {
		return srcRecord4;
	}

	public void setSrcRecord4(WheelRecord srcRecord4) {
		this.srcRecord4 = srcRecord4;
	}

	public WheelRecord getDestRecrord1() {
		return destRecrord1;
	}

	public void setDestRecrord1(WheelRecord destRecrord1) {
		this.destRecrord1 = destRecrord1;
	}

	public WheelRecord getDestRecrord2() {
		return destRecrord2;
	}

	public void setDestRecrord2(WheelRecord destRecrord2) {
		this.destRecrord2 = destRecrord2;
	}

	public WheelRecord getDestRecrord3() {
		return destRecrord3;
	}

	public void setDestRecrord3(WheelRecord destRecrord3) {
		this.destRecrord3 = destRecrord3;
	}

	public WheelRecord getDestRecrord4() {
		return destRecrord4;
	}

	public void setDestRecrord4(WheelRecord destRecrord4) {
		this.destRecrord4 = destRecrord4;
	}

	public Date getRepairDate() {
		return repairDate;
	}

	public void setRepairDate(Date repairDate) {
		this.repairDate = repairDate;
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

	public String getJcNumRep() {
		return jcNumRep;
	}

	public void setJcNumRep(String jcNumRep) {
		this.jcNumRep = jcNumRep;
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
	
	

}
