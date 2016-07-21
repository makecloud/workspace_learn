
【mybatis入门第二步——使用接口interface】

与原始的区别总结：
原始的是获取sqlSession，调用session的selectOne方法，selectOne方法又调用mapper实例，在mapper实例中查询结果user对象；
使用接口的是 session根据user.xml生成实现接口的mapper实例，调用mapper实例的方法，得到查询结果user对象；