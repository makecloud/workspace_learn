package com.liuyihui.yungeweather.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.HttpException;
import org.junit.Test;

import com.liuyihui.yungeweather.service.WeatherInvoker;
import com.liuyihui.yungeweather.util.HMACSHA1;

public class TestWeatherConsumer {
	private WeatherInvoker weatherConsumer=new WeatherInvoker();
	
	@Test
	public void useProp() throws FileNotFoundException, IOException{
		
		Properties prop=new Properties();
		prop.load(this.getClass().getClassLoader().getResourceAsStream("weather.properties"));
		System.out.println(prop.toString());
	}
	@Test
	public void testInvokeWeatherApiBaseJavaNet() throws Exception{
		String url="http://open.weather.com.cn/data/";
		String areaId="101190101";
		String type="observe";
		String date="201606201000";
		String appId="382627ecb7964497";
		String privateKey="yunge_webapi_data";
		String invokeResult = weatherConsumer.invokeWeatherApiBaseJavaNet(url,areaId,type,date,appId,privateKey);
		System.out.println("testInvokeWeatherApiBaseJavaNet:"+invokeResult);
		
	}
	
	/**
	 * 使用 commons-httpclient 
	 * 
	 * @throws HttpException
	 * @throws IOException
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 */
	@Test
	public void TestInvokeWeatherApiBaseHttpClient() throws HttpException, IOException, InvalidKeyException, NoSuchAlgorithmException{
		String url="http://open.weather.com.cn/data/";
		String areaId="101190101";
		String type="index";
		String date="201606221800";
		String appId="382627ecb7964497";
		String privateKey="yunge_webapi_data";
		String invokeResult = weatherConsumer.invokeWeatherApiBaseHttpClient(url,areaId,type,date,appId,privateKey);
		System.out.println("invokeWeatherApiBaseHttpClient:"+invokeResult);
		
	}
	@Test
	public void testBase64() throws InvalidKeyException, NoSuchAlgorithmException{
		String publicKey="http://open.weather.com.cn/data/?areaid=101190101&type=observe&date=201606201000&appid=382627ecb7964497";
		String privateKey="yunge_webapi_data";
		String base64Result = Base64.encodeBase64String(HMACSHA1.encrypt(publicKey.getBytes(), privateKey.getBytes()));
		System.out.println("base64Result:"+base64Result);
		String urlsuffix = URLEncoder.encode(base64Result);
		System.out.println("urlsuffix:"+urlsuffix);
	}
	@Test
	public void testWriteIntoFile(){
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
					File f = new File("D:\\cloudsong\\云歌时代_comment\\weatherLiveData.txt");
					BufferedWriter bw = new BufferedWriter(new FileWriter(f,true));
//					bw.append(invokeResult);
					bw.write(now+":"+invokeResult+"\n");
					bw.close();
					
				}
				catch (Exception e) {
					e.printStackTrace();
				}
	}
}
