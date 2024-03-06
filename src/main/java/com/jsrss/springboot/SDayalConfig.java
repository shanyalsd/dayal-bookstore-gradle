package com.jsrss.springboot;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@SpringBootApplication
public class SDayalConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

	@Autowired
	DataSource dataSource;
	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/myjsps/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		return resolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		String usersQuery = "select username, password, active from myusers where username=? and active=1";
		String rolesQuery = "select myusername, role from myroles where myusername=?";
		auth.jdbcAuthentication()
				.dataSource(dataSource)
				.usersByUsernameQuery(usersQuery)
				.authoritiesByUsernameQuery(rolesQuery);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/deleteBook**")
															.access("hasRole('ROLE_ADMIN')")
															.antMatchers("/addBook**")
															.access("hasAnyRole('ROLE_ADMIN','ROLE_STOREKEEPER')")
															.antMatchers("/editBook**")
															.access("hasAnyRole('ROLE_ADMIN','ROLE_STOREKEEPER')")
															.antMatchers("/placeOrder**")
															.access("hasAnyRole('ROLE_CUSTOMER')")
															.and()
															.formLogin()
															.loginPage("/login")
															.failureUrl("/login?error")
															.usernameParameter("myusername")
															.passwordParameter("mypassword")
															.and()
															.logout()
															.invalidateHttpSession(true)
															.logoutSuccessUrl("/logout")
															.and()
															.formLogin()
															.and()
															.exceptionHandling()
															.accessDeniedPage("/WEB-INF/myjsps/invalidAccess.jsp");
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
}