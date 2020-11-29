package kr.ac.hansung.cse.controller;

import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.hansung.cse.model.Cart;
import kr.ac.hansung.cse.model.CartItem;
import kr.ac.hansung.cse.model.Product;
import kr.ac.hansung.cse.service.CartItemService;
import kr.ac.hansung.cse.service.CartService;
import kr.ac.hansung.cse.service.ProductService;

@RestController // @Controller + @ResponseBody
@RequestMapping("/api/cart")
public class CartRestController {

	@Autowired
	private CartService cartService;

	@Autowired
	private CartItemService cartItemService;

	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/{cartId}", method = RequestMethod.GET)
	public ResponseEntity<Cart> getCartById(@PathVariable(value = "cartId") int cartId) {

		Cart cart = cartService.getCartById(cartId);
		return new ResponseEntity<Cart>(cart, HttpStatus.OK);

	}

	@RequestMapping(value = "/{cartId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> clearCart(@PathVariable(value = "cartId") int cartId) {

		Cart cart = cartService.getCartById(cartId);
		cartItemService.removeAllCartItems(cart);

		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

	}

	@RequestMapping(value = "/cartItem/{productId}", method = RequestMethod.PUT)
	public ResponseEntity<Void> addItem(@PathVariable(value = "productId") int productId) {

		Product product = productService.getProductById(productId);

		Cart cart = cartService.getCartById(1); // temporary

		List<CartItem> cartItems = cart.getCartItems();

		// check if cartitem for a given product already exists
		for (int i = 0; i < cartItems.size(); i++) {
			if (product.getId() == cartItems.get(i).getProduct().getId()) {
				CartItem cartItem = cartItems.get(i);
				cartItem.setQuantity(cartItem.getQuantity() + 1);
				cartItem.setTotalPrice(product.getPrice() * cartItem.getQuantity());

				cartItemService.addCartItem(cartItem);

				return new ResponseEntity<Void>(HttpStatus.OK);
			}
		}

		// create new cartItem
		CartItem cartItem = new CartItem();
		cartItem.setQuantity(1);
		cartItem.setTotalPrice(product.getPrice() * cartItem.getQuantity());

		cartItem.setProduct(product);
		cartItem.setCart(cart);

		cartItemService.addCartItem(cartItem);

		// bidirectional
		cart.getCartItems().add(cartItem);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "/cartItem/{productId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> removeItem(@PathVariable(value = "productId") int productId) {

		Cart cart = cartService.getCartById(1); // temporary

		CartItem cartItem = cartItemService.getCartItemByProductId(cart.getId(), productId);
		cartItemService.removeCartItem(cartItem);

		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

	}

	@RequestMapping(value = "/cartItem/increase/{productId}", method = RequestMethod.PUT)
	public ResponseEntity<Void> increaseQuantityFromCart(@PathVariable(value = "productId") int productId,
			HttpServletRequest request) {
		
		System.out.println("Request Message");
		Enumeration<String> requestHeaderNames = request.getHeaderNames();
		while (requestHeaderNames.hasMoreElements()) {
			String name = (String) requestHeaderNames.nextElement();
			String value = request.getHeader(name);
			System.out.println(name + " : " + value + "<br>");
		}

		Product product = productService.getProductById(productId);

		Cart cart = cartService.getCartById(1); // temporary

		List<CartItem> cartItems = cart.getCartItems();

		// check if cartitem for a given product already exists
		for (int i = 0; i < cartItems.size(); i++) {
			if (product.getId() == cartItems.get(i).getProduct().getId()) {

				CartItem cartItem = cartItems.get(i);
				if (cartItem.getQuantity() + 1 <= product.getUnitInStock()) { // 상품재고수량 보다 적은 경우
					cartItem.setQuantity(cartItem.getQuantity() + 1);
				} else { // 상품재고수량을 초과한 경우
					return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
				}

				cartItem.setTotalPrice(product.getPrice() * cartItem.getQuantity());

				cartItemService.addCartItem(cartItem);
			}
		}
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "/cartItem/decrease/{productId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> decreaseQuantityFromCart(@PathVariable(value = "productId") int productId) {

		Cart cart = cartService.getCartById(1); // temporary

		CartItem cartItem = cartItemService.getCartItemByProductId(cart.getId(), productId);

		if (cartItem.getQuantity() == 1) { // 상품 현재 수량이 1개일 경우 해당 상품을 카트에서 삭제
			cartItemService.removeCartItem(cartItem);
		} else { // 1개가 아닐 경우 해당 상품의 수량을 1감소하여 갱신
			int currentQuantity = cartItem.getQuantity();
			cartItem.setQuantity(currentQuantity - 1);
			cartItem.setTotalPrice(cartItem.getProduct().getPrice() * cartItem.getQuantity());

			cartItemService.addCartItem(cartItem);
		}

		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

	}

}
