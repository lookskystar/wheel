package com.lundui.manage.system.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.lundui.manage.model.Depot;
import com.lundui.manage.model.RoleResource;
import com.lundui.manage.model.User;
import com.lundui.manage.model.WheelStock;
import com.lundui.manage.system.service.UserManageService;
import com.lundui.manage.system.service.WheelAlarmService;

public class LoginAction{
	
	private String loginName;
	
	private String loginPwd;
	
	private UserManageService service;
	
	@Resource(name="alarmService")
	private WheelAlarmService alarmService;
	
	private String errorMsg;

	
	/**
	 * 用户登录
	 * @return
	 */
	public String login(){
		HttpSession session=ServletActionContext.getRequest().getSession();
		User user=service.getUser(loginName, loginPwd);
		if(user==null){//登陆失败
			errorMsg="用户名或密码错误!";
			return "failure";
		}
		//用户所在的段
		Depot currentDepot=user.getDepot();
		//登陆成功
		session.setAttribute("currentUser", user);
		session.setAttribute("depotCode", currentDepot.getDepotCode());
		session.setAttribute("depotType", currentDepot.getType());
		//获取用户资源
		Map<String,List<RoleResource>> resource =service.getResourceByUser(user);
		session.setAttribute("userResource", resource);
		List<WheelStock> alrms=alarmService.getAlarm(currentDepot.getDepotCode());
		if(alrms.size()!=0){
			session.setAttribute("hasAlarm", true);
			session.setAttribute("alarm", alrms);
		}else{
			session.setAttribute("hasAlarm", false);
		}
		//获取用户权列表
		return "success";
	}
	
	/**
	 * 用户退出
	 * @return
	 */
	public String exit(){
		HttpSession session=ServletActionContext.getRequest().getSession();
		session.removeAttribute("user");
		session.removeAttribute("depotCode");
		session.removeAttribute("resource");
		return "login";
	}

	/**
	 * 用户修改密码
	 * @return
	 */
	public String updatePwd(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		String new_pwd=request.getParameter("new_pwd");
		User user = (User) request.getSession().getAttribute("currentUser");
		String result="failure";
		if(user==null){
			result="session_null";
		}else{
			user.setLoginPwd(new_pwd);
			service.updateUser(user);
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
	
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public UserManageService getService() {
		return service;
	}

	public void setService(UserManageService service) {
		this.service = service;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
