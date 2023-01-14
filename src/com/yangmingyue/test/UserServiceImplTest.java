package com.yangmingyue.test;

import com.yangmingyue.Dao.UserDao;
import com.yangmingyue.Dao.impl.UserDaOImpl;
import com.yangmingyue.pojo.User;
import com.yangmingyue.service.UserService;
import com.yangmingyue.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceImplTest {

    //实例化一个被测试对象
    private UserService userService=new UserServiceImpl();
    @Test
    public void login() {
        System.out.println(userService.login(new User(null,"admin","123456",null)));
    }

    @Test
    public void register() {
        userService.register(new User(null,"陈海海","1997925","13096304205"));
    }

    @Test
    public void isExistUsername() {
        System.out.println(userService.isExistUsername("admin"));
    }
}