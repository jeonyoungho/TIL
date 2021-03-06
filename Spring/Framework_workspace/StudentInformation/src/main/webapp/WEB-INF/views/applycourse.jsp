<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수강신청</title>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/resources/css/main.css">
<!--css 파일 적용하는 코드  -->

</head>
<body>
	<%-- course객체에 저장할 데이터를 입력 받는 폼태그 --%>
	<sf:form method="post" 
		action="${pageContext.request.contextPath}/doapply"
		modelAttribute="course">
		<%--  ${pageContext.request.contextPath} -->contextroot --%>
		<table class="formtable">

			<tr>
				<td class="label">수강연도: </td>
				<td class="value">${course.year}년</td>
			</tr>

			<tr>
				<td class="label">수강학기: </td>
				<td class="value">${course.semester}학기</td>
			</tr>
		
			<tr>
				<td class="label">교과코드: </td>
				<td class="value"><sf:input class="control" type="text" path="code" /><br>
				<sf:errors path="code" class="error"/> </td><!--code데이터부분에 의해 발생한에러를 출력하는 부분  -->
			</tr>
			
			<tr>
				<td class="label">과목명: </td>
				<td class="value"><sf:input class="control" type="text" path="coursename" /><br>
				<sf:errors path="coursename" class="error"/> </td><!--coursename부분에 의해 발생한에러를 출력하는 부분  -->
			</tr>
			
			<tr>
				<td class="label">분반: </td>
				<td class="value"><sf:input class="control" type="text" path="division" /><br>
				<sf:errors path="division" class="error"/> </td><!--division부분에 의해 발생한에러를 출력하는 부분  -->
			</tr>
			
			<tr>
				<td class="label">학점: </td>
				<td class="value"><sf:input class="control" type="text" path="grade"/><Br>
				<sf:errors path="grade" class="error"/></td><!--grade부분에 의해 발생한에러를 출력하는 부분  -->
			</tr>
			
			<tr>
				<td class="label"></td>
				<td class="value"><input class="control" type="submit" value="신청" /></td>
			</tr>
			
		</table>
	</sf:form>

</body>
</html>