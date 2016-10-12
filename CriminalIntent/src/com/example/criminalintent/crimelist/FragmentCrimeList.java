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
 * ��ʾ��Ϊ�б��LIstFragment
 * crimes����ע���������������ݼ�����listView����������ȡÿ����ͼ.
 * 
 * @author liuyh 2016��9��21��
 */
public class FragmentCrimeList extends ListFragment {

	/** ��Ϊ�б� */
	private ArrayList<Crime> crimes;
	/** �����ӱ�����ʾ״̬ ��Ϊ����ת��ʹ�ã���������תǰһ�� */
	private boolean subtitleVisible;

	/**
	 * fragment����
	 * 
	 * @param savedInstanceState
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ���ñ���fragment���������豸��תʱ
		setRetainInstance(true);
		// �ӱ�����ʾ״̬Ϊfalse
		subtitleVisible = false;
		// �����в������˵�
		setHasOptionsMenu(true);
		// ��activity���ñ���
		getActivity().setTitle(R.string.crime_title);
		// ��ȡ��Ϊ�б�ʵ��
		crimes = (ArrayList<Crime>) CrimeLab.getIntance(getActivity()).getCrimes();
		// ����adapter������crimes���飬��adapterʹ��һ��android�Դ����б���xml��ΪListViewÿ�����ͼ
		// ArrayAdapter<Crime> adapter = new ArrayAdapter<>(getActivity(),
		// android.R.layout.simple_list_item_1, crimes);
		// ����adapter������crimes�������������ȡlistView��ÿ��
		CrimeAdapter adapter = new CrimeAdapter(crimes);
		// ����ListFragment����������listView�������������ȡ��Ҫ��ʾ�ĵ�ÿ�ÿ�����Ǹ���ͼ����
		setListAdapter(adapter);
	}

	/**
	 * ����fragment��ͼ����Щ��������Activity��fragmentManager����fragmentһ�����������еĽ׶Σ�
	 * 
	 * @param inflater
	 * @param container
	 * @param savedInstanceState
	 * @return
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// ���ﲢû�д�������xml�ļ�����inflate����ͼʵ����
		// ��Ϊ�����super.onCreateView������Ĭ�ϲ�����ͼ��Ĭ�ϲ�����ͼ�ְ���һ��listView��ͼ��
		// ��ʱ��ʹ��Ĭ�ϴ����Ĳ�����ͼv
		View v = super.onCreateView(inflater, container, savedInstanceState);
		// ����ת��ִ��onCreate����ִ��onCreateView��������create��������ʾ�ӱ��⣬�����豸��ת���ӱ��ⲻ��ʾ
		if (subtitleVisible) {
			getActivity().getActionBar().setSubtitle(R.string.subtitle);
		}
		// ��Ĭ�ϴ����Ĳ�����ͼ�л�ȡ�б����
		ListView listView = (ListView) v.findViewById(android.R.id.list);

		// �ж�sdk�汾������honeycombʱʹ��setChoiceMode����
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
			// �Ǽǳ����б���ͼ���������Ĳ˵���˵�������б�ʱ����ʾ���������Ĳ˵���
			registerForContextMenu(listView);
		}
		else {
			// ��HONEYCOMB���ϵ�ϵͳ�汾�У�����Ϊlistview��ѡ��ģʽ���������б�ʱ���ڲ������ϳ���һ��˵�����
			listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
			// ����listView��ѡģʽʱ���¼������߼�
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
							// ����
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
	 * ����б����¼��� �߼�
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

		// ��fragment��������һ��activity
		// Intent intent = new Intent(getActivity(), CrimeActivity.class);
		Intent intent = new Intent(getActivity(), ActivityCrimePager.class);

		intent.putExtra(FragmentCrime.EXTRA_CRIME_ID, crime.getId());
		startActivity(intent);
	}

	/**
	 * ����������->��ͣ�׶�
	 */
	@Override
	public void onPause() {
		super.onPause();
		CrimeLab.getIntance(getActivity().getApplicationContext()).serializeCrimes();
	}

	/**
	 * �ָ�fragment
	 */
	@Override
	public void onResume() {
		super.onResume();
		((CrimeAdapter) getListAdapter()).notifyDataSetChanged();
	}

	/**
	 * �����������˵���
	 * 
	 * @param menu
	 * @param inflater
	 */
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		// Ϊ������ת�� �˵��� ����ʾ�˵� ���ӱ�����ʾ״̬ƥ��
		MenuItem showSubtitle = menu.findItem(R.id.show_subtitle);
		if (subtitleVisible && showSubtitle != null) {
			showSubtitle.setTitle(R.string.hide_subtitle);
		}
	}

	/**
	 * �������������Ĳ˵����ڳ���ʱ��������һ��˵����������������Ĳ˵���
	 * 
	 * @param menu
	 * @param v ����һ����ͼ������ĸ��������Ĳ˵�
	 * @param menuInfo
	 */
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		getActivity().getMenuInflater().inflate(R.menu.crime_list_item_context, menu);
	}

	/**
	 * �������˵��ѡ��
	 * 
	 * @param item
	 * @return
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.show_subtitle:// ��ʾ�ӱ��ⰴť
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
	 * ���������Ĳ˵��ѡ��
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
	 * �ڲ��࣬���Ƶ�crime �������ࡣ
	 * ListFragment������listViewͨ������������ȡҪ��ʾ����ͼ�
	 * ��������Ҫ����һ�����������Ϊ��listView�ؼ�������
	 * ����������������ͼ��ķ�����
	 * 
	 * @author liuyh 2016��9��21��
	 */
	private class CrimeAdapter extends ArrayAdapter<Crime> {

		/**
		 * �ڹ��췽��������ΪlistView�ؼ������ݵ�����
		 * 
		 * @param crimes����
		 */
		public CrimeAdapter(ArrayList<Crime> crimes) {
			// ��0��Ϊ������Դid������ʹ��Ԥ���岼��
			super(getActivity(), 0, crimes);
		}

		/**
		 * ���ÿ�������ͼ��
		 * ������������������б���ÿ���а����Ŀؼ�
		 * 
		 * @param position listView�ĵڼ���
		 * @param convertView
		 * @param parent
		 * @return
		 */
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// ���ɽ�Ҫת��ΪlistView�����ͼ������ʹ�����Լ�����Ĳ����ļ�������ͼ��
			if (convertView == null) {
				convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_crime,
						null);
			}
			// ��ȡ��adapter�����ݣ���crimes���飩�е���
			Crime c = getItem(position);

			// ��ͼ�������
			TextView titleTextView;
			TextView dateTextView;
			CheckBox solvedCheckBox;
			// ��ȡ��ͼ���ʵ��
			titleTextView = (TextView) convertView.findViewById(R.id.crime_list_item_titleTextView);
			dateTextView = (TextView) convertView.findViewById(R.id.crime_list_item_dateTextView);
			solvedCheckBox = (CheckBox) convertView
					.findViewById(R.id.crime_list_item_solvedCheckBox);
			// ������ͼ�ؼ�
			titleTextView.setText(c.getTitle());
			dateTextView.setText(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(c.getDate()));
			solvedCheckBox.setChecked(c.isSolved());
			return convertView;
		}
	}

}
