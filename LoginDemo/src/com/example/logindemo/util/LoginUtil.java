package com.example.logindemo.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import android.os.AsyncTask;
import com.alibaba.fastjson.JSON;
import com.example.logindemo.entity.ApiResponse;
import com.example.logindemo.entity.LoginData;
import cz.msebera.android.httpclient.client.methods.CloseableHttpResponse;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.client.methods.HttpUriRequest;
import cz.msebera.android.httpclient.impl.client.CloseableHttpClient;
import cz.msebera.android.httpclient.impl.client.HttpClients;

public class LoginUtil extends AsyncTask<String, Integer, ApiResponse> {

	/** 登录url */
	private static final MessageFormat URL_FORMATE = new MessageFormat(
			"http://192.168.1.151:8080/platform-mobile/system/login?phone={0}&password={1}");
	private ApiResponse resp;

	public LoginUtil(ApiResponse resp) {
		this.resp = resp;
	}

	/**
	 * 任务执行前
	 */
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	/**
	 * 后台任务
	 * 
	 * @param params
	 * @return
	 */
	@Override
	protected ApiResponse doInBackground(String... params) {

		String phone = params[0];
		String password = params[1];
		String url = URL_FORMATE.format(new String[] { phone, password });

		// 创建可关闭的httpclient
		CloseableHttpClient client = HttpClients.createDefault();

		// 创建httpget请求，添加请求头
		HttpGet httpGet = new HttpGet(url);
		httpGet.addHeader("Content-Type", "application/json;charset=utf-8");
		httpGet.addHeader("Accept-Charset", "UTF-8");

		// 创建http响应对象
		CloseableHttpResponse response = null;

		// 执行http请求
		try {
			HttpUriRequest request = new HttpGet(url);
			response = client.execute(request);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		// 读取响应体
		StringBuilder sb = new StringBuilder();
		try {
			InputStreamReader isr = new InputStreamReader(response.getEntity().getContent());
			BufferedReader bf = new BufferedReader(isr);
			String str;
			while ((str = bf.readLine()) != null)
				sb.append(str);
		}
		catch (IllegalStateException | IOException e1) {
			e1.printStackTrace();
		}

		// // 打印结果
		// System.out.println(response.getStatusLine());
		// System.out.println("responseEntity:" + sb.toString());

		// 反序列化
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(new LoginData());
		apiResponse = JSON.parseObject(sb.toString(), ApiResponse.class);

		// 关闭
		try {
			response.close();
			client.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		return apiResponse;
	}

	/**
	 * @param values
	 */
	@Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);
	}

	@Override
	protected void onPostExecute(ApiResponse result) {
		super.onPostExecute(result);

	}
}
