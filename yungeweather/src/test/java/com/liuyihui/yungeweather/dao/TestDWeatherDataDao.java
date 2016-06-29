package com.liuyihui.yungeweather.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-config-test.xml"})
public class TestDWeatherDataDao {
	
	@Resource(name ="dWeatherDataDao")
	private DWeatherDataDao dWeatherDataDao;
		
	@Test
	public void testInsertWeatherIndexData(){
		DWeatherCreate wCreate = new DWeatherCreate();
		wCreate.setAreaId(10L);
		wCreate.setIndexId(203L);
		wCreate.setDate("20160622");
		wCreate.setHour(2);
		wCreate.setLevel(205);
		
		Integer result = dWeatherDataDao.insertWeatherIndexData(wCreate);
		System.out.println("testInsertWeatherIndexData:"+result);
	}	
	@Test
	public void testGetIndexIdByIndexName(){
		String indexName = "晾晒指数";
		long result = dWeatherDataDao.getIndexIdByIndexName(indexName);
		System.out.println("testGetIndexIdByIndexName:"+result);
	}
	@Test
	public void testGetLevelIdByIndexIdAndLevelName(){
		String indexLevel="较不宜";
		Long indexId=2L;
		try{
			Integer result = dWeatherDataDao.getLevelId(indexId, indexLevel);
			System.out.println("testGetLevelIdByIndexIdAndLevelName:"+result);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
