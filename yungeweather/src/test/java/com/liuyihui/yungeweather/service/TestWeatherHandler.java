package com.liuyihui.yungeweather.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.liuyihui.yungeweather.service.WeatherHandler;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-config-test.xml"})
public class TestWeatherHandler {
	
	@Resource(name = "weatherHandler")
	private WeatherHandler weatherHandler;
	
	@Test
	public void testGetTodayWeatherIndex() throws Exception{
		String areaid = "101190101";
		weatherHandler.gatherTodayWeatherIndex(areaid);
		
	}
}
