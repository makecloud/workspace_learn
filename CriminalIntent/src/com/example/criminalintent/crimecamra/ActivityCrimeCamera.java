package com.example.criminalintent.crimecamra;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import com.example.criminalintent.crimedetail.ActivitySingleFragment;

public class ActivityCrimeCamera extends ActivitySingleFragment {

	@Override
	protected FragmentCrimeCamera createFragment() {
		return new FragmentCrimeCamera();
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
