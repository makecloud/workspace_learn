package com.lyh.service.client;

import javax.xml.namespace.QName;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.junit.Test;

public class LoginServiceClient {
	@Test
	public void client1() throws AxisFault{
		String target="http://localhost:8080/axis2/services/LoginService";
		RPCServiceClient rpcclient=new RPCServiceClient();
		Options option= rpcclient.getOptions();
		option.setManageSession(true);
		EndpointReference epr=new EndpointReference(target);
		option.setTo(epr);
		QName qname=new QName("http://service.lyh.com", "login");
		Object[] result = rpcclient.invokeBlocking(qname, new Object[]{"admin","1234"},new Class[]{boolean.class});
		System.out.println(result[0]);
		
		qname=new QName("http://service.lyh.com","getLoginMessage");
		result=rpcclient.invokeBlocking(qname, new Object[]{}, new Class[]{String.class});
		System.out.println(result[0]);
		
	}
}
