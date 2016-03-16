package com.lundui.manage.stock.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.lundui.manage.model.Outlay;
import com.lundui.manage.model.User;
import com.lundui.manage.model.WheelRecord;
import com.lundui.manage.model.WheelStock;
import com.lundui.manage.stock.dao.OutlayDao;
import com.lundui.manage.stock.dao.WheelRecordDao;
import com.lundui.manage.stock.dao.WheelStockDao;
import com.lundui.manage.stock.service.OutlayManageService;
import com.lundui.manage.stock.service.WheelStockManageService;
import com.lundui.manage.system.dao.DepotDao;
import com.lundui.manage.system.dao.UserDao;
import com.lundui.manage.util.PageModel;

public class OutlayManageServiceImpl implements OutlayManageService{
	
	@Resource(name="outlayDao")
	private OutlayDao outlayDao;

	@Resource(name="depotDao")
	private DepotDao depotDao;
	
	@Resource(name="wheelRecordDao")
	private WheelRecordDao recordDao;
	
	@Resource(name="wheelStockDao")
	private WheelStockDao stockDao;
	
	@Resource(name="userDao")
	private UserDao userDao;
	
	@Resource(name="wheelStockManageService")
	private WheelStockManageService stockService;
	
	public OutlayDao getOutlayDao() {
		return outlayDao;
	}

	public void setOutlayDao(OutlayDao outlayDao) {
		this.outlayDao = outlayDao;
	}

	@Override
	public Outlay getOutlay(Long id) {
		return outlayDao.getEntity(id);
	}

	@Override
	public PageModel<Outlay> findOutlayList() {
		return outlayDao.findPageModel(new Criterion[0]);
	}

	@Override
	public Outlay addOutlay(Outlay out, WheelRecord record) {
		int reason = out.getReason().intValue();
		//获取原有的记录
		WheelRecord old=this.recordDao.getEntity(record.getId());
		//根据原有的记录，更新库存记录
		this.stockService.deleteStockRecord(old.getAxleType(), old.getWheelTypeName(), out.getDepotCode(), out.getDepotName(), old.getStatus()==0?true:false,out.getReason());
		//修改履历信息
		if(reason==1){//上车
			old.setWhereabouts((short)1);//轮对上车
			old.setJcNum(record.getJcNum());
			old.setPosition(record.getPosition());
		}else if(reason==2){//送修
			old.setWhereabouts((short)2);//轮对送修
		}else if(reason==3){//报废
			old.setStatus((short)2);
			old.setWhereabouts((short)3);//其他（轮对在库存中，但是已经报废）
		}else if(reason==4){//其他
			old.setWhereabouts((short)3);
		}else if(reason==5){//修竣支出
			old.setStatus((short)0);//修竣后将状态调回良好
			old.setWhereabouts((short)2);//不在库存中了。
		}
		record=this.recordDao.updateEntity(old);
		out.setRecord(record);	
		//设置支出用户
		User user=userDao.getUserByJobNum(out.getUserNum());
		out.setUserName(user.getUsername());
		return outlayDao.saveEntity(out);
	}

	@Override
	public Outlay updateOutlay(Outlay outlay) {
		return outlayDao.updateEntity(outlay);
	}

	@Override
	public void deleteOutlays(List<Long> ids) {
		this.outlayDao.deleteOutlays(ids);
	}

	@Override
	public PageModel<Outlay> findOUtlayList(String queryName, String value,
			Date dateFrom, Date dateTo, Short status) {
		Criterion crit=null;
		String field=null,alias=null;
		if(queryName.equals("status")){
			crit=Restrictions.eq(queryName, status);
		}else if(queryName.equals("time")){
			crit=Restrictions.between(queryName, dateFrom, dateTo);
		}else if(queryName.equals("wheelCardNum")||queryName.equals("axleType")||queryName.equals("wheelTypeName")){
			field="record";
			alias="t";
			crit=Restrictions.like("t."+queryName, "%"+value+"%");
		}else{
			crit=Restrictions.like(queryName, "%"+value+"%");
		}
		Criterion[] cirts=new Criterion[1];
		cirts[0]=crit;
		if(field!=null){
			return outlayDao.findPageModel(cirts,field,alias);
		}else{
			return outlayDao.findPageModel(cirts);
		}
	}

	@Override
	public Outlay findLastestOutlayByWheel(Long id) {
		return outlayDao.findLastestRecordByWheel(id);
	}

	@Override
	public PageModel<Outlay> queryOutlayList(String axleNum, String axleType,
			String wheelType, Date dateFrom, Date dateTo, Short status,
			String factory, String depotCode,boolean sub) {
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
				crits.add(Restrictions.like("depotCode", depotCode));
			}
		}
		if(factory!=null&&!factory.equals("")){
			crits.add(Restrictions.like("t.factory", "%"+factory+"%"));
		}
		if(dateFrom!=null){
			crits.add(Restrictions.ge("time", dateFrom));
		}
		if(dateTo!=null){
			crits.add(Restrictions.le("time", dateTo));
		}
		if(status!=null){
			crits.add(Restrictions.eq("t.status", status));
		}
		return outlayDao.findPageModel(crits,field,alias,Order.desc("time"));
	}
	

}
