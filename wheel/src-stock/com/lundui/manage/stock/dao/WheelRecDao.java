package com.lundui.manage.stock.dao;


import com.lundui.manage.model.WheelRec;
import com.lundui.manage.util.HibernateBaseDao;

public class WheelRecDao extends HibernateBaseDao<WheelRec,Long>{
	
	/**
	 * 新增记录表
	 */
	public WheelRec saveWheelRec(WheelRec wheelrec){
		
		getHibernateTemplate().save(wheelrec);
		return wheelrec;
	}
	
}
