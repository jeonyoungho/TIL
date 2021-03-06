<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/css/course.css">

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학기별 이수 학점 조회</title>
</head>
<body>
	<!-- 학기별 이수 학점 현황을 출력하는 페이지-->
	<h2>학기별 이수 학점 현황</h2>
	<table class = "takedcourse">
		<thead id ="item">
			<tr>
				<th>수강년도</th>
				<th>학기</th>
				<th>교과코드</th>
				<th>교과목명</th>
				<th>구분</th>
				<th>학점</th>
			</tr>
			</thead>
		<tbody id = "list">

			<!-- CourseController로 부터 전달 받은 모델로 부터 course객체의 멤버변수 출력 -->
			<c:forEach var="courses" items="${takedcourse}"> 
				<tr>
					<td><c:out value="${courses.year}"></c:out></td>
					<td><c:out value="${courses.semester}"></c:out></td>
					<td><c:out value="${courses.code}"></c:out></td>
					<td><c:out value="${courses.coursename}"></c:out></td>
					<td><c:out value="${courses.division}"></c:out></td>
					<td><c:out value="${courses.grade}"></c:out></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
<p> <a href="${pageContext.request.contextPath}/"> Home </a></p>
</body>
</html>