package com.lundui.manage.stock.service;

import java.util.List;

import com.lundui.manage.model.WheelStock;
import com.lundui.manage.util.PageModel;


/**
 * 轮对库存管理Service
 * @author Administrator
 *
 */
public interface WheelStockManageService {
	
	/**
	 * 获取一条库存记录
	 * @param id
	 * @return
	 */
	public WheelStock getStock(Long id);
	
	/**
	 * 更新一条库存记录
	 * @param stock
	 * @return
	 */
	public WheelStock updateStock(WheelStock stock);
	
	/**
	 * 查询库存记录
	 * @return
	 */	
	public PageModel<WheelStock> queryStocks();
	
	/**
	 * 查询库存记录
	 * @param axleType 轴型
	 * @param wheelType 轮型
	 * @param depotCode 段代码，会查询出所有下级单位的库存记录
	 * @return
	 */
	public PageModel<WheelStock> queryStocks(String axleType,String wheelType,String depotCode);
	
	/**
	 * 添加一个轮对的库存记录
	 * @param axleType 轴型
	 * @param wheelType 轮型
	 * @param depotCode 段代码
	 * @param depotName 段名
	 * @param isGood 是否良好
	 * @param reason 收入原因
	 */
	public void addStockRecord(String axleType,String wheelType,String depotCode,String depotName,boolean isGood,int reason);
	
	/**
	 * 删除一个轮对的库存记录
	 * @param axleType 轴型
	 * @param wheelType 轮型
	 * @param depotCode 段代码
	 * @param depotName 段名
	 * @param isGood 是否支出的是良好轮对
	 * @param reason 支出原因
	 */
	public void  deleteStockRecord(String axleType,String wheelType,String depotCode,String depotName,boolean isGood,int reason);
	
	/**
	 * 查询某个单位的库存信息
	 * @param depotCode
	 * @return
	 */
	public List<WheelStock> queryStock(String depotCode);

}
