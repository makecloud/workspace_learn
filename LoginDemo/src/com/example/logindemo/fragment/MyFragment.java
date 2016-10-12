package com.example.logindemo.fragment;

import java.util.ArrayList;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.logindemo.R;
import com.example.logindemo.entity.MyMenu;

/**
 * 【我的】页面
 * 
 * @author liuyh 2016年10月10日
 */
public class MyFragment extends ListFragment {

	private String[] myMenuNames = { "我的账户", "我的消息", "意见反馈", "帮助信息", "设置" };
	private int iconId[] = { R.drawable.my_account, R.drawable.my_message, R.drawable.my_advice,
			R.drawable.my_help, R.drawable.my_settings };

	private ArrayList<MyMenu> myMenus;

	/**
	 * 创建
	 * 
	 * @param savedInstanceState
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		myMenus = new ArrayList<MyMenu>();
		myMenus.add(new MyMenu(iconId[0], myMenuNames[0]));
		myMenus.add(new MyMenu(iconId[1], myMenuNames[1]));
		myMenus.add(new MyMenu(iconId[2], myMenuNames[2]));
		myMenus.add(new MyMenu(iconId[3], myMenuNames[3]));
		myMenus.add(new MyMenu(iconId[4], myMenuNames[4]));

		// 创建适配器
		MyItemAdapter adapter = new MyItemAdapter(myMenus);
		// TODO
		// 设置ListFragment的适配器
		setListAdapter(adapter);
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
		View v = inflater.inflate(R.layout.fragment_my, container);
		return v;

	}

	/**
	 * listView的适配器类
	 * 
	 * @author liuyh 2016年10月11日
	 */
	private class MyItemAdapter extends ArrayAdapter<MyMenu> {

		/**
		 * 构造方法中注入数据集
		 */
		public MyItemAdapter(ArrayList<MyMenu> datas) {
			super(getActivity(), 0, datas);
		}

		/**
		 * 生成一行的视图
		 * 
		 * @param position 第几行
		 * @param convertView
		 * @param parent
		 * @return
		 */
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = getActivity().getLayoutInflater()
						.inflate(R.layout.my_menu_item, null);
			}

			// 获取绑定的数据集的对应元素
			MyMenu menu = getItem(position);
			// 获取控件
			ImageView myMenuIcon = (ImageView) convertView.findViewById(R.id.my_menu_icon);
			TextView myMenuTextView = (TextView) convertView.findViewById(R.id.my_menu_text);
			// 设置控件
			myMenuIcon.setImageResource(menu.getMenuIconId());
			myMenuTextView.setText(menu.getMenuName());

			return convertView;
		}
	}
}
