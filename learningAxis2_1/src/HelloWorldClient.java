import javax.xml.namespace.QName;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;


public class HelloWorldClient {
	public static void main(String[] args) throws AxisFault {
		
		/**
		 * RPCServiceClient ��RPC��ʽ����
		 */
		RPCServiceClient client = new RPCServiceClient();
		Options options=client.getOptions();
		/**
		 * ���õ��õķ����url
		 */
		String address = "http://localhost:8080/axis2/services/HelloWorldService";
		EndpointReference epf = new EndpointReference(address);
		options.setTo(epf);
		/**
		 * ���ý����õķ�����http://ws.apache.org/axis2 �Ƿ�����Ĭ�ϣ�û��package�������ռ䣬
		 * ����а�������http://service.hoo.com �������������� ��ggetAge ���Ƿ����ˡ�
		 */
		QName qname=new QName("http://ws.apache.org/axis2", "ggetAge");
		/**
		 * ָ������ �������������顢����ֵ��������
		 */
		Object[] result = client.invokeBlocking(qname, new Object[]{3}, new Class[]{String.class});
		System.out.println(result[0]);
		
	}
}
