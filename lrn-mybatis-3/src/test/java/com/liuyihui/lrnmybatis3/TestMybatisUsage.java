package com.liuyihui.lrnmybatis3;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.*;

import com.liuyihui.lrnmybatis3.api.IUser;
import com.liuyihui.lrnmybatis3.entity.User;




public class TestMybatisUsage {
	
	/** sqlsession工厂 */
	private  SqlSessionFactory sqlSessionFactory;
	
    /** reader */
    private  Reader reader; 
    
    /**
     *  初始化
     */
    @Before
    public void before(){
    	try{
            reader    = Resources.getResourceAsReader("mybatis-configuration.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    
    /**
     * 使用mybatis mapper查询
     */
    @Test
    public void testQuery() {
    	//获取sqlsession实例
    	SqlSession session = sqlSessionFactory.openSession();
        try {
        	//从sqlsesion根据定义的接口，获取mapper实例
            IUser iUserMapper=session.getMapper(IUser.class);
            //调用mapper的方法，查询数据库
            List<User> users = iUserMapper.queryUser2("w%");
            
            //使用接口跟映射xml文件，就如上两行这么简单。
            
            //打印结果
            for(User user:users){
            	System.out.println(user.getUserName());
            }
        } finally {
            session.close();
        }
    }
    
    /**
     *  测试mybatis mapper插入
     */
    @Test
    public void testAddUser(){
    	//获取sqlsession
    	SqlSession sqlSession = sqlSessionFactory.openSession();
    	try{
    		//创建user
    		User user = new User();
    		user.setUserName("liuyihui");
    		user.setUserAge("22");
    		user.setUserAddress("上地");
    		
    		//从session获取mapper实例
    		IUser userMapper = sqlSession.getMapper(IUser.class);
    		
    		//使用mapper实例的插入
    		userMapper.addUser(user);
    		
    		//提交事务，保证插入
    		sqlSession.commit();
    	}finally{
    		sqlSession.close();
    	}
    }
}
