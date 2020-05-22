package testHibernate;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

@Entity
@Table(name="category")
public class Category {

	@Id
	@GeneratedValue
	private int id;
	
	private String name;
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL,fetch = FetchType.EAGER) 
	private Set<Product> products = new HashSet<Product>(); 
	
	// maapedBy 실제로 Product의 category필드를 참조해서 이필드를 채우게 된다
	// 둘다 또는 어느쪽에 Cascade를 줘도 상관없다 , category를 가지고 작업을 하기에 product는 삭제
	// category를 저장 또는 삭제하면 products도 저장 또는 삭제
	
}
