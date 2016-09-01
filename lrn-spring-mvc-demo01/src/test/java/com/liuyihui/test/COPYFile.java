package com.liuyihui.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.liuyihui.webapp.util.CMD;

public class COPYFile {
	public static void main(String[] args) throws InterruptedException {
		
		String cmdLine = "copy /y c:\\file.txt c:\\copy\\m.txt";
		CMD.exec(cmdLine);
	}
	
}
