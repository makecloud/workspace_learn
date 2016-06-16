package com.lyh.service.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.apache.xml.utils.QName;

import com.sun.tools.xjc.reader.xmlschema.bindinfo.BIConversion.User;

public class ComplexTypeServiceClient {
	public static void main(String[] args) throws IOException {
		//
		RPCServiceClient client =new RPCServiceClient();
		Options opt=client.getOptions();
		//
		String address="http://localhost:8080/axis2/services/ComplexTypeService";
		EndpointReference erf= new EndpointReference(address);
		opt.setTo(erf);
		//
		javax.xml.namespace.QName qname=new javax.xml.namespace.QName("http://ws.apache.org/axis2", "upload4Byte");
		
		String path=System.getProperty("user.dir");
		File file = new File(path+"/WebRoot/index.jsp");
		FileInputStream fis = new FileInputStream(file);
		int len= (int) file.length();
		byte[] b= new byte[len];
		int read= fis.read(b);
		fis.close();
		
		Object[] result=client.invokeBlocking(qname, new Object[]{b,len},new Class[]{String.class});
		System.out.println("upload to :"+result[0]);
		//第二个调用
		qname=new javax.xml.namespace.QName("http://ws.apache.org/axis2","getArray");
		result = client.invokeBlocking(qname, new Object[]{7}, new Class[]{int[].class});
		int arr[] = (int[]) result[0];
		for (Integer i : arr){
			System.out.println(i);
		}
		//第三个调用
		qname=new javax.xml.namespace.QName("http://ws.apache.org/axis2","getUser");
		result = client.invokeBlocking(qname, new Object[]{}, new Class[]{data.User.class});
		System.out.println("User:"+(data.User)result[0]);
		//第四个调用
		qname= new javax.xml.namespace.QName("http://ws.apache.org/axis2","getTwoArray");
		result=client.invokeBlocking(qname, new Object[]{}, new Class[]{String[][].class});
		System.out.println("String[][]:"+(String[][])result[0]);
		
	}
}
