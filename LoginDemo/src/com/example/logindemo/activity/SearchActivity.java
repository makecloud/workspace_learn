package com.example.logindemo.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import com.example.logindemo.R;

/**
 * 搜索页面activity
 * 
 * @author liuyh 2016年10月13日
 */
public class SearchActivity extends FragmentActivity implements OnClickListener {

	// 建立控件的引用
	private ImageView goBackImageView;

	/**
	 * 创建
	 * 
	 * @param arg0
	 */
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		// 设置不现实Activity的操作栏action bar
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 设置视图的布局
		setContentView(R.layout.activity_search);
		// 设置控件的点击事件逻辑
		goBackImageView.setOnClickListener(this);
	}

	/**
	 * 初始化视图控件
	 */
	private void initViewComponent() {
		goBackImageView = (ImageView) findViewById(R.id.go_back);
	}

	/**
	 * 点击事件逻辑
	 * 
	 * @param v
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.go_back:
				// 回退到上一个Activity
				this.finish();
				break;

			default:
				break;
		}
	}
}
