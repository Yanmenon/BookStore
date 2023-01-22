package com.yangmingyue.web;

import com.yangmingyue.Dao.UserDao;
import com.yangmingyue.Dao.impl.UserDaOImpl;
import com.yangmingyue.pojo.Cart;
import com.yangmingyue.pojo.User;
import com.yangmingyue.service.OrderService;
import com.yangmingyue.service.UserService;
import com.yangmingyue.service.impl.OrderServiceImpl;
import com.yangmingyue.service.impl.UserServiceImpl;
import com.yangmingyue.utils.webUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: 实现订单页面的跳转和联动
 * @Author: Yamenon
 * @Date: 2023-01-14 17:56
 */
public class OrderServlet extends BaseServlet{

    private OrderService orderService=new OrderServiceImpl();
    protected void createOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.首先通过session获取登录用户的用户id
        User user = (User)request.getSession().getAttribute("user");
        if(user==null){
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
            return;
        }
        //2.通过用户姓名查找用户对象
        UserDao userdao=new UserDaOImpl();
        User user1 = userdao.queryUserByUsername(user.getUsername());
        //2.通过session获取用户车的实例
        Cart cart =(Cart)request.getSession().getAttribute("cart");
        //3.调用OrderService对象的方法来保存数据
        String orderId = orderService.saveOrderAndOrderItem(cart, user1.getId());
        //4.把页面跳转到cart/checkout.jsp页面
        request.setAttribute("orderId",orderId);
        request.getRequestDispatcher("/pages/cart/checkout.jsp").forward(request,response);
    }
}
