package com.lundui.manage.system.service;

import java.util.List;
import java.util.Map;

import com.lundui.manage.model.Depot;

public interface DepotManageService {
	
	/**
	 * 添加一个端(车间)信息
	 * @param depot
	 * @return
	 */
	public Depot addDepot(Depot depot);
	
	/**
	 * 更新一个段(车间)信息
	 * @param depot
	 * @return
	 */
	public Depot updateDepot(Depot depot);
	
	/**
	 * 删除一个段(车间)信息
	 * @param depot
	 */
	public void deleteDepot(Depot depot);
	
	public void deleteSubDepot(Depot depot);
	
	/**
	 * 插叙一条信息
	 * @param depot
	 * @return
	 */
	public Depot getDepot(Depot depot);
	
	/**
	 * 获取所有车间的信息
	 * @return
	 */
	public Map<Depot,List<Depot>> getDepotList();
	
	/**
	 * 查找该段(厂)下是否存在车间
	 */
	public long countDepot(long depotID) ;
	
	
	/**
	 * 查找该车间下是否存在用户
	 */
	public long countUser(long depotID);

}
