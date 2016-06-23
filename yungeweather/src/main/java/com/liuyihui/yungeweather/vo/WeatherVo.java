package com.liuyihui.yungeweather.vo;

import java.util.List;

public class WeatherVo {
	
	private String i0;
	private List<WeatherIndexVo> i;
	
	public String getI0() {
		return i0;
	}
	public void setI0(String i0) {
		this.i0 = i0;
	}
	public List<WeatherIndexVo> getI() {
		return i;
	}
	public void setI(List<WeatherIndexVo> i) {
		this.i = i;
	}
	
	@Override
	public String toString() {
		return "{\"i0\":"+i0+",\"i\":"+i.toString()+"}";
	}
}
