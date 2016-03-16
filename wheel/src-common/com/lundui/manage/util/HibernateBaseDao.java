package com.lundui.manage.util;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HibernateBaseDao<T, ID extends Serializable> extends HibernateDaoSupport{
	
	private Class<T> _persistentClass;
	
	@SuppressWarnings("unchecked")
	public HibernateBaseDao(){
		this._persistentClass = ((Class<T>) ((java.lang.reflect.ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0]);
	}
	
	public Class<T> getPersistentClass()
	{
		return this._persistentClass;
	}

	public void setPersistentClass(Class<T> clazz)
	{
		this._persistentClass = clazz;
	}
	
	@SuppressWarnings("unchecked")
	public PageModel<T> findPageModel(Criterion[] criterion,String field,String alias){
		int offset =SystemContext.getOffset();
		int pagesize = SystemContext.getPageSize();	
		PageModel<T> page=new PageModel<T>();
		Criteria crit = getSession().createCriteria(getPersistentClass());
		Criteria countCrit = getSession().createCriteria(getPersistentClass());
		if(field!=null&&!field.equals("")){
			crit.createAlias(field, alias);
			countCrit.createAlias(field, alias);
		}
		for (Criterion c : criterion)
		{
			crit.add(c);
			countCrit.add(c);
		}
		Integer count = (Integer) countCrit.setProjection(Projections.rowCount()).uniqueResult();
		page.setCount(count);
		crit.setFirstResult(offset).setMaxResults(pagesize);
		page.setDatas(crit.list());
		return page;
	}
	
	@SuppressWarnings("unchecked")
	public PageModel<T> findPageModel(List<Criterion> criterion,String field,String alias,Order order){
		int offset =SystemContext.getOffset();
		int pagesize = SystemContext.getPageSize();	
		PageModel<T> page=new PageModel<T>();
		Criteria crit = getSession().createCriteria(getPersistentClass());
		Criteria countCrit = getSession().createCriteria(getPersistentClass());
		if(field!=null&&!field.equals("")){
			crit.createAlias(field, alias);
			countCrit.createAlias(field, alias);
		}
		for (Criterion c : criterion)
		{
			crit.add(c);
			countCrit.add(c);
		}
		if(order!=null) crit.addOrder(order);
		Integer count = (Integer) countCrit.setProjection(Projections.rowCount()).uniqueResult();
		page.setCount(count);
		crit.setFirstResult(offset).setMaxResults(pagesize);
		page.setDatas(crit.list());
		return page;
	}
	
	@SuppressWarnings("unchecked")
	public PageModel<T> findPageModel(Criterion[] criterion){
		int offset =SystemContext.getOffset();
		int pagesize = SystemContext.getPageSize();	
		PageModel<T> page=new PageModel<T>();
		Criteria crit = getSession().createCriteria(getPersistentClass());
		Criteria countCrit = getSession().createCriteria(getPersistentClass());
		for (Criterion c : criterion)
		{
			crit.add(c);
			countCrit.add(c);
		}
		Integer count = (Integer) countCrit.setProjection(Projections.rowCount()).uniqueResult();
		page.setCount(count);
		crit.setFirstResult(offset).setMaxResults(pagesize);
		page.setDatas(crit.list());
		return page;
	}
	
	@SuppressWarnings("unchecked")
	public PageModel<T> findPageModel(List<Criterion> criterion){
		int offset =SystemContext.getOffset();
		int pagesize = SystemContext.getPageSize();	
		PageModel<T> page=new PageModel<T>();
		Criteria crit = getSession().createCriteria(getPersistentClass());
		Criteria countCrit = getSession().createCriteria(getPersistentClass());
		for (Criterion c : criterion)
		{
			crit.add(c);
			countCrit.add(c);
		}
		Integer count = (Integer) countCrit.setProjection(Projections.rowCount()).uniqueResult();
		page.setCount(count);
		crit.setFirstResult(offset).setMaxResults(pagesize);
		page.setDatas(crit.list());
		return page;
	}
	
	@SuppressWarnings("unchecked")
	public PageModel<T> findPageModel(Criterion[] criterion,Order order){
		int offset =SystemContext.getOffset();
		int pagesize = SystemContext.getPageSize();	
		PageModel<T> page=new PageModel<T>();
		Criteria crit = getSession().createCriteria(getPersistentClass());
		Criteria countCrit = getSession().createCriteria(getPersistentClass());
		for (Criterion c : criterion)
		{
			crit.add(c);
			countCrit.add(c);
		}
		crit.addOrder(order);
		Integer count = (Integer) countCrit.setProjection(Projections.rowCount()).uniqueResult();
		page.setCount(count);
		crit.setFirstResult(offset).setMaxResults(pagesize);
		page.setDatas(crit.list());
		return page;
	}
	
	@SuppressWarnings("unchecked")
	public PageModel<T> findPageModel(List<Criterion> criterion,Order order){
		int offset =SystemContext.getOffset();
		int pagesize = SystemContext.getPageSize();	
		PageModel<T> page=new PageModel<T>();
		Criteria crit = getSession().createCriteria(getPersistentClass());
		Criteria countCrit = getSession().createCriteria(getPersistentClass());
		for (Criterion c : criterion)
		{
			crit.add(c);
			countCrit.add(c);
		}
		crit.addOrder(order);
		Integer count = (Integer) countCrit.setProjection(Projections.rowCount()).uniqueResult();
		page.setCount(count);
		crit.setFirstResult(offset).setMaxResults(pagesize);
		page.setDatas(crit.list());
		return page;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findAll(Criterion[] criterion){
		Criteria crit = getSession().createCriteria(getPersistentClass());
		Criteria countCrit = getSession().createCriteria(getPersistentClass());
		for (Criterion c : criterion)
		{
			crit.add(c);
			countCrit.add(c);
		}
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findAll(List<Criterion> criterion){
		Criteria crit = getSession().createCriteria(getPersistentClass());
		Criteria countCrit = getSession().createCriteria(getPersistentClass());
		for (Criterion c : criterion)
		{
			crit.add(c);
			countCrit.add(c);
		}
		return crit.list();
	}
	
	public T getEntity(ID id){
		return getHibernateTemplate().get(getPersistentClass(), id);
	}
	
	public T getEntityById(Long id){
		return getHibernateTemplate().get(getPersistentClass(), id);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getList(){
		Criteria crit = getSession().createCriteria(getPersistentClass());
		return crit.list();
	}
	
	public T saveEntity(T entity)
	{
		this.getSession().save(entity);
		return entity;
	}
	
	public T saveOrUpdateEntity(T entity){
		this.getSession().saveOrUpdate(entity);
		return entity;
	}

	public void deleteEntity(T entity)
	{
		getSession().delete(entity);
	}

	public T updateEntity(T entity)
	{
		this.getSession().update(entity);
		return entity;
		/*
		Session session = this.getSession().getSessionFactory().getCurrentSession();
		session.clear();
		session.update(entity);
		session.flush();
		return entity;*/
	}
	
	public long findTotalRecordNum()
	{
		Long count = (Long) getSession().createCriteria(getPersistentClass()).setProjection(Projections.rowCount())
				.uniqueResult();
		return count.longValue();
	}


}
