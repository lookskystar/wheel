package com.lundui.manage.system.service.impl;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.lundui.manage.model.Resource;
import com.lundui.manage.model.Role;
import com.lundui.manage.model.RoleResource;
import com.lundui.manage.model.UserRole;
import com.lundui.manage.system.dao.ResourceDao;
import com.lundui.manage.system.dao.RoleDao;
import com.lundui.manage.system.dao.RoleResourceDao;
import com.lundui.manage.system.dao.UserRoleDao;
import com.lundui.manage.system.service.RoleManageService;
import com.lundui.manage.util.PageModel;

public class RoleManageServiceImpl implements RoleManageService{
	
	@javax.annotation.Resource(name="roleDao")
	private RoleDao roleDao;
	
	@javax.annotation.Resource(name="resourceDao")
	private ResourceDao resourceDao;
	
	@javax.annotation.Resource(name="userRoleDao")
	private UserRoleDao userRoleDao;
	
	@javax.annotation.Resource(name="roleResourceDao")
	private RoleResourceDao roleResourceDao;

	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	public void deleteRole(Role role) {
		roleDao.deleteEntity(role);
	}

	@Override
	public Role addRole(Role role) {
		return roleDao.saveEntity(role);
	}

	@Override
	public Role updateRole(Role role) {
		return roleDao.updateEntity(role);
	}

	@Override
	public PageModel<Role> queryRole(String field, String value) {
		if(field!=null){
			Criterion crit=Restrictions.like(field, "%"+value+"%");
			Criterion[] cirts=new Criterion[1];
			cirts[0]=crit;
			return roleDao.findPageModel(cirts);
		}else{
			return roleDao.findPageModel( new Criterion[0]);
		}
	}

	@Override
	public Role getRole(Long id) {
		return roleDao.getEntity(id);
	}

	@Override
	public List<Resource> getResource() {
		return this.resourceDao.findResources();
	}

	public ResourceDao getResourceDao() {
		return resourceDao;
	}

	public void setResourceDao(ResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}

	@Override
	public int deleteRoles(List<Long> ids) {
		return roleDao.deleteRoles(ids);
	}

	@Override
	public List<Role> getAllRole() {
		return roleDao.getList();
	}
	
	@Override
	public List<UserRole> getRoleByUser(Long id) {
		return userRoleDao.finfRoleByUser(id);
	}

	public UserRoleDao getUserRoleDao() {
		return userRoleDao;
	}

	public void setUserRoleDao(UserRoleDao userRoleDao) {
		this.userRoleDao = userRoleDao;
	}

	@Override
	public void assignResource(Role role, List<Long> ids,List<Short> ops) {
		//Role r=roleDao.getEntity(role.getId());
		roleResourceDao.deleteRoleResourcByRole(role.getId());//删除之前的记录
		Resource re=null;
		RoleResource rr=null;
		for(int i=0;i<ids.size();i++){
			re=new Resource();
			re.setId(ids.get(i));
			rr=new RoleResource();
			//re=resourceDao.getEntity(ids.get(i));
			rr.setResource(re);
			rr.setRole(role);
			if(ops.get(i)==1){
				rr.setOperator((short)1);
			}else{
				rr.setOperator((short)0);
			}
			roleResourceDao.saveEntity(rr);
		}
	}

	public RoleResourceDao getRoleResourceDao() {
		return roleResourceDao;
	}

	public void setRoleResourceDao(RoleResourceDao roleResourceDao) {
		this.roleResourceDao = roleResourceDao;
	}

	@Override
	public List<RoleResource> getResourceByRole(Role role) {
		return roleResourceDao.getRoleResourceByRole(role.getId());
	}

	@Override
	public void deleteRoleResourceByRole(Role role) {
		roleResourceDao.deleteRoleResourcByRole(role.getId());
	}

	@Override
	public long countUsers(long roleID) {
		long count=roleDao.countUsers(roleID);
		return count;
	}
	
	

}
