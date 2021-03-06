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
	<!-- 등록된 수강신청 내역을 출력하는 페이지-->
	<h2>수강 신청 현황</h2>
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

			<c:forEach var="courses" items="${applyedcourse}">
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
	
</body>
</html>