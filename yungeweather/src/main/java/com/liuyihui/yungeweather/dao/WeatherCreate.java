package com.liuyihui.yungeweather.dao;

public class WeatherCreate {
	private Long areaId;
	private Long indexId;
	private String date;
	private Integer hour;
	private Integer level;
	
	
	public Long getAreaId() {
		return areaId;
	}
	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}
	public Long getIndexId() {
		return indexId;
	}
	public void setIndexId(Long indexId) {
		this.indexId = indexId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Integer getHour() {
		return hour;
	}
	public void setHour(Integer hour) {
		this.hour = hour;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	
}
