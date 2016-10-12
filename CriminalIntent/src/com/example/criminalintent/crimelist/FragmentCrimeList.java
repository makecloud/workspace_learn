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
import com.example.criminalintent.crimedetail.ActivityCrimePager;
import com.example.criminalintent.crimedetail.FragmentCrime;
import com.example.criminalintent.entity.Crime;
import com.example.criminalintent.entity.CrimeLab;

/**
 * 显示行为列表的LIstFragment
 * crimes数组注入适配器（绑定数据集），listView从适配器获取每项视图.
 * 
 * @author liuyh 2016年9月21日
 */
public class FragmentCrimeList extends ListFragment {

	/** 行为列表 */
	private ArrayList<Crime> crimes;
	/** 保存子标题显示状态 ，为了旋转后使用，保持于旋转前一致 */
	private boolean subtitleVisible;

	/**
	 * fragment创建
	 * 
	 * @param savedInstanceState
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
		// 创建adapter，传入crimes数组，此adapter使用一个android自带的列表布局xml作为ListView每项的视图
		// ArrayAdapter<Crime> adapter = new ArrayAdapter<>(getActivity(),
		// android.R.layout.simple_list_item_1, crimes);
		// 创建adapter。传入crimes数组对象，用来获取listView的每项
		CrimeAdapter adapter = new CrimeAdapter(crimes);
		// 设置ListFragment的适配器，listView会调用适配器获取将要显示的的每项（每个项是个视图对象）
		setListAdapter(adapter);
	}

	/**
	 * 创建fragment视图（这些方法都是Activity的fragmentManager调用fragment一个生命周期中的阶段）
	 * 
	 * @param inflater
	 * @param container
	 * @param savedInstanceState
	 * @return
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// 这里并没有创建布局xml文件，用inflate画视图实例。
		// 因为下面的super.onCreateView创建了默认布局视图。默认布局视图又包含一个listView视图。
		// 暂时先使用默认创建的布局视图v
		View v = super.onCreateView(inflater, container, savedInstanceState);
		// 在旋转后不执行onCreate，而执行onCreateView，所以在create方法里显示子标题，遇到设备旋转，子标题不显示
		if (subtitleVisible) {
			getActivity().getActionBar().setSubtitle(R.string.subtitle);
		}
		// 从默认创建的布局视图中获取列表组件
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
	 * 点击列表项事件的 逻辑
	 * 
	 * @param l
	 * @param v
	 * @param position
	 * @param id
	 */
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		Crime crime = ((CrimeAdapter) getListAdapter()).getItem(position);
		// use the crime that be clicked

		// 从fragment里启动另一个activity
		// Intent intent = new Intent(getActivity(), CrimeActivity.class);
		Intent intent = new Intent(getActivity(), ActivityCrimePager.class);

		intent.putExtra(FragmentCrime.EXTRA_CRIME_ID, crime.getId());
		startActivity(intent);
	}

	/**
	 * 生命周期中->暂停阶段
	 */
	@Override
	public void onPause() {
		super.onPause();
		CrimeLab.getIntance(getActivity().getApplicationContext()).serializeCrimes();
	}

	/**
	 * 恢复fragment
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
	 * 内部类，定制的crime 适配器类。
	 * ListFragment包含的listView通过适配器来获取要显示的视图项。
	 * 适配器需要传入一个数组对象，作为给listView控件的数据
	 * 适配器包含生成视图项的方法。
	 * 
	 * @author liuyh 2016年9月21日
	 */
	private class CrimeAdapter extends ArrayAdapter<Crime> {

		/**
		 * 在构造方法传入作为listView控件的数据的数组
		 * 
		 * @param crimes数组
		 */
		public CrimeAdapter(ArrayList<Crime> crimes) {
			// 传0作为布局资源id，代表不使用预定义布局
			super(getActivity(), 0, crimes);
		}

		/**
		 * 获得每个项的视图。
		 * 这个方法是用来生成列表项每项中包含的控件
		 * 
		 * @param position listView的第几项
		 * @param convertView
		 * @param parent
		 * @return
		 */
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// 生成将要转化为listView项的视图（这里使用了自己定义的布局文件生成视图）
			if (convertView == null) {
				convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_crime,
						null);
			}
			// 获取本adapter的数据（即crimes数组）中的项
			Crime c = getItem(position);

			// 视图组件引用
			TextView titleTextView;
			TextView dateTextView;
			CheckBox solvedCheckBox;
			// 获取视图组件实例
			titleTextView = (TextView) convertView.findViewById(R.id.crime_list_item_titleTextView);
			dateTextView = (TextView) convertView.findViewById(R.id.crime_list_item_dateTextView);
			solvedCheckBox = (CheckBox) convertView
					.findViewById(R.id.crime_list_item_solvedCheckBox);
			// 设置试图控件
			titleTextView.setText(c.getTitle());
			dateTextView.setText(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(c.getDate()));
			solvedCheckBox.setChecked(c.isSolved());
			return convertView;
		}
	}

}
