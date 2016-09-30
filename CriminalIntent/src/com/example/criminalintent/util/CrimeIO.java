package com.example.criminalintent.util;

import java.io.File;
import java.io.IOException;
import android.os.Environment;

/**
 * 文件处理工具类
 * 
 * @author liuyh 2016年9月30日
 */
public class CrimeIO {

	/**
	 * 获取sd卡上的本应用目录,如不存在本应用文件夹则创建.
	 * 
	 * @return /storage/emulated/0/crime/
	 * @throws IOException
	 */
	public static String getAppSDPath() throws IOException {
		String SDPath = null;
		// Environment.getExternalStorageDirectory().getAbsolutePath()：/storage/emulated/0
		// 判断外部存储可用
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			// 获取sd卡根目录
			String SDRootPath = Environment.getExternalStorageDirectory().getAbsolutePath();
			// 获取sd卡下的应用目录/crime，不存在则创建
			File appDir = new File(SDRootPath + "/crime");
			if (!appDir.exists() || !appDir.isDirectory()) {
				appDir.mkdir();
			}
			SDPath = appDir.getAbsolutePath() + "/";
		}
		if (SDPath == null) {
			throw new IOException("获取sd卡目录为空");
		}
		return SDPath;
	}
}
