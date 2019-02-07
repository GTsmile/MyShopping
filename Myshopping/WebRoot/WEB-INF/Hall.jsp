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
    
    <title>购物大厅</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		function goMyCart(){
			window.location.href = "<%=basePath%>ShoppingClServlet?type=go";
// 			window.alert("OK");
		}

	
	</script>
	

  </head>
  
  <body>
    <h1>欢迎访问购物大厅</h1>
    <table border="1px">
    	<tr><td>书籍编号</td><td>书名</td><td>价格</td><td>出版社</td><td>点击购买</td></tr>
    	<%
    		//取出request中的ArrayList
    		ArrayList al=(ArrayList)request.getAttribute("books");
//     		Systerm.out.println("al.size():"+al.size());
    		for(int i=0;i<al.size();i++){
    			Book book=(Book)al.get(i);
    	%>
    		<tr>
      			<td><%=book.getId()%>			</td>	<%-- 编号 --%>
     			<td><%=book.getName()%>			</td>	<%-- 书名 --%>
     			<td><%=book.getBookprice()%>	</td>	<%-- 书名 --%>
     			<td><%=book.getPublishHouse()%>	</td>	<%-- 书名 --%>
      			<td><a href=<%=basePath%>ShoppingClServlet?type=add&id=<%=book.getId()%>>购买</a></td>
    		</tr>	
    	<%	
    		}
    	%>
    	<tr><td colspan=5><input type="button" onclick="goMyCart()" value="查看购物车"></td></tr>		
    </table>
    <a href="<%=path%>">返回重新登录</a>
  </body>
</html>
