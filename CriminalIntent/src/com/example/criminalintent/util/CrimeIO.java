package com.example.criminalintent.util;

import java.io.File;
import java.io.IOException;
import android.os.Environment;

/**
 * �ļ���������
 * 
 * @author liuyh 2016��9��30��
 */
public class CrimeIO {

	/**
	 * ��ȡsd���ϵı�Ӧ��Ŀ¼,�粻���ڱ�Ӧ���ļ����򴴽�.
	 * 
	 * @return /storage/emulated/0/crime/
	 * @throws IOException
	 */
	public static String getAppSDPath() throws IOException {
		String SDPath = null;
		// Environment.getExternalStorageDirectory().getAbsolutePath()��/storage/emulated/0
		// �ж��ⲿ�洢����
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			// ��ȡsd����Ŀ¼
			String SDRootPath = Environment.getExternalStorageDirectory().getAbsolutePath();
			// ��ȡsd���µ�Ӧ��Ŀ¼/crime���������򴴽�
			File appDir = new File(SDRootPath + "/crime");
			if (!appDir.exists() || !appDir.isDirectory()) {
				appDir.mkdir();
			}
			SDPath = appDir.getAbsolutePath() + "/";
		}
		if (SDPath == null) {
			throw new IOException("��ȡsd��Ŀ¼Ϊ��");
		}
		return SDPath;
	}
}
