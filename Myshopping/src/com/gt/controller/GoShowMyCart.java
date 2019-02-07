package com.gt.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gt.service.MyCart;

@WebServlet("/GoShowMyCart")
public class GoShowMyCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoShowMyCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//得到购物车
		MyCart myCart = (MyCart)request.getSession().getAttribute("myCart");
		//把要显示的数据放入request，准备显示
		request.setAttribute("bookList",myCart.showMyCart());
		request.setAttribute("totalPrice",myCart.getTotalPrice());
		//跳转到显示我的购物车中去
		request.getRequestDispatcher("/WEB-INF/showMyCart.jsp").forward(request,response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
