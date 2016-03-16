package com.lundui.manage.system.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import com.lundui.manage.model.User;
import com.lundui.manage.util.HibernateBaseDao;

public class UserDao extends HibernateBaseDao<User,Long>{
	
	/**
	 * 批量删除用户
	 * @param ids
	 * @return
	 */
	public int deleteUsers(List<Long> ids){
		String hql="delete User where id in(:ids)";
		Query query = getSession().createQuery(hql);
        query.setParameterList("ids",ids);
        int dels = query.executeUpdate();
        return dels;
	}
	
	/**
	 * 根据用户的登陆名和密码查询一个用户
	 * @param loginName
	 * @param loginpwd
	 * @return
	 */
	public User getUser(String loginName,String loginpwd){
		String hql="from User where loginName=:name and loginPwd=:pwd";
		Query query = getSession().createQuery(hql);
		query.setParameter("name", loginName);
		query.setParameter("pwd", loginpwd);
		return (User)query.uniqueResult();
	}
	
	/**
	 * 根据段ID查询该段的所有人员
	 * @param depotId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<User> getUserByDepot(Long depotId){
		String hql="delete User as u where u.depot.id=:id";
		Query query = getSession().createQuery(hql);
        query.setParameter("id",depotId);
        return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getUsersByDepot(String depotCode){
		String hql="from User as u where u.depot.depotCode =:code";
		Query query = getSession().createQuery(hql);
		query.setString("code", depotCode);
        return query.list();
	}
	
	/**
	 * 根据工号查询用户信息
	 * @param jobNum
	 * @return
	 */
	public User getUserByJobNum(String jobNum){
		Criteria crit = getSession().createCriteria(getPersistentClass());
		crit.add(Restrictions.eq("jobNum", jobNum));
		return (User)crit.uniqueResult();
	}

	/**
	 * 根据登陆名查询用户信息,判断唯一时用
	 * @param loginName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<User> getUserByloginName(String loginName){
		String hql="from User u where u.loginName=?";
		return getHibernateTemplate().find(hql,loginName);
	}
	
	/**
	 * 根据工号查询用户信息,判断唯一时用
	 * @param jobNum
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<User> findUserByJobNum(String jobNum){
		String hql="from User u where u.jobNum=?";
		return getHibernateTemplate().find(hql,jobNum);
	}

}
