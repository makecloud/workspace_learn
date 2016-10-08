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
 * 照片对话框
 * 
 * @author liuyh 2016年9月30日
 */
public class FragmentCrimePhotoDialog extends DialogFragment {

	public static final String EXTRA_PHOTO_PATH = "photo_path";
	private ImageView imageView;

	/**
	 * 生成自己的实例，的同时设置fragment argument，容纳照片路径。
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
	 * 创建视图
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
	 * 销毁
	 */
	@Override
	public void onDestroy() {
		super.onDestroy();
		PictureUtils.cleanImageView(imageView);
	}
}