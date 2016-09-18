package com.liuyh.learn.learn;

import org.junit.Test;

import com.alibaba.fastjson.JSON;

public class TestFastjson {
	
	@Test
	public void test1(){
		//把实体转换到json字符串
		JSON.toJSONString(null);
	}
}
