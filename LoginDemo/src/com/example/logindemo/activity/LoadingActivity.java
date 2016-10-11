package com.example.logindemo.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import com.example.logindemo.R;

public class LoadingActivity extends Activity {

	public static LoadingActivity instance;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loading);
		instance = this;

		// 这里Handler的postDelayed方法，等待10000毫秒在执行run方法。
		// 在Activity中我们经常需要使用Handler方法更新UI或者执行一些耗时事件，
		// 并且Handler中post方法既可以执行耗时事件也可以做一些UI更新的事情，比较好用，推荐使用
		// new Handler().postDelayed(new Runnable() {
		//
		// public void run() {
		// // 等待10000毫秒后销毁此页面，并提示登陆成功
		// LoadingActivity.this.finish();
		// Toast.makeText(getApplicationContext(), "登录成功",
		// Toast.LENGTH_SHORT).show();
		// }
		// }, 10000);

	}

	@Override
	public View onCreateView(String name, Context context, AttributeSet attrs) {
		return super.onCreateView(name, context, attrs);
	}

	@Override
	protected void onResume() {
		super.onResume();

	}
}