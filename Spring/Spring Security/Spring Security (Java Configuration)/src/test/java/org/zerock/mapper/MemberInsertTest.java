package org.zerock.mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.mapper.MemberMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {org.zerock.config.RootConfig.class, org.zerock.config.SecurityConfig.class})
public class MemberInsertTest {
	@Autowired
	private PasswordEncoder pwencoder;

	@Autowired
	private MemberMapper mapper;

	@Autowired
	private DataSource ds;


	/*
	 * @Test public void testRead() { MemberVO vo = mapper.read("admin");
	 * 
	 * log.info(vo.toString());
	 * 
	 * System.out.println(vo.getUser_name());
	 * 
	 * vo.getAuthList().forEach(authVO -> log.info(authVO.toString())); }
	 */

	@Test
	public void testInsertMember() {
		String insertMemberSql = "insert into tbl_member(user_id, user_pw) values(?,?)";
		String insertAuthSql = "insert into tbl_member_auth(user_id, auth) values(?,?)";

		Connection con = null;
		PreparedStatement insertMemberPstmt = null;

		PreparedStatement insertAuthPstmt = null;

		try {

			con = ds.getConnection();
			insertMemberPstmt = con.prepareStatement(insertMemberSql);
			insertAuthPstmt = con.prepareStatement(insertAuthSql);

			insertMemberPstmt.setString(1, "admin");
			insertMemberPstmt.setString(2, pwencoder.encode("admin"));
			
			insertMemberPstmt.executeUpdate();

			insertAuthPstmt.setString(1, "admin");
			insertAuthPstmt.setString(2, "ROLE_ADMIN");

			insertAuthPstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (insertMemberPstmt != null) {
				try {
					insertMemberPstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (insertAuthPstmt != null) {
				try {
					insertMemberPstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}
	
}

