package org.zerock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.zerock.domain.AuthVO;
import org.zerock.domain.MemberVO;
import org.zerock.mapper.MemberMapper;

@Service
public class MemberService {
	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private PasswordEncoder pwencoder;
	
	
	public void insertMember(MemberVO memberVO) {
		String userPw = memberVO.getUserPw();
		memberVO.setUserPw(pwencoder.encode(userPw));
		memberMapper.insertMember(memberVO);
		
		AuthVO authVO = new AuthVO();
		authVO.setUserId(memberVO.getUserId());
		authVO.setAuth("ROLE_USER");
		
		memberMapper.insertAuth(authVO);
	}
	
	public MemberVO getMember(String userId) {
		return memberMapper.getMember(userId);
	}
	
	public void removeMember(String userId) {
		memberMapper.removeMember(userId);
	}
}
