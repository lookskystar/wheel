package com.lundui.manage.common.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.lundui.manage.model.ZXType;
import com.lundui.manage.util.HibernateBaseDao;

public class ZXTypeDao extends HibernateBaseDao<ZXType,Long>{
	
	public ZXType findZXTypeByName(String name){
		Criteria crit = getSession().createCriteria(getPersistentClass());
		crit.add(Restrictions.eq("typeName", name));
		return (ZXType)crit.uniqueResult();
	}

}
