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
<p>Please enter your user name and password. Then click <b>Log In</b>.</p>
<fieldset>

<form  action="${pageContext.request.contextPath}/Login" method="get">
<table border="0">
		<tr>
			<td><div id="header" class="dark">${sessionScope.UserNameOrPasswordError }</div></td>
		</tr>
		<tr>
			<td>User Name</td>
			<td><input type="text" name="userName" size="12"></td>
		</tr>
		<tr>
			<td>Password</td>
			<td><input type="password" name="password" size="12"></td>
		</tr>
</table>
<input type="submit" name="loginButtonPressed" value="login"></form>
<p> Not registered? Click <a href="${pageContext.request.contextPath}/register.jsp" >Here</a>
</fieldset>
<% 
request.getSession().setAttribute("UserNameOrPasswordError", null);
%>
</body>
</html>