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
 * ���fragment
 * 
 * @author liuyh 2016��9��30��
 */
public class FragmentCrimeCamera extends Fragment {

	private static final String TAG = "CrimeCameraFragment";
	public static final String EXTRA_PHOTO_FILENAME = "photo_filename";

	/** ������ã� */
	/** �����ͼ */
	private SurfaceView surfaceView;
	/** ���հ�ť */
	private Button takeButton;
	/** ϵͳ������� */
	private Camera camera;
	/** ��������ͼ */
	private View progressContainer;

	/** ������չ��̵Ļص� */
	private Camera.ShutterCallback shutterCallback = new ShutterCallback() {

		@Override
		public void onShutter() {
			progressContainer.setVisibility(View.VISIBLE);
		}
	};
	/** �������jpegͼ���Ļص� */
	private Camera.PictureCallback pictureCallback = new PictureCallback() {

		@Override
		public void onPictureTaken(byte[] data, Camera camera) {
			// �����ļ�,���浽�洢�豸
			String fileName = UUID.randomUUID().toString() + ".jpg";
			FileOutputStream fos = null;
			boolean success = true;
			try {
				fos = new FileOutputStream(CrimeIO.getAppSDPath() + fileName);
				fos.write(data);
			}
			catch (IOException e) {
				Log.e(TAG, "������Ƭ����");
				success = false;
			}
			finally {
				try {
					if (fos != null) {
						fos.close();
					}
				}
				catch (IOException e) {
					Log.e(TAG, "�ر�fileOutputStream����", e);
					success = false;
				}
			}
			if (success) {
				Log.i(TAG, "ͼƬ����ɹ���" + fileName);
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
	 * ������ͼ
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
		// ���ݲ���������ͼ
		View v = inflater.inflate(R.layout.fragment_crime_camera, container, false);
		// ��ȡ��ͼ�е����
		surfaceView = (SurfaceView) v.findViewById(R.id.crime_camera_surfaceView);
		takeButton = (Button) v.findViewById(R.id.takePictureButton);
		progressContainer = v.findViewById(R.id.processContainer);
		progressContainer.setVisibility(View.INVISIBLE);

		/*
		 * surface������Դ��
		 * surfaceView�ؼ���������Ļ��ʱ���ᴴ��surface��
		 * surfaceView����Ļ����ʧʱ��surface��������٣�
		 */

		// ��ȡholder��holder��camera��surface���� ��ͨ��Ŧ��
		SurfaceHolder holder = surfaceView.getHolder();
		holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

		// ��holder��Ӽ���surface���ڵ��¼�
		holder.addCallback(new SurfaceHolder.Callback() {

			// surface���ڣ�����surface�׶�
			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				if (camera != null) {
					// ���ֹͣԤ��
					camera.stopPreview();
				}
			}

			// surface���ڣ�����surface�׶�
			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				try {
					if (camera != null) {
						// ���������surfaceHolder
						camera.setPreviewDisplay(holder);
					}
				}
				catch (IOException e) {
					Log.e(TAG, "camera����holder����", e);
				}
			}

			// surface���ڣ�surface�����ı�
			@Override
			public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
				if (camera == null)
					return;
				// surface�Ѹı��С������camera��Ԥ����С��Ԥ�����������ʵʱȡ����
				// ��ȡ�����������
				Camera.Parameters cameraParams = camera.getParameters();
				// ��ȡ����ʵ�Ԥ����С
				Size s = getBestSupportedSize(cameraParams.getSupportedPreviewSizes(), width,
						height);
				// ������ʵ�Ԥ����С�ŵ��������������
				cameraParams.setPreviewSize(s.width, s.height);
				// ��ȡ����ʵ���Ƭ�ߴ����
				s = getBestSupportedSize(cameraParams.getSupportedPictureSizes(), width, height);
				// ������ʵ���Ƭ��С�ŵ��������������
				cameraParams.setPictureSize(s.width, s.height);
				// ����������������
				camera.setParameters(cameraParams);

				try {
					// �����ʼԤ��
					camera.startPreview();
				}
				catch (Exception e) {
					Log.e(TAG, "camera����Ԥ������", e);
					camera.release();
					camera = null;
				}
			}
		});
		// �������հ�ť�����¼������߼�
		takeButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (camera != null) {
					// ������������շ�����ע�⴫�����������������е������ص�����
					camera.takePicture(shutterCallback, null, pictureCallback);
				}
			}
		});

		// ������ͼ�����Activity
		return v;
	}

	/**
	 * �ָ�fragment
	 * 
	 * @see android.support.v4.app.Fragment#onResume()
	 */
	@Override
	public void onResume() {
		super.onResume();
		// �������Դ
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
			camera = Camera.open(0);
		}
		else {
			camera = Camera.open();
		}
	}

	/**
	 * ��ͣfragment
	 * 
	 * @see android.support.v4.app.Fragment#onPause()
	 */
	@Override
	public void onPause() {
		super.onPause();
		// �ͷ������Դ
		if (camera != null) {
			camera.release();
			camera = null;
		}
	}

	/**
	 * �㷨����ȡsizes�����б��У�����size����size�ĳ�*������size
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
