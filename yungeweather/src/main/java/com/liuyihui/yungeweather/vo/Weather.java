package com.liuyihui.yungeweather.vo;

import java.util.List;

public class Weather {
	private String areaid;
	private List<WeatherIndex> WeatherIndexs = null;
	private String publishTime;
	
	
	public String getAreaid() {
		return areaid;
	}
	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}
	public List<WeatherIndex> getWeatherIndexs() {
		return WeatherIndexs;
	}
	public void setWeatherIndexs(List<WeatherIndex> weatherIndexs) {
		WeatherIndexs = weatherIndexs;
	}
	public String getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}
	
	
	
}
