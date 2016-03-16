package com.lundui.manage.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.lundui.manage.model.Depot;
import com.lundui.manage.system.dao.DepotDao;
import com.lundui.manage.system.service.DepotManageService;

public class DepotManageServiceImpl implements DepotManageService{
	
	@Resource(name="depotDao")
	private DepotDao dao;

	@Override
	public Depot addDepot(Depot depot) {
		Depot parent=dao.getEntity(depot.getParent().getId());
		if(depot.getType()==null||depot.getType()==3){
			depot.setType((short)3);//仅车间
			Depot d=dao.saveEntity(depot);
			d.setDepotCode(parent.getDepotCode()+(d.getId()>=10?d.getId():"0"+d.getId()));
			return dao.updateEntity(d);
		}else{
			Depot d=dao.saveEntity(depot);
			d.setDepotCode("01"+(d.getId()>=10?d.getId():"0"+d.getId()));
			return dao.updateEntity(d);
		}
	}

	@Override
	public void deleteDepot(Depot depot) {
		dao.deleteEntity(depot);
	}
	
	public void deleteSubDepot(Depot depot){
		Depot d=dao.getEntity(depot.getId());
		dao.deleteDepots(d.getDepotCode());
	}

	@Override
	public Map<Depot, List<Depot>> getDepotList() {
		Map<Depot, List<Depot>> map=new HashMap<Depot,List<Depot>>();
		List<Depot> depots=dao.findDepots();
		Depot depot=null;
		List<Depot> sublist=null;
		for(int i=0;i<depots.size();i++){
			depot=depots.get(i);
			if(depot.getType()==1){
			}else if(depot.getType()==2){
				sublist=new ArrayList<Depot>();
				map.put(depot, sublist);
			}else{
				sublist.add(depot);
			}
		}
		return map;
	}

	@Override
	public Depot updateDepot(Depot depot) {
		Depot tart=dao.getEntity(depot.getId());
		tart.setDepotName(depot.getDepotName());
		tart.setLocation(depot.getLocation());
		tart.setType(depot.getType());
		return dao.updateEntity(tart);
	}

	public DepotDao getDao() {
		return dao;
	}

	public void setDao(DepotDao dao) {
		this.dao = dao;
	}

	@Override
	public Depot getDepot(Depot depot) {
		return this.dao.getEntity(depot.getId());
	}

	@Override
	public long countDepot(long depotID) {
		long count=dao.countDepot(depotID);
		return count;
	}

	@Override
	public long countUser(long depotID) {
		long count=dao.countUser(depotID);
		return count;
	}

}
