import javax.xml.namespace.QName;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;


public class HelloWorldClient {
	public static void main(String[] args) throws AxisFault {
		
		/**
		 * RPCServiceClient 是RPC方式调用
		 */
		RPCServiceClient client = new RPCServiceClient();
		Options options=client.getOptions();
		/**
		 * 设置调用的服务的url
		 */
		String address = "http://localhost:8080/axis2/services/HelloWorldService";
		EndpointReference epf = new EndpointReference(address);
		options.setTo(epf);
		/**
		 * 设置将调用的方法，http://ws.apache.org/axis2 是方法的默认（没有package）命名空间，
		 * 如果有包名就是http://service.hoo.com 包名倒过来即可 。ggetAge 就是方法了。
		 */
		QName qname=new QName("http://ws.apache.org/axis2", "ggetAge");
		/**
		 * 指定调用 方法、参数数组、返回值类型数组
		 */
		Object[] result = client.invokeBlocking(qname, new Object[]{3}, new Class[]{String.class});
		System.out.println(result[0]);
		
	}
}
