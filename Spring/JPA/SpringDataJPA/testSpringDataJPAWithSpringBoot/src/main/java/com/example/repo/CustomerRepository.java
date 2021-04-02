package com.example.repo;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.example.model.Customer;

//model에 있는 클래스와 primay key(id)의 타입을 인자로 넣어서 CrudRepository를 상속 받으됨
public interface CustomerRepository extends CrudRepository<Customer, Long> { 
	List<Customer> findByAge(int age);
}