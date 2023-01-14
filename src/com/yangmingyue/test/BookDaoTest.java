package com.yangmingyue.test;

import com.yangmingyue.Dao.BookDao;
import com.yangmingyue.Dao.impl.BookDaoImpl;
import com.yangmingyue.pojo.Book;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class BookDaoTest {

    private BookDao bookDao=new BookDaoImpl();
    @Test
    public void insertBook() {
        bookDao.insertBook(new Book(null,"LittleWomen",new BigDecimal(24.50),"Jane",20,100,"static/img/default.jpg"));
    }

    @Test
    public void deleteBookById() {
        bookDao.deleteBookById(5);
    }

    @Test
    public void updateBookById() {
        bookDao.updateBookById(new Book(null,"LoveSea",new BigDecimal(22),"lily",7,20,"static/img/default.jpg"));
    }

    @Test
    public void queryBookById() {
        Book book = bookDao.queryBookById(1);
        System.out.println(book);
    }

    @Test
    public void queryBook() {
        List<Book> books = bookDao.queryBook();
        for (Book book:books
             ) {
            System.out.println(book);
        }
    }

    @Test
    public void queryPageForTotalCount() {
        System.out.println(bookDao.queryPageForTotalCount());
    }

    @Test
    public void queryForPage() {
        System.out.println(bookDao.queryForPage(0,4));
    }

}