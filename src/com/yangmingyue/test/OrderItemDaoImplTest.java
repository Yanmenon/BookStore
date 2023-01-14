package com.yangmingyue.test;

import com.yangmingyue.Dao.OrderItemDao;
import com.yangmingyue.Dao.impl.OrderItemDaoImpl;
import com.yangmingyue.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderItemDaoImplTest {
    OrderItemDao orderItemDao=new OrderItemDaoImpl();
    @Test
    public void savaOrderItem() {
        orderItemDao.savaOrderItem(new OrderItem(null,"老人与海",1,new BigDecimal(45.99),new BigDecimal(45.99),"123"));
    }

    @Test
    public void queryOrderItemByOrderId() {
    }
}