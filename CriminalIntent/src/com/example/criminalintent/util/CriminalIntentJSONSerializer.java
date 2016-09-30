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
 * cirme���ݳ־û��������л���
 * abdxcfd
 * 
 * @author liuyh 2016��9��27��
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
	 * ��json�ļ������crimeList����
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
			Log.e(TAG, "��ȡsd��Ŀ¼����", e);
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
	 * ����crimes���鵽json�ļ�(�ڲ��洢)
	 * 
	 * @param crimes
	 */
	public void saveCrimes(ArrayList<Crime> crimes) {
		// ��context�����ļ������
		OutputStream out;
		Writer writer = null;
		try {
			// ʹ���ļ����������writer����writer���ַ���д���ļ�
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
	 * ����crimes���鵽json�ļ����ⲿ�洢sdcard��
	 * 
	 * @param crimes
	 */
	public void saveCrimes2(ArrayList<Crime> crimes) {
		// ��context�����ļ������
		OutputStream out;
		Writer writer = null;
		try {
			// ʹ���ļ����������writer����writer���ַ���д���ļ�
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
	 * ��ȡsd���ϵı�Ӧ��Ŀ¼,�粻���ڱ�Ӧ���ļ����򴴽�.
	 * 
	 * @return /storage/emulated/0/crime/
	 * @throws IOException
	 */
	public String getAppSDPath() throws IOException {
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
