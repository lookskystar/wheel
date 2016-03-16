package com.lundui.manage.stock.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.lundui.manage.model.Income;
import com.lundui.manage.model.Outlay;
import com.lundui.manage.model.TemporaryRepair;
import com.lundui.manage.model.User;
import com.lundui.manage.model.WheelRecord;
import com.lundui.manage.stock.dao.TemporaryRepairDao;
import com.lundui.manage.stock.dao.WheelRecordDao;
import com.lundui.manage.stock.dao.WheelStockDao;
import com.lundui.manage.stock.service.IncomeManageService;
import com.lundui.manage.stock.service.OutlayManageService;
import com.lundui.manage.stock.service.TempRepairManageService;
import com.lundui.manage.system.dao.UserDao;
import com.lundui.manage.util.PageModel;

public class TempRepairManageServiceImpl implements TempRepairManageService{
	
	@Resource(name="tempRepairDao")
	private TemporaryRepairDao dao;
	
	@Resource(name="wheelRecordDao")
	private WheelRecordDao recordDao;
	
	@Resource(name="wheelStockDao")
	private WheelStockDao stockDao;
	
	@Resource(name="outlayManageService")
	private OutlayManageService outlayService;
	
	@Resource(name="incomeManageService")
	private IncomeManageService incomeService;
	
	@Resource(name="userDao")
	private UserDao userDao;
	

	@Override
	public TemporaryRepair addTemporaryRepair(TemporaryRepair repair,WheelRecord in ,WheelRecord out){
		WheelRecord record=recordDao.getEntity(in.getId());//收入的轮对
		String jcNum=in.getJcNum();
		String position=in.getPosition();
		//设置收入用户
		User user=userDao.getUserByJobNum(repair.getHandlerUserNum());//处理人员
		repair.setHandlerUserName(user.getUsername());
		User cuser=userDao.getUserByJobNum(repair.getComfirmUserNum());//确认人员
		repair.setComfirmUserName(cuser.getUsername());
		if(repair.getTreatment()==1){//换轮
			//收入轮对入库
			record.setStatus((short)1);//状态不良好
			Income income=new Income();
			income.setDepotCode(repair.getDepotCode());
			income.setDepotName(repair.getDepotName());
			income.setReason((short)3);//临修
			income.setRecord(record);
			income.setTime(repair.getTime());
			income.setUserName(repair.getHandlerUserName());
			income.setUserNum(repair.getHandlerUserNum());
			this.incomeService.addIncome(income, record,true);//添加收入记录
			WheelRecord outRec=recordDao.getEntity(out.getId());//支出的轮对
			outRec.setJcNum(jcNum);
			outRec.setPosition(position);
			Outlay outlay=new Outlay();
			outlay.setDepotCode(repair.getDepotCode());
			outlay.setDepotName(repair.getDepotName());
			outlay.setReason((short)1);
			outlay.setRecord(outRec);
			outlay.setRemark("临修装车,上车号:"+jcNum+",上车位:"+position);
			outlay.setTime(repair.getTime());
			outlay.setUserName(repair.getHandlerUserName());
			outlay.setUserNum(repair.getHandlerUserNum());
			this.outlayService.addOutlay(outlay, outRec);//添加支出记录
			repair.setOutRecord(outRec);
		}
		repair.setJcNum(jcNum);
		repair.setPosition(position);
		repair.setRecord(record);
		return dao.saveEntity(repair);//添加临修记录
	}

	@Override
	public TemporaryRepair updateTemporaryRepair(TemporaryRepair repair) {
		return dao.updateEntity(repair);
	}

	@Override
	public TemporaryRepair getTemporaryRepair(Long id) {
		return dao.getEntity(id);
	}

	@Override
	public void delteTemporaryRepair(TemporaryRepair repair) {
		dao.deleteEntity(repair);
	}

	@Override
	public PageModel<TemporaryRepair> findTemporaryRepair() {
		return dao.findPageModel(new Criterion[0]);
	}

	public TemporaryRepairDao getDao() {
		return dao;
	}

	public void setDao(TemporaryRepairDao dao) {
		this.dao = dao;
	}

	@Override
	public PageModel<TemporaryRepair> findTemporaryRepair(String queryName,String value,Date dateFrom,Date dateTo,Short status) {
		Criterion crit=null;
		String field=null,alias=null;
		if(queryName.equals("findTime")){
			crit=Restrictions.between(queryName, dateFrom, dateTo);
		}else if(queryName.equals("wheelCardNum")||queryName.equals("axleType")||queryName.equals("wheelTypeName")){
			field="record";
			alias="t";
			crit=Restrictions.like("t."+queryName, value, MatchMode.ANYWHERE);
		}else if(queryName.equals("username")){
			field="findUser";
			alias="u";
			crit=Restrictions.like("u."+queryName, "%"+value+"%");
		}
		Criterion[] cirts=new Criterion[1];
		cirts[0]=crit;
		if(field!=null){
			return dao.findPageModel(cirts,field,alias);
		}else{
			return dao.findPageModel(cirts);
		}
	}

	@Override
	public void delteTemporaryRepair(List<Long> ids) {
		dao.deleteTempRepairs(ids);
	}

	public WheelRecordDao getRecordDao() {
		return recordDao;
	}

	public void setRecordDao(WheelRecordDao recordDao) {
		this.recordDao = recordDao;
	}

	@Override
	public PageModel<TemporaryRepair> findTemporaryRepair(String depotCode,
			Date dateFrom, Date dateTo) {
		List<Criterion> crits=new ArrayList<Criterion>();
		if(depotCode!=null&&!depotCode.equals("")){
			crits.add(Restrictions.like("depotCode", depotCode+"%"));
		}
		if(dateFrom!=null){
			crits.add(Restrictions.ge("time", dateFrom));
		}
		if(dateTo!=null){
			crits.add(Restrictions.le("time", dateTo));
		}
		return this.dao.findPageModel(crits);
	}

	public WheelStockDao getStockDao() {
		return stockDao;
	}

	public void setStockDao(WheelStockDao stockDao) {
		this.stockDao = stockDao;
	}

	public OutlayManageService getOutlayService() {
		return outlayService;
	}

	public void setOutlayService(OutlayManageService outlayService) {
		this.outlayService = outlayService;
	}

	public IncomeManageService getIncomeService() {
		return incomeService;
	}

	public void setIncomeService(IncomeManageService incomeService) {
		this.incomeService = incomeService;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public PageModel<TemporaryRepair> findTemporaryRepair(String axleNum,
			String axleType, String wheelType, Date dateFrom, Date dateTo,
			Short treatment, String jcNum, String depotCode, boolean sub) {
		List<Criterion> crits=new ArrayList<Criterion>();
		String field="record",alias="t";
		if(axleNum!=null&&!axleNum.equals("")){
//			crits.add(Restrictions.eq("t.axleNum", axleNum));
			String axleNums = axleNum.replaceAll("\\,|\\，", ",");
			crits.add(Restrictions.in("t.axleNum", axleNums.split(",")));
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
		if(dateFrom!=null){
			crits.add(Restrictions.ge("time", dateFrom));
		}
		if(dateTo!=null){
			crits.add(Restrictions.le("time", dateTo));
		}
		if(treatment!=null){
			crits.add(Restrictions.eq("treatment", treatment));
		}
		if(jcNum!=null&&!jcNum.equals("")){
			crits.add(Restrictions.eq("jcNum", jcNum));
		}
		return this.dao.findPageModel(crits,field,alias,Order.desc("time"));
	}
	
	

}
