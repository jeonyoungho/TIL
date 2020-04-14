package kr.ac.hansung.cse.model;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Product {
	
	private int id;
	
	@NotEmpty(message="The product Name must not be null")
	private String name;
	
	private String category;
	
	@Min(value=0, message="The product rice must not be lsee than zero")
	private int price;
	
	@NotEmpty(message="The product Manufacturer must not be null")
	private String manufacturer;
	
	@Min(value=0, message="The product unitInStock must not be lsee than zero")
	private int unitInStock;
	
	private String description;
}
