package com.example.criminalintent.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
	/**
	 * 以制定格式，转换date对象到String字符串
	 * 
	 * @param date 日期对象
	 * @param format 日期格式
	 * @return
	 */
	public static String date2String(Date date, String format) {
		return new SimpleDateFormat(format).format(date);
	}
}
