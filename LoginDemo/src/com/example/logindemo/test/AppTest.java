package com.example.logindemo.test;

import java.util.concurrent.ExecutionException;
import org.junit.Test;
import com.example.logindemo.exception.YungeException;

public class AppTest {

	@Test
	public void testLogin() throws YungeException, InterruptedException, ExecutionException {
		// 设置参数
		// String phone = "13888888888";
		// String password = "e10adc3949ba59abbe56e057f20f883e";
		//
		// ExecutorService executorService;
		// executorService = Executors.newCachedThreadPool();
		// Future<ApiResponse> fu = executorService.submit(new Login(phone,
		// password));
		// ApiResponse loginReponse = fu.get();// 阻塞get
		//
		// // 调用登录
		// // ApiResponse apiResponse = Login.login(phone, password);
		//
		// // 打印结果
		// System.out.println("testResult：" + JSON.toJSONString(loginReponse));

	}
}
