package com.example.logindemo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.example.logindemo.R;

/**
 * 搜索页 搜索历史记录fragment
 * 
 * @author liuyh 2016年10月13日
 */
public class SearchFragment extends Fragment implements OnClickListener {

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
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	/**
	 * 点击事件处理方法
	 * 
	 * @param v
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.go_back:// 搜索页的，返回图片
				// 返回一层Activity
				// TODO
				break;

			default:
				break;
		}
	}
}
