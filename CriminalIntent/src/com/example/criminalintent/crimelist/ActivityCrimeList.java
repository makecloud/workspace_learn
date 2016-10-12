package com.example.criminalintent.crimelist;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;
import com.example.criminalintent.R;
import com.example.criminalintent.crimedetail.ActivityCrimePager;
import com.example.criminalintent.crimedetail.ActivitySingleFragment;
import com.example.criminalintent.crimedetail.FragmentCrime;
import com.example.criminalintent.entity.Crime;
import com.example.criminalintent.entity.CrimeLab;

public class ActivityCrimeList extends ActivitySingleFragment {

	@Override
	protected Fragment createFragment() {

		return new FragmentCrimeList();
	}

	/**
	 * ActivityManager�����˵�����ʱ���ô˷���
	 * 
	 * @param menu�˵�����
	 * @return
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// ���˵�
		getMenuInflater().inflate(R.menu.fragment_crime_list, menu);
		return super.onCreateOptionsMenu(menu);

	}

	/**
	 * �������˵��ѡ��
	 * 
	 * @param item�˵������
	 * @return
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.new_crime:// �½���ť
				Crime crime = new Crime();
				CrimeLab.getIntance(getApplicationContext()).addCrime(crime);
				Intent intent = new Intent(getApplicationContext(), ActivityCrimePager.class);
				intent.putExtra(FragmentCrime.EXTRA_CRIME_ID, crime.getId());
				startActivityForResult(intent, 0);
				return true;
				/*
				 * case R.id.show_subtitle: if (getActionBar().getSubtitle() ==
				 * null) { getActionBar().setSubtitle(R.string.subtitle);
				 * item.setTitle(R.string.hide_subtitle); } else {
				 * getActionBar().setSubtitle(null);
				 * item.setTitle(R.string.show_subtitle); } return true;
				 */
			default:
				return super.onOptionsItemSelected(item);
		}
	}
}
