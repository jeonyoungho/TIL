var cartApp = angular.module('cartApp', []);

cartApp.controller("cartCtrl", function($scope, $http) {

	$scope.initCartId = function(cartId) {
		$scope.cartId = cartId;
		$scope.refreshCart();

	};

	$scope.refreshCart = function() {
		
		$http.get('/eStoreHomework2/api/cart/' + $scope.cartId).then(
				function successCallback(response) {
					//sync call-> 응답이 올 때까지 기다
					//async call ->call해버리고 기다리지 않고 바로리턴하고 나중에 응답이 왔을때 callback function이 수행하게 됨
					$scope.cart = response.data;
				});
	};

	$scope.clearCart = function() {
				
		$http({
			method : 'DELETE',
			url : '/eStoreHomework2/api/cart/' + $scope.cartId
		}).then(function successCallback() {
			//콜백function이 하나만있으면 무조건 success function이고
			//콜백function이 두개있으면 처음께 success callback function이고
			//두번째께 error callback function이다
			$scope.refreshCart();
		}, function errorCallback(response) {
			console.log(response.data);
		});

	};
	
	$scope.addToCart = function(productId) {
				
		$http.put('/eStoreHomework2/api/cart/cartItem/' + productId).then(
				function successCallback() {
					alert("Product successfully added to the cart!");

				}, function errorCallback() {
					alert("Adding to the cart failed!")
				});
	};

	$scope.removeFromCart = function(productId) {
		
		$http({
			method : 'DELETE',
			url : '/eStoreHomework2/api/cart/cartItem/' + productId
		}).then(function successCallback() {
			$scope.refreshCart();
		}, function errorCallback(response) {
			console.log(response.data);
		});
	};

	$scope.calGrandTotal = function() {
		var grandTotal = 0;

		for (var i = 0; i < $scope.cart.cartItems.length; i++) {
			grandTotal += $scope.cart.cartItems[i].totalPrice;
		}

		return grandTotal;
	};
	
	$scope.increaseQuantityFromCart = function(productId) {
		
		$http.put('/eStoreHomework2/api/cart/cartItem/increase/' + productId).then(
				function successCallback(res) {
					console.log(res);
					$scope.refreshCart();
				}, function errorCallback() {
					alert("해당 상품 재고수량을 초과하였습니다.")
				});
	};
	
	$scope.decreaseQuantityFromCart = function(productId) {
		
		$http({
			method : 'DELETE',
			url : '/eStoreHomework2/api/cart/cartItem/decrease/' + productId
		}).then(function successCallback() {
			$scope.refreshCart();
		}, function errorCallback(response) {
			alert("Decreasing to the quantity failed!")
		});
	};
	
});