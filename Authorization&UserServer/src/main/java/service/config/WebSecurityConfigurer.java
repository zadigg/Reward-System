package service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import service.controller.UserDetailController;
import service.domain.User;
import service.repository.UserDetailRepository;
import service.service.UserDetailService;

import java.util.List;
import java.util.Optional;

@Configuration
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	@Bean
	public UserDetailsService userDetailsServiceBean() throws Exception {
		return super.userDetailsServiceBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.inMemoryAuthentication()
		  .withUser("admin1").password("{noop}password1").roles("ADMIN")
		  .and()
		  .withUser("teacher1").password("{noop}password1").roles("TEACHER")
				.and().withUser("student1").password("{noop}password1").roles("STUDENT");
	}


}
