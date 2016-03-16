package com.lundui.manage.system.dao;

import java.util.List;

import org.hibernate.Query;

import com.lundui.manage.model.Role;
import com.lundui.manage.util.HibernateBaseDao;

public class RoleDao extends HibernateBaseDao<Role,Long>{

	public int deleteRoles(List<Long> ids){
		String hql="delete Role where id in(:ids)";
		Query query = getSession().createQuery(hql);
        query.setParameterList("ids",ids);
        int dels = query.executeUpdate();
        return dels;
	}
	
	/**
	 * 查找该角色下是否存在用户
	 */
	public long countUsers(long roleID) {
		String hql = "select count(*) from  UserRole d where d.role.id =?";
		return (Long) getHibernateTemplate().find(hql, roleID).get(0);
	}

}
