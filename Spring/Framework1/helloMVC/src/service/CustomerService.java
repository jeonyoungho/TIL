package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Customer;

public class CustomerService {
	
	private Map<String,Customer> customerMap;
	
	public CustomerService() {
		customerMap = new HashMap<String, Customer>();
		
		addCustomer(new Customer("id001","alice","alice.hansung.ac.kr"));
		addCustomer(new Customer("id002","bob","bob.hansung.ac.kr"));
		addCustomer(new Customer("id003","charile","charile.hansung.ac.kr"));
		addCustomer(new Customer("id004","daniel","daniel.hansung.ac.kr"));
		addCustomer(new Customer("id005","trudy","trudy.hansung.ac.kr"));
	}

	private void addCustomer(Customer customer) {
		
		customerMap.put(customer.getId(),customer);
		
	}
	
	public Customer findCustomer(String id) {
		if(id != null)
			return (customerMap.get(id.toLowerCase()));
		else
			return null;
		
	}
	
	public List<Customer> getAllCustomers(){
		List<Customer> customerList = new ArrayList<Customer>(customerMap.values());
		
		return customerList;
		
	}
	
	
}
