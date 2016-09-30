package com.example.criminalintent.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import android.content.Context;
import android.os.Environment;
import android.util.Log;
import com.alibaba.fastjson.JSON;
import com.example.criminalintent.entity.Crime;

/**
 * cirme数据持久化，反序列化类
 * abdxcfd
 * 
 * @author liuyh 2016年9月27日
 */
public class CriminalIntentJSONSerializer {

	private final String TAG = "JSONSerializer";

	private Context context;
	private String fileName;

	public CriminalIntentJSONSerializer(Context c, String s) {
		context = c;
		fileName = s;
	}

	/**
	 * 从json文件里加载crimeList数组
	 * 
	 * @return
	 */
	public ArrayList<Crime> loadCrimesFromJsonFile() {
		ArrayList<Crime> crimes = new ArrayList<Crime>();
		InputStreamReader isr = null;
		try {
			isr = new InputStreamReader(new FileInputStream(getAppSDPath() + fileName));
			String crimesJsonStr = ReadFileUtil.readInputstreamreader(isr);
			crimes = (ArrayList<Crime>) JSON.parseArray(crimesJsonStr, Crime.class);
		}
		catch (IOException e) {
			Log.e(TAG, "获取sd卡目录出错", e);
		}
		finally {
			try {
				if (isr != null) {
					isr.close();
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		return crimes;

	}

	/**
	 * 保存crimes数组到json文件(内部存储)
	 * 
	 * @param crimes
	 */
	public void saveCrimes(ArrayList<Crime> crimes) {
		// 用context创建文件输出流
		OutputStream out;
		Writer writer = null;
		try {
			// 使用文件输出流创建writer，用writer把字符串写入文件
			out = context.openFileOutput(fileName, Context.MODE_PRIVATE);
			writer = new OutputStreamWriter(out);
			writer.write(JSON.toJSONString(crimes));
			Log.i("JSONSerializer", Environment.getExternalStorageDirectory().getAbsolutePath());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (writer != null) {
				try {
					writer.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * 保存crimes数组到json文件（外部存储sdcard）
	 * 
	 * @param crimes
	 */
	public void saveCrimes2(ArrayList<Crime> crimes) {
		// 用context创建文件输出流
		OutputStream out;
		Writer writer = null;
		try {
			// 使用文件输出流创建writer，用writer把字符串写入文件
			out = new FileOutputStream(getAppSDPath() + fileName);
			writer = new OutputStreamWriter(out);
			writer.write(JSON.toJSONString(crimes));
		}
		catch (IOException e) {
			Log.e("JSONSerializer", e.getMessage(), e);
		}
		finally {
			if (writer != null) {
				try {
					writer.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * 获取sd卡上的本应用目录,如不存在本应用文件夹则创建.
	 * 
	 * @return /storage/emulated/0/crime/
	 * @throws IOException
	 */
	public String getAppSDPath() throws IOException {
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
