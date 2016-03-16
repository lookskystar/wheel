package com.lundui.manage.stock.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.lundui.manage.model.Depot;
import com.lundui.manage.model.DepotRepair;
import com.lundui.manage.model.Income;
import com.lundui.manage.model.Outlay;
import com.lundui.manage.model.User;
import com.lundui.manage.model.WheelRecord;
import com.lundui.manage.stock.dao.DepotRepairDao;
import com.lundui.manage.stock.dao.IncomeDao;
import com.lundui.manage.stock.dao.OutlayDao;
import com.lundui.manage.stock.dao.WheelRecordDao;
import com.lundui.manage.stock.service.DepotRepairManageService;
import com.lundui.manage.stock.service.IncomeManageService;
import com.lundui.manage.stock.service.OutlayManageService;
import com.lundui.manage.util.PageModel;

public class DepotRepairServiceImpl implements DepotRepairManageService{
	
	@Resource(name="depotRepairDao")
	private DepotRepairDao depotRepairDao;

	@Resource(name="wheelRecordDao")
	private WheelRecordDao wheelRecordDao;
	
	
	@Resource(name="outlayManageService")
	private OutlayManageService outlayService;
	
	@Resource(name="incomeManageService")
	private IncomeManageService incomeService;

	
	@Override
	public DepotRepair addDepotRepair(DepotRepair entity) {
		if(entity.getSrcRecord1()!=null){
			
		}
		if(entity.getSrcRecord2()!=null){
			
		}
		if(entity.getSrcRecord3()!=null){
	
		}
		if(entity.getSrcRecord4()!=null){
	
		}
		return this.depotRepairDao.saveEntity(entity);
	}

	@Override
	public DepotRepair addDepotRepair(User user, Depot depot,
			DepotRepair entity) {
		WheelRecord record = null;
		WheelRecord destRecord = null;
		if(entity.getSrcRecord1()!=null){
			//被替换的轮对
			record = wheelRecordDao.getEntity(entity.getSrcRecord1().getId());
			//先添加一个轮对支出
			destRecord = wheelRecordDao.getEntity(entity.getDestRecrord1().getId());
			addIncomAndOutlay(user, record, destRecord, depot);
		}
		if(entity.getSrcRecord2()!=null){
			//被替换的轮对
			record = wheelRecordDao.getEntity(entity.getSrcRecord2().getId());
			//先添加一个轮对支出
			destRecord = wheelRecordDao.getEntity(entity.getDestRecrord2().getId());
			addIncomAndOutlay(user, record, destRecord, depot);
		}
		if(entity.getSrcRecord3()!=null){
			//被替换的轮对
			record = wheelRecordDao.getEntity(entity.getSrcRecord3().getId());
			//先添加一个轮对支出
			destRecord = wheelRecordDao.getEntity(entity.getDestRecrord3().getId());
			addIncomAndOutlay(user, record, destRecord, depot);
		}
		if(entity.getSrcRecord4()!=null){
			//被替换的轮对
			record = wheelRecordDao.getEntity(entity.getSrcRecord4().getId());
			//先添加一个轮对支出
			destRecord = wheelRecordDao.getEntity(entity.getDestRecrord4().getId());
			addIncomAndOutlay(user, record, destRecord, depot);
		}
		return this.depotRepairDao.saveEntity(entity);
	}
	
	/**
	 * 添加一个收入的同时，添加一个支出
	 * @param user
	 * @param record
	 * @param destRecord
	 * @param depot
	 */
	private void addIncomAndOutlay(User user,WheelRecord record,WheelRecord destRecord,
			Depot depot){
		destRecord.setWhereabouts((short)1);//轮对已上车
		destRecord.setDepotCode(depot.getDepotCode());
		destRecord.setDepotName(depot.getDepotName());
		destRecord.setJcNum(record.getJcNum()); //替换的轮对上车号
		destRecord.setPosition(record.getPosition()); //替换的轮对上车位
		destRecord = wheelRecordDao.saveOrUpdateAfterEvict(destRecord);//更新轮对信息
		
		Outlay out = new Outlay();
		out.setDepotCode(depot.getDepotCode());
		out.setDepotName(depot.getDepotName());
		out.setReason((short)1);
		out.setRecord(destRecord);
		out.setTime(new Date());
		out.setUserName(user.getUsername());
		out.setUserNum(user.getJobNum());
		outlayService.addOutlay(out, destRecord);
		
		//再添加一条轮对收入记录
		record.setWhereabouts((short)0);//轮对进入在库存中
		record.setStatus((short)1); //轮对下车后，状态就为不良好了
		record.setDepotCode(depot.getDepotCode());
		record.setDepotName(depot.getDepotName());
		record.setJcNum(null); //清空被替换的轮对上车号
		record.setPosition(null); //清空被替换的轮对上车位
		record = wheelRecordDao.saveOrUpdateAfterEvict(record);//更新轮对信息
		
		Income in = new Income();
		in.setDepotCode(depot.getDepotCode());
		in.setDepotName(depot.getDepotName());
		in.setReason((short)6);
		in.setTime(new Date());
		in.setUserNum(user.getJobNum());
		in.setUserName(user.getUsername());
		in.setRecord(record);
		incomeService.addIncome(in, record, true);;
	}
	
	@Override
	public PageModel<DepotRepair> findDepotRepairs(String jcNum, Date dateFrom,
			Date dateTo, String depotCode) {
		List<Criterion> crits=new ArrayList<Criterion>();
		if(jcNum!=null&&!"".equals(jcNum)){
			crits.add(Restrictions.eq("jcNum",jcNum));
		}
		if(dateFrom!=null){
			crits.add(Restrictions.ge("repairDate", dateFrom));
		}
		if(dateTo!=null){
			crits.add(Restrictions.le("repairDate", dateTo));
		}
		if(depotCode!=null){
			crits.add(Restrictions.like("depotCode", depotCode+"%"));
		}
		return this.depotRepairDao.findPageModel(crits,Order.desc("repairDate"));
	}

}
