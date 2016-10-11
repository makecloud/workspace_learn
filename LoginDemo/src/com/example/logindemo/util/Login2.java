package com.example.logindemo.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.alibaba.fastjson.JSON;
import com.example.logindemo.entity.ApiResponse;
import com.example.logindemo.entity.LoginData;
import cz.msebera.android.httpclient.client.methods.CloseableHttpResponse;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.client.methods.HttpUriRequest;
import cz.msebera.android.httpclient.impl.client.CloseableHttpClient;
import cz.msebera.android.httpclient.impl.client.HttpClients;

public class Login2 extends Thread {

	/** 登录url常量 */
	private static final MessageFormat URL_FORMATE = new MessageFormat(
			"http://192.168.1.122:8080/platform-mobile/system/login?phone={0}&password={1}");
	/** 响应实体的键值常量 */
	public static final String EXTRA_BUNDLE_APIRESPONSE = "apiResponse";
	/** 电话号 */
	private String phone;
	/** 密码 */
	private String password;
	/** 传入主线程的消息处理器 */
	private Handler handler = null;

	public Login2(String phone, String password, Handler handler) {
		this.phone = phone;
		this.password = password;
		this.handler = handler;
	}

	@Override
	public void run() {

		String url = URL_FORMATE.format(new String[] { phone, password });

		// 创建可关闭的httpclient
		CloseableHttpClient client = HttpClients.createDefault();

		// 创建httpget请求，添加请求头
		HttpGet httpGet = new HttpGet(url);
		httpGet.addHeader("Content-Type", "application/json;charset=utf-8");
		httpGet.addHeader("Accept-Charset", "UTF-8");

		// 设置请求内容
		// StringEntity se = new StringEntity("{abc:你好}",
		// ContentType.APPLICATION_JSON);
		// httpPost.setEntity(se);

		// 打印所有的http请求头（仅测试需要）
		// HeaderIterator it = httpGet.headerIterator();
		// while (it.hasNext()) {
		// System.out.println(it.next());
		// }

		// httpclient执行请求，返回响应对象
		CloseableHttpResponse response = null;
		ApiResponse apiResponse = new ApiResponse();
		;
		try {

			HttpUriRequest request = new HttpGet(url);
			response = client.execute(request);

			// Log.i("Login2", response.getStatusLine().toString());
			// 读取响应体
			StringBuilder sb = new StringBuilder();
			InputStreamReader isr = new InputStreamReader(response.getEntity().getContent());
			BufferedReader bf = new BufferedReader(isr);
			String str;
			while ((str = bf.readLine()) != null)
				sb.append(str);

			// // 打印响应码
			// System.out.println(response.getStatusLine());
			// System.out.println("responseEntity:" + sb.toString());

			// 反序列化
			apiResponse.setData(new LoginData());
			apiResponse = JSON.parseObject(sb.toString(), ApiResponse.class);
			// 关闭
			response.close();
			client.close();
		}
		catch (Exception e) {
			apiResponse.setCode(1111);
			apiResponse.setMessage("服务器异常！	请联系管理员");
		}
		finally {

			// 创建键值对，类似map。android里叫bundle。存入apiResponse对象
			Bundle bundle = new Bundle();
			bundle.putSerializable(EXTRA_BUNDLE_APIRESPONSE, apiResponse);
			// 创建消息对象，存入bundle数据。
			Message message = new Message();
			message.setData(bundle);
			// 发送消息。hanler是主线程的handler，所以消息发给了主线程，由主线程的消息处理方法接收处理
			handler.sendMessage(message);
			// handler.sendMessageDelayed(message, 5000);
		}
	}

}
