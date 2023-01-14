package com.yangmingyue.pojo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
    private Integer totalCount;
    private Integer totalItemsPrice;
    private Map<Integer,CartItem> items=new LinkedHashMap<Integer,CartItem>();

    /**
     * 添加商品
     * */
    public void addItem(CartItem item){
        CartItem cartItem = items.get(item.getId());
        //购物车已经由该商品，则更新商品的数量和总金额
        if(cartItem!=null){
            //更新商品的数量
            cartItem.setCount(cartItem.getCount()+1);
            //更新商品的总金额
            cartItem.setTotalPrice(new BigDecimal(cartItem.getCount()).multiply(cartItem.getPrice()));
        }else{
        //购物车没有该商品
            items.put(item.getId(),item);
        }
    }

    /**
     * 清空购物车
     * */
    public void clearCart(){
        items.clear();
    }

    /**
     * 更新商品的数量
     * */
    public void updataItemCount(Integer id,Integer count){
    //根据商品的id首先找到商品是否需存在，然后重新设置数量
        CartItem cartItem = items.get(id);
        if(cartItem!=null){
            cartItem.setCount(count);
            cartItem.setTotalPrice(new BigDecimal(cartItem.getCount()).multiply(cartItem.getPrice()));
        }else{
            System.out.println("商品不存在");
        }
    }

    /**
     * 删除商品**/
    public  void deleteItem(Integer id){
        items.remove(id);
    }

    public Cart() {
    }

    public Cart(Integer totalCount, Integer totalItemsPrice, Map<Integer, CartItem> items) {
        this.totalCount = totalCount;
        this.totalItemsPrice = totalItemsPrice;
        this.items = items;
    }

    public Integer getTotalCount() {
        //返回cartitems对象的数量
        Integer number = 0;
        for (Map.Entry<Integer, CartItem> item : items.entrySet()) {
            number += item.getValue().getCount();

        }
        return number;
    }



    public BigDecimal getTotalItemsPrice() {
        BigDecimal itemsPrice=new BigDecimal(0);
        for (Map.Entry<Integer,CartItem> item:items.entrySet()
             ) {
           itemsPrice=itemsPrice.add(item.getValue().getTotalPrice());
        }
        return itemsPrice;
    }


    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "TotalCount=" + getTotalCount() +
                ", TotalItemsPrice=" + getTotalItemsPrice() +
                ", items=" + items +
                '}';
    }
}
