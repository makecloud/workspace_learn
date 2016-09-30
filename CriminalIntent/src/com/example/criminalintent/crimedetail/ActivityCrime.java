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
 * ��Ϊ��ϸactivity��������Ϊ��ϸfragment
 * 
 * @author liuyh 2016��9��22��
 */
public class ActivityCrime extends FragmentActivity {

	/**
	 * ����activity
	 * 
	 * @param savedInstanceState
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ����activity�Ĳ���xml
		setContentView(R.layout.activity_fragment);
		// ��activity���ȡfm
		FragmentManager fm = getSupportFragmentManager();
		// ʹ��fm��ȡfragment����ȡΪnull���򴴽�fragment������ӵ���activity
		Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);
		if (fragment == null) {
			// �Ȼ�ȡactivity��intent�е�����
			UUID crimeId = (UUID) getIntent().getSerializableExtra(FragmentCrime.EXTRA_CRIME_ID);
			// ������Ϊ��ϸcrimeFragment
			fragment = FragmentCrime.newInstance(crimeId);
			// ��(��Ϊ��ϸcrimeFragment)��ӵ�activity
			fm.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
		}

	}

	/**
	 * ����ѡ��˵�
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
	 * ѡ��˵���ѡ��
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
