package com.lundui.manage.system.service;

import java.util.List;
import java.util.Map;

import com.lundui.manage.model.Depot;
import com.lundui.manage.model.RoleResource;
import com.lundui.manage.model.User;
import com.lundui.manage.util.PageModel;

/**
 * 用户管理
 * @author Administrator
 *
 */
public interface UserManageService {
	
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	public User addUser(User user);
	
	/**
	 * 删除用户
	 * @param user
	 */
	public void deleteUser(User user);
	
	/**
	 * 修改角色
	 * @param user
	 * return
	 */
	public User updateUser(User user);
	
	
	/**
	 * 给用户分配角色
	 * @param user
	 * @param role
	 */
	public void assignRole(User user,List<Long> role);
	
	/**
	 * 获取段列表
	 * @return
	 */
	public List<Depot> getDepotList();
	
	/**
	 * 下及单位列表
	 * @param depotCode
	 * @return
	 */
	public List<Depot> getSubDepotList(String depotCode);
	
	/**
	 * 查询用户列表
	 * @return
	 */
	public PageModel<User> queryUser(String username,String jobnum,String depotCode);
	
	/**
	 * 批量删除用户
	 * @param ids
	 * @return
	 */
	public int deleteUsers(List<Long> ids);
	
	/**
	 * 查询一个用户信息
	 * @param id
	 * @return
	 */
	public User getUser(Long id);
	
	/**
	 * 根据用户信息
	 * @param loginName
	 * @param loginPwd
	 * @return
	 */
	public User getUser(String loginName,String loginPwd);
	
	/**
	 * 根据工号查询用户信息
	 * @param jobNum
	 * @return
	 */
	public User getUserbyJobNum(String jobNum);
	
	/**
	 * 根据登陆名查询用户信息
	 * @param loginName
	 * @return
	 */
	public List<User> getUserByloginName(String loginName);
	
	/**
	 * 根据工号查询用户信息,判断唯一时用
	 * @param jobNum
	 * @return
	 */
	public List<User> findUserByJobNum(String jobNum);
	
	
	/**
	 * 通过用户
	 * @param user
	 * @return
	 */
	public Map<String,List<RoleResource>> getResourceByUser(User user);
	
	/**
	 * 获取用户列表
	 * @return
	 */
	public List<User> getUserList(String depotCode);
	
	/**
	 * 根据DepotID查询用户列表
	 * @param depotId
	 * @return
	 */
	public List<User> getUserListByDepot(Long depotId);
	
	public RoleResource  getRoleResource(Long id);
	

}
