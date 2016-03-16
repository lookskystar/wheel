package com.lundui.manage.stock.service;

import java.util.Date;
import java.util.List;

import com.lundui.manage.model.Outlay;
import com.lundui.manage.model.WheelRecord;
import com.lundui.manage.util.PageModel;

public interface OutlayManageService {
	/**
	 * 获取一个轮对支出
	 * @param id
	 * @return
	 */
	public Outlay getOutlay(Long id);
	
	/**
	 * 查找轮对支出列表
	 * @return
	 */
	public PageModel<Outlay> findOutlayList();
	
	public PageModel<Outlay> findOUtlayList(String queryName,String value,Date dateFrom,Date dateTo,Short status);
	
	/**
	 * 添加轮对支出
	 * @param outlay
	 * @param record
	 * @return
	 */
	public Outlay addOutlay(Outlay outlay, WheelRecord record);
	
	/**
	 * 更新轮对支出
	 * @param outlay
	 * @return
	 */
	public Outlay updateOutlay(Outlay outlay);
	
	/**
	 * 批量删除轮对支出
	 * @param ids
	 */
	public void deleteOutlays(List<Long> ids);

	/**
	 * 查询当前轮对的最近的一次支出记录
	 * @param id 轮对履历id
	 * @return
	 */
	public Outlay findLastestOutlayByWheel(Long id);

	/**
	 * 根据查询条件查询轮对支出记录
	 * @param axleNum 轴号
	 * @param axleType 轴型
	 * @param wheelType 轮型
	 * @param dateFrom 起始时间
	 * @param dateTo 结束时间
	 * @param status 状态
	 * @param value 查询关键字
	 * @param depot 所属段
	 * @param sub 是否考虑下级单位
	 * @return
	 */
	public PageModel<Outlay> queryOutlayList(String axleNum, String axleType,
			String wheelType, Date dateFrom, Date dateTo, Short status,
			String value, String depot,boolean sub);

}
