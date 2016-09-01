package com.liuyh.learn.learn;

import java.lang.invoke.ConstantCallSite;

import org.junit.Test;

import com.liuyihui.Enum.COsType;

public class TestEnumUsage {
	
	enum Constant {
		constant1("枚举成员A"),
		constant3(),
		constant2("枚举成员B");
		
		private String des = null;
		
		private Constant(){
			
		}
		
		private Constant(String a){
			this.des=a;
		}
		private Constant(int a){
			
		}
		
		
	}
	
	@Test
	public void test1(){
		Constant a = Constant.constant1;
		
		System.out.println(Constant.valueOf("constant1"));
	}
}
