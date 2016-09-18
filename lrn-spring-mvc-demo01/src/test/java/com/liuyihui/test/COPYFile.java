package com.liuyihui.test;


import com.liuyihui.webapp.util.CMD;

public class COPYFile {
	public static void main(String[] args) throws InterruptedException {
		
		String cmdLine = "copy /y c:\\file.txt c:\\copy\\m.txt";
		CMD.exec(cmdLine);
	}
	
}
