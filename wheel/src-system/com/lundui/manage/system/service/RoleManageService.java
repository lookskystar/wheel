package com.lundui.manage.system.service;

import java.util.List;

import com.lundui.manage.model.Resource;
import com.lundui.manage.model.Role;
import com.lundui.manage.model.RoleResource;
import com.lundui.manage.model.UserRole;
import com.lundui.manage.util.PageModel;

/**
 * 角色管理
 * @author Administrator
 *
 */
public interface RoleManageService {
	
	/**
	 * 删除角色
	 * @param role
	 */
	public void deleteRole(Role role);
	
	public int deleteRoles(List<Long> ids);
	
	/**
	 * 查找角色下是否存在用户
	 */
	public long countUsers(long roleID);
	
	/**
	 * 添加角色
	 */
	public Role addRole(Role role);
	
	/**
	 * 更新角色
	 */
	public Role updateRole(Role role);
	
	/**
	 * 查询角色
	 * @return
	 */
	public PageModel<Role> queryRole(String field,String value);
	
	/**
	 * 给角色添加资源
	 */
	public void assignResource(Role role,List<Long> ids,List<Short> ops);
	
	/**
	 * 查询一个角色
	 * @param id
	 * @return
	 */
	public Role getRole(Long id);
	
	/**
	 * 查询资源列表
	 * @return
	 */
	public List<Resource> getResource();
	
	/**
	 * 查询所有的角色
	 * @return
	 */
	public List<Role> getAllRole();
	
	/**
	 * 通过用户查找对应角色
	 * @param id
	 * @return
	 */
	public List<UserRole> getRoleByUser(Long id);
	
	/**
	 * 根据角色获取资源
	 * @param role
	 * @return
	 */
	public List<RoleResource> getResourceByRole(Role role);
	
	/**
	 * 删除角色对应的资源列表
	 * @param role
	 */
	public void deleteRoleResourceByRole(Role role);

}
