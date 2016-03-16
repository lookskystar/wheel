package com.lundui.manage.stock.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.lundui.manage.model.WheelStock;
import com.lundui.manage.stock.dao.WheelStockDao;
import com.lundui.manage.stock.service.WheelStockManageService;
import com.lundui.manage.util.PageModel;

public class WheelStockManageServiceImpl implements WheelStockManageService{
	
	@Resource(name="wheelStockDao")
	private WheelStockDao stockDao;

	@Override
	public WheelStock getStock(Long id) {
		return stockDao.getEntity(id);
	}

	@Override
	public WheelStock updateStock(WheelStock stock) {
		return stockDao.updateEntity(stock);
	}

	@Override
	public PageModel<WheelStock> queryStocks() {
		return stockDao.findPageModel(new Criterion[0]);
	}
	
	public WheelStockDao getStockDao() {
		return stockDao;
	}

	public void setStockDao(WheelStockDao stockDao) {
		this.stockDao = stockDao;
	}

	@Override
	public PageModel<WheelStock> queryStocks(String axleType, String wheelType,
			 String depotCode) {
		List<Criterion> list=new ArrayList<Criterion>();
		if(axleType!=null&&!axleType.equals("")){
			list.add(Restrictions.eq("axleType",axleType));
		}
		if(wheelType!=null&&!wheelType.equals("")){
			list.add(Restrictions.eq("wheelType",wheelType));
		}
		if(depotCode!=null&&!depotCode.equals("")){
			list.add(Restrictions.like("depotCode", depotCode+"%"));
		}
		return stockDao.findPageModel(list);
	}

	@Override
	public void addStockRecord(String axleType, String wheelType,
			String depotCode, String depotName,boolean isGood,int reason) {
		WheelStock stock=this.stockDao.findWheelStockByInfo(axleType,wheelType,depotCode);
		if(stock==null){//如果没有找到记录
			stock=new WheelStock();
			stock.setAxleType(axleType);
			stock.setGoodNum(0);
			stock.setNotGoodNum(0);
			stock.setInventory(0);
			stock.setWheelType(wheelType);
			stock.setMinStock(0);
			stock.setDepotCode(depotCode);
			stock.setDepotName(depotName);
			stock.setZcNum(0);
			stock.setSxNum(0);
		}
		if(isGood){
			stock.setGoodNum(stock.getGoodNum()+1);//良好库存+1
		}else{
			stock.setNotGoodNum(stock.getNotGoodNum()+1);//不良库存+1
		}
		stock.setInventory(stock.getInventory()+1);//总库存+1;
		if(reason==3){//临修收入(这时装车的数量会减一)
			stock.setZcNum(stock.getZcNum()>0?(stock.getZcNum()-1):0);
		}else if(reason==5){//修竣后接收(这时送修的数量会减一)
			stock.setSxNum(stock.getSxNum()>0?(stock.getSxNum()-1):0);
		}
		this.stockDao.saveOrUpdateEntity(stock);
	}

	@Override
	public void deleteStockRecord(String axleType, String wheelType,
			String depotCode, String depotName, boolean isGood, int reason) {
		WheelStock stock=this.stockDao.findWheelStockByInfo(axleType,wheelType,depotCode);
		if(stock==null){//没有库存记录，那么添加一条库存记录，所有数据都设置为1。
			stock=new WheelStock();
			stock.setAxleType(axleType);
			stock.setGoodNum(0);
			stock.setNotGoodNum(0);
			stock.setInventory(0);
			stock.setWheelType(wheelType);
			stock.setMinStock(0);
			stock.setDepotCode(depotCode);
			stock.setDepotName(depotName);
			if(reason==1){
				stock.setZcNum(1);
			}
			if(reason==2){
				stock.setSxNum(1);
			}
			this.stockDao.saveEntity(stock);
		}else{
			//判断是否是良好的
			if(isGood){
				int goodNum=stock.getGoodNum();
				stock.setGoodNum(goodNum<1?0:(goodNum-1));
			}else{
				int notGoodNum=stock.getNotGoodNum();
				stock.setNotGoodNum(notGoodNum<1?0:(notGoodNum-1));
			}
			//支出原因
			if(reason==1){//装车
				stock.setZcNum(stock.getZcNum()+1);
			}else if(reason==2){//送修
				stock.setSxNum(stock.getSxNum()+1);
			}
			//库存数量减一
			stock.setInventory(stock.getInventory()>0?(stock.getInventory()-1):0);
			this.stockDao.updateEntity(stock);
		}
		
	}

	@Override
	public List<WheelStock> queryStock(String depotCode) {
		List<Criterion> list=new ArrayList<Criterion>();
		list.add(Restrictions.eq("depotCode",depotCode));
		return this.stockDao.findAll(list);
	}
	
}
