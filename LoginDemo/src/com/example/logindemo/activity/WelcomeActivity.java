package com.example.logindemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import com.example.logindemo.R;

public class WelcomeActivity extends Activity {

	Handler handler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// 设置不显示Activity的操作栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_welcome);

		// 延迟3.5秒执行 run
		// handler.postDelayed(new WelcomeDo(), 3500);
		// 方便测试延迟1秒
		handler.postDelayed(new WelcomeDo(), 1000);
	}

	class WelcomeDo implements Runnable {

		@Override
		public void run() {
			// // 启动登录activity
			// startActivity(new Intent(getApplicationContext(),
			// LoginActivity.class));
			// WelcomeActivity.this.finish();

			// 方便测试，直接启动主activity
			Intent intent = new Intent(getApplicationContext(), MainActivity.class);
			intent.putExtra("token", "方便测试。已跳过登录activity");
			startActivity(intent);
			WelcomeActivity.this.finish();
		}
	}
}
