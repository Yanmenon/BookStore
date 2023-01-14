package com.yangmingyue.Dao;

import com.yangmingyue.pojo.OrderItem;

import java.util.List;

public interface OrderItemDao {
    //将购物车里的物品重新保存到新的数据库表中
    public int savaOrderItem(OrderItem orderItem);
    //根据订单号查询订单明细
    public List<OrderItem> queryOrderItemByOrderId(String orderId);

}
