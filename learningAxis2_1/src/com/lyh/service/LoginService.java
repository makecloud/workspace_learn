package com.lyh.service;

import org.apache.axis2.context.MessageContext;
import org.apache.axis2.context.ServiceContext;

public class LoginService {
	public boolean login(String username,String password){
		MessageContext ctx=MessageContext.getCurrentMessageContext();
		ServiceContext sctx= ctx.getServiceContext();
		if("admin".equals(username)&&"1234".equals(password)){
			sctx.setProperty("username", username);
			sctx.setProperty("password", password);
			sctx.setProperty("msg","��¼�ɹ�");
			return true;
		}
		sctx.setProperty("msg", "��¼ʧ��");
		return false;
	}
	public String getLoginMessage(){
		MessageContext mctx=MessageContext.getCurrentMessageContext();
		ServiceContext sctx=mctx.getServiceContext();
		return sctx.getProperty("username")+"#"+sctx.getProperty("msg");
	}
}
