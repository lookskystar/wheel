package com.lundui.manage.stock.service;

import java.util.Date;
import java.util.List;

import com.lundui.manage.model.TemporaryRepair;
import com.lundui.manage.model.WheelRecord;
import com.lundui.manage.util.PageModel;

/**
 * 轮对领袖管理
 * @author Administrator
 *
 */
public interface TempRepairManageService {
	
	/**
	 * 添加轮对临修记录
	 * @param repair
	 * @return
	 */
	public TemporaryRepair addTemporaryRepair(TemporaryRepair repair,WheelRecord in,WheelRecord out);
	
	/**
	 * 编辑轮对临修记录
	 * @param repair
	 * @return
	 */
	public TemporaryRepair updateTemporaryRepair(TemporaryRepair repair);
	
	/**
	 * 获取一条轮对临修记录
	 * @param id
	 * @return
	 */
	public TemporaryRepair getTemporaryRepair(Long id);
	
	/**
	 * 删除一条轮对临修记录
	 * @param repair
	 */
	public void delteTemporaryRepair(TemporaryRepair repair);
	
	
	/**
	 * 删除多条轮对临修记录
	 * @param repair
	 */
	public void delteTemporaryRepair(List<Long> ids);
	
	/**
	 * 查找轮对临修记录
	 * @return
	 */
	public PageModel<TemporaryRepair> findTemporaryRepair();
	
	@Deprecated
	public PageModel<TemporaryRepair> findTemporaryRepair(String queryName,String value,Date dateFrom,Date dateTo,Short status);
	
	public PageModel<TemporaryRepair> findTemporaryRepair(String depotCode,Date dateFrom ,Date dateTo);
	
	/**
	 * 查询轮对临修信息
	 * @param axleNum 故障轮对轴号
	 * @param axleType 故障轮对轴型
	 * @param wheelType 故障轮对轮型
	 * @param dateFrom 处理日期开始
	 * @param dateTo 处理日期结束
	 * @param treatment 处理方式
	 * @param jcNum 机车号
	 * @param depotCode 处理单位
	 * @param sub 是否考虑下级单位
	 * @return 
	 */
	public PageModel<TemporaryRepair> findTemporaryRepair(String axleNum,String axleType,String wheelType,Date dateFrom ,Date dateTo,Short treatment,String jcNum,String depotCode,boolean sub);

}
