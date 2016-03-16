package com.lundui.manage.common.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.lundui.manage.model.WheelType;
import com.lundui.manage.util.HibernateBaseDao;

public class WheelTypeDao extends HibernateBaseDao<WheelType,Long>{
	
	public WheelType findWheelTypeByName(String name){
		Criteria crit = getSession().createCriteria(getPersistentClass());
		crit.add(Restrictions.eq("wheelNum", name));
		return (WheelType)crit.uniqueResult();
	}

}
