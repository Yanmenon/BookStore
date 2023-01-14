package com.yangmingyue.Dao.impl;

import com.yangmingyue.Dao.OrderDao;
import com.yangmingyue.pojo.Order;

import java.util.List;

public class OrderDaoImpl extends BaseDao implements OrderDao {
    //保存订单信息到数据库
    @Override
    public int saveOrder(Order order) {
        String sql="insert into t_order(order_id,create_time,price,status,user_id) values(?,?,?,?,?)";
        return update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }

    @Override
    public List<Order> queryOrders() {
        return null;
    }

    @Override
    public int changeOrderStatus(String orderId, Integer status) {
        return 0;
    }

    @Override
    public Order queryOrderByUserId(Integer userId) {
        return null;
    }
}
