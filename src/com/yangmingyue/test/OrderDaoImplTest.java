package com.yangmingyue.test;

import com.yangmingyue.Dao.BookDao;
import com.yangmingyue.Dao.OrderDao;
import com.yangmingyue.Dao.impl.BookDaoImpl;
import com.yangmingyue.Dao.impl.OrderDaoImpl;
import com.yangmingyue.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

public class OrderDaoImplTest {
    OrderDao orderDao=new OrderDaoImpl();
    @Test
    public void saveOrder() {
        orderDao.saveOrder(new Order("123",new Date(),new BigDecimal(32),0,1));

    }

    @Test
    public void queryOrders() {
    }

    @Test
    public void changeOrderStatus() {
    }

    @Test
    public void queryOrderByUserId() {
    }
}