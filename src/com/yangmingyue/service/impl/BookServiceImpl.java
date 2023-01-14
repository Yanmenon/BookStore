package com.yangmingyue.service.impl;

import com.yangmingyue.Dao.BookDao;
import com.yangmingyue.Dao.impl.BookDaoImpl;
import com.yangmingyue.pojo.Book;
import com.yangmingyue.pojo.Page;
import com.yangmingyue.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDao bookDao=new BookDaoImpl();
    @Override
    public void insertBook(Book book) {
        bookDao.insertBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBookById(Book book) {
        bookDao.updateBookById(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBook() {
        return bookDao.queryBook();
    }

    //处理分页业务，求出总记录数，总页码，当前页的数据
    @Override
    public Page<Book> page(Integer pageNo, Integer pageSize) {
        Page<Book> page = new Page();
        //1.总记录数
        Integer pageTotalCount=bookDao.queryPageForTotalCount();
        page.setPageTotalCount(pageTotalCount);
        //2.总页码=总记录数/每页显示的记录数
        Integer pageTotal=pageTotalCount/Page.PAGE_SIZE;
        //3.如果整页显示不完则添加页面
        if(pageTotalCount%Page.PAGE_SIZE>0){
            pageTotal++;
        }
        page.setPageTotal(pageTotal);
        /**
         * 数据边界的有效校验
         * */
        if(pageNo<1){
            pageNo=1;
        }
        if(pageNo>pageTotal){
            pageNo=pageTotal;
        }
        page.setPageNo(pageNo);
        //3.求出当前页的数据
        int begin=(pageNo-1)*Page.PAGE_SIZE;
        List<Book> list=bookDao.queryForPage(begin,Page.PAGE_SIZE);
        page.setItems(list);
        return page;
    }

    @Override
    public Page queryPageForPrice(Integer pageNo, Integer pageSize, Integer min, Integer max) {
        Page<Book> page = new Page();
        //1.总记录数
        Integer pageTotalCount=bookDao.queryPageForTotalCountLimitPrice(min,max);
        page.setPageTotalCount(pageTotalCount);
        //2.总页码=总记录数/每页显示的记录数
        Integer pageTotal=pageTotalCount/Page.PAGE_SIZE;
        //3.如果整页显示不完则添加页面
        if(pageTotalCount%Page.PAGE_SIZE>0){
            pageTotal++;
        }
        page.setPageTotal(pageTotal);
        /**
         * 数据边界的有效校验
         * */
        if(pageNo<1){
            pageNo=1;
        }
        if(pageNo>pageTotal){
            pageNo=pageTotal;
        }
        page.setPageNo(pageNo);
        //3.求出当前页的数据
        int begin=(pageNo-1)*Page.PAGE_SIZE;
        List<Book> list=bookDao.queryForPageLimitPrice(begin,Page.PAGE_SIZE,min,max);
        page.setItems(list);
        return page;
    }
}
