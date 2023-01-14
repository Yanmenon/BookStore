package com.yangmingyue.Dao.impl;

import com.yangmingyue.Dao.BookDao;
import com.yangmingyue.pojo.Book;

import java.util.List;

public class BookDaoImpl extends BaseDao implements BookDao {
    @Override
    public int insertBook(Book book) {
        String sql="insert into t_book(name,author,price,sales,stock,img_path) values(?,?,?,?,?,?)";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath());
    }

    @Override
    public int deleteBookById(Integer id) {
        String sql="delete from t_book where id=?";
        return update(sql,id);
    }

    @Override
    public int updateBookById(Book book) {
        String sql="update t_book set name=?,author=?,price=?,sales=?,stock=?,img_path=? where id=?";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath(),book.getId());
    }

    @Override
    public Book queryBookById(Integer id) {
        //注意此处要把数据库中的属性名和javabean中的属性名对应起来
        String sql="select id,name,author,price,sales,stock,img_path as imgPath from t_book where id=?";
        return queryForOne(Book.class,sql,id);
    }

    @Override
    public List<Book> queryBook() {
        String sql="select * from t_book";
        return queryForList(Book.class,sql);
    }

    @Override
    public Integer queryPageForTotalCount() {
        String sql="select count(*) from t_book";
        //此处queryForSingleValue返回的是一个number类型
        Number number =(Number)queryForSingleValue(sql);
        //把number类型转为int类型
        return number.intValue();
    }


    @Override
    public List<Book> queryForPage(int begin, Integer pageSize) {
        String sql="select id,name,author,price,sales,stock,img_path as imgPath from t_book limit ?,?";
        return queryForList(Book.class,sql,begin,pageSize);
    }

    @Override
    public Integer queryPageForTotalCountLimitPrice(Integer min, Integer max) {
        String sql="select count(*) from t_book where price between ? and ?";
        Number number=(Number) queryForSingleValue(sql,min,max);
        return number.intValue();
    }

    @Override
    public List<Book> queryForPageLimitPrice(int begin, Integer pageSize, Integer min, Integer max) {
        String sql="select id,name,author,price,sales,stock,img_path as imgPath from t_book where price between ? and ? order by price limit ?,?";
        return queryForList(Book.class,sql,min,max,begin,pageSize);
    }
}
