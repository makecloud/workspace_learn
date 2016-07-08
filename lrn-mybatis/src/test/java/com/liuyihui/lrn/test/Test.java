package com.liuyihui.lrn.test;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.liuyihui.lrn.entity.User;

public class Test {
	
	/** sqlsession工厂 */
	private static SqlSessionFactory sqlSessionFactory;
	
    /** reader */
    private static Reader reader; 
    
    //初始化属性
    static{
        try{
            reader    = Resources.getResourceAsReader("mybatis-configuration.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 获取session
     * @return 
     */
    public static SqlSessionFactory getSession(){
        return sqlSessionFactory;
    }
    
    /**
     * 主方法
     * @param args 
     */
    public static void main(String[] args) {
    	//开启session
        SqlSession session = sqlSessionFactory.openSession();
        try {
        	//执行查询
	        User user = (User) session.selectOne("com.liuyihui.lrn.entity.UserMapper.selectUserByID", 1);
	        
	        //打印结果
	        System.out.println(user.getUserAddress());
	        System.out.println(user.getUserName());
        } finally {
        	session.close();
        }
    }
}
