package com.yangmingyue.web;

import com.yangmingyue.pojo.Book;
import com.yangmingyue.pojo.Cart;
import com.yangmingyue.pojo.CartItem;
import com.yangmingyue.service.BookService;
import com.yangmingyue.service.impl.BookServiceImpl;
import com.yangmingyue.utils.webUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CartServlet extends BaseServlet{

    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取需要加入购物车的商品的id
        Integer id = webUtils.stringCastInt(req.getParameter("id"), 0);
        //根据商品id找到该商品对象
        BookService bookService=new BookServiceImpl();
        Book book = bookService.queryBookById(id);
        //此时获取服务器为cart的session,如果返回的值是空，则表明服务器现在没有一个session对象用来来存放这个cart
        Cart cart =(Cart) req.getSession().getAttribute("cart");
        //如果cart为空，则创建一个session对象
        if(cart==null){
            Cart cart1 = new Cart();
            cart1.addItem(new CartItem(book.getId(), book.getName(), 1,book.getPrice(),book.getPrice()));
            req.getSession().setAttribute("cart",cart1);
        }else{
            cart.addItem(new CartItem(book.getId(), book.getName(), 1,book.getPrice(),book.getPrice()));
        }
        //重定向回原来的页面，这个时候可以保证页面地址没有改变
        resp.sendRedirect(req.getHeader("Referer"));

        //将最后加入的商品的姓名加入到session域中
        req.getSession().setAttribute("lastname",book.getName());
    }

    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取需要删除的商品的id
        Integer id = webUtils.stringCastInt(req.getParameter("id"), 0);
        //2.调用cart对象的删除方法
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        cart.deleteItem(id);
        //3.重新返回到购物车页面
        resp.sendRedirect(req.getHeader("Referer"));
    }

    protected void clearCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.调用cart对象的删除购物车
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        cart.clearCart();
        resp.sendRedirect(req.getHeader("Referer"));
    }

    protected void updateItemById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.调用cart对象的删除购物车
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //2.获取itemid和count参数
        Integer id = webUtils.stringCastInt(req.getParameter("id"), 0);
        Integer count = webUtils.stringCastInt(req.getParameter("count"), 0);
        if(cart!=null){
            //3.调用cart类的方法
            cart.updataItemCount(id,count);
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
}
