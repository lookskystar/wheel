package com.lundui.manage.system.dao;

import java.util.List;

import org.hibernate.Query;

import com.lundui.manage.model.Resource;
import com.lundui.manage.model.RoleResource;
import com.lundui.manage.util.HibernateBaseDao;

public class RoleResourceDao extends HibernateBaseDao<RoleResource,Long>{
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<RoleResource> getRoleResourceByRole(Long id){
		String hql="from RoleResource as rr where rr.role.id=:id order by rr.resource.path";
		Query query = getSession().createQuery(hql);
        query.setParameter("id",id);
        return query.list();
	}

	/**
	 * 删除角色对应的资源列表
	 * @param id
	 */
	public void deleteRoleResourcByRole(Long id){
		String hql="delete from RoleResource as rr where rr.role.id=:id";
		Query query = getSession().createQuery(hql);
        query.setParameter("id",id);
        query.executeUpdate();
	}
	
	/**
	 * 根据角色ID查询对应的资源列表
	 * @param id
	 */
	@SuppressWarnings("unchecked")
	public List<Resource> getResourceByRole(Long id){
		String hql="select rr.resource from RoleResource as rr where rr.role.id=:id";
		Query query = getSession().createQuery(hql);
        query.setParameter("id",id);
        return query.list();
	}

}
