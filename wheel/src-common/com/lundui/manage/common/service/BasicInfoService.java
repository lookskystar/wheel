package com.lundui.manage.common.service;

import java.util.List;

import com.lundui.manage.model.AxleType;
import com.lundui.manage.model.Depot;
import com.lundui.manage.model.WheelType;
import com.lundui.manage.model.XC;
import com.lundui.manage.model.ZXType;

/**
 * 基础数据管理Service
 * 用于获取系统基础数据，这些数据直接通过数据库录入，用户无法修改。
 * @author Administrator
 *
 */
public interface BasicInfoService {
	
	/**
	 * 获取所有轴型
	 * @return
	 */
	public List<AxleType> getAxleTypes();
	
	/**
	 * 获取所有轮型
	 * @return
	 */
	public List<WheelType> getWheelTypes();
	
	/**
	 * 获取所有的修成信息
	 * @return
	 */
	public List<XC> getXCs();
	
	/**
	 * 查询当前及下级单位的段列表
	 * @param depotCode
	 * @return
	 */
	public List<Depot> getDepotList(String depotCode);
	
	/**
	 * 查询所有的段
	 * @return
	 */
	public List<Depot> getAllDepots();
	
	/**
	 * 查询所有的车间
	 * @return
	 */
	public List<Depot> getCJDepots();
	
	/**
	 * 查询一个段信息
	 * @param depotCode
	 * @return
	 */
	public Depot getDepot(String depotCode);
	
	/**
	 * 查询下级段信息
	 * @param depotCode
	 * @return
	 */
	public List<Depot> getSubDepotList(String depotCode);
	
	/**
	 * 获取所有的轴箱类型
	 * @return
	 */
	public List<ZXType> getZXTypes();
	
	/**
	 * 查询所有运用车间
	 * @return
	 */
	public List<Depot> getYYDepotList(String depotCode);

}
