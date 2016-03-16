package com.lundui.manage.common.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.lundui.manage.model.AxleType;
import com.lundui.manage.util.HibernateBaseDao;

public class AxleTypeDao extends HibernateBaseDao<AxleType,Long>{
	
	/**
	 * 通过axleNum查询一个AxleType
	 * @param name
	 * @return
	 */
	public AxleType findAxleTypeByName(String name){
		Criteria crit = getSession().createCriteria(getPersistentClass());
		crit.add(Restrictions.eq("axleNum", name));
		return (AxleType)crit.uniqueResult();
	}

}
