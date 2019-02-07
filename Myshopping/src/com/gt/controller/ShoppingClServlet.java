package com.gt.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gt.service.BookService;
import com.gt.service.MyCart;

/**
 * 该控制器响应用户购买商品的控制器
 */
@WebServlet("/ShoppingClServlet")
public class ShoppingClServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShoppingClServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 接收用户想购买的商品的ID号
		String id=request.getParameter("id");
		System.out.println("ShoppingClServlet--id:"+id);
		//接收type值，区分用户希望做什么 del add update
		String type= request.getParameter("type");
		System.out.println("ShoppingClServlet type:"+type);
		//得到购物车
		MyCart myCart = (MyCart)request.getSession().getAttribute("myCart");
		if (type.equals("add")) {
			//用户希望添加商品到购物车
			//把商品添加到购物车中
			myCart.addBook(id,new BookService().getBookById(id));
//			myCart.addBook2(id);

		}else if (type.equals("del")) {
			//用户要删除商品（从购物车）
			myCart.delBook(id);
			
		}else if (type.equals("update")) {
			//更新
			System.out.println("用户数据更新");
			//得到用户希望更新的书号和数量
			String bookIds[] = request.getParameterValues("id");
			//得到每本书的数量
			String nums[] = request.getParameterValues("booknum");
			for (int i = 0; i < bookIds.length; i++) {
//				System.out.println(bookIds[i]+" "+nums[i]);
				//更新整个购物车
				myCart.updateBook(bookIds[i],Integer.parseInt(nums[i]));	
			}
		}

//		//把要显示的数据放入request，准备显示
//		request.setAttribute("bookList",myCart.showMyCart());
//		request.setAttribute("totalPrice",myCart.getTotalPrice());
		//跳转到显示我的购物车中去
//		request.getRequestDispatcher("/WEB-INF/showMyCart.jsp").forward(request,response);
		//为了防止某个页面刷新，我们可以sendRedirect
		response.sendRedirect("GoShowMyCart");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
