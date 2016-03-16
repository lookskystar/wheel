package com.lundui.manage.stock.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.lundui.manage.model.WheelStock;
import com.lundui.manage.util.HibernateBaseDao;

public class WheelStockDao extends HibernateBaseDao<WheelStock,Long>{
	
	/**
	 * 根据轮型，轴型查询一条轮对库存记录
	 * @param axleNum
	 * @param axleType
	 * @param wheelType
	 * @return
	 */
	public WheelStock findWheelStockByInfo(String axleType,String wheelType,String depotCode){
		Criteria crit = getSession().createCriteria(getPersistentClass());
		crit.add(Restrictions.eq("axleType", axleType));
		crit.add(Restrictions.eq("wheelType", wheelType));
		crit.add(Restrictions.eq("depotCode", depotCode));
		return (WheelStock)crit.uniqueResult();
	}
	
	/**
	 * 查询单位中各种型号库存信息
	 * @param depotCode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<WheelStock> findWheelStockByInfo(String depotCode){
		Criteria crit = getSession().createCriteria(getPersistentClass());;
		crit.add(Restrictions.eq("depotCode", depotCode));
		return crit.list();
	}

}
