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

    /**
     * 查询出order表中所有的order记录
     * */
    @Override
    public List<Order> queryOrders() {
        String sql="select order_id as orderId,create_time as createTime,price,status,userId from t_order";
        return queryForList(Order.class,sql);
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
