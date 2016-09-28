package com.example.criminalintent.crimedetail;

import java.util.ArrayList;
import java.util.UUID;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.criminalintent.R;
import com.example.criminalintent.R.id;
import com.example.criminalintent.entity.Crime;
import com.example.criminalintent.entity.CrimeLab;

/**
 * 可以滑动的activity
 * 
 * @author liuyh 2016年9月22日
 */
public class CrimePagerActivity extends FragmentActivity {
	private ViewPager viewPager;
	private ArrayList<Crime> crimes;

	/**
	 * activity创建
	 * 
	 * @param savedInstanceState
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		viewPager = new ViewPager(this);
		viewPager.setId(R.id.viewPager);
		setContentView(viewPager);

		crimes = (ArrayList<Crime>) CrimeLab.getIntance(getApplication()).getCrimes();

		FragmentManager fm = getSupportFragmentManager();
		viewPager.setAdapter(new FragmentStatePagerAdapter(fm) {

			@Override
			public int getCount() {
				return crimes.size();
			}

			@Override
			public Fragment getItem(int arg0) {
				UUID crimeId = crimes.get(arg0).getId();
				CrimeFragment fragment = CrimeFragment.newInstance(crimeId);

				return fragment;
			}

		});
		UUID crimeId = (UUID) getIntent().getSerializableExtra(CrimeFragment.EXTRA_CRIME_ID);
		for (int i = 0; i < crimes.size(); i++) {
			if (crimes.get(i).getId().equals(crimeId)) {
				viewPager.setCurrentItem(i);
				break;
			}

		}
		// 设置滑动事件逻辑
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				Crime crime = crimes.get(arg0);
				if (crime.getTitle() != null) {
					setTitle(crime.getTitle());
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});

	}

}
