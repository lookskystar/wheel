package com.lundui.manage.system.service.impl;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.lundui.manage.common.dao.AxleTypeDao;
import com.lundui.manage.common.dao.WheelTypeDao;
import com.lundui.manage.model.AxleType;
import com.lundui.manage.model.Depot;
import com.lundui.manage.model.WheelStock;
import com.lundui.manage.model.WheelType;
import com.lundui.manage.stock.dao.WheelStockDao;
import com.lundui.manage.system.dao.DepotDao;
import com.lundui.manage.system.service.WheelAlarmService;
import com.lundui.manage.util.PageModel;

public class WheelAlarmServiceImpl implements WheelAlarmService {
	
	@Resource(name="wheelStockDao")
	private WheelStockDao stockDao;
	
	@Resource(name="axleTypeDao")
	private AxleTypeDao axleTypeDao;
	
	@Resource(name="wheelTypeDao")
	private WheelTypeDao wheelTypeDao;
	
	@Resource(name="depotDao")
	private DepotDao depotDao;
	
	public List<AxleType> getAxleTypes(){
		return axleTypeDao.getList();
	}
	
	public List<WheelType> getWheelTypes(){
		return wheelTypeDao.getList();
	}
	
	public List<Depot> getDepots(){
		//Criterion[] crits=new Criterion[]{Restrictions.eq("type", (short)3)};
		//return depotDao.findAll(crits);
		return depotDao.getList();
	}
	
	public List<WheelStock> getAlarm(String depotCode){
		Criterion[] crits=new Criterion[]{Restrictions.like("depotCode", depotCode+"%")};
		List<WheelStock> stocks=stockDao.findAll(crits);
		List<WheelStock> results=new ArrayList<WheelStock>();
		WheelStock stock=null;
		for(int i=0;i<stocks.size();i++){
			stock=stocks.get(i);
			if(stock.getMinStock()>(stock.getInventory())){//总库存小于最小库存
				results.add(stock);
			}else if(stock.getMaxBadStock()>0&&stock.getMaxBadStock()<stock.getNotGoodNum()){//不良库存过多
				results.add(stock);
			}
		}
		return results;
	}
	
	private JSONObject  putToJSON(WheelStock stock,int index){
		JSONObject jo=new JSONObject();
		jo.put("id", Integer.parseInt("1"+stock.getDepotCode())*100+index);
		jo.put("axleType", stock.getAxleType());
		jo.put("wheelType", stock.getWheelType());
		jo.put("depotCode", stock.getDepotCode());
		jo.put("depotName", stock.getDepotName());
		jo.put("minStock", stock.getMinStock());
		jo.put("inventory", stock.getInventory());
		jo.put("maxBadStock", stock.getMinStock());
		jo.put("notGoodNum", stock.getNotGoodNum());
		jo.put("_parentId",Integer.parseInt(stock.getDepotCode()));
		jo.put("reason", "库存不足");
		return jo;
	}
	
	public JSONObject putDepotToJSON(Depot depot,boolean hasParent,String reason){
		JSONObject jo=new JSONObject();
		jo.put("depotCode", depot.getDepotCode());
		jo.put("depotName", depot.getDepotName());
		jo.put("id", Integer.parseInt(depot.getDepotCode()));
		if(hasParent){
			jo.put("_parentId",Integer.parseInt(depot.getParent().getDepotCode()));
		}
		jo.put("reason", reason);
		return jo;
	}
	
	/**
	 * 轮对库存报警信息JSON列表
	 */
	public String getAlarmJSON(String depotCode){
		List<WheelStock> list=this.getAlarm(depotCode);
		List<Depot> depots=this.depotDao.getYYDepotList(depotCode);//只查看运用车间的数据
		/*
		Tree tree=TreeUtil.makeTree(this.depotDao.findDepots(depotCode), list);*/
		//return "["+tree.toJSONString()+"]";
		JSONArray ja=new JSONArray();
		JSONObject result=new JSONObject();
		Depot depot=null;
		WheelStock stock=null;	
		String stockDepot=null,currentDepot=null,reason=null;
		boolean flag=false;
		for(int i=0;i<depots.size();i++){
			depot=depots.get(i);
			currentDepot=depot.getDepotCode();
			for(int j=0;j<list.size();j++){
				stock=list.get(j);
				if(stock.getMinStock()>stock.getInventory()){
					reason="库存不足";
					flag=true;
				}else if(stock.getMaxBadStock()>0&&stock.getMaxBadStock()<stock.getNotGoodNum()){
					reason="不良库存过多";
					flag=true;
				}
				if(flag){
					stockDepot=stock.getDepotCode();
					if(stockDepot.equals(currentDepot)){
						if(currentDepot.equals(depotCode)){
							ja.add(putDepotToJSON(depot,false,reason));
						}else{
							ja.add(putDepotToJSON(depot,true,reason));
						}
						break;
					}else if(stockDepot.startsWith(currentDepot)){
						if(currentDepot.equals(depotCode)){
							ja.add(putDepotToJSON(depot,false,""));
						}else{
							ja.add(putDepotToJSON(depot,true,""));
						}
						break;
					}
				}
				flag=false;
				reason=null;
			}
			/*
			if(depot.getDepotCode().equals(depotCode)){
				ja.add(putDepotToJSON(depot,false));
			}else{
				ja.add(putDepotToJSON(depot,true));
			}*/
		}
		/*
		for(int i=0;i<list.size();i++){
			stock=list.get(i);
			ja.add(putToJSON(stock,i));
		}*/
		result.put("total", ja.size());
		result.put("rows", ja);
		//System.out.println(ja.toString());
		return result.toString();
	}
	
	public PageModel<WheelStock> getAlarmInfo(String axleNum,String wheelType,String depotCode){
		List<Criterion> critList=new ArrayList<Criterion>();
		if(axleNum!=null&&!axleNum.equals("")){
			critList.add(Restrictions.eq("axleType", axleNum));
		}
		if(wheelType!=null&&!wheelType.equals("")){
			critList.add(Restrictions.eq("wheelType", wheelType));
		}
		if(depotCode!=null&&!depotCode.equals("")){
			critList.add(Restrictions.like("depotCode", depotCode+"%"));
		}
		return stockDao.findPageModel(critList);
	}

	public WheelStockDao getStockDao() {
		return stockDao;
	}

	public void setStockDao(WheelStockDao stockDao) {
		this.stockDao = stockDao;
	}

	public AxleTypeDao getAxleTypeDao() {
		return axleTypeDao;
	}

	public void setAxleTypeDao(AxleTypeDao axleTypeDao) {
		this.axleTypeDao = axleTypeDao;
	}

	public WheelTypeDao getWheelTypeDao() {
		return wheelTypeDao;
	}

	public void setWheelTypeDao(WheelTypeDao wheelTypeDao) {
		this.wheelTypeDao = wheelTypeDao;
	}

	public DepotDao getDepotDao() {
		return depotDao;
	}

	public void setDepotDao(DepotDao depotDao) {
		this.depotDao = depotDao;
	}

	@Override
	public WheelStock updateAlarmInfo(String axleNum, String wheelType,
			String depotCode, Integer min, Integer maxbad) {
		WheelStock stock=this.stockDao.findWheelStockByInfo(axleNum, wheelType, depotCode);
		Depot depot=depotDao.getDepot(depotCode);
		if(stock==null){
			stock=new WheelStock();
			stock.setAxleType(axleNum);
			stock.setGoodNum(0);
			stock.setNotGoodNum(0);
			stock.setInventory(0);
			stock.setWheelType(wheelType);
			stock.setMinStock(0);
			stock.setDepotCode(depotCode);
			stock.setDepotName(depot.getDepotName());
			stock.setZcNum(0);
			stock.setSxNum(0);
		}
		if(min!=null){
			stock.setMinStock(min);
		}
		if(maxbad!=null){
			stock.setMaxBadStock(maxbad);
		}
		return this.stockDao.saveOrUpdateEntity(stock);
	}

	@Override
	public WheelStock getStock(Long id) {
		return this.stockDao.getEntity(id);
	}
	
	

}
