package com.liuyihui.test;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HeaderIterator;
import org.apache.http.RequestLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

/**
 * 这个测试类里，模拟浏览器，发送http请求，来测试controller2的效果
 * 
 * @author liuyh
 */
public class SpringmvcControllerTest {
	private final String urlpre = "http://localhost:8080/lrn-spring-mvc-demo01/controller2";

	/**
	 * 发送htt请求（使用了apache httpclient）
	 * 
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	@Test
	public void testRequest2() throws ClientProtocolException, IOException {

		// 创建可关闭的httpclient
		CloseableHttpClient client = HttpClients.createDefault();

		// 创建httpget请求，添加请求头
		HttpPost httpPost = new HttpPost(urlpre + "/request2");
		httpPost.addHeader("Content-Type", "application/json;charset=utf-8");
		httpPost.addHeader("Accept-Charset", "UTF-8");

		// 打印看看renquestLine是什么
		RequestLine rl = httpPost.getRequestLine();
		System.out.println("reuqestLine:" + rl);

		// 设置请求内容(请求体)
		StringEntity se = new StringEntity("{abc:你好}", ContentType.APPLICATION_JSON);
		httpPost.setEntity(se);

		// 打印所有的http请求头
		HeaderIterator it = httpPost.headerIterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}

		// httpclient执行请求，返回响应对象
		CloseableHttpResponse response = client.execute(httpPost);

		String responseStr = response.toString();

		// 打印结果
		System.out.println(response.getStatusLine());
		System.out.println("response:" + responseStr);

		// 关闭
		response.close();
	}

	/**
	 * spring自带的http客户端工具示例
	 * 
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	@Test
	public void testrequest2() throws IOException, URISyntaxException {
		// 创建请求对象
		ClientHttpRequest request = new SimpleClientHttpRequestFactory().createRequest(new URI(
				"http://localhost:8080/controller2/request2"), HttpMethod.POST);

		// 设置请求头，体
		request.getHeaders().set("key", "value");
		request.getHeaders().setContentType(MediaType.APPLICATION_JSON);
		request.getBody().write("请求内容".getBytes());

		// 执行请求
		ClientHttpResponse response = request.execute();

		// 处理请求
		response.getStatusCode();
		response.getBody();
	}

	@Test
	public void main() {

		String cmd = "cmd.exe copy d:\\file.txt d:\\copy\\file.txt";
		String cmd1 = "cmd.exe  ipconfig";
		Runtime rt = Runtime.getRuntime();
		Process p = null;
		try {
			p = rt.exec(cmd);
			System.out.println(p.toString());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (p != null) {
				p.destroy();
			}
		}

	}

	public static void main(String[] args) {
		String cmd = "cmd.exe copy /y c:\\file.txt c:\\copy\\b.txt\r\n";
		Runtime rt = Runtime.getRuntime();
		Process p = null;
		try {
			p = rt.exec("cmd");
			DataOutputStream dos = new DataOutputStream(p.getOutputStream());
			dos.writeBytes("copy /y c:\\file.txt c:\\copy\\b.txt/r/n");
			dos.flush();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {

		}
	}
}
