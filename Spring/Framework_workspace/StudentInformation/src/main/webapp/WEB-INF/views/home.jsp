<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
<!DOCTYPE html>
<html>
<head>
<title>Home</title>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/css/home.css">

</head>
<body>

	<h2>
		목록을 선택하세요
	</h2>

	<div>

	<p>
		<a  href="${pageContext.request.contextPath}/takedcourse"> ① 학기별 이수 학점 조회 </a>
	</p>
	
	<p>
		<a  href="${pageContext.request.contextPath}/applycourse"> ② 수강 신청하기 </a>
	</p>
	
	<p>
		<a  href="${pageContext.request.contextPath}/applyedcourse"> ③ 수강 신청 조회 메뉴 </a>
	</p>
	
	<!--사용자인증이 되있을시 로그아웃 항목출력코드 , logout폼태그를 submit수행  -->
	<c:if test="${pageContext.request.userPrincipal.name != null}"> 
		<a href="javascript:document.getElementById('logout').submit()">④Logout</a>
	</c:if>

	
	<form class="logout" id="logout" action="<c:url value="/logout" />" method="post">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /><!--토큰전달  -->
	</form>
	
	</div>
</body>
</html>