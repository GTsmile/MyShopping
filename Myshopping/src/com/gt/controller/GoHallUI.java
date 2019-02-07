package com.gt.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gt.domain.Users;
import com.gt.service.BookService;
import com.gt.service.MyCart;
import com.gt.service.UsersService;

/**
 * Servlet implementation class GoHallUI
 */
@WebServlet("/GoHallUI")
public class GoHallUI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoHallUI() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//先判断该用户是否已经登录，如果登录了的话，则直接跳转到购物大厅可读性差一些
		if (request.getSession().getAttribute("loginUser")!=null) {
			BookService bookService = new BookService();
			ArrayList al=bookService.getAllBook();
			//把显示的数据放入到request原因第因为request对象的生命最短
			request.setAttribute("books",al);
			request.getRequestDispatcher("/WEB-INF/Hall.jsp").forward(request,response);
			return ;
		}
		
		
		
		// 取出从登录页面传递的用户名和密码
		String id=request.getParameter("userid");
		String p=request.getParameter("password");
		
		//创建一个Users()对象
		Users loginUser = new Users(Integer.parseInt(id),p);
		
		//使用业务逻辑类完成验证
		UsersService usersService = new UsersService();
		System.out.println("loginUser checkUser"+usersService.checkUser(loginUser));
		if (usersService.checkUser(loginUser)) {
			//说明是合法用户，跳转到购物大厅	
			//因为在其他的页面都可能使用到用户信息，因此，我们可以把用户信息存放到session中去
			System.out.println("用户账号："+loginUser.getId()+" 用户密码："+loginUser.getPwd());
			request.getSession().setAttribute("loginUser",loginUser);
			
			//创建购物车对象（当用户登录成功后，为其创建购物车）
			MyCart myCart= new MyCart();
			request.getSession().setAttribute("myCart",myCart);
			//也是给下一个页面hall.jsp准备要显示的数据
			//模式开发，在一定程度上约束了程序员的自由
			BookService bookService = new BookService();
			ArrayList al=bookService.getAllBook();
			//把显示的数据放入到request原因第因为request对象的生命最短
			request.setAttribute("books",al);
			request.getRequestDispatcher("/WEB-INF/Hall.jsp").forward(request,response);
		}else{
			//用户不合法
			request.getRequestDispatcher("/WEB-INF/Login.jsp").forward(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
