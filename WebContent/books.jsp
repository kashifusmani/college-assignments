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
<br/>
${sessionScope.bookAdded }
<%
request.getSession().setAttribute("bookAdded", null);
%>
<br/>
${sessionScope.bookEdited }
<%
request.getSession().setAttribute("bookEdited", null);
%>
<br/>

<table border="1"> 
	<tr> Add a new Book </tr>
	<br/>
	<form action="${pageContext.request.contextPath}/Add" method="post">
		<tr>
			<td>Description</td>
			<td><input type="text" name="description" size="100"></td>
		</tr>
		<tr>
			<td>Quantity Available</td>
			<td><input type="text" name="quantity" size="100"></td>
		</tr>
		<tr>
			<td>Author</td>
			<td><input type="text" name="author" size="100"></td>
		</tr>
		<tr>
			<td>Genre</td>
			<td><input type="text" name="genre" size="100"></td>
		</tr>
		<tr>
			<td>ISBN</td>
			<td><input type="text" name="isbn" size="100"></td>
		</tr>
		<tr>
			<td>Image Link(ex: http://somewebsite/img.png)</td>
			<td><input type="text" name="image" size="100"></td>
		</tr>
		<input type="submit" name="addBook" value="Add">
	</form>
</table>

<br/>
<p> Your present inventory </p>
<TABLE border="1">
	<tr>
	<td><b> </b> </td>
	<td><b> Description </b> </td>
	<td><b> Quantity Available </b> </td>
	<td><b> Author </b> </td>
	<td><b> ISBN </b> </td>
	<td><b> Genre </b> </td>
	<td><b> Delete </b> </td>
	<td><b> Edit </b> </td>
	</tr>

	<c:forEach items='${booksList}' var="book">
		<form action="${pageContext.request.contextPath}/Delete" method="post">
  			<tr>
    			<td>
    				<img SRC="${book.image}" height="100" width="100"/>
    				
    			</td>
    			<td>
    				<b><c:out value="${book.description}"/></b>
    					
    			</td>    			
    			<td>
    				<b><c:out value="${book.quantity}"/></b>
    				
    			</td>
    			<td>
    				<b><c:out value="${book.author}"/></b>
    				
    			</td>
    			<td>
    				<b><c:out value="${book.isbn}"/></b>
    				
    			</td>
    			<td>
    				<b><c:out value="${book.genre}"/></b>
    				
    			</td>	
    			<td>
    				<input name="editaction" type="submit" value="Delete"/>
          			<input type="hidden" name="bookId" value="${book.id}"> 
          		</td>
          		<td>
    				<input name="editaction" type="submit" value="Edit"/>
          			<input type="hidden" name="bookId" value="${book.id}"> 
          			<input type="hidden" name="image" value="${book.image}">
          			<input type="hidden" name="description" value="${book.description}">
          			<input type="hidden" name="quantity" value="${book.quantity}">
          			<input type="hidden" name="author" value="${book.author}">
          			<input type="hidden" name="isbn" value="${book.isbn}">
          			<input type="hidden" name="genre" value="${book.genre}">
          			
          		</td>
  			</tr>
  		</form>
	</c:forEach>
</TABLE>

</body>
</html>