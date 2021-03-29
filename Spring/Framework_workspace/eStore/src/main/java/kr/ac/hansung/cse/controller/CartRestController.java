package kr.ac.hansung.cse.controller;

import java.util.List;

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
		
		Cart cart = cartService.getCartById(1);  // temporary

		
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

		Cart cart = cartService.getCartById(1);  // temporary

		CartItem cartItem = cartItemService.getCartItemByProductId(cart.getId(), productId);
		cartItemService.removeCartItem(cartItem);

		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

	}

}
