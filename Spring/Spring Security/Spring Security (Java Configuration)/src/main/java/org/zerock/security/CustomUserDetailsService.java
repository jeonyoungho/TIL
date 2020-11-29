package org.zerock.security;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.zerock.mapper.MemberMapper;
import org.zerock.security.domain.CustomUser;
import org.zerock.security.domain.MemberVO;


public class CustomUserDetailsService implements UserDetailsService {

	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(CustomUserDetailsService.class);
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		System.out.println("Load User By UserName : " + userName);
		
		//userName means userid
		MemberVO vo = memberMapper.getMember(userName);
		
		System.out.println("required by member mapper: " + vo);
		
		return vo == null ? null : new CustomUser(vo);
	}

}
