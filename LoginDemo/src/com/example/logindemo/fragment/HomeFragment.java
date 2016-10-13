package com.example.logindemo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.example.logindemo.R;
import com.example.logindemo.activity.SearchActivity;

/**
 * 【首页】fragment页面
 * 
 * @author liuyh 2016年10月10日
 */
public class HomeFragment extends Fragment implements OnClickListener {

	// 建立控件的引用
	private LinearLayout searchLinearLayout;

	/**
	 * 创建
	 * 
	 * @param savedInstanceState
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	/**
	 * 创建视图
	 * 
	 * @param inflater
	 * @param container
	 * @param savedInstanceState
	 * @return
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_home, container);
		// 初始化视图控件
		initViewComponent(v);
		// 给搜索白框设置点击事件
		searchLinearLayout.setOnClickListener(this);
		return v;
	}

	/**
	 * 初始化视图控件
	 * 
	 * @param v
	 */
	private void initViewComponent(View v) {
		searchLinearLayout = (LinearLayout) v.findViewById(R.id.search_background);
	}

	/**
	 * 点击事件逻辑
	 * 
	 * @param v
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.search_background:// 点击搜索白框控件
				// 启动搜索Activity
				startActivity(new Intent(getActivity(), SearchActivity.class));
				break;

			default:
				break;
		}
	}
}
