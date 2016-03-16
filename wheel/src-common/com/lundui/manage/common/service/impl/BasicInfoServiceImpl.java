package com.lundui.manage.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.lundui.manage.common.dao.AxleTypeDao;
import com.lundui.manage.common.dao.WheelTypeDao;
import com.lundui.manage.common.dao.XCDao;
import com.lundui.manage.common.dao.ZXTypeDao;
import com.lundui.manage.common.service.BasicInfoService;
import com.lundui.manage.model.AxleType;
import com.lundui.manage.model.Depot;
import com.lundui.manage.model.WheelType;
import com.lundui.manage.model.XC;
import com.lundui.manage.model.ZXType;
import com.lundui.manage.system.dao.DepotDao;

public class BasicInfoServiceImpl implements BasicInfoService{
	
	@Resource(name="axleTypeDao")
	private AxleTypeDao axleTypeDao;
	
	@Resource(name="wheelTypeDao")
	private WheelTypeDao wheelTypeDao;
	
	@Resource(name="xcDao")
	private XCDao xcDao;
	
	@Resource(name="zxTypeDao")
	private ZXTypeDao zxTypeDao;
	
	@Resource(name="depotDao")
	private DepotDao depotDao;

	@Override
	public List<AxleType> getAxleTypes() {
		return axleTypeDao.getList();
	}

	@Override
	public List<WheelType> getWheelTypes() {
		return wheelTypeDao.getList();
	}

	@Override
	public List<XC> getXCs() {
		return xcDao.getList();
	}

	public AxleTypeDao getAxleTypeDao() {
		return axleTypeDao;
	}

	public void setAxleTypeDao(AxleTypeDao axleTypeDao) {
		this.axleTypeDao = axleTypeDao;
	}

	public WheelTypeDao getWheelTypeDao() {
		return wheelTypeDao;
	}

	public void setWheelTypeDao(WheelTypeDao wheelTypeDao) {
		this.wheelTypeDao = wheelTypeDao;
	}

	public XCDao getXcDao() {
		return xcDao;
	}

	public void setXcDao(XCDao xcDao) {
		this.xcDao = xcDao;
	}

	public DepotDao getDepotDao() {
		return depotDao;
	}

	public void setDepotDao(DepotDao depotDao) {
		this.depotDao = depotDao;
	}

	@Override
	public List<Depot> getDepotList(String depotCode) {
		return depotDao.findDepots(depotCode);
	}

	public ZXTypeDao getZxTypeDao() {
		return zxTypeDao;
	}

	public void setZxTypeDao(ZXTypeDao zxTypeDao) {
		this.zxTypeDao = zxTypeDao;
	}

	@Override
	public List<ZXType> getZXTypes() {
		return this.zxTypeDao.getList();
	}

	@Override
	public Depot getDepot(String depotCode) {
		return this.depotDao.getDepot(depotCode);
	}

	@Override
	public List<Depot> getSubDepotList(String depotCode) {
		return this.depotDao.getSubDepotList(depotCode);
	}

	@Override
	public List<Depot> getAllDepots() {
		return this.depotDao.findDepots();
	}

	@Override
	public List<Depot> getYYDepotList(String depotCode) {
		return this.depotDao.getYYDepotList(depotCode);
	}

	@Override
	public List<Depot> getCJDepots() {
		return this.depotDao.findCJDepots();
	}
	
	

}
