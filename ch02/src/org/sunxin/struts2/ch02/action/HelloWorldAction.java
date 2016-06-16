package org.sunxin.struts2.ch02.action;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;

public class HelloWorldAction implements Action {

	private String message;
	public String getMessage()
	{
		return message;
	}
	
	
	@Override
	public String execute() throws Exception {
		ServletContext sc=ServletActionContext .getServletContext();
		
		
		message=sc.getRealPath("");
		
		return SUCCESS;
	}

}
