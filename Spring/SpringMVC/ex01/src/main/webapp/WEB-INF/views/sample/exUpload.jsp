<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="/ex01/sample/exUploadPost" method="post"
		enctype="multipart/form-data">

		<div>
			<input type='file' name='files'>
		</div>
		<div>
			<input type='file' name='files'>
		</div>
		<div>
			<input type='file' name='files'>
		</div>
		<div>
			<input type='file' name='files'>
		</div>
		<div>
			<input type='file' name='files'>
		</div>
		<div>
			<input type='submit'>
		</div>
	</form>
</body>
</html>