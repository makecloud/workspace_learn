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
 * cirme数据持久化，反序列化类
 * abdxcfd
 * 
 * @author liuyh 2016年9月27日
 */
public class CriminalIntentJSONSerializer {

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
	 * 保存crimes数组到json文件
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
