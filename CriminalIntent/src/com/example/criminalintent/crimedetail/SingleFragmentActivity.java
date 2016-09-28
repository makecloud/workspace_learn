package com.example.criminalintent.crimedetail;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

import com.example.criminalintent.R;

/**
 * 继承这个抽象activity类 ，目的统一使用一个布局xml文件
 * 
 * @author liuyh 2016年9月21日
 */
public abstract class SingleFragmentActivity extends Activity {
	/**
	 * 创建fragment
	 * 
	 * @return
	 */
	protected abstract Fragment createFragment();

	/**
	 * 创建activity
	 * 
	 * @param savedInstanceState
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置activity的布局xml
		setContentView(R.layout.activity_fragment);
		// 在activity里获取fm
		FragmentManager fm = getFragmentManager();
		// 使用fm获取fragment，获取为null，则创建fragment，并添加到本activity
		Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);
		if (fragment == null) {
			fragment = createFragment();
			fm.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
		}
	}
}
