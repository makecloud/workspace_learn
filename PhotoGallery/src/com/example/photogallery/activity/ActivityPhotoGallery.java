package com.example.photogallery.activity;

import android.support.v4.app.Fragment;
import com.example.photogallery.fragment.FragmentPhotoGallery;

public class ActivityPhotoGallery extends ActivitySingleFragment {

	@Override
	protected Fragment createFragment() {
		return new FragmentPhotoGallery();
	}
}
