package com.example.criminalintent.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
	/**
	 * ���ƶ���ʽ��ת��date����String�ַ���
	 * 
	 * @param date ���ڶ���
	 * @param format ���ڸ�ʽ
	 * @return
	 */
	public static String date2String(Date date, String format) {
		return new SimpleDateFormat(format).format(date);
	}
}
