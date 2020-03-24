package kr.ac.hansung.cse.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.ac.hansung.cse.model.Product;

@Repository
public class ProductDao {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);//dataSource를 인자로 한 JdbcTemplate객체를 생성	
	}
	
	public List<Product> getProducts(){
		String sqlStatement = "select * from product";
		
		return jdbcTemplate.query(sqlStatement,new RowMapper<Product>(){ //anonymous class,레코드를 객체로 매핑해주는 부분
				@Override
				public Product mapRow(ResultSet rs, int rowNum) throws SQLException {//RowMapper인터페이스의 구현해야될 메서드
					Product product = new Product();
					
					product.setId(rs.getInt("id"));
					product.setName(rs.getString("name"));
					product.setCategory(rs.getString("category"));
					product.setPrice(rs.getInt("price"));
					product.setManufacturer(rs.getString("menufacturer"));
					product.setUnitInStock(rs.getInt("unitInStock"));
					product.setDescription(rs.getString("description"));
					
					return product;
				}
		});
	}
	
	
	
	
}
