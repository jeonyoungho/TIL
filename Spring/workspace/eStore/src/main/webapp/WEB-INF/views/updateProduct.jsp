<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<!-- container wrapper -->
<div class="container-wrapper">
	<div class="container">
		<h1 >Update Product</h1>
		<p class="lead">Fill the below information to update a product:</p>
		
		<sf:form action="${pageContext.request.contextPath}/admin/productInventory/updateProduct"
			method="post" modelAttribute="product">
		
		<!-- id를 꼭 보내줘야함 , 없을시 0으로 값이 넘어감 -->
		<sf:hidden path="id"/>
		
		<div class="form-group">
			<label for="name">Name</label>
			<sf:input path="name" id="name" class="form-control"/>
			<sf:errors path="name" cssStyle="color:#ff0000;"/>
		</div>
		
		<div class="form-group">
			<label for="category">Category: </label>
			<sf:radiobutton path="category" id="category" value="컴퓨터" /> 컴퓨터
			<sf:radiobutton path="category" id="category" value="가전" /> 가전
			<sf:radiobutton path="category" id="category" value="잡화" /> 잡화
		</div>
		
		<div class="form-group">
			<label for="description">Description</label>
			<sf:input path="description" id="description" class="form-control"/>
		</div>
		
		<div class="form-group">
			<label for="price">Price</label>
			<sf:input path="price" id="price" class="form-control"/>
			<sf:errors path="price" cssStyle="color:#ff0000;"/>
		</div>

				<div class="form-group">
			<label for="unitInStock">Unit In Stock</label>
			<sf:input path="unitInStock" id="unitInStock" class="form-control"/>
			<sf:errors path="unitInStock" cssStyle="color:#ff0000;"/>
		</div>

		<div class="form-group">
			<label for="manufacturer">Manufacturer</label>
			<sf:input path="manufacturer" id="manufacturer" class="form-control"/>
			<sf:errors path="manufacturer" cssStyle="color:#ff0000;"/>
		</div>

			<button type="submit" class="btn btn-primary">Submit</button>
			<a href="<c:url value="/admin/productInventory" />" class="btn btn-dark">Cancel</a>
		</sf:form>
		<br>		
	</div>
</div>
