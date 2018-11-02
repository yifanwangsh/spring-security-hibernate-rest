package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AuthenticationEntryPoint restEndPoint;
	
	@Autowired
	private AuthenticationSuccessHandler successHandler;
	
	@Autowired
	private AuthenticationFailureHandler failureHandler;
	
	@Autowired
	private DriverManagerDataSource ds;
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        /*auth.inMemoryAuthentication()
          .withUser("admin").password(passwordEncoder().encode("admin"))
          .authorities("ADMIN");
        
        auth.inMemoryAuthentication().withUser("user").password(passwordEncoder().encode("password"))
          .authorities("USER");*/
		auth.jdbcAuthentication().dataSource(ds).usersByUsernameQuery(
				"SELECT username, password, enabled FROM auth.login WHERE username = ?").authoritiesByUsernameQuery(
				"SELECT username, authority FROM auth.authority WHERE username= ?").passwordEncoder(
						new BCryptPasswordEncoder()); 
    }
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.exceptionHandling()
			.authenticationEntryPoint(restEndPoint)
			.and()
			.authorizeRequests()
			.antMatchers("/api/foo").authenticated()
			.and()
			.formLogin()
			.successHandler(successHandler)
			.failureHandler(failureHandler)
			.and()
			.logout();
    }
 
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
