package com.lundui.manage.system.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.lundui.manage.model.RoleResource;
import com.lundui.manage.model.User;
import com.lundui.manage.system.service.UserManageService;

public class ResourceAction {
	
	private Long id;
	
	private RoleResource roleResource;
	
	@Resource(name="userManageService")
	private UserManageService userService;
	
	private String url;
	
	@Resource(name="userManageService")
	private UserManageService service;
	
	public String vistResource(){
		roleResource=service.getRoleResource(id);
		if(roleResource==null){
			HttpSession session=ServletActionContext.getRequest().getSession();
			User user=(User)session.getAttribute("currentUser");
			Map<String,List<RoleResource>> resource =service.getResourceByUser(user);
			session.setAttribute("userResource", resource);
			return "reload";
		}
		ServletActionContext.getRequest().getSession().setAttribute("operator", roleResource.getOperator());
		url=this.roleResource.getResource().getResourceURL();
		return "visit";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RoleResource getRoleResource() {
		return roleResource;
	}

	public void setRoleResource(RoleResource roleResource) {
		this.roleResource = roleResource;
	}

	public UserManageService getService() {
		return service;
	}

	public void setService(UserManageService service) {
		this.service = service;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public UserManageService getUserService() {
		return userService;
	}

	public void setUserService(UserManageService userService) {
		this.userService = userService;
	}
	
	

}
