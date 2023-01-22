package com.yangmingyue.service.impl;

import com.sun.org.apache.xpath.internal.operations.Or;
import com.yangmingyue.Dao.OrderDao;
import com.yangmingyue.Dao.OrderItemDao;
import com.yangmingyue.Dao.impl.OrderDaoImpl;
import com.yangmingyue.Dao.impl.OrderItemDaoImpl;
import com.yangmingyue.pojo.*;
import com.yangmingyue.service.OrderService;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private OrderItemDao orderItemDao=new OrderItemDaoImpl();
    private OrderDao orderDao=new OrderDaoImpl();
    @Override
    public String saveOrderAndOrderItem(Cart cart, Integer userId) {
        String orderId=System.currentTimeMillis()+userId.toString();
        //1.保存订单信息到order表中
        orderDao.saveOrder(new Order(orderId,new Date(),cart.getTotalItemsPrice(),0,userId));
        //2.保存购物车的信息到orderItem表中
         for(Map.Entry<Integer,CartItem> item:cart.getItems().entrySet()){
             orderItemDao.savaOrderItem(new OrderItem(null,item.getValue().getName(),item.getValue().getCount(),item.getValue().getPrice(),item.getValue().getTotalPrice(),orderId));
         }
         return orderId;
    }

    @Override
    public List<Order> queryOrders() {
        //查看所有的订单
        List<Order> orders = orderDao.queryOrders();
        return  orders;
    }

    @Override
    public void sendOrder(Integer orderId) {

    }

    @Override
    public void showOrderDetails(Integer orderId) {

    }

    @Override
    public List<Order> showMyOrders(Integer userId) {
        return null;
    }

    @Override
    public void receiveOrder(Integer orderId) {

    }
}
