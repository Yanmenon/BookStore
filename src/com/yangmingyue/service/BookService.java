package com.yangmingyue.service;

import com.yangmingyue.pojo.Book;
import com.yangmingyue.pojo.Page;

import java.util.List;

public interface BookService {
    public void insertBook(Book book);
    public void deleteBookById(Integer id);
    public void updateBookById(Book book);
    public Book queryBookById(Integer id);
    public List<Book> queryBook();

    public Page page(Integer pageNo, Integer pageSize);

    Page queryPageForPrice(Integer pageNo, Integer pageSize, Integer min, Integer max);
}
