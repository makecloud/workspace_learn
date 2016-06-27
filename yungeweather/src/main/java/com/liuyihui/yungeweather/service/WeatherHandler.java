package com.liuyihui.yungeweather.service;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.stereotype.Service;

import com.liuyihui.yungeweather.dao.DWeatherDataDao;
import com.liuyihui.yungeweather.dao.WeatherCreate;
import com.liuyihui.yungeweather.vo.Weather;
import com.liuyihui.yungeweather.vo.WeatherIndex;
import com.liuyihui.yungeweather.vo.WeatherIndexVo;
import com.liuyihui.yungeweather.vo.WeatherVo;

/**
 * 天气处理器
 * 
 * @author liuyh
 *
 */
@Service("weatherHandler")
public class WeatherHandler {
	@Resource(name = "weatherInvoker")
	private WeatherInvoker weatherInvoker;
	@Resource(name = "dWeatherDataDao")
	private DWeatherDataDao dWeatherDataDao;
	
	/**
	 * 收集今天的天气指数
	 * 
	 * @param areaid 地区id
	 * @throws Exception
	 */
	public void gatherTodayWeatherIndex(String areaid) throws Exception{
		//初始化
		Weather weather = new Weather();
		weather.setWeatherIndexs(new ArrayList<WeatherIndex>());
		WeatherVo weatherVo =  new WeatherVo();
		weatherVo.setI(new ArrayList<WeatherIndexVo>());
		
		//设置调用参数
		String url="http://open.weather.com.cn/data/";
		String type="index";
		String date="201606201000";
		String appId="382627ecb7964497";
		String privateKey="yunge_webapi_data";
		
		//调用气象局api
		String invokeResult = weatherInvoker.invokeWeatherApiBaseJavaNet(url, areaid, type, "201606220000", appId, privateKey);
		
		//数据转换weatherVo -> weather
		ObjectMapper om = new ObjectMapper();
//		weatherVo = om.readValue(invokeResult, new TypeReference<WeatherVo>() {});
		weatherVo=om.readValue(invokeResult, WeatherVo.class);
		
		for(WeatherIndexVo weatherIndexVo : weatherVo.getI()){
			//初始化
			WeatherIndex weatherIndex = new WeatherIndex();
			
			//设置属性
			weatherIndex.setIndex(weatherIndexVo.getI1());
			weatherIndex.setIndexName(weatherIndexVo.getI2().equals("紫外线强度指数")?"防晒指数":weatherIndexVo.getI2());
			weatherIndex.setIndexLevel(weatherIndexVo.getI4());
			
			//封装
			weather.getWeatherIndexs().add(weatherIndex);
		}
		weather.setAreaid(areaid);
		weather.setPublishTime(weatherVo.getI0());
		
		//数据入库
		for(WeatherIndex wIndex : weather.getWeatherIndexs()){
			System.out.println(wIndex);//to be deleted
			WeatherCreate weatherCreate = new WeatherCreate();
			
			Long indexId = dWeatherDataDao.getIndexIdByIndexName(wIndex.getIndexName());
			Integer levelId = dWeatherDataDao.getLevelId( indexId,wIndex.getIndexLevel());
			//校验
			if(indexId == null ){
				throw new Exception("指数中文名："+wIndex.getIndexName()+",未查到指数id");
			}
			if( levelId == null ){
				throw new Exception("指数中文名："+wIndex.getIndexName()+",指数id："+indexId+",指数级别中文名："+wIndex.getIndexLevel()+",未查到指数级别id");
			}
			weatherCreate.setAreaId(Long.parseLong(weather.getAreaid()));
			weatherCreate.setIndexId(indexId);
			weatherCreate.setLevel(levelId);
			weatherCreate.setDate(weather.getPublishTime().substring(0,8));
			weatherCreate.setHour(Integer.parseInt(weather.getPublishTime().substring(8,10)));
			
			dWeatherDataDao.insertWeatherIndexData(weatherCreate);
		}
		
	}
}	
