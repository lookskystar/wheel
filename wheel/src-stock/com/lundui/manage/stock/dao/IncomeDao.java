package com.lundui.manage.stock.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import com.lundui.manage.model.Income;
import com.lundui.manage.model.WheelRecord;
import com.lundui.manage.util.HibernateBaseDao;

public class IncomeDao extends HibernateBaseDao<Income,Long>{
	
	@SuppressWarnings("unchecked")
	public List<Income> getIncomeListByDate(Date dateFrom,Date dateTo){
		Criteria crit = getSession().createCriteria(getPersistentClass());
		crit.add(Restrictions.between("time", dateFrom, dateTo));
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	/**
	 * 根据轮对履历id查询最近一次的轮对收入记录
	 * @param id
	 * @return
	 */
	public Income getLastRecordByWheelId(Long id){
		String hql="from Income as ic where ic.record.id=:wheelId order by ic.time desc";
		Query query = getSession().createQuery(hql);
		query.setParameter("wheelId", id);
		query.setFirstResult(0).setMaxResults(1);
		List<Income> list=query.list();
		if(list==null||list.size()==0){
			return null;
		}
		return list.get(0);
	}
	
	
	/**
	 * 根据轴号查询轮对履历信息
	 * @param axleNum
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<WheelRecord> getWheelRecordByAxleNum(String axleNum){
		String hql="from WheelRecord t where t.axleNum=?";
		return getHibernateTemplate().find(hql,axleNum);
	}

}
