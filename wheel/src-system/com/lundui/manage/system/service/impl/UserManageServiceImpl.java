package com.lundui.manage.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.lundui.manage.model.Depot;
import com.lundui.manage.model.Resource;
import com.lundui.manage.model.Role;
import com.lundui.manage.model.RoleResource;
import com.lundui.manage.model.User;
import com.lundui.manage.model.UserRole;
import com.lundui.manage.system.dao.DepotDao;
import com.lundui.manage.system.dao.RoleDao;
import com.lundui.manage.system.dao.RoleResourceDao;
import com.lundui.manage.system.dao.UserDao;
import com.lundui.manage.system.dao.UserRoleDao;
import com.lundui.manage.system.service.UserManageService;
import com.lundui.manage.util.PageModel;

public class UserManageServiceImpl implements UserManageService{

	@javax.annotation.Resource(name="userDao")
	private UserDao userDao;
	
	@javax.annotation.Resource(name="depotDao")
	private DepotDao depotDao;
	
	@javax.annotation.Resource(name="userRoleDao")
	private UserRoleDao userRoleDao;
	
	@javax.annotation.Resource(name="roleDao")
	private RoleDao roleDao;
	
	@javax.annotation.Resource(name="roleResourceDao")
	private RoleResourceDao roleRDao;
	
	@Override
	public User addUser(User user) {
		//Depot depot=depotDao.getEntity(user.getDepot().getId());
		//user.setDepot(depot);
		return userDao.saveEntity(user);
	}

	@Override
	public void deleteUser(User user) {
		userDao.deleteEntity(user);
	}

	@Override
	public User updateUser(User user) {
		return userDao.updateEntity(user);
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public List<Depot> getDepotList() {
		return depotDao.getList();
	}

	public DepotDao getDepotDao() {
		return depotDao;
	}

	public void setDepotDao(DepotDao depotDao) {
		this.depotDao = depotDao;
	}

	@Override
	public PageModel<User> queryUser(String username,String jobnum,String depotCode) {
		List<Criterion> crits=new ArrayList<Criterion>();
		String field=null,alias=null;
		if(username!=null&&!username.equals("")){
			crits.add(Restrictions.like("username","%"+ username+"%"));
		}
		if(jobnum!=null&&!jobnum.equals("")){
			crits.add(Restrictions.like("jobNum", "%"+jobnum+"%"));
		}
		if(depotCode!=null&&!"".equals(depotCode.trim())){
			field="depot";
			alias="t";
			crits.add(Restrictions.like("t.depotCode", depotCode+"%"));
		}
		if(field!=null){
			return userDao.findPageModel(crits, field, alias,null);
		}else{
			return userDao.findPageModel(crits);
		}
	}

	@Override
	public int deleteUsers(List<Long> ids) {
		return userDao.deleteUsers(ids);
	}

	@Override
	public User getUser(Long id) {
		return userDao.getEntity(id);
	}

	@Override
	public void assignRole(User user, List<Long> ids) {
		UserRole ur=null;
		Role role=null;
		//user=userDao.getEntity(user.getId());
		userRoleDao.deleteRoleByUser(user.getId());//删除之前的记录
		for(int i=0;i<ids.size();i++){
			role=new Role();
			role.setId(ids.get(i));
			ur=new UserRole();
			ur.setRole(role);
			ur.setUser(user);
			userRoleDao.saveEntity(ur);
		}
	}
	
	public User getUser(String loginName,String loginPwd){
		return userDao.getUser(loginName,loginPwd);
	}

	@Override
	public Map<String,List<RoleResource>> getResourceByUser(User user) {
		List<UserRole> urlist=userRoleDao.finfRoleByUser(user.getId());//用户角色列表
		List<RoleResource> rrList=null;
		Role role=null;
		RoleResource rr=null;
		Map<String,List<RoleResource>> menu=new HashMap<String,List<RoleResource>>();
		//Map<String,RoleResource> subMenu=null;
		List<RoleResource> subMenu=null;
		Resource re=null,pre=null;
		for(int i=0;i<urlist.size();i++){
			role=urlist.get(i).getRole();
			rrList=roleRDao.getRoleResourceByRole(role.getId());//角色资源列表
			for(int j=0;j<rrList.size();j++){
				rr=rrList.get(j);
				re=rr.getResource();
				pre=re.getParentResource();
				if(menu.get(pre.getResourceName())==null){
					subMenu=new ArrayList<RoleResource>();
					menu.put(pre.getResourceName(), subMenu);
					subMenu.add(rr);
				}else{
					subMenu=menu.get(pre.getResourceName());
					if(!contain(subMenu, rr)){
						subMenu.add(rr);
					}
				}
			}
		}
		return menu;
	}
	
	private  boolean contain(List<RoleResource> list,RoleResource rr){
		RoleResource r=null;
		for(int i=0;i<list.size();i++){
			r=list.get(i);
			if(r.getResource().getId().equals(rr.getResource().getId())){
				return true;
			}
		}
		return false;
	}

	public UserRoleDao getUserRoleDao() {
		return userRoleDao;
	}

	public void setUserRoleDao(UserRoleDao userRoleDao) {
		this.userRoleDao = userRoleDao;
	}

	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	public List<User> getUserList(String depotCode) {
		return userDao.getUsersByDepot(depotCode);
	}

	@Override
	public List<User> getUserListByDepot(Long depotId) {
		return userDao.getUserByDepot(depotId);
	}

	public RoleResourceDao getRoleRDao() {
		return roleRDao;
	}

	public void setRoleRDao(RoleResourceDao roleRDao) {
		this.roleRDao = roleRDao;
	}

	@Override
	public RoleResource getRoleResource(Long id) {
		return this.roleRDao.getEntity(id);
	}

	@Override
	public List<Depot> getSubDepotList(String depotCode) {
		return this.depotDao.findDepots(depotCode);
	}

	@Override
	public User getUserbyJobNum(String jobNum) {
		return this.userDao.getUserByJobNum(jobNum);
	}

	@Override
	public List<User> findUserByJobNum(String jobNum) {
		return userDao.findUserByJobNum(jobNum);
	}

	@Override
	public List<User> getUserByloginName(String loginName) {
		return userDao.getUserByloginName(loginName);
	}


	

}
