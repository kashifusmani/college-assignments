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
<h2><i>Welcome </i></h2>
<p>Please enter your user name and password then click <b>Register</b>.</p>
<fieldset>

<form  action="${pageContext.request.contextPath}/Register" method="post">
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
<input type="submit" name="registerButtonPressed" value="Register"></form>
</fieldset>
</body>
</html>