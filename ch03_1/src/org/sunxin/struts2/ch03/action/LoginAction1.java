package org.sunxin.struts2.ch03.action;

import java.util.Map;

import org.sunxin.struts2.ch03.model.User;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

public class LoginAction1 implements Action{

	private User user;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@SuppressWarnings("unchecked")
	@Override
	public String execute() throws Exception {
		if("liuyihui".equals(user.getUsername())&&"123".equals(user.getPassword()))
		{
			ActionContext context=ActionContext.getContext();
			Map request=(Map)context.get("request");
			Map session=(Map)context.getSession();
			Map application=(Map)context.getApplication();
			request.put("greet", "»¶Ó­À´µ½ÍøÕ¾");
			session.put("user", user);
			Integer count=(Integer)application.get("count");
			if(null==count)
			{
				count=1;
			}
			else
				count++;
			application.put("count", count);
			
			return SUCCESS;
		}
		else
			return ERROR;
	}
}
