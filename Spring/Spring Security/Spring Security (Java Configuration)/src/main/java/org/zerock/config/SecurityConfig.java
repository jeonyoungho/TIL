package org.zerock.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.zerock.security.CustomLoginSuccessHandler;
import org.zerock.security.CustomUserDetailsService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;


@Configuration
@EnableWebSecurity
@Log4j
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Setter(onMethod_ = {@Autowired})
	private DataSource dataSource;
	
	// config memory authentication
	/*@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		log.info("configure..................................");
		auth.inMemoryAuthentication().withUser("admin").password("{noop}admin").roles("ADMIN");
		auth.inMemoryAuthentication().withUser("member").password("$2a$10$pRLhy0EyMWq44x/zKWYUpex7zvlVuEg.Kx/aa0b/N4T.IzqzybmtK").roles("MEMBER");
	}*/
	
	// config database authentication
	/*@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		log.info("configure JDBC..................................");
		
		String queryUser = "select user_id, user_pw, enabled from tbl_member where user_id=?";
		String queryDetails = "select user_id, auth from tbl_member_auth where user_id=?";
		
		auth.jdbcAuthentication().
			dataSource(dataSource).
			passwordEncoder(passwordEncoder()).
			usersByUsernameQuery(queryUser).
			authoritiesByUsernameQuery(queryDetails);
	}*/
	
	@Bean
	public UserDetailsService customUserService() {
		return new CustomUserDetailsService();
	}
	
	// in custom userdetails
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		log.info("configure JDBC..................................");
		auth.userDetailsService(customUserService()).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public AuthenticationSuccessHandler loginSuccessHandler() {
		return new CustomLoginSuccessHandler();
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()
//			.antMatchers("/sample/all").permitAll()
//			.antMatchers("/sample/admin").access("hasRole('ROLE_ADMIN')")
			.antMatchers("/").access("hasRole('ROLE_MEMBER')");
		
		http.formLogin().loginPage("/customLogin").loginProcessingUrl("/login").successHandler(loginSuccessHandler());
	
		http.logout().logoutUrl("/customLogout").invalidateHttpSession(true).deleteCookies("remember-me", "JSESSION_ID");
		
		http.rememberMe().key("zerock").tokenRepository(persistentTokenRepository()).tokenValiditySeconds(604800);
	}
	
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
		repo.setDataSource(dataSource);
		return repo;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

}
