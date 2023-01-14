package com.yangmingyue.service;

import com.yangmingyue.pojo.User;

/**
 * 此处只是用来定义接口，定义页面实现的业务逻辑
 * 关于用户就有登录逻辑，注册逻辑，用户名已存在
 * */
public interface UserService {

    //验证登录函数
    User login(User user);

    //验证注册函数
    void register(User user);

    //验证用户名是否已经存在
    Boolean isExistUsername(String username);

}
