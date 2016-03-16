package com.lundui.manage.stock.dao;

import java.util.List;

import org.hibernate.Query;

import com.lundui.manage.model.Axle;
import com.lundui.manage.util.HibernateBaseDao;

public class AxleDao extends HibernateBaseDao<Axle,Long>{
	
	/**
	 * 批量删除Axles
	 * @param ids
	 * @return
	 */
	public int deleteAxles(List<Long> ids){
		String hql="delete Axle where id in(:ids)";
		Query query = getSession().createQuery(hql);
        query.setParameterList("ids",ids);
        int dels = query.executeUpdate();
        return dels;
	}

}
