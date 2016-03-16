package com.lundui.manage.stock.service;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import com.lundui.manage.model.WheelRec;
import com.lundui.manage.model.WheelRecord;
import com.lundui.manage.util.PageModel;

/**
 * 轮对履历管理Service
 * @author Administrator
 *
 */
public interface WheelRecordManageService {
	
	/**
	 * 添加轮对履历
	 * @param record
	 * @return
	 */
	public WheelRecord addWheelRecord(WheelRecord record);
	
	
	public int saveFromExcel(File file);
	
	/**
	 * 删除轮对履历
	 * @param record
	 */
	public void deleteWheelRecord(WheelRecord record);
	
	/**
	 * 更新轮对履历记录
	 * @param record
	 * @return
	 */
	public WheelRecord updateWheelRecord(WheelRecord record);
	
	/**
	 * 新增轮对履历修改记录
	 * @param record
	 * @return
	 */
	public WheelRec saveWheelRec(WheelRec wheelrec);
	
	/**
	 * 查询轮对履历
	 * @param axleNum 轴号
	 * @param axleType 轴型
	 * @param wheelType 轮型
	 * @param depotCode 段代码
	 * @param factory 车轴制造厂
	 * @param createDateFrom 制造日期开始
	 * @param createDateTO 制造日期结束
	 * @param status 状态
	 * @param whereabouts 去向
	 * @return
	 */
	public PageModel<WheelRecord> queryWheelRecodList(String axleNum,String axleType,String wheelType,String depotCode,String factory,Date createDateFrom,Date createDateTO,Short status,Short whereabouts,boolean sub);
	
	public PageModel<WheelRecord> queryWheelRecodList(String axleNum,String axleType,String wheelType,String depotCode,String factory,Date createDateFrom,Date createDateTO,Short status,Short whereabouts,boolean sub,Double diameterFrom,Double diameterTo);
	
	/**
	 * 查询轮对修改记录
	 */
	public PageModel<WheelRec> queryWheelRecList(String axleNum,String oldaxleNum,String userName,String depotCode,Date beginTime,Date endTime,boolean sub);
	
	
	/**
	 * 查询轮对履历
	 * @return
	 */
	public PageModel<WheelRecord> queryWheelRecordList(String depotCode);
	
	public PageModel<WheelRecord> queryWheelRecordList(String queryName,String value,Date dataFrom,Date dateTo,Short status,String depotCode);
	
	/**
	 * 根据ID查询一条轮对履历
	 * @param id
	 * @return
	 */
	public WheelRecord getRecord(Long id);

	/**
	 * 查询自己所需的轮对（除当前轮对和报废的轮对）
	 * @param recordId 当前轮对
	 * @param axleNum 轴号
	 * @param axleType 轴型
	 * @param wheelType 轮型
	 * @param depot 段代码
	 * @param factory 车轴制造厂
	 * @return
	 */
	public PageModel<WheelRecord> queryElectiveWheelRecodList(String recordId,
			String axleNum, String axleType, String wheelType, String depot,
			String factory);

	/**
	 * 获取供下载的轮对Excel表格
	 * @return
	 */
	public InputStream getExcelFile(String axleNum,String axleType,String wheelType,String depotCode,String factory,Date createDateFrom,Date createDateTO,Short status,Short whereabouts,boolean sub);
	
	public InputStream getExcelFilebyIds(Long[] ids);
	
	/**
	 * 根据上车信息查询轮对履历
	 * @param jcNum
	 * @param position
	 * @return
	 */
	public WheelRecord queryWheelRecordBySC(String jcNum,String position);
	
	public List<WheelRecord> findWheelRecords(String jcNum);
	
	/**
	 * 根据段代码查询需要接收的轮对(不去分是检修收入还是修竣接收)
	 * @param depotCode
	 * @return
	 */
	public List<WheelRecord> findWheelRecordsByDepot(String depotCode);
}
