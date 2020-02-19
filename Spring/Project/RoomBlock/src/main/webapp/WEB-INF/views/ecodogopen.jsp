<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
<!DOCTYPE html>
<html>
<head>
<title>MJart</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/resources/css/main.css">
<!--css 파일 적용하는 코드  -->

</head>
<body>
<h2>에코독애견펜션 방풀기</h2>
<form method="post"
		action="${pageContext.request.contextPath}/ecodogopenroom">
		<%--  ${pageContext.request.contextPath} -->contextroot --%>
		<table class="dtable">
			<tr>
				<th>객실/기간 <span style="color: Red;">*</span></th>
				<td>
				<select id="room" name="roomname">
						<option selected="selected" value="">선택</option>
						<option value="301호">301호</option>
						<option value="302호">302호</option>
						<option value="303호">303호</option>
						<option value="601호">601호</option>
						<option value="602호">602호</option>
						<option value="701호">701호</option>
						<option value="702호">702호</option>
						<option value="801호">801호</option>
						<option value="802호">802호</option>
						<option value="803호">803호</option>
				</select>
				&nbsp; 
				<select style="width: 50px;" name="lengthOfStay">
						<option selected="selected" value="1">1박</option>
						<option value="2">2박</option>
						<option value="3">3박</option>
						<option value="4">4박</option>
						<option value="5">5박</option>
						<option value="6">6박</option>
						<option value="7">7박</option>
						<option value="8">8박</option>
						<option value="9">9박</option>
						<option value="10">10박</option>
						<option value="11">11박</option>
						<option value="12">12박</option>
						<option value="13">13박</option>
						<option value="14">14박</option>
						<option value="15">15박</option>
				</select>
				</td>
			<tr>
				<th>일자 <span style="color: Red;">*</span></th>
				<td>
				<select name="year">
						<option selected="selected" value="">선택</option>
						<option value="2020">2020</option>
						<option value="2021">2021</option>
						<option value="2022">2022</option>
						<option value="2023">2023</option>
						<option value="2024">2024</option>
						<option value="2025">2025</option>
				</select> 년 <input type="text" maxlength="4" size="4" name="month"/> 월 <input
					type="text" maxlength="4" size="4" name="day" /> 일</td>
			</tr>

			<tr>
				<th>예약사이트</th>
			 	<td>
			 		<input type="radio" name="flowinDomain" value="떠나요">홈페이지/떠나요
        			<input type="radio" name="flowinDomain" value="온다">온다
         			<input type="radio" name="flowinDomain" value="네이버"> 네이버
        			<input type="radio" name="flowinDomain" value="호텔타임"> 호텔타임
        			<input type="radio" name="flowinDomain" value="야놀자"> 야놀자
        		</td>
			</tr>
			
			</table>
			
			<br>
			<br>
			
			<input type="submit" style="font-weight: bold; color: Red; "value="방풀기">
			
			</form>
</body>
</html>