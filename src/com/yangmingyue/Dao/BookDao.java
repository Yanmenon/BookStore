package com.yangmingyue.Dao;

import com.yangmingyue.pojo.Book;

import java.util.List;

public interface BookDao {
    //添加图书到数据库中
    public int insertBook(Book book);

    //根据id删除图书
    public int deleteBookById(Integer id);

    //根据id修改图书
    public int updateBookById(Book book);

    //根据id查询数据
    public Book queryBookById(Integer id);

    //返回一系列图书
    public List<Book> queryBook();

    Integer queryPageForTotalCount();

    List<Book> queryForPage(int begin, Integer pageZise);

    Integer queryPageForTotalCountLimitPrice(Integer min, Integer max);

    List<Book> queryForPageLimitPrice(int begin, Integer pageSize, Integer min, Integer max);
}
