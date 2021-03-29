package kr.ac.hansung.cse.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import kr.ac.hansung.cse.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> { // model에 있는 클래스와 id의 타입을 인자로 넣어서 CrudRepository를 상속 받으됨
	List<Customer> findByAge(int age);
}
