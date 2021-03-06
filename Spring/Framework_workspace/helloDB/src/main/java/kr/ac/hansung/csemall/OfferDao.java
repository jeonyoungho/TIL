package kr.ac.hansung.csemall;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

//component annotation -> spring에서 쭉 스캔해서 클래스를 bean으로 등록해주게 되있음 , 인자로id를 지정
@Component("offerDao")
public class OfferDao {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);//dataSource를 인자로 한 JdbcTemplate객체를 생성	
	}
	
	public int getRowCount() {
		
		String sqlStatement = "select count(*) from offers";
		return jdbcTemplate.queryForObject(sqlStatement, Integer.class);
	}
	
	//query and return a single object , R
	public Offer getOffer(String name) {
		String sqlstatement = "select * from offers where name=?"; //place holder
		
		return jdbcTemplate.queryForObject(sqlstatement, new Object[] {name},new RowMapper<Offer>() { 
			//RowMapper인퍼페이스의 mapRow메소드 구현 ->anonymouseclass(익명 클래스)를 사용하여 코드를 간결하게 구현 
			//객체를 만드는 부분과 클래스를 선언하는 부분을 동시에 만든다
			//인스턴스를 한 번 사용하는 경우 사용

			@Override
			public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
				Offer offer = new Offer();
				offer.setId(rs.getInt("id"));
				offer.setName(rs.getString("name"));
				offer.setEmail(rs.getString("email"));
				offer.setText(rs.getString("text"));
				
				return offer;
			}
			
		}); //첫번째인자는 query문  두번째인자는 placeholder 인자값 , 세번째인자는 RomMapper인터페이스 
	}
	
	//query and return a multiple object , R
	public List<Offer> getOffers() {
		String sqlstatement = "select * from offers"; //place holder
		
		return jdbcTemplate.query(sqlstatement , new RowMapper<Offer>() { //여러개의 객체를 조회하는 경우 qurey ,하나는 queryForObject
			@Override
			public Offer mapRow(ResultSet rs, int rowNum) throws SQLException { //레코드갯수 만큼 mapRow메소드 호출됨
				
				
				Offer offer = new Offer();
				offer.setId(rs.getInt("id"));
				offer.setName(rs.getString("name"));
				offer.setEmail(rs.getString("email"));
				offer.setText(rs.getString("text"));
				
				return offer;
			}
			
		}); //첫번째인자는 query문  두번째인자는 placeholder 인자값 , 세번째인자는 RomMapper인터페이스 
	}
	
	//C
	public boolean insert(Offer offer) {
		
		String name = offer.getName();
		String email = offer.getEmail();
		String text = offer.getText();
		
		String sqlStatement = "insert into offers (name,email,text) values(?,?,?)";
		return (jdbcTemplate.update(sqlStatement, new Object[] {name,email,text}) == 1); //update된 레코드갯수가 리턴됨
		
	}
	
	//U
	public boolean update(Offer offer) {
		
		int id = offer.getId();
		
		String name = offer.getName();
		String email = offer.getEmail();
		String text = offer.getText();
		
		String sqlStatement = "update offers set name=?, email=?, text=? where id=?";
		return (jdbcTemplate.update(sqlStatement, new Object[] {name,email,text,id}) == 1); //update된 레코드갯수가 리턴됨
		
	}
	
	public boolean delete(int id) {
		
		String sqlStatement = "delete from offers where id=?";
		
		return (jdbcTemplate.update(sqlStatement, new Object[] {id}) == 1); //update된 레코드갯수가 리턴됨
	}
}
