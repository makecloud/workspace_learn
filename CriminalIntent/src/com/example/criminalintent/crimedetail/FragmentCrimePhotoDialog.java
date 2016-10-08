package com.example.criminalintent.crimedetail;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.criminalintent.util.PictureUtils;

/**
 * ��Ƭ�Ի���
 * 
 * @author liuyh 2016��9��30��
 */
public class FragmentCrimePhotoDialog extends DialogFragment {

	public static final String EXTRA_PHOTO_PATH = "photo_path";
	private ImageView imageView;

	/**
	 * �����Լ���ʵ������ͬʱ����fragment argument��������Ƭ·����
	 * 
	 * @param photoPath
	 * @return
	 */
	public static FragmentCrimePhotoDialog getInstance(String photoPath) {
		Bundle bundle = new Bundle();
		bundle.putSerializable(EXTRA_PHOTO_PATH, photoPath);
		FragmentCrimePhotoDialog frg = new FragmentCrimePhotoDialog();
		frg.setArguments(bundle);
		frg.setStyle(DialogFragment.STYLE_NO_TITLE, 0);

		return frg;
	}

	/**
	 * ������ͼ
	 * 
	 * @param inflater
	 * @param container
	 * @param savedInstanceState
	 * @return
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// View v = super.onCreateView(inflater, container, savedInstanceState);
		imageView = new ImageView(getActivity());
		String path = (String) getArguments().getSerializable(EXTRA_PHOTO_PATH);
		BitmapDrawable b = PictureUtils.getScaledDrawable(getActivity(), path);
		imageView.setImageDrawable(b);
		return imageView;
	}

	/**
	 * ����
	 */
	@Override
	public void onDestroy() {
		super.onDestroy();
		PictureUtils.cleanImageView(imageView);
	}
}