package com.example.logindemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.logindemo.R;

public class MainActivity extends FragmentActivity implements OnClickListener {

	/** 定义组件引用 */
	private TextView noteTextView;
	private TextView textViewHome;
	private TextView textViewAdv;
	private TextView textViewCharge;
	private TextView textViewMy;
	private LinearLayout homeLinearLayout;
	private LinearLayout advPutLinearLayout;
	private LinearLayout chargeLinearLayout;
	private LinearLayout myLinearLayout;
	private ImageButton btnHome;
	private ImageButton btnAdvPut;
	private ImageButton btnCharge;
	private ImageButton btnMy;

	/** 4个fragment引用 */
	private Fragment homeFragment;
	private Fragment AdvFragment;
	private Fragment chargeFragment;
	private Fragment myFragment;
	Fragment[] fragments;

	FragmentManager fm;

	/**
	 * activity创建
	 * 
	 * @param savedInstanceState
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 隐藏操作栏action bar
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		// 设置操作栏标题：主界面
		setTitle("主界面");
		// 获取FragmentManager
		fm = getSupportFragmentManager();
		initView();
		initFragment();
		// 设置点击事件逻辑
		homeLinearLayout.setOnClickListener(this);
		advPutLinearLayout.setOnClickListener(this);
		chargeLinearLayout.setOnClickListener(this);
		myLinearLayout.setOnClickListener(this);

		// 获取上一个Activity传入数据
		Intent in = getIntent();
		String loginData = (String) in.getSerializableExtra("token");

	}

	/**
	 * 初始化视图控件
	 * (获取各个控件的实例)
	 */
	private void initView() {

		// 底部菜单的四个linearLayout布局。没个linearLayout包含下面一个按钮一个文字
		homeLinearLayout = (LinearLayout) findViewById(R.id.home_page);
		advPutLinearLayout = (LinearLayout) findViewById(R.id.adv_put);
		chargeLinearLayout = (LinearLayout) findViewById(R.id.charge);
		myLinearLayout = (LinearLayout) findViewById(R.id.my);

		// 底部菜单的按钮
		btnHome = (ImageButton) findViewById(R.id.btn_home);
		btnAdvPut = (ImageButton) findViewById(R.id.btn_adv_put);
		btnCharge = (ImageButton) findViewById(R.id.btn_charge);
		btnMy = (ImageButton) findViewById(R.id.btn_my);

		// 底部菜单的文字
		textViewHome = (TextView) findViewById(R.id.text_home);
		textViewAdv = (TextView) findViewById(R.id.text_adv);
		textViewCharge = (TextView) findViewById(R.id.text_charge);
		textViewMy = (TextView) findViewById(R.id.text_my);
	}

	/**
	 * 初始化4个fragment。
	 * 并用一个数组fragments来存放四个fargment
	 */
	public void initFragment() {
		homeFragment = fm.findFragmentById(R.id.fragment_home);
		AdvFragment = fm.findFragmentById(R.id.fragment_adv);
		chargeFragment = fm.findFragmentById(R.id.fragment_charge);
		myFragment = fm.findFragmentById(R.id.fragment_my);
		fragments = new Fragment[] { homeFragment, AdvFragment, chargeFragment, myFragment };
		showFragment(0);
	}

	/**
	 * 点击底部菜单时，显示一个对应的fragment
	 * （四个fragment作为一个数组，用下标来传递哪一个fragment）
	 * 
	 * @param fragment下标
	 */
	public void showFragment(int witch) {
		fm.beginTransaction().hide(fragments[0]).hide(fragments[1]).hide(fragments[2])
				.hide(fragments[3]).show(fragments[witch]).commit();

	}

	/**
	 * 重置所有底部按钮的图标为未选中图标样式
	 */
	public void resetAllButtonStatu() {
		// 设置底部菜单图片按钮为未选择图标
		btnHome.setImageResource(R.drawable.home_unselected);
		btnAdvPut.setImageResource(R.drawable.adv_put_unselected);
		btnCharge.setImageResource(R.drawable.charge_unselected);
		btnMy.setImageResource(R.drawable.my_unselected);

		// 设置底部菜单文字的颜色为灰色
		textViewHome.setTextColor(getResources().getColor(R.color.gray));
		textViewAdv.setTextColor(getResources().getColor(R.color.gray));
		textViewCharge.setTextColor(getResources().getColor(R.color.gray));
		textViewMy.setTextColor(getResources().getColor(R.color.gray));

	}

	/**
	 * 在本Activity上的点击事件逻辑
	 * 
	 * @param v
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.home_page:// 【首页】被点击
				// 设置图标为选中样式
				resetAllButtonStatu();
				btnHome.setImageResource(R.drawable.home_selected);
				textViewHome.setTextColor(getResources().getColor(R.color.black));
				// 启动【首页】fragment
				showFragment(0);
				break;
			case R.id.adv_put:// 【广告投放】被点击
				// 设置图标为选中样式
				resetAllButtonStatu();
				btnAdvPut.setImageResource(R.drawable.adb_put_selected);
				textViewAdv.setTextColor(getResources().getColor(R.color.black));
				// 启动【广告投放】fragment
				showFragment(1);
				break;
			case R.id.charge:// 【充值】被点击
				// 设置图标为选中样式
				resetAllButtonStatu();
				btnCharge.setImageResource(R.drawable.charge_selected);
				textViewCharge.setTextColor(getResources().getColor(R.color.black));
				// 启动【充值】fragment
				showFragment(2);
				break;
			case R.id.my:// 【我的】被点击
				// 设置图标为选中样式
				resetAllButtonStatu();
				btnMy.setImageResource(R.drawable.my_selected);
				textViewMy.setTextColor(getResources().getColor(R.color.black));
				// 启动【我的】fragment
				showFragment(3);
				break;
			default:
				break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_mainactivity, menu);
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
