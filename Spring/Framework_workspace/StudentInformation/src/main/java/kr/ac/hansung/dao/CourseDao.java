package kr.ac.hansung.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.ac.hansung.model.Course;

//component annotation -> spring에서 쭉 스캔해서 클래스를 bean으로 등록해주게 되있음 , 인자로id를 지정
@Repository
public class CourseDao {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);//dataSource를 인자로 한 JdbcTemplate객체를 생성	
	}
	
	public int getTakedRowCount() {
		
		String sqlStatement = "select count(*) from takedcourse";
		return jdbcTemplate.queryForObject(sqlStatement, Integer.class);
	}
	
	//query and return a single object , R
	public Course getTakedCourse(String name) {
		String sqlstatement = "select * from takedcourse where name=?"; //place holder
		
		return jdbcTemplate.queryForObject(sqlstatement, new Object[] {name},new RowMapper<Course>() { 
			//RowMapper인퍼페이스의 mapRow메소드 구현 ->anonymouseclass(익명 클래스)를 사용하여 코드를 간결하게 구현 
			//객체를 만드는 부분과 클래스를 선언하는 부분을 동시에 만든다
			//인스턴스를 한 번 사용하는 경우 사용

			@Override
			public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
				Course course = new Course();
				course.setYear(rs.getInt("year"));
				course.setSemester(rs.getInt("semester"));
				course.setCode(rs.getString("code"));
				course.setCoursename(rs.getString("coursename"));
				course.setDivision(rs.getString("division"));
				course.setGrade(rs.getInt("grade"));
				
				return course;
			}
			
		}); //첫번째인자는 query문  두번째인자는 placeholder 인자값 , 세번째인자는 RomMapper인터페이스 
	}
	
	//query and return a multiple object , R
	public List<Course> getTakedCourse() {
		String sqlstatement = "select * from takedcourse"; //place holder
		
		return jdbcTemplate.query(sqlstatement , new RowMapper<Course>() { //여러개의 객체를 조회하는 경우 qurey ,하나는 queryForObject
			@Override
			public Course mapRow(ResultSet rs, int rowNum) throws SQLException { //레코드갯수 만큼 mapRow메소드 호출됨
				
				Course course = new Course();
				
				course.setYear(rs.getInt("year"));
				course.setSemester(rs.getInt("semester"));
				course.setCode(rs.getString("code"));
				course.setCoursename(rs.getString("coursename"));
				course.setDivision(rs.getString("division"));
				course.setGrade(rs.getInt("grade"));
				
				return course;
			}
			
		}); //첫번째인자는 query문  두번째인자는 placeholder 인자값 , 세번째인자는 RomMapper인터페이스 
	}
	
	public List<Course> getApplyedCourse() {
		String sqlstatement = "select * from applyedcourse"; //place holder
		
		return jdbcTemplate.query(sqlstatement , new RowMapper<Course>() { //여러개의 객체를 조회하는 경우 qurey ,하나는 queryForObject
			@Override
			public Course mapRow(ResultSet rs, int rowNum) throws SQLException { //레코드갯수 만큼 mapRow메소드 호출됨
				
				Course course = new Course();
				
				course.setYear(rs.getInt("year"));
				course.setSemester(rs.getInt("semester"));
				course.setCode(rs.getString("code"));
				course.setCoursename(rs.getString("coursename"));
				course.setDivision(rs.getString("division"));
				course.setGrade(rs.getInt("grade"));
				
				return course;
			}
			
		}); //첫번째인자는 query문  두번째인자는 placeholder 인자값 , 세번째인자는 RomMapper인터페이스 
	}
	
	//insert record into table, C
	public boolean insert(Course course) {
		
		int year = course.getYear();
		int semester = course.getSemester();
		String code = course.getCode();
		String coursename = course.getCoursename();
		String division = course.getDivision();
		int grade = course.getGrade();
		
		String sqlStatement = "insert into applyedcourse (year, semester, code, coursename, division, grade) values(?,?,?,?,?,?)";
		return (jdbcTemplate.update(sqlStatement, 
				new Object[] {year, semester, code, coursename, division, grade}) == 1); //update된 레코드갯수가 리턴됨
	}
	
	//update record in table , U
	public boolean update(Course course) {
		
		int year = course.getYear();
		int semester = course.getSemester();
		String code = course.getCode();
		String coursename = course.getCoursename();
		String division = course.getDivision();
		int grade = course.getGrade();
		System.out.println(year);
		System.out.println(semester);
		String sqlStatement = "update applyedcourse set year=?, semester=?, coursename=?, division =?, grade=? where code=?";
		
		return (jdbcTemplate.update(sqlStatement, 
				new Object[] {year, semester, coursename, division, grade, code}) == 1); //update된 레코드갯수가 리턴됨
		

	}
	
	//delete record in table , D
	public boolean delete(String code) {
		
		String sqlStatement = "delete from applyedcourse where code=?";
		
		return (jdbcTemplate.update(sqlStatement, new Object[] {code}) == 1); //update된 레코드갯수가 리턴됨
	}
}
