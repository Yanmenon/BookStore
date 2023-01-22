package com.yangmingyue.test;

import com.yangmingyue.Dao.OrderDao;
import com.yangmingyue.Dao.OrderItemDao;
import com.yangmingyue.Dao.impl.OrderDaoImpl;
import com.yangmingyue.Dao.impl.OrderItemDaoImpl;
import com.yangmingyue.pojo.Cart;
import com.yangmingyue.pojo.CartItem;
import com.yangmingyue.service.OrderService;
import com.yangmingyue.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderServiceImplTest {
    private OrderService orderService=new OrderServiceImpl();
    @Test
    public void saveOrderAndOrderItem() {
        Cart cart=new Cart();
        CartItem cartItem1 = new CartItem(1, "红高粱", 1, new BigDecimal(29.99), new BigDecimal(29.99));
        CartItem cartItem2 = new CartItem(1, "红高粱", 1, new BigDecimal(29.99), new BigDecimal(29.99));
        cart.addItem(cartItem1);
        cart.addItem(cartItem2);
        orderService.saveOrderAndOrderItem(cart,1);
    }
    /**
     *
     * */
    @Test
    public void queryOrders() {
    }

    @Test
    public void sendOrder() {
    }

    @Test
    public void showOrderDetails() {
    }

    @Test
    public void showMyOrders() {
    }

    @Test
    public void receiveOrder() {
    }
}