package com.yangmingyue.test;

import com.yangmingyue.Dao.UserDao;
import com.yangmingyue.Dao.impl.UserDaOImpl;
import com.yangmingyue.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoTest {

    @Test
    public void queryUserByUsername() {
        UserDao userDao=new UserDaOImpl();
        if(userDao.queryUserByUsername("admin")==null){
            System.out.println("用户名可用");
        }else{
            System.out.println("用户名已存在");
        }
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        UserDao userDao=new UserDaOImpl();
        if(userDao.queryUserByUsernameAndPassword("admin","12345")==null){
            System.out.println("用户名或者密码错误");
        }else{
            System.out.println("登录成功");
        }
    }

    @Test
    public void saveUser() {
        UserDao userDao=new UserDaOImpl();
        User user = new User(null,"小阳", "123456", "3100995848@qq.com");
        if (userDao.saveUser(user)==-1){
            System.out.println("保存失败");
        }else{
            System.out.println("保存成功");
        }
    }
}