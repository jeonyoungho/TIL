package kr.ac.hansung.cse.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="product")
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="product_id", nullable = false, updatable = false)
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
	
	// hibernate = jpa + native
}
