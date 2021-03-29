<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script src="<c:url value="/resources/js/controller.js" /> "></script>

<div class="container-wrapper">
	<div class="container">
	
		<div class="jumbotron"> <!-- 전광판 같은 내용 -->
			<div class="container">
				<h2>Cart</h2>

				<p>All the selected products in your shopping cart</p>
			</div>
		</div>
		
            
		<section class="container" ng-app="cartApp">
			<div ng-controller="cartCtrl" ng-init="initCartId('${cartId}')">
				<a class="btn btn-warning pull-left" ng-click="clearCart()">
						<i class="fas fa-trash"></i> Clear Cart
				</a> 			

				<br/>
				
				<table  class="table table-hover" style="border-spacing: 5px 10px;">
					<tr>
						<th>Product</th>
						<th>Unit Price</th>
						<th>Quantity</th>
						<th>Total Price</th>
						<th>Action</th>
					</tr>
					
					<tr ng-repeat="item in cart.cartItems">
						<td>{{item.product.name}}</td>
						<td>{{item.product.price}}</td>
						<td>{{item.quantity}}</td>
						<td>{{item.totalPrice}}</td>
						<td style="padding-left:0px; padding-right:0px;">
							
							<a style="padding: 0.5 0.5 0.5 0.5; color: white;"class="btn btn-danger btn-sm"
							 ng-click="removeFromCart(item.product.id)"> 
							<i class="fas fa-times" ></i>remove
							</a>
							
							<a style="padding: 0.5 0.5 0.5 0.5; color: white;" class="btn btn-danger btn-sm"
							 ng-click="increaseQuantityFromCart(item.product.id)"> 
							<i class="fas fa-plus"></i>plus
							</a>
							
							<a style="padding: 0.5 0.5 0.5 0.5; color: white;" class="btn btn-danger btn-sm"
							 ng-click="decreaseQuantityFromCart(item.product.id)"> 
							<i class="fas fa-minus"></i>minus 
							</a>
								
						</td>
					</tr>
					
					<tr>
						<td></td>
						<td></td>
						<td>Grand Total</td>
						<td>{{calGrandTotal()}}</td>
						<td></td>
					</tr>
				</table>

				<a class="btn btn-info" href="<c:url value="/products" />" class="btn btn-default">Continue	Shopping</a>
			</div>
		</section>

	</div>
</div>