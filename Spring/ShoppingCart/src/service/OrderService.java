package service;

import model.Order;

public class OrderService {
	Order order;
	
	public OrderService(String cardNum,String cardType,String price, String initial,String itemNum, String address,
			String firstName,String description,String lastName, String repeatCardNum) {
		if(initial.equals("")) {
			initial = "No Value";
		}
		
		order = new Order(cardNum,cardType,price,initial,itemNum,address,firstName,description,lastName,
				repeatCardNum);
	}
	
	public Order findOrder() {
		return order;
	}
}
