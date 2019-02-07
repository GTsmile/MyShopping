package com.gt.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gt.service.MyCart;

/**
 * 用于处理用户查看订单的一个跳转请求Servlet
 */
@WebServlet("/GoMyOrderServlet")
public class GoMyOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoMyOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//得到购物车
		MyCart myCart = (MyCart)request.getSession().getAttribute("myCart");
		if(myCart.IsEmpty()){	//如果购物车为空
			request.getRequestDispatcher("/WEB-INF/MyCartError.jsp").forward(request,response);
//			request.getRequestDispatcher("/servlet/GoHallUI").forward(request,response);
		}else{
		
			ArrayList al=myCart.showMyCart();
			float totalPrice=myCart.getTotalPrice();
			request.setAttribute("orderinfo",al);
			request.setAttribute("totalPrice",totalPrice);
			//跳到显示我的订单的页面
			request.getRequestDispatcher("/WEB-INF/showMyOrder.jsp").forward(request,response);
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
