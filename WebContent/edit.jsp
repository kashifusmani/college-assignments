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

<form  action="${pageContext.request.contextPath}/Logout" method="post">
	<input type="submit" value="Logout">
</form>
<br/>

<table> 
	<form action="${pageContext.request.contextPath}/Edit" method="post">
		<tr>
			<td>Description</td>
			<td><input type="text" name="description" size="100" value='${description}'></td>
		</tr>
		<tr>
			<td>Quantity Available</td>
			<td><input type="text" name="quantity" size="12" value='${quantity}'></td>
		</tr>
		<tr>
			<td>Author</td>
			<td><input type="text" name="author" size="12" value='${author}'></td>
		</tr>
		<tr>
			<td>Genre</td>
			<td><input type="text" name="genre" size="12" value='${genre}'></td>
		</tr>
		<tr>
			<td>ISBN</td>
			<td><input type="text" name="isbn" size="12" value='${isbn}'></td>
		</tr>
		<tr>
			<td>Image Link(ex: http://somewebsite/img.png)</td>
			<td><input type="text" name="image" size="12" value='${image}'></td>
			<input type="hidden" name="bookId" value="${bookId}"> 
		</tr>
		<input type="submit" name="editBook" value="Save">
	</form>
</table>
<br/>
<TABLE border="1">
		<c:forEach items='${booksList}' var="book">
		<form action="${pageContext.request.contextPath}/Edit" method="post">
  			<tr>
    			<td><img SRC="${book.image}" height="100" width="100"/></td>
    			<input type="hidden" name="image" value="${book.image}">
    			<td><b><c:out value="${book.description}"/></b></td>
    			<input type="hidden" name="description" value="${book.description}">
    			<td><b><c:out value="${book.quantity}"/></b></td>
    			<input type="hidden" name="quantity" value="${book.quantity}">
    			<td><b><c:out value="${book.author}"/></b></td>
    			<input type="hidden" name="author" value="${book.author}">
    			<td><b><c:out value="${book.isbn}"/></b></td>
    			<input type="hidden" name="isbn" value="${book.isbn}">
    			<td><b><c:out value="${book.genre}"/></b></td>
    			<input type="hidden" name="genre" value="${book.genre}">
    			<td>
    				<input name="editaction" type="submit" value="Delete"/>
          			<input type="hidden" name="bookId" value="${book.id}"> 
          		</td>
          		<td>
    				<input name="editaction" type="submit" value="Edit"/>
          			<input type="hidden" name="bookId" value="${book.id}"> 
          		</td>
  			</tr>
  		</form>
	</c:forEach>
</TABLE>

</body>
</html>