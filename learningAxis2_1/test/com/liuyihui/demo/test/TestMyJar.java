package com.liuyihui.demo.test;

import lyh.test.FileOperUtil;

import org.junit.Test;

public class TestMyJar {
	@Test
	public void testMyJar(){
		String[] s={"abc","cba","NBA"};
		FileOperUtil.printArray(s);
	}
}	
