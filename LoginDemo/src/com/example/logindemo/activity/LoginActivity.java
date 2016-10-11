package com.example.logindemo.activity;

import java.util.concurrent.ExecutorService;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.alibaba.fastjson.JSON;
import com.example.logindemo.R;
import com.example.logindemo.entity.ApiResponse;
import com.example.logindemo.util.Login;
import com.example.logindemo.util.Login2;

/**
 * 登录Activity
 * 
 * @author liuyh 2016年10月8日
 */
public class LoginActivity extends Activity implements OnClickListener {

	public static String INTENT_EXTRA_HANDLER = "extra_handler";
	/** 进度条对话框 */
	private ProgressDialog progressDialog;
	/** 线程执行服务 */
	private ExecutorService executorService;
	/** http响应实体 */
	private ApiResponse loginReponse = null;

	// ----- 组件引用-----//
	/** 登录按钮 */
	private Button loginButton;
	private EditText usernameEditText;
	private EditText passwordEditText;
	private Button clearUsernameButton;
	private Button clearPwdButton;
	private Button lookPwdButton;

	private TextWatcher usernameWatcher;
	private TextWatcher passwordWatcher;
	// 登录结果消息处理器
	public Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// 撤销等待框
			if (progressDialog != null || progressDialog.isShowing()) {
				progressDialog.dismiss();
			}

			// 从消息里获取响应实体对象
			loginReponse = (ApiResponse) msg.getData().getSerializable(
					Login.EXTRA_BUNDLE_APIRESPONSE);
			// 校验登录结果
			if (loginReponse == null || loginReponse.getCode() != 0) {
				if (loginReponse != null) {
					Toast.makeText(getApplicationContext(), loginReponse.getMessage(),
							Toast.LENGTH_SHORT).show();
				}
				else {
					Toast.makeText(getApplicationContext(), "服务器未响应...", Toast.LENGTH_SHORT).show();
				}
			}
			else {
				// 校验成功，启动主界面
				Intent intent = new Intent(getApplicationContext(), MainActivity.class);
				intent.putExtra("token", JSON.toJSONString(loginReponse.getData()));
				startActivity(intent);
			}
		}
	};

	/**
	 * Activity创建
	 * 
	 * @param savedInstanceState
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置不显示Activity的操作栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		// 绘制布局视图
		setContentView(R.layout.activity_login);

		// -----从视图获取组件-----//
		loginButton = (Button) findViewById(R.id.loginButton);
		usernameEditText = (EditText) findViewById(R.id.username);
		passwordEditText = (EditText) findViewById(R.id.password);
		clearUsernameButton = (Button) findViewById(R.id.bt_username_clear);
		clearPwdButton = (Button) findViewById(R.id.bt_pwd_clear);
		lookPwdButton = (Button) findViewById(R.id.bt_pwd_eye);

		// 设置登录按钮事件
		loginButton.setOnClickListener(this);
		clearUsernameButton.setOnClickListener(this);
		clearPwdButton.setOnClickListener(this);
		lookPwdButton.setOnClickListener(this);

		// 设置输入框文本变化事件监听逻辑
		initWatcher();

		// 文本改变事件监听逻辑
		usernameEditText.addTextChangedListener(usernameWatcher);
		passwordEditText.addTextChangedListener(passwordWatcher);
	}

	/**
	 * 统一的点击事件监听方法
	 * 
	 * @param v点击的视图实例
	 */
	@Override
	public void onClick(View v) {
		// 根据视图id区分事件来源
		switch (v.getId()) {
			case R.id.loginButton:// 登录按钮
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
				// 弹登录进度条
				progressDialog = ProgressDialog.show(v.getContext(), "提示", "正在登录....");
				try {
					// executorService = Executors.newCachedThreadPool();
					// Future<ApiResponse> fu = executorService.submit(new
					// Login(phone, password,
					// handler));
					// loginReponse = fu.get();// 阻塞get
					new Login2(phone, password, handler).start();

				}
				finally {
					// executorService.shutdown();
				}
				break;
			case R.id.bt_pwd_clear:// 密码清除按钮
				passwordEditText.setText("");
				break;
			case R.id.bt_pwd_eye:// 密码显示按钮
				if (passwordEditText.getInputType() == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)) {
					passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT
							| InputType.TYPE_TEXT_VARIATION_NORMAL);
					// passwordEditText.setBackgroundResource(R.drawable.eye_close);
				}
				else {
					passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT
							| InputType.TYPE_TEXT_VARIATION_PASSWORD);
					// passwordEditText.setBackgroundResource(R.drawable.eye_open);
				}
				break;
			case R.id.bt_username_clear:// 用户名清除按钮
				usernameEditText.setText("");
				break;

			default:
				break;
		}
	}

	private void initWatcher() {
		usernameWatcher = new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

			@Override
			public void afterTextChanged(Editable s) {
				if (s.toString().length() > 0) {
					clearUsernameButton.setVisibility(View.VISIBLE);
				}
				else {
					clearUsernameButton.setVisibility(View.INVISIBLE);
				}
			}
		};
		passwordWatcher = new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

			@Override
			public void afterTextChanged(Editable s) {
				if (s.toString().length() > 0) {
					clearPwdButton.setVisibility(View.VISIBLE);
				}
				else {
					clearPwdButton.setVisibility(View.INVISIBLE);
				}
			}
		};
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
