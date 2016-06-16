package ch17_ssh_combine.springexample;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class FirstSpring {
	public static void main(String[] args) {
		//����ApplicationContext���󣬲�װ��applicationContext.xml�ļ�
		ApplicationContext applicationContext=new FileSystemXmlApplicationContext("src//applicationContext.xml");
		HelloWorld helloWorld=(HelloWorld)applicationContext.getBean("helloworld");
		System.out.println(helloWorld.getGreeting());
		
	}
}
