package com.lundui.manage.system.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.lundui.manage.model.Depot;
import com.lundui.manage.model.Role;
import com.lundui.manage.model.User;
import com.lundui.manage.model.UserRole;
import com.lundui.manage.model.WheelRecord;
import com.lundui.manage.system.service.RoleManageService;
import com.lundui.manage.system.service.UserManageService;
import com.lundui.manage.util.PageModel;

public class UserManageAction {
	
	private User user;
	
	private UserManageService service;
	
	private RoleManageService roleService;
	
	private List<Depot> depots;
	
	private String result;
	
	private PageModel<User> pageModel;
	
	private List<Long> ids=new ArrayList<Long>();
	
	private List<Role> roles;
	
	private List<UserRole> userRole;
	
	private String username;
	
	private String jobnum;
	
	private Long depotId;
	
	private String depot;
	
	private String valueParam;
	
	/**
	 * 添加用户
	 * @return
	 */
	public String addUser(){
//		User tar=service.getUserbyJobNum(user.getJobNum());
//		if(tar!=null){
//			result= "该工号的用户已经存在!";
//		}
//		User tar=service.getUserByloginName(user.getLoginName());
//		if(tar!=null){
//			result= "该登陆名的用户已经存在!";
//		}
//		else{
//			User u=service.addUser(user);
//			if(u==null){
//				result="添加用户失败!";
//			}else{
//				result="1";
//			}
//		}
//		return "addSuccess";
		
		
		
		User u = service.addUser(user);
		if (u == null) {
			result = "添加用户失败!";
		} else {
			result = "1";
		}
		return "addSuccess";
	}
	
	/**
	 * 查看该工号是否存在
	 */
	public String ajaxExistJobNum(){
		String jobNum=ServletActionContext.getRequest().getParameter("jobNum");
		HttpServletResponse response=ServletActionContext.getResponse();
		List<User> users=service.findUserByJobNum(jobNum);
		String result="false";
		if(users!=null&&users.size()>0){
			result="success";
		}
		response.setContentType("text/plain");
		try {
			response.getWriter().write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 查看该登陆名是否存在
	 */
	public String ajaxExistloginName(){
		String loginName=ServletActionContext.getRequest().getParameter("loginName");
		HttpServletResponse response=ServletActionContext.getResponse();
		List<User> users=service.getUserByloginName(loginName);
		String result="false";
		if(users!=null&&users.size()>0){
			result="success";
		}
		response.setContentType("text/plain");
		try {
			response.getWriter().write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public String userList() throws UnsupportedEncodingException{
		String cdepotCode=(String)ServletActionContext.getRequest().getSession().getAttribute("depotCode");
		if(valueParam!=null && !"".equals(valueParam.trim())){
			username = URLDecoder.decode(valueParam, "utf-8");
		}
		if(depot==null||"".equals(depot.trim())){
			depot=cdepotCode;
		}
		depots=service.getSubDepotList(depot);
		pageModel=service.queryUser(username,jobnum,depot);
		if(username!=null&&!username.equals("")){
			valueParam = URLEncoder.encode(username, "utf-8");
		}
		return "list";
	}
	
	//编辑页面
	public String edit(){
		String depotCode=(String)ServletActionContext.getRequest().getSession().getAttribute("depotCode");
		depots=service.getSubDepotList(depotCode);
		user=service.getUser(user.getId());
		return "edit";
	}
	
	public String toAddUser(){
		depots=service.getDepotList();
		return "toAdd";
	}
	
	public String updateUser(){
		user=service.updateUser(user);
		if(user==null){
			this.result = "0";
		}else{
			this.result = "1";
		}
		return "updateSuccess";
	}
	
	/**
	 * 删除多个角色
	 * @return
	 */
	public String deleteUsers(){
		service.deleteUsers(ids);
		return "deleteSuccess";
	}
	
	public String deleteUser(){
		service.deleteUser(user);
		return "deleteSuccess";
	}
	
	/**
	 * 准备分配角色
	 * @return
	 */
	public String toAssignRole(){
		roles=roleService.getAllRole();
		userRole=roleService.getRoleByUser(user.getId());
		return "toAssignRole";
	}
	
	/**
	 * 分配角色
	 * @return
	 */
	public String assignRole(){
		service.assignRole(user, ids);
		result="1";
		return "assignRole";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserManageService getService() {
		return service;
	}

	public void setService(UserManageService service) {
		this.service = service;
	}

	public List<Depot> getDepots() {
		return depots;
	}

	public void setDepots(List<Depot> depots) {
		this.depots = depots;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public PageModel<User> getPageModel() {
		return pageModel;
	}

	public void setPageModel(PageModel<User> pageModel) {
		this.pageModel = pageModel;
	}

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

	public RoleManageService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleManageService roleService) {
		this.roleService = roleService;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<UserRole> getUserRole() {
		return userRole;
	}

	public void setUserRole(List<UserRole> userRole) {
		this.userRole = userRole;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getJobnum() {
		return jobnum;
	}

	public void setJobnum(String jobnum) {
		this.jobnum = jobnum;
	}

	public Long getDepotId() {
		return depotId;
	}

	public void setDepotId(Long depotId) {
		this.depotId = depotId;
	}

	public String getValueParam() {
		return valueParam;
	}

	public void setValueParam(String valueParam) {
		this.valueParam = valueParam;
	}

	public String getDepot() {
		return depot;
	}

	public void setDepot(String depot) {
		this.depot = depot;
	}

	
}
