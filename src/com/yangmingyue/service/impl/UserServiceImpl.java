package com.yangmingyue.service.impl;

import com.yangmingyue.Dao.UserDao;
import com.yangmingyue.Dao.impl.BaseDao;
import com.yangmingyue.Dao.impl.UserDaOImpl;
import com.yangmingyue.pojo.User;
import com.yangmingyue.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao=new UserDaOImpl();
    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public void register(User user) {
        userDao.saveUser(user);
    }

    @Override
    public Boolean isExistUsername(String username) {
        if(userDao.queryUserByUsername(username)==null){
            return false;
        }else{
            return true;
        }
    }
}
