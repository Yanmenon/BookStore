package com.yangmingyue.web;

import com.yangmingyue.pojo.User;
import com.yangmingyue.service.UserService;
import com.yangmingyue.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    private UserService userService=new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        1、获取请求的参数
        String username=request.getParameter("username");
        String password=request.getParameter("password");
//        2、调用XxxService.xxx0处理业务
        User loginUser = userService.login(new User(null, username, password, null));
//        3、根扬login方法返回值判断登录是否成功
//        成功
        if(loginUser==null){
            //        失败
//                跳回登录页面
            request.setAttribute("msg","用户名或密码错误");
            request.setAttribute("username",username);
            System.out.println("登录失败");
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
        }else{
            //  跳到成功页面
            request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request,response);
        }

    }
}
