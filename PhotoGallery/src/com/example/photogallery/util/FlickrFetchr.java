package com.example.photogallery.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FlickrFetchr {

	/**
	 * 获取url返回的字节
	 * 
	 * @param urlSpec
	 * @return
	 * @throws IOException
	 */
	public byte[] getUrlBytes(String urlSpec) throws IOException {
		URL url = new URL(urlSpec);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			InputStream in = connection.getInputStream();

			if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
				return null;
			}

			int byteRead = 0;
			byte[] buffer = new byte[1024];
			while ((byteRead = in.read()) > 0) {
				out.write(buffer);
			}
			out.close();
			return out.toByteArray();
		}
		finally {
			connection.disconnect();
		}
	}

	/**
	 * @param urlSpec
	 * @return
	 * @throws IOException
	 */
	public String getString(String urlSpec) throws IOException {
		return new String(getUrlBytes(urlSpec));
	}
}
