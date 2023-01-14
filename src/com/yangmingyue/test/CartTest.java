package com.yangmingyue.test;

import com.yangmingyue.pojo.Cart;
import com.yangmingyue.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CartTest {
    Cart cart = new Cart();
    @Test
    public void addItem() {
        CartItem cartItem1 = new CartItem(1, "红高粱", 1, new BigDecimal(29.99), new BigDecimal(29.99));
        CartItem cartItem2 = new CartItem(1, "红高粱", 1, new BigDecimal(29.99), new BigDecimal(29.99));
        CartItem cartItem3 = new CartItem(3, "蒜薹天堂", 1, new BigDecimal(32.00), new BigDecimal(32.00));


        cart.addItem(cartItem1);
        cart.addItem(cartItem2);
        cart.addItem(cartItem3);

        System.out.println(cart);

    }

    @Test
    public void clearCart() {
        cart.clearCart();
        System.out.println(cart);
    }

    @Test
    public void updataItemCount() {
        CartItem cartItem1 = new CartItem(1, "红高粱", 1, new BigDecimal(29.99), new BigDecimal(29.99));
        CartItem cartItem2 = new CartItem(1, "红高粱", 1, new BigDecimal(29.99), new BigDecimal(29.99));
        CartItem cartItem3 = new CartItem(3, "蒜薹天堂", 1, new BigDecimal(32.00), new BigDecimal(32.00));


        cart.addItem(cartItem1);
        cart.addItem(cartItem2);
        cart.addItem(cartItem3);

        cart.updataItemCount(1,1);
        System.out.println(cart);
    }

    @Test
    public void deleteItem() {
        CartItem cartItem1 = new CartItem(1, "红高粱", 1, new BigDecimal(29.99), new BigDecimal(29.99));
        CartItem cartItem2 = new CartItem(1, "红高粱", 1, new BigDecimal(29.99), new BigDecimal(29.99));
        CartItem cartItem3 = new CartItem(3, "蒜薹天堂", 1, new BigDecimal(32.00), new BigDecimal(32.00));
        cart.addItem(cartItem1);
        cart.addItem(cartItem2);
        cart.addItem(cartItem3);

        cart.deleteItem(1);
        System.out.println(cart);
    }
}