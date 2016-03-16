package com.lundui.manage.lead.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.lundui.manage.common.dao.WheelTypeDao;
import com.lundui.manage.lead.service.LeadManageService;
import com.lundui.manage.model.Depot;
import com.lundui.manage.model.Income;
import com.lundui.manage.model.Outlay;
import com.lundui.manage.model.WheelStock;
import com.lundui.manage.model.WheelType;
import com.lundui.manage.stock.dao.IncomeDao;
import com.lundui.manage.stock.dao.OutlayDao;
import com.lundui.manage.stock.dao.WheelRecordDao;
import com.lundui.manage.stock.dao.WheelStockDao;
import com.lundui.manage.system.dao.DepotDao;
import com.lundui.manage.util.PageModel;


public class LeadManageServiceImpl implements LeadManageService{
	
	private IncomeDao incomeDao;
	
	private OutlayDao outlayDao;
	
	private DepotDao depotDao;
	
	private WheelTypeDao wheelType;
	
	@Resource(name="wheelRecordDao")
	private WheelRecordDao recordDao;
	
	@Resource(name="wheelStockDao")
	private WheelStockDao stockDao;

	public IncomeDao getIncomeDao() {
		return incomeDao;
	}

	public void setIncomeDao(IncomeDao incomeDao) {
		this.incomeDao = incomeDao;
	}

	public OutlayDao getOutlayDao() {
		return outlayDao;
	}

	public void setOutlayDao(OutlayDao outlayDao) {
		this.outlayDao = outlayDao;
	}
	
	private static int indexOf(String[] src,String target){
		for(int j=0;j<src.length;j++){
			if(src[j].equals(target)){
				return j;
			}
		}
		return -1;
	}
	
	public List<Object> findIncomeRecordByMonth(Date dateFrom,Date dateTo,Depot depot){
		depot=depotDao.getEntity(depot.getId());
		List<Income> incomeList=incomeDao.getIncomeListByDate(dateFrom, dateTo);
		//查询所有的地点
		List<Depot> depots=null;
		if(depot==null){
			depots=depotDao.findDepots();
		}else{
			depots=depotDao.findDepots(depot);
		}
		Set<String> set=new HashSet<String>();		
		for(int i=0;i<depots.size();i++){
			set.add(depots.get(i).getLocation());
		}
		String[] locas=new String[set.size()];
		Iterator<String> ite=set.iterator();
		int point=0;
		while(ite.hasNext()){
			locas[point]=ite.next();
			point++;
		}
		//所有的轮型
		List<WheelType> types=wheelType.getList();
		String[] cals=new String[locas.length*2+2];
		cals[0]="合计";
		cals[locas.length+1]="合计";
		System.arraycopy(locas, 0, cals, 1, locas.length);
		System.arraycopy(locas, 0, cals, locas.length+2, locas.length);
		Map<String,int[]> report=new HashMap<String,int[]>();
		Income income=null;
		String wheelType=null,location=null;
		int[] lines=null;
		for(int i=0;i<types.size();i++){
			lines=new int[cals.length];
			report.put(types.get(i).getWheelNum(), lines);
		}
		for(int i=0;i<incomeList.size();i++){
			income=incomeList.get(i);
			wheelType=income.getRecord().getWheelTypeName();
			location=income.getRecord().getDepotName();
			lines=report.get(wheelType);
			if(lines==null){
				lines=new int[cals.length];
				report.put(wheelType, lines);
			}
			int index=indexOf(locas,location);
			lines[1+index]+=1;
			lines[0]++;
			
		}
		
		List<Outlay> outlayList=outlayDao.getOutlayListByDate(dateFrom, dateTo);
		Outlay outlay=null;
		for(int i=0;i<outlayList.size();i++){
			outlay=outlayList.get(i);
			wheelType=outlay.getRecord().getWheelTypeName();
			location=outlay.getRecord().getDepotName();
			lines=report.get(wheelType);
			if(lines==null){
				lines=new int[cals.length];
				report.put(wheelType, lines);
			}
			int index=indexOf(locas,location);
			lines[2+index+locas.length]++;
			lines[locas.length+1]++;
		}
		
		List<Object> result=new ArrayList();
		result.add(cals);
		result.add(report);
		result.add(locas.length+1);
		return result;
	}
	
	public Map<String,Map<String,Integer>> findOutlayReportByMonth(Date dateFrom,Date dateTo,Depot depot){
		List<Outlay> outlayList=outlayDao.getOutlayListByDate(dateFrom, dateTo);
		Outlay outlay=null;
		String company=null,wheelType=null;
		Map<String,Map<String,Integer>> report=new HashMap<String,Map<String,Integer>>();
		Map<String,Integer> item=null;
		for(int i=0;i<outlayList.size();i++){
			outlay=outlayList.get(i);
			company=outlay.getDepotName();//收入单位
			wheelType=outlay.getRecord().getWheelTypeName();//轮型
			item=report.get(wheelType);
			if(item==null){
				item=new HashMap<String,Integer>();
			}
			Integer count=item.get(company);
			if(count==null){
				count=0;
			}
			count++;
			item.put(company, count);
			report.put(wheelType, item);
		}
		return report;
	}

	public DepotDao getDepotDao() {
		return depotDao;
	}

	public void setDepotDao(DepotDao depotDao) {
		this.depotDao = depotDao;
	}

	public WheelTypeDao getWheelType() {
		return wheelType;
	}

	public void setWheelType(WheelTypeDao wheelType) {
		this.wheelType = wheelType;
	}

	@Override
	public int[] findStockByDepot(String depotCode) {
		List<WheelStock> stocks=this.stockDao.findWheelStockByInfo(depotCode);
		WheelStock stock=null;
		int[] lines=new int[]{0,0,0,0,0};
		for(int i=0;i<stocks.size();i++){
			stock=stocks.get(i);
			lines[1]+=stock.getGoodNum();
			lines[2]+=stock.getNotGoodNum();
			lines[3]+=stock.getSxNum();
			lines[4]+=stock.getZcNum();
		}
		lines[0]=lines[1]+lines[2];
		return lines;
	}

	public WheelStockDao getStockDao() {
		return stockDao;
	}

	public void setStockDao(WheelStockDao stockDao) {
		this.stockDao = stockDao;
	}

	public WheelRecordDao getRecordDao() {
		return recordDao;
	}

	public void setRecordDao(WheelRecordDao recordDao) {
		this.recordDao = recordDao;
	}

	@Override
	public PageModel<WheelStock> findStockPageByDepot(String depotCode) {
		List<Criterion> crits=new ArrayList<Criterion>();
		crits.add(Restrictions.eq("depotCode", depotCode));
		return this.stockDao.findPageModel(crits);
	}

	@Override
	public List<WheelStock> findSubStocksByDepot(String depotCode) {
		Criterion[] crits=new Criterion[]{Restrictions.like("depotCode", depotCode+"%")};
		List<WheelStock> stocks=stockDao.findAll(crits);
		return stocks;
	}
	
	

}
