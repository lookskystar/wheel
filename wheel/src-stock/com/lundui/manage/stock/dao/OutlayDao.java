package com.lundui.manage.stock.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import com.lundui.manage.model.Outlay;
import com.lundui.manage.model.WheelRecord;
import com.lundui.manage.util.HibernateBaseDao;

public class OutlayDao extends HibernateBaseDao<Outlay,Long>{
	
	/**
	 * 批量删除轮对支出
	 * @param ids
	 * @return
	 */
	public int deleteOutlays(List<Long> ids){
		String hql="delete Outlay where id in(:ids)";
		Query query = getSession().createQuery(hql);
        query.setParameterList("ids",ids);
        int dels = query.executeUpdate();
        return dels;
	}
	
	@SuppressWarnings("unchecked")
	public List<Outlay>  getOutlayListByDate(Date dateFrom,Date dateTo){
		Criteria crit = getSession().createCriteria(getPersistentClass());
		crit.add(Restrictions.between("time", dateFrom, dateTo));
		return crit.list();
	}

	/**
	 * 查询当前轮对最近的一次支出记录
	 * @param id
	 * @return
	 */
	public Outlay findLastestRecordByWheel(Long id) {
		String hql = "from Outlay as ol where ol.record.id=? order by ol.time desc";
		List<Outlay> outs = getHibernateTemplate().find(hql, id);
		if(outs!=null && outs.size()>0){
			return outs.get(0);
		}
		return null;
	}
	
	public List<WheelRecord> findWheelRecordsByDepot(String depotCode,int whereabout){
		String hql="select distinct ol.record from Outlay as ol where ol.targetUnit = :depotCode and ol.record.whereabouts=:whereabout";
		Query query = getSession().createQuery(hql);
        query.setString("depotCode", depotCode);
        query.setInteger("whereabout", whereabout);
        return query.list();
	}


}
