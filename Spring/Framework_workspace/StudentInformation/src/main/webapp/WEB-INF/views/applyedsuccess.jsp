<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/css/course.css">

<body> 
<!-- 성공적으로 DB에 저장된 Course객체에 대한 정보를 출력하는 페이지 -->
<h2>성공적으로 과목을 등록하였습니다!!</h2>
<p>수강연도 : ${course.year}<br>
<p>수강학기 : ${course.semester}<br>
<p>과목코드 : ${course.code}<br>
<p>과목명    : ${course.coursename}<br>
<p>분반      : ${course.division}<br>
<p>학점      : ${course.grade}<br>

<p> <a href="${pageContext.request.contextPath}/"> Home </a></p>
</body>
</html>