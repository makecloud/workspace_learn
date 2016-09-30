package com.example.criminalintent.crimecamra;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.hardware.Camera.Size;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.criminalintent.R;
import com.example.criminalintent.util.CrimeIO;

/**
 * 相机fragment
 * 
 * @author liuyh 2016年9月30日
 */
public class FragmentCrimeCamera extends Fragment {

	private static final String TAG = "CrimeCameraFragment";
	public static final String EXTRA_PHOTO_FILENAME = "photo_filename";

	/** 组件引用： */
	/** 相机视图 */
	private SurfaceView surfaceView;
	/** 拍照按钮 */
	private Button takeButton;
	/** 系统相机引用 */
	private Camera camera;
	/** 进度条视图 */
	private View progressContainer;

	/** 相机拍照过程的回调 */
	private Camera.ShutterCallback shutterCallback = new ShutterCallback() {

		@Override
		public void onShutter() {
			progressContainer.setVisibility(View.VISIBLE);
		}
	};
	/** 相机生成jpeg图像后的回调 */
	private Camera.PictureCallback pictureCallback = new PictureCallback() {

		@Override
		public void onPictureTaken(byte[] data, Camera camera) {
			// 创建文件,保存到存储设备
			String fileName = UUID.randomUUID().toString() + ".jpg";
			FileOutputStream fos = null;
			boolean success = true;
			try {
				fos = new FileOutputStream(CrimeIO.getAppSDPath() + fileName);
				fos.write(data);
			}
			catch (IOException e) {
				Log.e(TAG, "保存照片出错");
				success = false;
			}
			finally {
				try {
					if (fos != null) {
						fos.close();
					}
				}
				catch (IOException e) {
					Log.e(TAG, "关闭fileOutputStream出错", e);
					success = false;
				}
			}
			if (success) {
				Log.i(TAG, "图片保存成功：" + fileName);
				Intent i = new Intent();
				i.putExtra(EXTRA_PHOTO_FILENAME, fileName);
				getActivity().setResult(Activity.RESULT_OK, i);
			}
			else {
				getActivity().setResult(Activity.RESULT_CANCELED);
			}
			getActivity().finish();
		}
	};

	/**
	 * 创建视图
	 * 
	 * @param inflater
	 * @param container
	 * @param savedInstanceState
	 * @return
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater,
	 *      android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// 根据布局生成视图
		View v = inflater.inflate(R.layout.fragment_crime_camera, container, false);
		// 获取视图中的组件
		surfaceView = (SurfaceView) v.findViewById(R.id.crime_camera_surfaceView);
		takeButton = (Button) v.findViewById(R.id.takePictureButton);
		progressContainer = v.findViewById(R.id.processContainer);
		progressContainer.setVisibility(View.INVISIBLE);

		/*
		 * surface对象起源：
		 * surfaceView控件出现在屏幕上时，会创建surface；
		 * surfaceView从屏幕上消失时，surface随机被销毁；
		 */

		// 获取holder。holder是camera与surface对象 沟通的纽带
		SurfaceHolder holder = surfaceView.getHolder();
		holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

		// 给holder添加监听surface周期的事件
		holder.addCallback(new SurfaceHolder.Callback() {

			// surface周期：销毁surface阶段
			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				if (camera != null) {
					// 相机停止预览
					camera.stopPreview();
				}
			}

			// surface周期：创建surface阶段
			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				try {
					if (camera != null) {
						// 相机关联到surfaceHolder
						camera.setPreviewDisplay(holder);
					}
				}
				catch (IOException e) {
					Log.e(TAG, "camera设置holder出错", e);
				}
			}

			// surface周期：surface发生改变
			@Override
			public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
				if (camera == null)
					return;
				// surface已改变大小，更新camera的预览大小（预览就是相机的实时取景）
				// 获取相机参数对象
				Camera.Parameters cameraParams = camera.getParameters();
				// 获取最合适的预览大小
				Size s = getBestSupportedSize(cameraParams.getSupportedPreviewSizes(), width,
						height);
				// 把最合适的预览大小放到相机参数对象中
				cameraParams.setPreviewSize(s.width, s.height);
				// 获取最合适的照片尺寸对象
				s = getBestSupportedSize(cameraParams.getSupportedPictureSizes(), width, height);
				// 把最合适的照片大小放到相机参数对象中
				cameraParams.setPictureSize(s.width, s.height);
				// 设置相机的拍摄参数
				camera.setParameters(cameraParams);

				try {
					// 相机开始预览
					camera.startPreview();
				}
				catch (Exception e) {
					Log.e(TAG, "camera开启预览出错", e);
					camera.release();
					camera = null;
				}
			}
		});
		// 设置拍照按钮单击事件监听逻辑
		takeButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (camera != null) {
					// 调用相机的拍照方法，注意传入的是相机拍照周期中的三个回调方法
					camera.takePicture(shutterCallback, null, pictureCallback);
				}
			}
		});

		// 返回视图对象给Activity
		return v;
	}

	/**
	 * 恢复fragment
	 * 
	 * @see android.support.v4.app.Fragment#onResume()
	 */
	@Override
	public void onResume() {
		super.onResume();
		// 打开相机资源
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
			camera = Camera.open(0);
		}
		else {
			camera = Camera.open();
		}
	}

	/**
	 * 暂停fragment
	 * 
	 * @see android.support.v4.app.Fragment#onPause()
	 */
	@Override
	public void onPause() {
		super.onPause();
		// 释放相机资源
		if (camera != null) {
			camera.release();
			camera = null;
		}
	}

	/**
	 * 算法：获取sizes数组列表中，最大的size，即size的长*宽最大的size
	 * 
	 * @param sizes
	 * @param width
	 * @param height
	 * @return
	 */
	private Size getBestSupportedSize(List<Size> sizes, int width, int height) {
		Size bestsSize = sizes.get(0);
		int largestArea = bestsSize.width * bestsSize.height;
		for (Size size : sizes) {
			int area = size.height * size.width;
			if (area > largestArea) {
				largestArea = area;
				bestsSize = size;
			}
		}
		return bestsSize;
	}
}
