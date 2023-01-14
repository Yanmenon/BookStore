package com.yangmingyue.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;


public class webUtils {
//    public static Object copyParamToBeam(Object bean, Map value){
    /***
     * 这里的<T>表示该函数是一个泛型
     * 函数中的T表示函数的返回值类型是一个泛型
     * 参数声明T表示该参数的类型是一个泛型
     * */
  public static <T> T copyParamToBeam(T bean, Map value){
    try {
            BeanUtils.populate(bean,value);
            System.out.println(bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }


    public static Integer stringCastInt(String id,Integer default_value){
        if( id!=null &&!"".equals(id)){
            try {
                return Integer.parseInt(id);
            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }
        }
        return default_value;
    }
}
