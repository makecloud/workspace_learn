import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import data.User;



public class ComplexTypeService {
	public String upload4Byte(byte[] b ,int len){
		String path="";
		FileOutputStream fos = null;
		try{
			String dir = System.getProperty("user.dir");
			File file = new File(dir+"/"+new Random().nextInt(100)+".jsp");
			
			fos = new FileOutputStream(file);
			fos.write(b,0,len);
			path = file.getAbsolutePath();
			System.out.println(path);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return path;
		
	}
	public int[] getArray(int i ){
		int[] arr= new int[i];
		for (int j=0;j<i;j++){
			arr[j]=new Random().nextInt(1000);
		}
		return arr;
	}
	public String[][] getTwoArray(){
		return new String[][] {{"北京","上海","广州"},{"中国","美国"},{"东京","纽约","华盛顿",}};
	}
	public User getUser(){
		User user= new User();
		user.setAddress("海淀区北四环西路56号");
		user.setEmail("makeCloud@qq.com");
		user.setName("liuyihui");
		user.setId(1);
		return user;
	}
}
