package com.example.logindemo.activity;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.logindemo.R;
import com.example.logindemo.entity.ApiResponse;
import com.example.logindemo.util.Login;

public class LoginActivity extends Activity {
	// ----- 组件引用-----//
	/** 登录按钮 */
	private Button loginButton;
	private EditText usernameEditText;
	private EditText passwordEditText;

	/** 线程执行服务 */
	private ExecutorService executorService;

	/**
	 * Activity创建
	 * 
	 * @param savedInstanceState
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// requestWindowFeature(Window.FEATURE_NO_TITLE);

		// 绘制布局视图
		setContentView(R.layout.login);

		// -----从视图获取组件-----//
		loginButton = (Button) findViewById(R.id.loginButton);
		usernameEditText = (EditText) findViewById(R.id.username);
		passwordEditText = (EditText) findViewById(R.id.password);

		// 设置登录按钮事件
		loginButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// 获取用户名、密码
				String phone = usernameEditText.getText().toString().trim();
				String password = passwordEditText.getText().toString().trim();
				// 校验
				if (phone == null || phone.equals("")) {
					Toast.makeText(getApplicationContext(), "请输入用户名！", Toast.LENGTH_SHORT).show();
					return;
				}
				if (password == null || phone.equals("")) {
					Toast.makeText(getApplicationContext(), "请输入密码！", Toast.LENGTH_SHORT).show();
					return;
				}
				// 登录
				ApiResponse loginReponse = null;
				try {
					executorService = Executors.newCachedThreadPool();
					Future<ApiResponse> fu = executorService.submit(new Login(phone, password));
					loginReponse = fu.get();// 阻塞get

				}
				catch (InterruptedException | ExecutionException e) {
					Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG)
							.show();
				}
				finally {
					executorService.shutdown();
				}

				// 校验登录结果
				if (loginReponse == null || loginReponse.getCode() != 0) {
					if (loginReponse != null) {
						Toast.makeText(getApplicationContext(), loginReponse.getMessage(),
								Toast.LENGTH_SHORT).show();
					}
				}
				else {
					// 启动主界面
					Intent intent = new Intent(getApplicationContext(), MainActivity.class);
					intent.putExtra("token", JSON.toJSONString(loginReponse.getData()));
					startActivity(intent);
				}

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
