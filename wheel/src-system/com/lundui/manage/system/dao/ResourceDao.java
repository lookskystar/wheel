package com.lundui.manage.system.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

import com.lundui.manage.model.Resource;
import com.lundui.manage.util.HibernateBaseDao;

public class ResourceDao extends HibernateBaseDao<Resource,Long>{
	
	@SuppressWarnings("unchecked")
	public List<Resource> findResources(){
		Criteria crit = getSession().createCriteria(Resource.class);
		crit.addOrder(Order.asc("path"));
		return crit.list();
	}
	
	

}
