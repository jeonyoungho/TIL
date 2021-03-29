<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- container wrapper -->
<div class="container-wrapper">
	<div class="container">
		<h2>Administrator Page</h2>
		<p>Product를 관리 할 수 있는 페이지입니다.</p>


	</div>

	<div class="container">
		<h2><a href="<c:url value="/admin/productInventory"/>"> Product Inventory</a></h2>
		<p>Product 재고 현황을 조회, 수정할 수 있습니다.</p>
	</div>
	
	
	
	
</div>
