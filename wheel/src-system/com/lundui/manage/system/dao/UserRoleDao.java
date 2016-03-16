package com.lundui.manage.system.dao;

import java.util.List;

import org.hibernate.Query;

import com.lundui.manage.model.UserRole;
import com.lundui.manage.util.HibernateBaseDao;

public class UserRoleDao extends HibernateBaseDao<UserRole,Long>{
	
	/**
	 * 根据用户ID查询用户角色列表
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<UserRole> finfRoleByUser(Long id){
		String hql="from UserRole as ur where ur.user.id=:id";
		Query query = getSession().createQuery(hql);
        query.setParameter("id",id);
        return query.list();
	}
	
	public void deleteRoleByUser(Long id){
		String hql="delete from UserRole as ur where ur.user.id=:id";
		Query query = getSession().createQuery(hql);
        query.setParameter("id",id);
        query.executeUpdate();
	}

	
}
