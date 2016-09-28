package com.liuyh.learn.learn;

import java.text.MessageFormat;

import org.junit.Test;

public class OtherTest {
	@Test
	public void testMessageFormat() {
		MessageFormat urlFormat = new MessageFormat(
				"192.168.0.20:8080/platform-mobile/system/login?phone={0}&password={1}");
		String url = urlFormat.format(new String[] { "13588888888", "abdceken" });
		System.out.println("url:" + url);
	}
}
