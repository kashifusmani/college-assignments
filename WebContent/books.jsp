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

<p><b> Hello ${sessionScope.userName} </b></p>	

<form action="${pageContext.request.contextPath}/AddBook" method="get"> 
	<input type="submit" value="My Cart">
</form>

<form  action="${pageContext.request.contextPath}/Logout" method="post">
	<input type="submit" value="Logout">
</form>

${sessionScope.bookAdded }
<%
request.getSession().setAttribute("bookAdded", null);
%>



<TABLE border="1">
	<tr>
	<td><b> </b> </td>
	<td><b> Description </b> </td>
	<td><b> Quantity Available </b> </td>
	<td><b> Author </b> </td>
	<td><b> Genre </b> </td>
	<td><b> ISBN </b> </td>
	</tr>

	<c:forEach items='${booksList}' var="book">
		<form action="${pageContext.request.contextPath}/Delete" method="post">
  			<tr>
    			<td><img SRC="${book.image}" height="100" width="100"/></td>
    			<td><b><c:out value="${book.description}"/></b></td>
    			<td><b><c:out value="${book.quantity}"/></b></td>
    			<td><b><c:out value="${book.quantity}"/></b></td>
    			<td>
    				<input name="Add to my cart" type="submit" value="Add to Cart"/>
          			<input type="hidden" name="productId" value="${product.id}"> 
          			<input type="hidden" name="productDescr" value="${product.description}">
          		</td>
  			</tr>
  		</form>
	</c:forEach>
</TABLE>

</body>
</html>