package com.yangmingyue.web;

import com.yangmingyue.pojo.Book;
import com.yangmingyue.pojo.Page;
import com.yangmingyue.service.BookService;
import com.yangmingyue.service.impl.BookServiceImpl;
import com.yangmingyue.utils.webUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookServlet extends BaseServlet{
    private BookService bookService=new BookServiceImpl();
    protected void insertBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer pageNo=webUtils.stringCastInt(request.getParameter("pageNo"),0);
        //1.获取从html表单传输的数据
        Book book = webUtils.copyParamToBeam(new Book(), request.getParameterMap());
        //2.将数据存储到数据库中
        bookService.insertBook(book);
        //3.将页面跳转到所有图书的显示页面,设置请求重定向以后就相当于两次请求，刷新客户端只会对应第二次请求，也就是访问queryBooks页面的请求，不会执行添加操作
        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=page&pageNo="+(pageNo+1));
    }

    protected void deleteBookById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer pageNo=webUtils.stringCastInt(request.getParameter("pageNo"),0);
        //1.获取请求的id,从浏览器页面返回的数据都是字符串类型，这里应该转换为包装类类型才可以
        String id=request.getParameter("id");
        //2.将string抓化为包装类类型
        Integer newId = webUtils.stringCastInt(id, 0);
        //3.根据id删除图书
        bookService.deleteBookById(newId);
        //4.删除图书之后继续回到显示所有图书页面
        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=page&pageNo="+pageNo);
    }

    protected void updateBookById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer pageNo=webUtils.stringCastInt(request.getParameter("pageNo"),0);
        //1.将修改后的信息获取并封装到一个javabean中
        Book book = webUtils.copyParamToBeam(new Book(), request.getParameterMap());
        //2.到数据库中更新图书
       bookService.updateBookById(book);
       //3.重新转发到查询页面
        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=page&pageNo="+pageNo);
    }


    protected void getUpdateBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1根据jsp页面传过来的id获取图书信息
        String id = request.getParameter("id");
        Integer newid = webUtils.stringCastInt(id, 0);
        //2.根据id获取到图书信息
        Book book = bookService.queryBookById(newid);
        //3.将需要显示的信息封装到request域中给book_edit.jsp页面,如果获取到待更新的图书信息就会显示，没获取到就显示为空
        request.setAttribute("book",book);
        //4.转发到book_edit.jsp页面
        request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request,response);
    }

    protected void queryBookById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void queryBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //查询数据库找到所有图书
        List<Book> books = bookService.queryBook();
        //将图书保存到request域中
        request.setAttribute("books",books);
        //请求转发到book_manager页面
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
    }

    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       //1.获取请求的参数
        //页码最开始如果不选择的话，默认应该从1开始
        Integer pageNo=webUtils.stringCastInt(request.getParameter("pageNo"),1);
        Integer pageSize=webUtils.stringCastInt(request.getParameter("pageSize"),Page.PAGE_SIZE);
        //2.调用BookService.page(pageNo,pageSize):返回一个page对象
        Page page = bookService.page(pageNo, pageSize);
        //3.将page对象保存到request域中
        request.setAttribute("page",page);
        //4.请求转发到/pages/manager/book_manager.jsp页面
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
    }

}
