package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Customer;

public class CustomerService {
	private Map<String,Customer> customerMap;

	public CustomerService() {//생성자
		this.customerMap = new HashMap<String, Customer>();	//HashMap객체생성
		addCustomer(new Customer("id001","1"));//Customer객체 생성하여 HaspMap객체에 저장하는 부분
		addCustomer(new Customer("id002","2"));
		addCustomer(new Customer("id003","3"));
		addCustomer(new Customer("id004","4"));
		addCustomer(new Customer("id005","5"));
	}
	private void addCustomer(Customer customer) { //HashMap객체에  Customer객체 등록하는 메소드
		customerMap.put(customer.getId(),customer);
	}
		public Customer findCustomer(String id) { //사용자가 입력한 id에 해당하는 객체를 리턴하는 메소드
		if(id != null)
			return (customerMap.get(id.toLowerCase()));
		else
			return null;
	}
	public List<Customer> findAllCustomer(){ //모든 Customer객체를 ArrayList로 생성하여 리턴하는 메소드
		List<Customer> customerList = new ArrayList<Customer>(customerMap.values());
		
		return customerList;
	}	
}
