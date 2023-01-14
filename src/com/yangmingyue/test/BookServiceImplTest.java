package com.yangmingyue.test;

import com.yangmingyue.pojo.Page;
import com.yangmingyue.service.BookService;
import com.yangmingyue.service.impl.BookServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class BookServiceImplTest {

    private BookService bookService=new BookServiceImpl();
    @Test
    public void page() {
        System.out.println(bookService.page(1, Page.PAGE_SIZE));
    }
}