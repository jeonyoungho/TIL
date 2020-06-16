package kr.ac.hansung.cse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hansung.cse.dao.ProductDao;
import kr.ac.hansung.cse.model.Product;

@Service
public class ProductService {
	@Autowired
	private ProductDao productDao;

	//R
	public Product getProductById(int id) {
		return productDao.getProductById(id);
	}
	
	public List<Product> getProducts(){
		return productDao.getProducts();
	}
	
	//C
	public void addProduct(Product product) {
		productDao.addProduct(product);
	}

	//D
	public void deleteProduct(Product product) {
		productDao.deleteProduct(product);
	}
	
	//U
	public void updateProduct(Product product) {
		productDao.updateProduct(product);
	}
	






}
