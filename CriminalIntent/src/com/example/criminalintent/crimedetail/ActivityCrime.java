package com.example.criminalintent.crimedetail;

import java.util.UUID;

import com.example.criminalintent.R;
import com.example.criminalintent.R.id;
import com.example.criminalintent.R.layout;
import com.example.criminalintent.R.menu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;

/**
 * 行为详细activity，承载行为详细fragment
 * 
 * @author liuyh 2016年9月22日
 */
public class ActivityCrime extends FragmentActivity {

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
		FragmentManager fm = getSupportFragmentManager();
		// 使用fm获取fragment，获取为null，则创建fragment，并添加到本activity
		Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);
		if (fragment == null) {
			// 先获取activity的intent中的数据
			UUID crimeId = (UUID) getIntent().getSerializableExtra(FragmentCrime.EXTRA_CRIME_ID);
			// 创建行为详细crimeFragment
			fragment = FragmentCrime.newInstance(crimeId);
			// 将(行为详细crimeFragment)添加到activity
			fm.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
		}

	}

	/**
	 * 创建选项菜单
	 * 
	 * @param menu
	 * @return
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.crime, menu);
		return true;
	}

	/**
	 * 选择菜单的选项
	 * 
	 * @param item
	 * @return
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
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
