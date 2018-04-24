<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<title>welcome page</title>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<!--  copy the next line to JSPs that use the core template library -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<body>
<% if (request.getSession().getAttribute("userName") == null) {
	request.getSession().setAttribute("UserNameOrPasswordError", "Please login first");
	request.getRequestDispatcher("/index.jsp").forward(request, response);
} %> 

<p>Welcome ${sessionScope.userName}</p>	




<form  action="${pageContext.request.contextPath}/Products" method="get">
	<input type="submit" value="Products">
</form>

<form action="${pageContext.request.contextPath}/Cart" method="get"> 
	<input type="submit" value="My Cart">
</form>

<form  action="${pageContext.request.contextPath}/Logout" method="post">
	<input type="submit" value="Logout">
</form>
<%
request.getSession().setAttribute("productAddedToCart", null);
request.getSession().setAttribute("cartConfirmationMessage", null);
%>


<TABLE border="1">
	<tr>
	<td><b> Product Image </b> </td>
	<td><b> Description </b> </td>
	<td><b> Price </b> </td>
	<td><b> OrderId </b> </td>
	</tr>

	<c:forEach items='${ordersList}' var="order">
			<tr>
    			<td><img SRC="${order.image}" height="100" width="100"/></td>
    			<td><b><c:out value="${order.description}"/></b></td>
    			<td><b><c:out value="${order.price}"/></b></td>
    			<td><b><c:out value="${order.orderId}"/></b></td>
  			</tr>
  		
	</c:forEach>
</TABLE>

</body>
</html>