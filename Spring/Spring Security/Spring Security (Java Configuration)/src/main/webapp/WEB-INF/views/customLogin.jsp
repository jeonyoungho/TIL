<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>Custom Login Page</h1>
<h2 style="color: red;"><c:out value="${errorMsg}"/></h2>
<h2 style="color: red;"><c:out value="${logoutMsg}"/></h2>

<form method='post' action="/login">

<div>
	아이디: <input type='text' name='username'>
</div>

<div>
	비밀번호: <input type='password' name='password'>
</div>

<div>
	<input type='submit'>
</div>
<div>
	<input type='checkbox' name='remember-me'>Remember Me
</div>

<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

</form>

</body>
</html>