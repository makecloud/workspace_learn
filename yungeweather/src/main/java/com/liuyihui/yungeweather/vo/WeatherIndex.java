package com.liuyihui.yungeweather.vo;

import java.io.Serializable;

public class WeatherIndex implements Serializable{
	//fs
	private String index;
	//防晒指数
	private String indexName;
	//强、极强
	private String indexLevel;
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getIndexName() {
		return indexName;
	}
	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}
	public String getIndexLevel() {
		return indexLevel;
	}
	public void setIndexLevel(String indexLevel) {
		this.indexLevel = indexLevel;
	}
	
}
