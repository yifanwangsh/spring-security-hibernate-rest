package config.oauth2.resource;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter{
	
	private String RESOURCE_ID = "resourceServerId";
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.resourceId(RESOURCE_ID).stateless(false);
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.requestMatchers().antMatchers("/user/**").and().authorizeRequests()
		.antMatchers("/users/**").access("hasRole('ADMIN')");
	}
}	
