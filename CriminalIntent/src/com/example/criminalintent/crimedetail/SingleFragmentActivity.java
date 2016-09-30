package com.example.criminalintent.crimedetail;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import com.example.criminalintent.R;

/**
 * �̳��������activity�� ��Ŀ��ͳһʹ��һ������xml�ļ�
 * 
 * @author liuyh 2016��9��21��
 */
public abstract class SingleFragmentActivity extends FragmentActivity {

	/**
	 * ����fragment
	 * 
	 * @return
	 */
	protected abstract Fragment createFragment();

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
			fragment = createFragment();
			fm.beginTransaction().add(R.id.fragmentContainer, fragment)
					.commit();
		}
	}
}
