package com.example.criminalintent.crimecamra;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import com.example.criminalintent.crimedetail.SingleFragmentActivity;

public class CrimeCameraActivity extends SingleFragmentActivity {

	@Override
	protected CrimeCameraFragment createFragment() {
		return new CrimeCameraFragment();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// ���ز�����
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// ����֪ͨ��
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

		super.onCreate(savedInstanceState);
	}
}
