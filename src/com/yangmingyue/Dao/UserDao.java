package com.yangmingyue.Dao;

import com.yangmingyue.pojo.User;

/**
 * 该类是一个接口类，所以定义的方法都只有方法名，没有方法体
 * 该类中定义的方法取决于登录注册页面对数据库的操作
 * */
public interface UserDao {

    //根据用户姓名查询用户，返回的是User类型的数据
    //如果返回的null类型这说明没有这个用户
//    public abstract Boolean  findUserByUsername(String name);
    public User queryUserByUsername(String username);

    //根据用户姓名和密码返回信息
    //如果返回的null类型,说明用户名或者是密码错误
//    public abstract Boolean findUserByUsernameAndPassword(String name,String password);
    public User queryUserByUsernameAndPassword(String username,String password);

    //将注册信息存入数据库
//    public abstract Boolean insertRegisterInfo(String name,String password,String email);
    public abstract int saveUser(User user);


}
