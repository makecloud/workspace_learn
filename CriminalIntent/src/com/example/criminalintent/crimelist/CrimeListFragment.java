package com.example.criminalintent.crimelist;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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
 * ��ʾ��Ϊ�б��fragment
 * 
 * @author liuyh 2016��9��21��
 */
public class CrimeListFragment extends ListFragment {

	/** ��Ϊ�б� */
	private ArrayList<Crime> crimes;
	/** �����ӱ�����ʾ״̬ ��Ϊ����ת��ʹ�ã���������תǰһ�� */
	private boolean subtitleVisible;

	/**
	 * fragment����
	 * 
	 * @param savedInstanceState
	 * @see android.app.Fragment#onCreate(android.os.Bundle)
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
		// ����adapter����adapterʹ��һ��android�Դ����б���xml��Ϊfragment��Ӧ�Ĳ���xml
		// ArrayAdapter<Crime> adapter = new ArrayAdapter<>(getActivity(),
		// android.R.layout.simple_list_item_1, crimes);
		CrimeAdapter adapter = new CrimeAdapter(crimes);
		// ����adapterΪ��fragment��adapter
		setListAdapter(adapter);

	}

	/**
	 * ����fragment��ͼ����Щ��������Activity��fragmentManager����fragmentһ�����������еĽ׶Σ�
	 * 
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
		// ����ת��ִ��onCreate����ִ��onCreateView��������create��������ʾ�ӱ��⣬�����豸��ת���ӱ��ⲻ��ʾ
		if (subtitleVisible) {
			getActivity().getActionBar().setSubtitle(R.string.subtitle);
		}
		// ��ȡ�б���ͼ
		ListView listView = (ListView) v.findViewById(android.R.id.list);
		// �Ǽǳ����б���ͼ���������Ĳ˵�
		registerForContextMenu(listView);
		return v;
	}

	/**
	 * ����б����¼�de �߼�
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

		// ��fragment��������һ��activity
		// Intent intent = new Intent(getActivity(), CrimeActivity.class);
		Intent intent = new Intent(getActivity(), CrimePagerActivity.class);

		intent.putExtra(CrimeFragment.EXTRA_CRIME_ID, crime.getId());
		startActivity(intent);
	}

	/**
	 * ����������->��ͣ�׶�
	 * 
	 * @see android.app.Fragment#onPause()
	 */
	@Override
	public void onPause() {
		super.onPause();
		CrimeLab.getIntance(getActivity().getApplicationContext()).serializeCrimes();
	}

	/**
	 * �ָ�fragment
	 * 
	 * @see android.app.Fragment#onResume()
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
	 * @see android.app.Fragment#onCreateOptionsMenu(android.view.Menu,
	 *      android.view.MenuInflater)
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
	 * ���������Ĳ������˵����ڳ���ʱ����������һ������������������Ĳ������˵���
	 * 
	 * @param menu
	 * @param v ���Ǹ���ͼ������������Ĳ������˵�
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
	 * �������˵��ѡ��
	 * 
	 * @param item
	 * @return
	 * @see android.app.Fragment#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.show_subtitle:
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
	 * �����Ĳ������˵��ѡ��
	 * 
	 * @param item
	 * @return
	 * @see android.app.Fragment#onContextItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		// case :
		//
		// return true;
			default:
				return super.onContextItemSelected(item);
		}

	}

	/**
	 * �ڲ��࣬���Ƶ�crime adapter�࣬�������б�����ʾ���ƵĲ���
	 * 
	 * @author liuyh 2016��9��21��
	 */
	private class CrimeAdapter extends ArrayAdapter<Crime> {

		public CrimeAdapter(ArrayList<Crime> crimes) {
			super(getActivity(), 0, crimes);
		}

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
