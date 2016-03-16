package com.lundui.manage.stock.service;

import java.util.Date;
import java.util.List;

import com.lundui.manage.model.Income;
import com.lundui.manage.model.WheelRecord;
import com.lundui.manage.util.PageModel;

/**
 * 轮对收入管理
 * @author Administrator
 *
 */
public interface IncomeManageService {
	
	/**
	 * 添加一个轮对收入
	 * @param in
	 * @param isTPAdd 是否是临修添加
	 * @return
	 */
	public Income addIncome(Income in,WheelRecord record,boolean isTPAdd);
	
	/**
	 * 添加一个新购轮对
	 * @param in
	 * @param record
	 * @return
	 */
	public Income addNewIncome(Income in,WheelRecord record);
	
	/**
	 * 删除一个轮对收入
	 * @param in
	 */
	public void deleteIncome(Income in);
	
	/**
	 * 修改一个轮对收入
	 * @param in
	 * @return
	 */
	public Income updateIncome(Income in);
	
	/**
	 * 获取一个轮对收入的信息
	 * @param id
	 * @return
	 */
	public Income getIncome(Long id);
	
	/**
	 * 查询
	 * @return
	 */
	public PageModel<Income> queryIncome();
	
	/**
	 * 条件查询
	 * @param queryName
	 * @param value
	 * @param dataFrom
	 * @param dateTo
	 * @param status
	 * @return
	 */
	@Deprecated
	public PageModel<Income> queryIncome(String queryName,String value,Date dataFrom,Date dateTo,Short status);
	
	/**
	 * 查询收入信息
	 * @param axleNum 轴号
	 * @param axleType 轴型
	 * @param wheelType 轮型
	 * @param timeFrom 收入时间开始
	 * @param timeTo 收入时间结束
	 * @param status 轮对状态
	 * @param factory 轮对生产公司
	 * @param depotCode 收入单位
	 * @param sub 是否包含下级单位的
	 * @return
	 */
	public PageModel<Income> queryIncom(String axleNum,String axleType,String wheelType, Date timeFrom,Date timeTo,Short status,String factory,String depotCode,boolean sub);

	/**
	 * 根据轮对履历id查询当前轮对的最近收入记录
	 * @param id 轮对履历id
	 * @return
	 */
	public Income queryLastestIncomeByWheel(Long id);
	
	/**
	 * 根据轴号查询轮对履历信息
	 * @param axleNum
	 * @return
	 */
	public List<WheelRecord> getWheelRecordByAxleNum(String axleNum);

}
