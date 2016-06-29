package com.liuyihui.yungeweather.scheduletask.task;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.liuyihui.yungeweather.service.WeatherInvoker;

public class GetWeatherLiveDataTask implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		//参数定义
		String url="http://open.weather.com.cn/data/";
		String areaId="101190101";
		String type="observe";
		String date="201606201000";
		String appId="382627ecb7964497";
		String privateKey="yunge_webapi_data";
		
		String now = new SimpleDateFormat("yyyyMMdd:HH时mm分ss秒").format(new Date());
		String invokeResult;
		try {
			invokeResult = WeatherInvoker.invokeWeatherApiBaseJavaNet(url,areaId,type,date,appId,privateKey);
			File f = new File("D:\\cloudsong\\云歌时代_comment\\weatherLiveData3.txt");
			BufferedWriter bw = new BufferedWriter(new FileWriter(f,true));
//			bw.append(invokeResult);
			bw.write(now+":"+invokeResult+"\n");
			bw.close();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
