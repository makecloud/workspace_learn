package com.lyh.service.client;

import java.io.IOException;

import javax.xml.namespace.QName;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.async.AxisCallback;
import org.apache.axis2.context.MessageContext;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.junit.Test;

public class AsynServiceClient {
	@Test
	public void client1() throws IOException{
		String target="http://localhost:8080/axis2/services/AsynService";
		RPCServiceClient client=new RPCServiceClient();
		Options option = client.getOptions();
		option.setManageSession(true);
		
		EndpointReference epr=new EndpointReference(target);
		option.setTo(epr);
		QName qname=new QName("http://service.lyh.com","execute");
		AsynServiceCallback cb=new AsynServiceCallback();
		client.invokeNonBlocking(qname, new Object[]{},cb);
		System.out.println("“Ï≤ΩwebService");
		System.in.read();
	}
}
