package com.example.criminalintent.crimelist;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;
import com.example.criminalintent.R;
import com.example.criminalintent.crimedetail.CrimeFragment;
import com.example.criminalintent.crimedetail.CrimePagerActivity;
import com.example.criminalintent.crimedetail.SingleFragmentActivity;
import com.example.criminalintent.entity.Crime;
import com.example.criminalintent.entity.CrimeLab;

public class CrimeListActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {

		return new CrimeListFragment();
	}

	/**
	 * ActivityManager创建菜单周期时调用此方法
	 * 
	 * @param menu
	 * @return
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// 画菜单
		getMenuInflater().inflate(R.menu.fragment_crime_list, menu);
		return super.onCreateOptionsMenu(menu);

	}

	/**
	 * 操作栏菜单项被选择
	 * 
	 * @param item
	 * @return
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.new_crime:// 新建按钮
				Crime crime = new Crime();
				CrimeLab.getIntance(getApplicationContext()).addCrime(crime);
				Intent intent = new Intent(getApplicationContext(), CrimePagerActivity.class);
				intent.putExtra(CrimeFragment.EXTRA_CRIME_ID, crime.getId());
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
