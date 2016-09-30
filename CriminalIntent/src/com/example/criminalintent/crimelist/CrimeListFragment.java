package com.example.criminalintent.crimelist;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import com.example.criminalintent.R;
import com.example.criminalintent.crimedetail.CrimeFragment;
import com.example.criminalintent.crimedetail.CrimePagerActivity;
import com.example.criminalintent.entity.Crime;
import com.example.criminalintent.entity.CrimeLab;

/**
 * 显示行为列表的fragment
 * 
 * @author liuyh 2016年9月21日
 */
public class CrimeListFragment extends ListFragment {

	/** 行为列表 */
	private ArrayList<Crime> crimes;
	/** 保存子标题显示状态 ，为了旋转后使用，保持于旋转前一致 */
	private boolean subtitleVisible;

	/**
	 * fragment创建
	 * 
	 * @param savedInstanceState
	 * @see android.app.Fragment#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		// 设置保留fragment，比如在设备旋转时
		setRetainInstance(true);
		// 子标题显示状态为false
		subtitleVisible = false;

		// 设置有操作栏菜单
		setHasOptionsMenu(true);
		// 给activity设置标题
		getActivity().setTitle(R.string.crime_title);
		// 获取行为列表实例
		crimes = (ArrayList<Crime>) CrimeLab.getIntance(getActivity()).getCrimes();
		// 定义adapter，此adapter使用一个android自带的列表布局xml作为fragment对应的布局xml
		// ArrayAdapter<Crime> adapter = new ArrayAdapter<>(getActivity(),
		// android.R.layout.simple_list_item_1, crimes);
		CrimeAdapter adapter = new CrimeAdapter(crimes);
		// 设置adapter为此fragment的adapter
		setListAdapter(adapter);

	}

	/**
	 * 创建fragment视图（这些方法都是Activity的fragmentManager调用fragment一个生命周期中的阶段）
	 * 
	 * @param inflater
	 * @param container
	 * @param savedInstanceState
	 * @return
	 * @see android.app.ListFragment#onCreateView(android.view.LayoutInflater,
	 *      android.view.ViewGroup, android.os.Bundle)
	 */
	/**
	 * @param inflater
	 * @param container
	 * @param savedInstanceState
	 * @return
	 * @see android.app.ListFragment#onCreateView(android.view.LayoutInflater,
	 *      android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = super.onCreateView(inflater, container, savedInstanceState);
		// 在旋转后不执行onCreate，而执行onCreateView，所以在create方法里显示子标题，遇到设备旋转，子标题不显示
		if (subtitleVisible) {
			getActivity().getActionBar().setSubtitle(R.string.subtitle);
		}
		// 获取列表视图
		ListView listView = (ListView) v.findViewById(android.R.id.list);

		// 判断sdk版本，大于honeycomb时使用setChoiceMode方法
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
			// 登记长按列表视图浮动上下文菜单（说明长按列表时，显示浮动上下文菜单）
			registerForContextMenu(listView);
		}
		else {
			// 在HONEYCOMB以上的系统版本中，设置为listview的选择模式（即长按列表时，在操作栏上出现一层菜单栏）
			listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
			// 设置listView多选模式时的事件监听逻辑
			listView.setMultiChoiceModeListener(new MultiChoiceModeListener() {

				@Override
				public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
					return false;
				}

				@Override
				public void onDestroyActionMode(ActionMode mode) {}

				@Override
				public boolean onCreateActionMode(ActionMode mode, Menu menu) {
					mode.getMenuInflater().inflate(R.menu.crime_list_item_context, menu);
					return true;
				}

				@Override
				public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
					switch (item.getItemId()) {
						case R.id.menu_item_deleteCrime:
							CrimeAdapter adapter = (CrimeAdapter) getListAdapter();
							// 遍历
							for (int i = adapter.getCount() - 1; i >= 0; i--) {
								if (getListView().isItemChecked(i)) {
									CrimeLab.getIntance(getActivity().getApplicationContext())
											.deleteCrime(adapter.getItem(i));
								}
							}
							mode.finish();
							adapter.notifyDataSetChanged();
							return true;
						default:
							return false;
					}
				}

				@Override
				public void onItemCheckedStateChanged(ActionMode mode, int position, long id,
						boolean checked) {}
			});
		}
		return v;
	}

	/**
	 * 点击列表项事件de 逻辑
	 * 
	 * @param l
	 * @param v
	 * @param position
	 * @param id
	 * @see android.app.ListFragment#onListItemClick(android.widget.ListView,
	 *      android.view.View, int, long)
	 */
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		Crime crime = ((CrimeAdapter) getListAdapter()).getItem(position);
		// use the crime that be clicked

		// 从fragment里启动另一个activity
		// Intent intent = new Intent(getActivity(), CrimeActivity.class);
		Intent intent = new Intent(getActivity(), CrimePagerActivity.class);

		intent.putExtra(CrimeFragment.EXTRA_CRIME_ID, crime.getId());
		startActivity(intent);
	}

	/**
	 * 生命周期中->暂停阶段
	 * 
	 * @see android.app.Fragment#onPause()
	 */
	@Override
	public void onPause() {
		super.onPause();
		CrimeLab.getIntance(getActivity().getApplicationContext()).serializeCrimes();
	}

	/**
	 * 恢复fragment
	 * 
	 * @see android.app.Fragment#onResume()
	 */
	@Override
	public void onResume() {
		super.onResume();
		((CrimeAdapter) getListAdapter()).notifyDataSetChanged();
	}

	/**
	 * 创建操作栏菜单项
	 * 
	 * @param menu
	 * @param inflater
	 * @see android.app.Fragment#onCreateOptionsMenu(android.view.Menu,
	 *      android.view.MenuInflater)
	 */
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		// 为了在旋转后 菜单项 ：显示菜单 与子标题显示状态匹配
		MenuItem showSubtitle = menu.findItem(R.id.show_subtitle);
		if (subtitleVisible && showSubtitle != null) {
			showSubtitle.setTitle(R.string.hide_subtitle);
		}
	}

	/**
	 * 创建浮动上下文菜单（在长按时，浮动出一层菜单，叫做浮动上下文菜单）
	 * 
	 * @param menu
	 * @param v 从哪一个视图浮动起的浮动上下文菜单
	 * @param menuInfo
	 * @see android.app.Fragment#onCreateContextMenu(android.view.ContextMenu,
	 *      android.view.View, android.view.ContextMenu.ContextMenuInfo)
	 */
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		getActivity().getMenuInflater().inflate(R.menu.crime_list_item_context, menu);
	}

	/**
	 * 操作栏菜单项被选择
	 * 
	 * @param item
	 * @return
	 * @see android.app.Fragment#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.show_subtitle:// 显示子标题按钮
				if (getActivity().getActionBar().getSubtitle() == null) {
					getActivity().getActionBar().setSubtitle(R.string.subtitle);
					item.setTitle(R.string.hide_subtitle);
					subtitleVisible = true;
				}
				else {
					getActivity().getActionBar().setSubtitle(null);
					item.setTitle(R.string.show_subtitle);
					subtitleVisible = false;
				}
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	/**
	 * 浮动上下文菜单项被选择
	 * 
	 * @param item
	 * @return
	 * @see android.app.Fragment#onContextItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		Crime crime = ((CrimeAdapter) getListAdapter()).getItem(info.position);

		switch (item.getItemId()) {
			case R.id.menu_item_deleteCrime:
				CrimeLab.getIntance(getActivity().getApplicationContext()).deleteCrime(crime);
				((CrimeAdapter) getListAdapter()).notifyDataSetChanged();
				return true;

			default:
				return super.onContextItemSelected(item);
		}
	}

	/**
	 * 内部类，定制的crime adapter类，用于让列表项显示定制的布局
	 * 
	 * @author liuyh 2016年9月21日
	 */
	private class CrimeAdapter extends ArrayAdapter<Crime> {

		/**
		 * 构造方法
		 * 
		 * @param crimes
		 */
		public CrimeAdapter(ArrayList<Crime> crimes) {
			super(getActivity(), 0, crimes);
		}

		/**
		 * 获得视图
		 * 
		 * @param position
		 * @param convertView
		 * @param parent
		 * @return
		 * @see android.widget.ArrayAdapter#getView(int, android.view.View,
		 *      android.view.ViewGroup)
		 */
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_crime,
						null);
			}
			Crime c = getItem(position);

			TextView titleTextView = (TextView) convertView
					.findViewById(R.id.crime_list_item_titleTextView);
			titleTextView.setText(c.getTitle());
			TextView dateTextView = (TextView) convertView
					.findViewById(R.id.crime_list_item_dateTextView);
			dateTextView.setText(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(c.getDate()));
			CheckBox solvedcCheckBox = (CheckBox) convertView
					.findViewById(R.id.crime_list_item_solvedCheckBox);
			solvedcCheckBox.setChecked(c.isSolved());
			return convertView;
		}

	}

}
