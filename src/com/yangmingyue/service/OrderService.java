package com.yangmingyue.service;

import com.yangmingyue.pojo.Cart;
import com.yangmingyue.pojo.Order;

import java.util.List;

public interface OrderService {
    //生成订单以及订单详情的方法
    public String saveOrderAndOrderItem(Cart cart,Integer userId);

    //查看所有订单
    public List<Order> queryOrders();

    //发货
    public void sendOrder(Integer orderId);

    //查看订单详情
    public void showOrderDetails(Integer orderId);

    //查看我的订单
    public List<Order> showMyOrders(Integer userId);

    //确认收货
    public void receiveOrder(Integer orderId);


}
