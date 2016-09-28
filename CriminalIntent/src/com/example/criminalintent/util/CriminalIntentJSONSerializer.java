package com.example.criminalintent.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import android.content.Context;
import com.alibaba.fastjson.JSON;
import com.example.criminalintent.entity.Crime;

/**
 * cirme���ݳ־û��������л���
 * abdxcfd
 * 
 * @author liuyh 2016��9��27��
 */
public class CriminalIntentJSONSerializer {

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
			isr = new InputStreamReader(context.openFileInput(fileName));
			String crimesJsonStr = ReadIO.readInputstreamreader(isr);
			crimes = (ArrayList<Crime>) JSON.parseArray(crimesJsonStr, Crime.class);
		}
		catch (IOException e) {
			e.printStackTrace();
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
	 * ����crimes���鵽json�ļ�
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
}
