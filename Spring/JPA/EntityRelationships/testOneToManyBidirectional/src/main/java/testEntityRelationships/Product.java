package testEntityRelationships;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

@Entity // represent for entity
@Table(name="product") // represent for table name
public class Product {
	
	@Id // primary key
	@GeneratedValue // auto increment
	@Column(name="product_id") // column name
	private int id;
	
	private String name;
	
	private int price;
	
	private String description;
	
	@ManyToOne 
	@JoinColumn(name="category_id") // join colum 지정
	private Category category;
}
