package com.lundui.manage.stock.dao;

import java.util.List;

import org.hibernate.Query;

import com.lundui.manage.model.TemporaryRepair;
import com.lundui.manage.util.HibernateBaseDao;

public class TemporaryRepairDao extends HibernateBaseDao<TemporaryRepair,Long>{
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	public int deleteTempRepairs(List<Long> ids){
		String hql="delete TemporaryRepair where id in(:ids)";
		Query query = getSession().createQuery(hql);
        query.setParameterList("ids",ids);
        int dels = query.executeUpdate();
        return dels;
	}

}
