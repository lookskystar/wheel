package com.lundui.manage.stock.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import com.lundui.manage.model.WheelRecord;
import com.lundui.manage.util.HibernateBaseDao;

public class WheelRecordDao extends HibernateBaseDao<WheelRecord,Long>{
	
	/**
	 * 删除多条记录
	 * @param ids
	 * @return
	 */
	public int deleteWheelRecord(List<String> ids){
		String hql="delete WheelRecord where wheelCardNum in(:ids)";
		Query query = getSession().createQuery(hql);
        query.setParameterList("ids",ids);
        int dels = query.executeUpdate();
        return dels;
	}
	
	public WheelRecord getWheelRecordByAxleNum(Long id,String axleNum){
		Criteria crit = getSession().createCriteria(getPersistentClass());
		crit.add(Restrictions.eq("id", id));
		crit.add(Restrictions.eq("axleNum", axleNum));
		return (WheelRecord)crit.uniqueResult();
	}
	
	public WheelRecord saveOrUpdateAfterEvict(WheelRecord record){
		WheelRecord rec=this.getEntity(record.getId());
		this.getSession().evict(rec);
		this.getSession().saveOrUpdate(record);
		return record;
	}
	
	public WheelRecord getWheelRecordBySC(String jcNum,String position){
		Criteria crit = getSession().createCriteria(getPersistentClass());
		crit.add(Restrictions.eq("jcNum", jcNum));
		crit.add(Restrictions.eq("position", position));
		crit.add(Restrictions.eq("whereabouts", (short)1));//已经上车的
		return (WheelRecord)crit.uniqueResult();
	}
	
	/**
	 * 根据轮对ID查询轮对信息
	 */
	public WheelRecord getWheelRecordById(long wheelId){
		return getHibernateTemplate().get(WheelRecord.class,wheelId);
	}
}
