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
${sessionScope.logoutError }
<p>Please enter login details</p>
<fieldset>

<form  action="${pageContext.request.contextPath}/Authenticate" method="post">
<table border="1">
		<tr>
			<td>${sessionScope.NamePassErr }</td>
		</tr>
		<tr>
			<td>User Name</td>
			<td><input type="text" name="userName" size="15"></td>
		</tr>
		<tr>
			<td>Password</td>
			<td><input type="password" name="password" size="15"></td>
		</tr>
</table>
<input type="submit" name="loginButton" value="Login"></form>
<p>  Register <a href="${pageContext.request.contextPath}/createnewuser.jsp" >Here</a>
</fieldset>
<% 
request.getSession().setAttribute("NamePassErr", null);
%>
</body>
</html>