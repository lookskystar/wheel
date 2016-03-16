package com.lundui.manage.stock.service;

import java.util.Date;

import com.lundui.manage.model.Depot;
import com.lundui.manage.model.DepotRepair;
import com.lundui.manage.model.FactoryRepair;
import com.lundui.manage.model.User;
import com.lundui.manage.util.PageModel;

public interface FactoryRepairManageService {
	
	/**
	 * 添加段修记录
	 */
	public FactoryRepair addFactoryRepair(FactoryRepair entity);
	
	public PageModel<FactoryRepair> findFactoryRepair(String jcNum,Date dateFrom,Date dateTo,String depotCode);

	/**
	 * 添加段修记录
	 * @param user 当前操作人员
	 * @param currentDepot 当前运用车间
	 * @param depotRepair 段修
	 * @return
	 */
	public FactoryRepair addFactoryRepair(User user, Depot currentDepot,
			FactoryRepair factoryRepair);

}
