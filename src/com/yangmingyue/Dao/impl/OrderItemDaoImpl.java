package com.yangmingyue.Dao.impl;

import com.yangmingyue.Dao.OrderItemDao;
import com.yangmingyue.pojo.OrderItem;

import java.util.List;

public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int savaOrderItem(OrderItem orderItem) {
        String sql="insert into t_orderitem(name,count,price,totalPrice,order_id) values(?,?,?,?,?)";
        return update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());
    }

    @Override
    public List<OrderItem> queryOrderItemByOrderId(String orderId) {
        return null;
    }
}
