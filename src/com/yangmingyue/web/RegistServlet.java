package com.yangmingyue.web;

import com.yangmingyue.pojo.User;
import com.yangmingyue.service.UserService;
import com.yangmingyue.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistServlet extends HttpServlet{

    private UserService userService=new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        String email=req.getParameter("email");
        String code=req.getParameter("code");

        //1.查看验证码是否正确
        if("abcde".equalsIgnoreCase(code)){
            //验证名字是否已存在
            if(userService.isExistUsername(username)==true){
                //名字已存在则返回注册页面
                req.setAttribute("msg","用户名已存在");
                req.setAttribute("username",username);
                req.setAttribute("email",email);
                System.out.println("用户姓名重复");
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
            }else{
                //名字不存在则注册成功，跳转到注册成功页面
                userService.register(new User(null,username,password,email));
                System.out.println("注册成功");
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
            }
        }else{
            req.setAttribute("msg","验证码错误");
            req.setAttribute("username",username);
            req.setAttribute("email",email);
            System.out.println("验证码错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }

    }
}