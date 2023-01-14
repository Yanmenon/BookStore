package com.yangmingyue.Dao;

import com.yangmingyue.pojo.Order;

import java.util.List;

public interface OrderDao {
    //保存订单信息到数据库
    public int saveOrder(Order order);
    //查询全部订单
    public List<Order> queryOrders();
    //根据订单编号修改订单的信息
    public int changeOrderStatus(String orderId,Integer status);
    //根据用户编号查询订单信息
    public Order queryOrderByUserId(Integer userId);
}
