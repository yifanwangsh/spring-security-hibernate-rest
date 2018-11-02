package config.oauth2.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;

public class AuthorizationServerConfiguration extends
		AuthorizationServerConfigurerAdapter {
	private String REALM = "oauth_realm";
	
	@Autowired
	TokenStore tokenStore;
	
	@Autowired
	UserApprovalHandler userApprovalHandler;
}
