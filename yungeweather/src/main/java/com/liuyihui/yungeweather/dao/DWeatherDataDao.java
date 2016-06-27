package com.liuyihui.yungeweather.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("dWeatherDataDao")
public interface DWeatherDataDao {
	/**
	 * 插入天气指数数据
	 * @param wCreate
	 */
	public Integer insertWeatherIndexData(@Param("wCreate") WeatherCreate wCreate);
	/**
	 * 根据指数中文名，获取指数id
	 * @param indexName
	 * @return
	 */
	public Long getIndexIdByIndexName(@Param("indexName") String indexName);
	/**
	 * 根据指数id，指数级别中文，获取指数级别id
	 * @param indexId
	 * @param indexLevel
	 * @return
	 */
	public Integer getLevelId(@Param("indexId") Long indexId,@Param("indexLevel") String indexLevel);
}
