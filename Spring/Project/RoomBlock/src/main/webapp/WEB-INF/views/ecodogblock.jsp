<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MJart</title>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/resources/css/main.css">
<!--css 파일 적용하는 코드  -->

<script type="text/javascript">
function show(){
	  var elements = document.getElementsByClassName('reservation');
	  for (var i=0;i<elements.length;i+=1){
	    elements[i].style.display = 'block';
	  }
}

function hide(){
	  var elements = document.getElementsByClassName('reservation');
	  for (var i=0;i<elements.length;i+=1){
	    elements[i].style.display = 'none';
	  }
}
	

</script>
</head>
<body>
<h2>에코독애견펜션 방막기</h2>
	<form method="post"
		action="${pageContext.request.contextPath}/ecodogblockroom">
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
			 		<input type="radio" name="flowinDomain" value="떠나요" onclick="hide();">홈페이지/떠나요
        			<input type="radio" name="flowinDomain" value="온다" onclick="show();">온다
         			<input type="radio" name="flowinDomain" value="네이버" onclick="show();"> 네이버
        			<input type="radio" name="flowinDomain" value="호텔타임" onclick="show();"> 호텔타임
        			<input type="radio" name="flowinDomain" value="야놀자" onclick="show();"> 야놀자
        		</td>
			</tr>
			</table>
			
			<br>
			
			<div class="reservation">
			<table class="dtable">
			<tr>
				<th align="center">예약자명</th>
				<td><input type="text" name="reservationname"/><br>
			</tr>


			<tr>
				<th>연락처 <span style="color: Red;">*</span></th>
				<td><select id="phonenum_1" name="htel_c1">
						<option selected="selected" value="">선택</option>
						<option value="010">010</option>
						<option value="011">011</option>
						<option value="016">016</option>
						<option value="017">017</option>
						<option value="018">018</option>
						<option value="019">019</option>
						<option value="0502">0502</option>
						<option value="0503">0503</option>
						<option value="0504">0504</option>
						<option value="0507">0507</option>
				</select> - <input type="text" maxlength="4" size="4" id="phonenum_2" name="htel_c2"/> - <input
					type="text" maxlength="4" size="4" id="phonenum_3" name="htel_c3"/></td>
			</tr>

<!-- 			<tr>
				<th>유입경로:</th>
				<td><input type="text" name="flowinDomain"/><br>
			</tr> -->

			<tr>
				<th>메모</th>
				<td><textarea rows="4" cols="50" style="width: 70%;" name="memo"></textarea>
					<div>0/200 bytes (최대 한글 100자, 영문 200자)</div></td>
			</tr>

			<tr>
				<th>인원</th>
				<td>성인&nbsp; 
				<select style="width: 40px;" name="numberOfAdult">
						<option value="0">0</option>
						<option value="1">1</option>
						<option selected="selected" value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
				</select> 아동&nbsp; 
				<select style="width: 40px;" name="numberOfChild">
						<option selected="selected" value="0">0</option>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
				</select> 유아&nbsp;
				<select style="width: 40px;" name="numberOfBaby">
						<option selected="selected" value="0">0</option>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
				</select></td>
				</tr>
				</table>
				</div>
				
				<div class ="reservation" style="color: Red; margin-top: 5px; text-align: left; font-size: 11px;">
				입금대기 하시면 예약정보만 입력되고 대기 시간까지 입금완료 되지 않으면 자동취소 됩니다.</div>
				
				<div class="reservation">
				<table class="dtable">
				
					<tr>
						<th style="text-align: right;">
						<span style="color: Red;">*</span>
						예약상태</th>
						
						<td style="text-align:left;"><span>
						<input type="radio" name="appmRegState" value="0" checked="checked" name="RegState"/><label>입금대기</label>
						<input type="radio" name="appmRegState" value="1" name="RegState"/><label>예약(입금)완료</label></span></td>
					</tr>
					
					<tr>
						<th style="text-align: right;">예약자에게 계좌번호 문자전송</th>
						<td style="text-align: left;">
						<span> <input type="radio" name="is_banknosms" value="0" checked="checked" /><label>발송안함</label>
							   <input type="radio" name="is_banknosms" value="1"/><label>발송함</label>
						</span></td>
					</tr>
					
					<tr>
						<th style="text-align: right;">예약자에게 예약내역 문자전송</th>
						<td style="text-align: left;"><span>
						<input type="radio" name="is_sms" value="0" checked="checked"/><label>발송안함</label>
						<input type="radio" name="is_sms" value="1"/><label>발송함</label></span></td>
					</tr>
					
					<tr>
						<th style="text-align: right;">펜션주에게 예약내역 문자전송</th>
						<td style="text-align: left;"><span>
						<input type="radio" name="is_admin" value="0" checked="checked" /><label>발송안함</label>
						<input type="radio" name="is_admin" value="1"/><label>발송함</label></span></td>
					</tr>
				</table>

				</div>
				
				<br>
				
				<div class="reservation">
				총 결제액: <input type="text" name ="amt" class="amt" style="text-align: right; ime-mode: disabled;"/> 원
				</div>
				
				<input type="submit" style="font-weight: bold; color: Red; "value="방막기">
				</form>

</body>
</html>