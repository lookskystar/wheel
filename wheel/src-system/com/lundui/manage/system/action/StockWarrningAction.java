package com.lundui.manage.system.action;

import com.lundui.manage.stock.service.WheelStockManageService;

/**
 * 轮对库存报警
 * @author Administrator
 *
 */
public class StockWarrningAction {
	
	private WheelStockManageService stockService;

	public WheelStockManageService getStockService() {
		return stockService;
	}

	public void setStockService(WheelStockManageService stockService) {
		this.stockService = stockService;
	}
	
	

}
