package org.sunxin.struts2.ch03.action;

import org.sunxin.struts2.ch03.model.User;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

public class LoginAction implements Action ,ModelDriven<User>{

	private User user=new User();
	
	@Override
	public String execute() throws Exception {
		if("liuyihui".equals(user.getUsername())&&"123".equals(user.getPassword()))
			return SUCCESS;
		else
			return ERROR;
	}
	@Override
	public User getModel() {
		
		return user;
	}

}
