package com.gt.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gt.domain.Users;
import com.gt.javamail.Mail;
import com.gt.service.MyCart;
import com.gt.service.OrderService;

/**
 * Servlet 要处理下订单的请求
 */
@WebServlet("/SubmitOrderServlet")
public class SubmitOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		try {
			OrderService orderService=new OrderService();
			MyCart myCart=(MyCart)request.getSession().getAttribute("myCart");
			Users user = (Users)request.getSession().getAttribute("loginUser");
			orderService.submitOrder(myCart,user);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			request.getRequestDispatcher("/WEB-INF/errInfo.jsp").forward(request,response);
		}
		request.getRequestDispatcher("/WEB-INF/orderOk.jsp").forward(request,response);
		
		//如果订单写入数据库成功，把邮件发送给客户
		//创建一个Mail对象实例
		Mail mail=new Mail();
		Users user = (Users)request.getSession().getAttribute("loginUser");
		String To=user.getEmail();
		String Subject="订单完成通知";
		String Content="你在GT网上订购了图书，请注意查收，谢谢！";
		String Username=user.getName();
		String Filename = "";
		try {
			mail.SendMail(To,Subject,Content,Username,Filename);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			System.out.println("购物车---SubmitOrderServlet--邮件发送失败");
			e.printStackTrace();
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
