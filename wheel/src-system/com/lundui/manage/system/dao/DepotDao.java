package com.lundui.manage.system.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.lundui.manage.model.Depot;
import com.lundui.manage.util.HibernateBaseDao;

public class DepotDao extends HibernateBaseDao<Depot,Long>{
	
	/**
	 * 查询所有的段信息，并按段代码排序
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Depot> findDepots(){
		Criteria crit = getSession().createCriteria(getPersistentClass());
		Order order=Order.asc("depotCode");
		crit.addOrder(order);
		return crit.list();
	}
	
	/**
	 * 查询所有的车间信息
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Depot> findCJDepots(){
		return  getHibernateTemplate().find("from Depot d where d.parent.id !=0 and d.parent.id !=1");
	}
	
	
	public List<Depot> findDepots(Depot depot){
		return findDepots(depot.getDepotCode());
	}
	
	@SuppressWarnings("unchecked")
	public List<Depot> findDepots(String depotCode){
		Criteria crit = getSession().createCriteria(getPersistentClass());
		crit.add(Restrictions.like("depotCode", depotCode+"%"));
		crit.addOrder(Order.asc("depotCode"));
		return crit.list();
	}
	
	public Depot getDepot(String depotCode){
		Criteria crit = getSession().createCriteria(getPersistentClass());
		crit.add(Restrictions.eq("depotCode", depotCode));
		return (Depot)crit.uniqueResult();
	}
	
	public Depot getDepotByName(String depotName){
		Criteria crit = getSession().createCriteria(getPersistentClass());
		crit.add(Restrictions.eq("depotName", depotName));
		List<Depot> depots=crit.list();
		if(depots.size()>0)return depots.get(0);
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Depot> getSubDepotList(String depotCode){
		Criteria crit = getSession().createCriteria(getPersistentClass());
		crit.createAlias("parent", "pa");
		crit.add(Restrictions.eq("pa.depotCode", depotCode));
		return crit.list();
	}
	
	public void deleteDepots(String depotCode){
		String hql="delete Depot where depotCode = :likeInfo";
		Query query = getSession().createQuery(hql);
        query.setString("likeInfo",depotCode);
        query.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public List<Depot> getYYDepotList(String depotCode){
		Criteria crit = getSession().createCriteria(getPersistentClass());
		crit.add(Restrictions.ne("type", (short)4));
		if(depotCode!=null&&!"".equals(depotCode)){
			crit.add(Restrictions.like("depotCode", depotCode+"%"));
		}
		crit.addOrder(Order.asc("depotCode"));
		return crit.list();
	}
	
	/**
	 * 查找该段(厂)下是否存在车间
	 */
	public long countDepot(long depotID) {
		String hql = "select count(*) from  Depot d where d.parent.id =?";
		return (Long) getHibernateTemplate().find(hql, depotID).get(0);
	}
	
	/**
	 * 查找该车间下是否存在用户
	 */
	public long countUser(long depotID) {
		String hql = "select count(*) from  User d where d.depot.id =?";
		return (Long) getHibernateTemplate().find(hql, depotID).get(0);
	}
}
