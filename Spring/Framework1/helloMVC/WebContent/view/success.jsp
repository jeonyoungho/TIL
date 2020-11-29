<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>
	<%-- JSP Expression Language -> 객체의 property 접근--%>
	<p>Hellow</p>
	<ul>
		<li>Id : ${customer.id}</li>
		<li>Name : ${customer.name}</li>
		<li>Email : ${customer.email}</li>
	</ul>
	
	<%--JSTL -> 자바의 동적인 코드부분이  필요한 경우에 사용하면 효율적--%>

	<table>
		<c:forEach var="customer" items="${customers}">
			<tr>
				<td>${customer.id}</td>
				<td>${customer.name}</td>
				<td>${customer.email}</td>
			</tr>
		</c:forEach>

	</table>

</body>
</html>