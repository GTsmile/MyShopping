<%@page import="com.sun.java.swing.plaf.windows.resources.windows"%>
<%@page import="com.gt.domain.Users"%>
<%@page import="com.gt.domain.Book"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>我的订单页面</title>

	<link rel="stylesheet" type="text/css" href="static/css/comm.css">
	<script type="text/javascript">
		function goSubmitOrder(){
			window.location.href = "<%=basePath%>SubmitOrderServlet";
// 			window.alert("OK");
		}

	
	</script>

  </head>
  
  <body>
<%--   <%request.setCharacterEncoding("UTF-8"); %> --%>
    <h1>我的订单</h1>
    <h2>我的个人信息</h2>
    <table border="1" style="border-collapse:collapse">
	    <tr><td colspan="2">用户个人信息</td></tr>
	    <tr><td>用户名</td><td><%=((Users)session.getAttribute("loginUser")).getName() %></td></tr>
	    <tr><td>电子邮箱</td><td><%=((Users)session.getAttribute("loginUser")).getEmail() %></td></tr>
	    <tr><td>用户级别</td><td><%=((Users)session.getAttribute("loginUser")).getGrade() %></td></tr>
    </table></br>
    	
    <table border=1 style="border-collapse:collapse">
    	<tr><td>书籍编号<td>书名</td><td>价格</td><td>出版社</td><td>数量</td></tr>
    	<%
    		//循环的显示购物车中的商品信息
    		ArrayList al=(ArrayList)request.getAttribute("orderinfo");
//     		System.out.println("al.size:"+al.size());
    		for(int i=0;i<al.size();i++)
    		{
    			Book book = (Book) al.get(i);
		%>
    			<tr>
   	    			<td><%=book.getId()%></td>					<%--书籍编号  --%>
   	    			<td><%=book.getName()%></td>				<%--书名          --%>
     				<td><%=book.getBookprice()%></td>			<%--价格          --%>
   	    			<td><%=book.getPublishHouse()%></td>		<%--出版社    --%>
 	    			<td><%=book.getShoppingNum()%>本</td>	 	<!--数量	   -->
 	    		</tr>  
    	<%  
    			}	
    	%>
    	<tr><td colspan="5">订单总价格：${totalPrice}元</td></tr> 
    </table>
    <input type="button" onclick="goSubmitOrder()" value="确认订单"/>
    
<%--     <a href="<%=basePath%>SubmitOrderServlet">提交订单</a> --%>
  </body>
</html>
