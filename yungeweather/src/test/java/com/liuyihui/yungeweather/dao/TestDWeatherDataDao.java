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
		
//		dWeatherDataDao.insertWeatherIndexData(wCreate);
	}	
	@Test
	public void testGetIndexIdByIndexName(){
		String indexName = "晾晒指数";
		long result = dWeatherDataDao.getIndexIdByIndexName(indexName);
		System.out.println("testGetIndexIdByIndexName:"+result);
	}
	@Test
	public void testGetLevelIdByIndexIdAndLevelName(){
		String indexLevel="舒适";
		Long indexId=2L;
		try{
			Integer result = dWeatherDataDao.getLevelId(indexId, indexLevel);
			System.out.println("testGetLevelIdByIndexIdAndLevelName:"+result);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
