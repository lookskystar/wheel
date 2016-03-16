package com.lundui.manage.stock.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.lundui.manage.model.Income;
import com.lundui.manage.model.User;
import com.lundui.manage.model.WheelRecord;
import com.lundui.manage.stock.dao.IncomeDao;
import com.lundui.manage.stock.dao.WheelRecordDao;
import com.lundui.manage.stock.dao.WheelStockDao;
import com.lundui.manage.stock.service.IncomeManageService;
import com.lundui.manage.stock.service.WheelStockManageService;
import com.lundui.manage.system.dao.DepotDao;
import com.lundui.manage.system.dao.UserDao;
import com.lundui.manage.util.ExcelReader;
import com.lundui.manage.util.PageModel;

public class IncomeManageServiceImpl implements IncomeManageService{
	
	@Resource(name="incomeDao")
	private IncomeDao incomeDao;
	
	@Resource(name="wheelStockDao")
	private WheelStockDao stockDao;
	
	@Resource(name="wheelRecordDao")
	private WheelRecordDao recordDao;
	
	@Resource(name="depotDao")
	private DepotDao depotDao;
	
	@Resource(name="userDao")
	private UserDao userDao;
	
	@Resource(name="wheelStockManageService")
	private WheelStockManageService stockService;

	public IncomeDao getIncomeDao() {
		return incomeDao;
	}

	public void setIncomeDao(IncomeDao incomeDao) {
		this.incomeDao = incomeDao;
	}
	
	/**
	 * 新购收入
	 */
	@Override
	public Income addNewIncome(Income in,WheelRecord record) {
		record.setWhereabouts((short)0);//轮对进入在库存中
		record.setDepotCode(in.getDepotCode());
		record.setDepotName(in.getDepotName());
		record=this.recordDao.saveEntity(record);
		in.setRecord(record);
		//添加库存记录
		stockService.addStockRecord(record.getAxleType(), record.getWheelTypeName(), in.getDepotCode(), in.getDepotName(), record.getStatus()==0?true:false,in.getReason());
		//设置操作人员
		User user=userDao.getUserByJobNum(in.getUserNum());
		in.setUserName(user.getUsername());
		//插入收入记录
		return this.incomeDao.saveEntity(in);
	}

	/**
	 * 其他类型收入，特点是不会新增履历记录，行为这时的履历是在库存中查找出来的。
	 * 收入:取出轮对履历-->更新轮对履历-->更新库存
	 * 支出:取出轮对履历-->更新库存-->更新轮对履历
	 */
	@Override
	public Income addIncome(Income in,WheelRecord record,boolean isTPAdd) {
		WheelRecord old=null;
		old=this.recordDao.getEntity(record.getId());//查询原有记录
		if(!isTPAdd){		
			old.setWhereabouts((short)0);//轮对进入在库存中
			old.setDepotCode(in.getDepotCode());
			old.setDepotName(in.getDepotName());
			//修改基本信息
			old.setAxleType(record.getAxleType());
			old.setWheelTypeName(record.getWheelTypeName());
			old.setFactory(record.getFactory());
			old.setStatus(record.getStatus());
			old.setAxleBoxType(record.getAxleBoxType());
			old.setAntiSkidLoc(record.getAntiSkidLoc());
			old.setHasAntiSkid(record.getHasAntiSkid());
			old.setHasAxleBoxRelay(record.getHasAxleBoxRelay());
			old.setCreateDate(record.getCreateDate());
			old.setAntiSkidSize(record.getAntiSkidSize());
			old.setLeftRimThickness(record.getLeftRimThickness());
			old.setRightRimThickness(record.getRightRimThickness());
			old.setLeftCircularWear(record.getLeftCircularWear());
			old.setRightCircularWear(record.getRightCircularWear());
			old.setLeftDiameter(record.getLeftDiameter());
			old.setRightDiamter(record.getRightDiamter());
			old.setLeftFlangeThickness(record.getLeftFlangeThickness());
			old.setRightFlangeThickness(record.getRightFlangeThickness());
			old.setLeftBrakeDiscWear(record.getLeftBrakeDiscWear());
			old.setRightBrakeDiscWear(record.getRightBrakeDiscWear());
			old.setInsideistance(record.getInsideistance());
		}else{//临修、段修收入，不会修改基本信息
			old.setWhereabouts((short)0);//轮对进入在库存中
			old.setDepotCode(in.getDepotCode());
			old.setJcNum(null);//清空上车信息
			old.setPosition(null);
			old.setDepotName(in.getDepotName());
		}
		this.recordDao.updateEntity(old);//更新数据
		in.setRecord(old);
		//将更新后的轮对履历添加到库存
		stockService.addStockRecord(old.getAxleType(), old.getWheelTypeName(), in.getDepotCode(), in.getDepotName(), old.getStatus()==0?true:false,in.getReason());
		//设置收入用户
		User user=userDao.getUserByJobNum(in.getUserNum());
		in.setUserName(user.getUsername());
		return incomeDao.saveEntity(in);
	}
	
	@Override
	public void deleteIncome(Income in) {
		incomeDao.deleteEntity(in);
	}

	@Override
	public Income updateIncome(Income in) {
		return incomeDao.updateEntity(in);
	}

	@Override
	public PageModel<Income> queryIncome() {
		return incomeDao.findPageModel(new Criterion[0]);
	}

	@Override
	public Income getIncome(Long id) {
		return this.incomeDao.getEntity(id);
	}

	public WheelStockDao getStockDao() {
		return stockDao;
	}

	public void setStockDao(WheelStockDao stockDao) {
		this.stockDao = stockDao;
	}

	@Override
	public PageModel<Income> queryIncome(String queryName, String value,
			Date dataFrom, Date dateTo, Short status) {
		Criterion crit=null;
		String field=null,alias=null;
		if(queryName.equals("status")){
			crit=Restrictions.eq(queryName, status);
		}else if(queryName.equals("createDate")){
			crit=Restrictions.between(queryName, dataFrom, dateTo);
		}else if(queryName.equals("wheelCardNum")||queryName.equals("axleType")||queryName.equals("wheelTypeName")){
			field="record";
			alias="t";
			crit=Restrictions.like("t."+queryName, value, MatchMode.ANYWHERE);
		}else{
			crit=Restrictions.like(queryName, "%"+value+"%");
		}
		Criterion[] cirts=new Criterion[1];
		cirts[0]=crit;
		if(field!=null){
			return incomeDao.findPageModel(cirts,field,alias);
		}else{
			return incomeDao.findPageModel(cirts,Order.desc("createDate"));
		}
	}

	public WheelRecordDao getRecordDao() {
		return recordDao;
	}

	public void setRecordDao(WheelRecordDao recordDao) {
		this.recordDao = recordDao;
	}

	public DepotDao getDepotDao() {
		return depotDao;
	}

	public void setDepotDao(DepotDao depotDao) {
		this.depotDao = depotDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public PageModel<Income> queryIncom(String axleNum, String axleType,
			String wheelType, Date timeFrom, Date timeTo, Short status,
			String factory, String depotCode,boolean sub) {
		List<Criterion> crits=new ArrayList<Criterion>();
		String field="record",alias="t";
		if(axleNum!=null&&!axleNum.equals("")){
			String axleNums = axleNum.replaceAll("\\,|\\，", ",");
			crits.add(Restrictions.in("t.axleNum", axleNums.split(",")));
//			crits.add(Restrictions.eq("t.axleNum", axleNum));
		}
		if(axleType!=null&&!axleType.equals("")){
			crits.add(Restrictions.eq("t.axleType", axleType));
		}
		if(wheelType!=null&&!wheelType.equals("")){
			crits.add(Restrictions.eq("t.wheelTypeName", wheelType));
		}
		if(depotCode!=null&&!depotCode.equals("")){
			if(sub){
				crits.add(Restrictions.like("depotCode", depotCode+"%"));
			}else{
				crits.add(Restrictions.eq("depotCode", depotCode));
			}
		}
		if(factory!=null&&!factory.equals("")){
			crits.add(Restrictions.like("t.factory", "%"+factory+"%"));
		}
		if(timeFrom!=null){
			crits.add(Restrictions.ge("time", timeFrom));
		}
		if(timeTo!=null){
			crits.add(Restrictions.le("time", timeTo));
		}
		if(status!=null){
			crits.add(Restrictions.eq("t.status", status));
		}
		return incomeDao.findPageModel(crits,field,alias,Order.desc("time"));
	}

	@Override
	public Income queryLastestIncomeByWheel(Long id) {
		return incomeDao.getLastRecordByWheelId(id);
	}

	@Override
	public List<WheelRecord> getWheelRecordByAxleNum(String axleNum) {
		return incomeDao.getWheelRecordByAxleNum(axleNum);
	}
}
