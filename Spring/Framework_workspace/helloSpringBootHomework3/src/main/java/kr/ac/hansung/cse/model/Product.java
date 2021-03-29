package kr.ac.hansung.cse.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "productTable")
public class Product implements Serializable{
	
	private static final long serialVersionUID = -8629621332391968687L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="product_id")
	private int id;
	
	private String name;
	private String category;
	private int price;
	private String manufacturer;
	
	@Column(name = "unitInStock")
	private int unitInStock;
	private String description;
	
}
