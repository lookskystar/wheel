package com.lundui.manage.system.interceptor;

import org.apache.struts2.ServletActionContext;

import com.lundui.manage.model.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class LoginInterceptor implements Interceptor{

	private static final long serialVersionUID = -1161998444480839247L;

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String intercept(ActionInvocation ai) throws Exception {
		User user=(User)ServletActionContext.getRequest().getSession().getAttribute("currentUser");
		if(user==null){
			return "login";
		}
		return ai.invoke();
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
