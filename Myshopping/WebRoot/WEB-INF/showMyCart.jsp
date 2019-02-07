<%@page import="com.gt.domain.Users"%>
<%@page import="com.gt.service.MyCart"%>
<%@page import="com.gt.domain.Book"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>我的购物车清单</title>
	<link rel="stylesheet" type="text/css" href="static/css/comm.css">
  </head>
  <body>
    <h1>我的购物车</h1>  
    <a href="<%=basePath%>GoHallUI">返回购物大厅</a>
    <form action="<%=basePath%>ShoppingClServlet?type=update" method="post">
    <table border=1 style="border-collapse:collapse">
    	<tr><td>书籍编号<td>书名</td><td>价格</td><td>出版社</td><td>数量</td><td>删除</td></tr>
    	<%
    		//从request取出要显示的商品的信息
    		System.out.println("OK，进入了我的购物车");
    		ArrayList al=(ArrayList)request.getAttribute("bookList");
//     		System.out.println("al.size:"+al.size());
    		for(int i=0;i<al.size();i++)
    		{
    			Book book = (Book) al.get(i);
    		
		%>
    			<tr>
   	    			<td><%=book.getId()%><input type='hidden' name='id' value="<%=book.getId()%>"></td>					<%--书籍编号  --%>
   	    			<td><%=book.getName()%></td>				<%--书名          --%>
     				<td><%=book.getBookprice()%></td>			<%--价格          --%>
   	    			<td><%=book.getPublishHouse()%></td>		<%--出版社    --%>
 	    			<td><input type="text" name="booknum" value="<%=book.getShoppingNum()%>">本</td>	 <!--数量	   -->
  	    			<td><a href='<%=basePath%>ShoppingClServlet?type=del&id=<%=book.getId()%>'>删除</a></td>						<!--删除书籍 -->
    			</tr>  
    	<%  
    			}	
    	%>

    	<tr><td colspan="6"><input type="submit" value="update"></td></tr>
    	<tr><td colspan="6">购物车的总价格：${totalPrice}元</td></tr>     		
    </table>
    </form>
    <a href="<%=basePath%>GoMyOrderServlet">提交订单</a>

  </body>
</html>



