package com.lundui.manage.lead.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lundui.manage.model.Depot;
import com.lundui.manage.model.WheelStock;
import com.lundui.manage.util.PageModel;

public interface LeadManageService {
	
	/**
	 * 获取收入月报表
	 * @param year
	 * @param month
	 * @return
	 */
	public List<Object>  findIncomeRecordByMonth(Date dateFrom,Date dateTo,Depot depot);
	
	/**
	 * 获取支出月报表
	 * @param year
	 * @param month
	 * @return
	 */
	public Map<String,Map<String,Integer>> findOutlayReportByMonth(Date dateFrom,Date dateTo,Depot depot);
	
	public int[] findStockByDepot(String depotCode);
	
	public PageModel<WheelStock> findStockPageByDepot(String depotCode);
	
	public List<WheelStock> findSubStocksByDepot(String depotCode);

}
