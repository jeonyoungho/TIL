package org.zerock.security.domain;

import java.util.Date;
import java.util.List;
import org.zerock.security.domain.AuthVO;

import lombok.Data;

@Data
public class MemberVO {
	
	private String userId;
	private String userPw;
	private boolean enabled;
	
	private List<AuthVO> authList;
}
