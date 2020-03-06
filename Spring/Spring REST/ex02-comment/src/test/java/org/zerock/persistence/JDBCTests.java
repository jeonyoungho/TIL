package org.zerock.persistence;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

@Log4j
public class JDBCTests {
static{
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
}
	@Test
	public void testConnection() {
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/exam?characterEncoding=UTF-8&serverTimezone=Asia/Seoul","root","*dydgh1092");
			log.info(con);
		}catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
