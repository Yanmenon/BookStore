package com.yangmingyue.Dao.impl;

import com.sun.xml.internal.ws.org.objectweb.asm.ClassAdapter;
import com.yangmingyue.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * BaseDao是一个抽象类只能被继承
 * **/

public abstract class BaseDao {
    //使用dbutils操作数据库
    private QueryRunner qr=new QueryRunner();

    /**
     * update方法用来执行insert,update,delete语句
     * */
    public int update(String sql,Object... args){
        Connection conn= JdbcUtils.getConnection();
        try {
            return qr.update(conn,sql,args);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
         JdbcUtils.close(conn);
        }
        return -1;
    }

    /**
     * 查询返回一个javabean的sql语句
     * type 返回的对象类型
     * sql  执行的sql语句
     * args sql对应的参数值
     * <T>  返回类型的泛型
     * */
    public <T> T queryForOne(Class<T> type,String sql,Object ... args) {
        Connection conn = JdbcUtils.getConnection();
        try {
//            将结果集中的第一行数据封装到一个对应的JavaBean实例中
            return qr.query(conn, sql, new BeanHandler<T>(type), args);
        } catch (SQLException e) {
           e.printStackTrace();
        } finally {
            JdbcUtils.close(conn);
        }
        return null;
    }

    /**
     * 查询返回多个javabean的语句
     * */
    public <T> List<T> queryForList(Class<T> type, String sql, Object ... args) {
        Connection conn = JdbcUtils.getConnection();
        try {
            //这里返回的是一个javabean的list
            return qr.query(conn, sql, new BeanListHandler<T>(type), args);
        } catch (SQLException e) {
           e.printStackTrace();
        } finally {
            JdbcUtils.close(conn);
        }
        return null;
    }

    /***
     * 返回一行一列的参数值
     * */
    public Object queryForSingleValue(String sql,Object ... args) {
        Connection conn = JdbcUtils.getConnection();
        try {
            return qr.query(conn,sql, new ScalarHandler(), args);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(conn);
        }
        return null;
    }
}
