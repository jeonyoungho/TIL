package kr.ac.hansung.cse.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="product")
public class Product implements Serializable {
	
	private static final long serialVersionUID = -567117648902464025L;

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
	
	@Transient // DB테이블 저장시 얘는 빠지게 된다
	private MultipartFile productImage;
	
	private String imageFilename;
	
	// hibernate = jpa + native
}
