package com.lundui.manage.system.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.lundui.manage.model.Resource;
import com.lundui.manage.model.Role;
import com.lundui.manage.model.RoleResource;
import com.lundui.manage.model.WheelRecord;
import com.lundui.manage.system.service.RoleManageService;
import com.lundui.manage.util.PageModel;

public class RoleManageAction {
	
	private Role role;
	
	private PageModel<Role> pageModel;
	
	private RoleManageService service;
	
	private String key;
	
	private String value;
	
	private String result;
	
	private List<Resource> resource;
	
	private List<RoleResource> roleResource;
	
	private Map<String,List<Resource>> groupResource;
	
	private List<Long> ids=new ArrayList<Long>();
	
	private List<Short> ops=new ArrayList<Short>();
	
	public String addRole(){
		role=this.service.addRole(role);
		if(role!=null){
			result="1";
		}else{
			result="0";
		}
		return "addSuccess";
	}
	
	public String roleList(){
		if(key!=null&&value!=null&&!key.trim().equals("")&&!value.trim().equals("")){
			this.pageModel=service.queryRole(key, value);
		}else{
			this.pageModel=service.queryRole(null, null);
		}
		return "showRoleList";
	}
	
	public String deleteRole(){
		this.service.deleteRole(role);
		this.result="1";
		return "deleteSuccess";
	}
	
	/**
	 * 查看该角色下是否存在用户
	 */
	public String countUsers(){
		long count=service.countUsers(role.getId());
		HttpServletResponse response=ServletActionContext.getResponse();
		String date="false";
		if(count==0){
			this.service.deleteRole(role);
			date="success";
		}
		response.setContentType("text/plain");
		try {
			response.getWriter().write(date);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 批量删除角色
	 * @return
	 */
	public String deleteRoles(){
		service.deleteRoles(ids);
		this.result="1";
		return "deleteSuccess";
	}

	public String editRole(){
		this.role=service.getRole(role.getId());
		return "edit";
	}
	
	/**
	 * 分配角色
	 * @return
	 */
	public String updateRole(){
		role = service.updateRole(role);
		if(role == null){
			this.result = "0";
		}else{
			this.result = "1";
		}
		return "updateSuccess";
	}
	
	/**
	 * 系统资源列表
	 * @return
	 */
	public String resourceList(){
		List<Resource> resource=service.getResource();
		Resource res=null;
		groupResource=new HashMap<String,List<Resource>>();
		List<Resource> subGroup=null;
		for(int i=0;i<resource.size();i++){
			res=resource.get(i);
			if(res.getParentResource()==null||res.getParentResource().getId()==0){//如果是父类ID
				subGroup=new ArrayList<Resource>();
				groupResource.put(res.getResourceName(), subGroup);
			}else{
				subGroup.add(res);
			}
		}
		roleResource=service.getResourceByRole(role);
		return "resourceList";
	}
	
	/**
	 * 给角色分配资源
	 * @return
	 */
	public String assignResource(){
		service.assignResource(role, ids,ops);
		result="1";
		return "assignSuccess";
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public RoleManageService getService() {
		return service;
	}

	public void setService(RoleManageService service) {
		this.service = service;
	}

	public PageModel<Role> getPageModel() {
		return pageModel;
	}

	public void setPageModel(PageModel<Role> pageModel) {
		this.pageModel = pageModel;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<Resource> getResource() {
		return resource;
	}

	public void setResource(List<Resource> resource) {
		this.resource = resource;
	}

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

	public Map<String, List<Resource>> getGroupResource() {
		return groupResource;
	}

	public void setGroupResource(Map<String, List<Resource>> groupResource) {
		this.groupResource = groupResource;
	}

	public List<RoleResource> getRoleResource() {
		return roleResource;
	}

	public void setRoleResource(List<RoleResource> roleResource) {
		this.roleResource = roleResource;
	}

	public List<Short> getOps() {
		return ops;
	}

	public void setOps(List<Short> ops) {
		this.ops = ops;
	}
	
	

}
