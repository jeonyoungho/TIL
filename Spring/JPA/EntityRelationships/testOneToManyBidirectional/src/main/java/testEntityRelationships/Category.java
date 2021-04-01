package testEntityRelationships;

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
	
	/*
	 * cascade type을 many쪽이든 one쪽이든 아무곳이나 지정해도 상관은 없지만 
	 *  인스턴스를 저장하거나 삭제할때는 parent 연산을 먼저 수행하므로 parent에 
	 *  두는게 보편적임
	 *  
	 */ 
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, 
			fetch = FetchType.LAZY)
	private Set<Product> products = new HashSet<Product>();
}
