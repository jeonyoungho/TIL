package testHibernate;

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

@Entity // 이클래스는 DB테이블에 매핑된다
@Table(name="product") // 테이블 이름 지정
public class Product {
	
	// 키를 자동생성하기 위해 내부적으로 hibernate_sequence라는 테이블을 사용함
	@Id // 이 필드가 primary key로 사용된다
	@GeneratedValue // 자동으로 생성하라
	@Column(name="product_id")
	private int id;
	
	private String name;
	
	private int price;
	
	private String description;
	
	@ManyToOne // product가 category를 가리키고 있기 때문에 product 저장이나 삭제시 category도 자동으로 반영이 됨 
	@JoinColumn(name="category_id")
	private Category category;
}
