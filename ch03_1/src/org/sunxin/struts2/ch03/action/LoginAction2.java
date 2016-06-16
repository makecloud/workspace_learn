package org.sunxin.struts2.ch03.action;

import java.util.Map;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.sunxin.struts2.ch03.model.User;

import com.opensymphony.xwork2.Action;

public class LoginAction2 implements Action,RequestAware,SessionAware,ApplicationAware {
	
	private User user;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	private Map request;
	private Map session;
	private Map application;
	@Override
	public String execute() throws Exception {
		if("liuyihui".equals(user.getUsername())&&"123".equals(user.getPassword()))
		{
			request.put("greet", "»¶Ó­À´µ½ÍøÒ³");
			session.put("user", user);
			return SUCCESS;
		}
		else
			return ERROR;
	}
	@Override
	public void setApplication(Map arg0) {
		this.application=arg0;
	}
	@Override
	public void setRequest(Map arg0) {
		this.request=arg0;
	}
	@Override
	public void setSession(Map arg0) {
		this.session=arg0;
	}
	
}
