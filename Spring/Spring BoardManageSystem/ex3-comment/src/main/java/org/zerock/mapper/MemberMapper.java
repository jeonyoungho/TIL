package org.zerock.mapper;

import org.zerock.domain.AuthVO;
import org.zerock.domain.MemberVO;

public interface MemberMapper {
	
	public void insertMember(MemberVO member);
	
	public void insertAuth(AuthVO authVO);
	
	public MemberVO getMember(String userId);
	
	public void removeMember(String userId);
}
