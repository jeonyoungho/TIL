package org.zerock.domain;

import java.util.List;

import lombok.Data;

@Data
public class MemberVO {
	
	private String userId;
	private String userPw;
	private boolean enabled;
	
	private List<AuthVO> authList;
}
