<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>form page</title>
</head>
<body style="background-color:#FFF8DC">
	<h3 style="text-align:center">A Sample Form</h3>
	<form action ="doOrder" method="get">
		Item Number: <input type="text" name="itemNum"/><br>
		Description: <input type="text" name="description"/><br>
		Price Each: <input type="text" name="price"/><br>
		<hr>
		First Name: <input type="text" name="firstName"/><br>
		Last Name: <input type="text" name="lastName"/><br>
		Middle Initial: <input type="text" name="initial"/><br>
		Shipping Address: <textArea rows = "3" cols="40" name="address"></textArea><br>
		Credit Card:<br>
		&nbsp;&nbsp; <input type="radio" name="cardType" value="Visa">Visa<br>
		&nbsp;&nbsp; <input type="radio" name="cardType" value="MasterCard">MasterCard<br>
		&nbsp;&nbsp; <input type="radio" name="cardType" value="American Express">American Express<br>
		&nbsp;&nbsp; <input type="radio" name="cardType" value="Discover">Discover<br>
		&nbsp;&nbsp; <input type="radio" name="cardType" value="Java SmartCard">Java SmartCard<br>
				
		Credit Card Number: <input type="password" name="cardNum"/><br>
		Repeat Credit Card Number: <input type="password" name="repeatCardNum"/><br>		
		<input type="submit" value="Submit Order" style="position:absolute; right:40%"><br>
	
	</form>
</body>
</html>