package com.yangmingyue.web;

import com.alibaba.druid.sql.ast.statement.SQLForeignKeyImpl;
import com.google.gson.Gson;
import com.yangmingyue.pojo.User;
import com.yangmingyue.service.UserService;
import com.yangmingyue.service.impl.UserServiceImpl;
import com.yangmingyue.utils.webUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();
    /***
     * 下面这段代码被baseservlet提取走
     * */
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String action = request.getParameter("action");
//        /**
//         * 下面是利用反射来调用方法
//         * */
//        Method method = null;
//        try {
//            method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
//            method.invoke(this, request, response);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //1.注销session
        request.getSession().invalidate();
        System.out.println("已经进入到方法内部");
        //2.将页面跳转到首页
        response.sendRedirect(request.getContextPath());
    }
    protected void ajaxExistUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //1.获取请求的参数username
        String username=request.getParameter("username");
        //2.调用userService.existUsername();
        boolean existUsername=userService.isExistUsername(username);
        //3.把返回的结果封装成为map对象
        Map<String, Object> resultMap=new HashMap<>();
        resultMap.put("existUsername",existUsername);
        //利用gson对象把一个map对象转化为一个json对象
        Gson gson=new Gson();
        String s = gson.toJson(resultMap);
        //把这个json对象返回到客户端
        response.getWriter().write(s);

    }

    /**
     * 处理登录的功能
     * */
    protected  void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //        1、获取请求的参数
        String username=request.getParameter("username");
        String password=request.getParameter("password");

//        User user = webUtils.copyParamToBeam(new User(),request.getParameterMap());
//        System.out.println(user);
//
        //把上述的代码转化为泛型
        User user=webUtils.copyParamToBeam(new User(),request.getParameterMap());


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
            //把登陆成功的用户信息存储到Session域中
            request.getSession().setAttribute("user",user);
            //  跳到成功页面
            request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request,response);
        }

    }
    /**
     * 处理注册的功能
     * */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        String email=req.getParameter("email");
        String code=req.getParameter("code");

        User user = webUtils.copyParamToBeam(new User(), req.getParameterMap());
        System.out.println(user);

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
