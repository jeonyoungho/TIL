<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MJart</title>
</head>
<body>
		<h2>Result</h2>
		<table class="dtable">
			<tr>
				<th>일자 <span style="color: Red;">*</span></th>
				<td>
				<c:out value="${ri.year}"></c:out>년
				<c:out value="${ri.month}"></c:out>월
				<c:out value="${ri.day}"></c:out>일
				&nbsp;<c:out value="${ri.lengthOfStay}"></c:out>박
				</td>
			</tr>
			
			<tr>
				<th>객실 <span style="color: Red;">*</span></th>
				<td>
					<c:out value="${ri.roomname}"></c:out>
				</td>
			</tr>

			<c:if test="${!empty ddnayoResult}">
					<tr>
						<th>떠나요</th>
						<c:choose>
						
						<c:when test="${ddnayoResult == 1}">
								<td>성공</td>
						</c:when>
							
						<c:otherwise>
								<td>실패</td>
						</c:otherwise>
						
						</c:choose>
					</tr>
			</c:if>
			
			<c:if test="${!empty ondaResult}">
					<tr>
						<th>온다</th>
						<c:choose>
						
						<c:when test="${ondaResult == 1}">
								<td>성공</td>
						</c:when>
							
						<c:otherwise>
								<td>실패</td>
						</c:otherwise>

						</c:choose>
					</tr>
			</c:if>
			
			<c:if test="${!empty naverResult}">
					<tr>
						<th>네이버</th>
						<c:choose>
						
						<c:when test="${naverResult == 1}">
								<td>성공</td>
						</c:when>
						
						<c:otherwise>
								<td>실패</td>
						</c:otherwise>

						</c:choose>
					</tr>
			</c:if>
			
			<c:if test="${!empty hoteltimeResult}">
					<tr>
						<th>호텔타임</th>
						<c:choose>
						
						<c:when test="${hoteltimeResult == 1}">
								<td>성공</td>
						</c:when>
							
						<c:otherwise>
								<td>실패</td>
						</c:otherwise>

						</c:choose>
					</tr>
			</c:if>
			
			<c:if test="${!empty yanoljaResult}">
					<tr>
						<th>야놀자</th>
						<c:choose>
						<c:when test="${yanoljaResult == 1}">
								<td>성공</td>
						</c:when>
						
						<c:otherwise>
								<td>실패</td>
						</c:otherwise>

						</c:choose>
					</tr>
			</c:if>
				
			</table>

<%-- 	<c:if test="${!empty ddnayoResult}">
	떠나요 <c:out value="${ddnayoResult}"></c:out> <br>
	</c:if>
	
		<c:if test="${!empty ondaResult}">
		온다 <c:out value="${ondaResult}"></c:out> <br>
	</c:if>
	
		<c:if test="${!empty naverResult}">
		네이버 <c:out value="${naverResult}"></c:out> <br>
	</c:if>

	<c:if test="${!empty hoteltimeResult}">
		호텔타임 <c:out value="${hoteltimeResult}"></c:out> <br>
	</c:if>
	
	<c:if test="${!empty yanoljaResult}">
		야놀자 <c:out value="${yanoljaResult}"></c:out> <br>
	</c:if> --%>
	
	<br>
	
	<a href="${pageContext.request.contextPath}/ecodog">
		<c:out value="${pension.pensionName}"></c:out>
	</a>
	
</body>
</html>