package com.example.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.model.Product;

@Repository
@Transactional // 모든 메서드들이 트랜잭션으로 수행되게 되어있음, begin transaction commit이 자동적으로 수행 됨 (AOP기능에 의해서)
public class ProductDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	//R
	public Product getProductById(int id) {
	
		Session session = sessionFactory.getCurrentSession();
		Product product = (Product) session.get(Product.class, id);
		return product;
	
	}
	
	public List<Product> getProducts(){
	
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Product";
		
		Query<Product> query = session.createQuery(hql, Product.class);
		List<Product> productList = query.getResultList();
		
		return productList;
	
	}
	
	//C
	public void addProduct(Product product) {
	
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(product);
		session.flush(); //꼭 해야하는건 아니다, flush mode를 설정하지 않은 디폹르 모드에서 commit이 수행될 때 자동적으로 flush가 이뤄진다 
	
	}
	
	//D
	public void deleteProduct(Product product) {
	
		Session session = sessionFactory.getCurrentSession();
		session.delete(product);
		session.flush();
	
	}
	
	//U
	public void updateProduct(Product product) {
	
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(product);
		session.flush();
	
	}
}

