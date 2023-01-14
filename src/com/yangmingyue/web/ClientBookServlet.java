package com.yangmingyue.web;

import com.yangmingyue.pojo.Page;
import com.yangmingyue.service.BookService;
import com.yangmingyue.service.impl.BookServiceImpl;
import com.yangmingyue.utils.webUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientBookServlet extends BaseServlet{
    private BookService bookService=new BookServiceImpl();
    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求的参数
        //页码最开始如果不选择的话，默认应该从1开始
        Integer pageNo= webUtils.stringCastInt(request.getParameter("pageNo"),1);
        Integer pageSize=webUtils.stringCastInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        //2.调用BookService.page(pageNo,pageSize):返回一个page对象
        Page page = bookService.page(pageNo, pageSize);
        //3.将page对象保存到request域中
        request.setAttribute("url","page");
        request.setAttribute("page",page);
        //4.请求转发到/pages/client/index.jsp页面
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request,response);
    }

    protected void pageForPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求的参数
        //页码最开始如果不选择的话，默认应该从1开始
        Integer pageNo= webUtils.stringCastInt(request.getParameter("pageNo"),1);
        Integer pageSize=webUtils.stringCastInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        Integer min=webUtils.stringCastInt(request.getParameter("min"),0);
        Integer max=webUtils.stringCastInt(request.getParameter("max"),Integer.MAX_VALUE);

        StringBuilder url=new StringBuilder("pageForPrice");
        //如果是从价格区间的分页跳转，则追加地址
        if(request.getParameter("min")!=null){
            url.append("&min=").append(request.getParameter("min"));
        }
        if(request.getParameter("max")!=null){
            url.append("&max=").append(request.getParameter("max"));
        }
        //2.调用BookService.page(pageNo,pageSize):返回一个page对象
        Page page = bookService.queryPageForPrice(pageNo, pageSize,min,max);
        //3.将page对象保存到request域中
        request.setAttribute("url",url.toString());
        request.setAttribute("page",page);
        //4.请求转发到/pages/client/index.jsp页面
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request,response);
    }
}
