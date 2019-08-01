/**
 * @ProjectName: oauth-server
 * @PackageName: com.aashayein.oauth.configuration
 * @FileName: AuthServerConfiguration.java
 * @Author: Avishek Das
 * @CreatedDate: 24-07-2019
 * @Modified_By avishek.das @Last_On 24-Jul-2019 12:32:34 AM
 */

package com.aashayein.oauth.configuration;

import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpointAuthenticationFilter;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.aashayein.oauth.exception.CustomWebResponseExceptionTranslator;

@Configuration
@EnableTransactionManagement
public class AuthServerConfiguration extends AuthorizationServerConfigurerAdapter {

	@Value("${check-user-scopes}")
	private Boolean checkUserScopes;

	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationEntryPoint authenticationEntryPoint;

	@Autowired
	private AccessDeniedHandler accessDeniedHandler;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private ClientDetailsService clientDetailsService;

	/*
	 * Spring Security OAuth exposes two endpoints for checking tokens
	 * (/oauth/check_token and /oauth/token_key). Those endpoints are not exposed by
	 * default (have access "denyAll()").
	 */
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {

		/*
		 * we're allowing access to the token only for clients with
		 * 'ROLE_TRUSTED_CLIENT' authority
		 */

		// security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
		
		security.tokenKeyAccess("isAnonymous() || hasAuthority('ROLE_TRUSTED_CLIENT')")
				.checkTokenAccess("hasAuthority('ROLE_TRUSTED_CLIENT')").accessDeniedHandler(accessDeniedHandler);
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.jdbc(dataSource).passwordEncoder(passwordEncoder);
	}

	/*
	 * Configure the properties and enhanced functionality of the Authorization
	 * Server endpoints.
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		final TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), accessTokenConverter()));

		endpoints.tokenStore(tokenStore()).tokenEnhancer(tokenEnhancerChain)
				.authenticationManager(authenticationManager).userDetailsService(userDetailsService)
				.exceptionTranslator(oauth2ResponseExceptionTranslator());

		if (checkUserScopes)
			endpoints.requestFactory(requestFactory());

//		endpoints.exceptionTranslator(exception -> {
//			if (exception instanceof OAuth2Exception) {
//				OAuth2Exception oAuth2Exception = (OAuth2Exception) exception;
//				return ResponseEntity.status(oAuth2Exception.getHttpErrorCode())
//						.body(new CustomOauthException(oAuth2Exception.getMessage()));
//			} else {
//				throw exception;
//			}
//		});

	}

	@Bean
	public WebResponseExceptionTranslator<OAuth2Exception> oauth2ResponseExceptionTranslator() {
		return new CustomWebResponseExceptionTranslator();
	}

	@Bean
	@Primary
	public DefaultTokenServices tokenServices() {
		final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
		defaultTokenServices.setTokenStore(tokenStore());
		defaultTokenServices.setSupportRefreshToken(true);

		return defaultTokenServices;
	}

	@Bean
	public OAuth2RequestFactory requestFactory() {
		CustomOauth2RequestFactory requestFactory = new CustomOauth2RequestFactory(clientDetailsService);
		requestFactory.setCheckUserScopes(true);

		return requestFactory;
	}

	@Bean
	public TokenEndpointAuthenticationFilter tokenEndpointAuthenticationFilter() {
		TokenEndpointAuthenticationFilter authenticationFilter = new TokenEndpointAuthenticationFilter(
				authenticationManager, requestFactory());
		authenticationFilter.setAuthenticationEntryPoint(authenticationEntryPoint);

		return authenticationFilter;
	}

	@Bean
	public TokenStore tokenStore() {

		/* use the JdbcTokenStore to store tokens */

		// return new JdbcTokenStore(dataSource);

		/* use the JwtTokenStore instead of JdbcTokenStore */

		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();

		/* for production, it is recommended to use public/private key pair */

		KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("aashayein.jks"),
				"aashayein".toCharArray());
		converter.setKeyPair(keyStoreKeyFactory.getKeyPair("aashayein"));

		/* for the simple demo purpose, used the secret for signing. */

		// converter.setSigningKey("123");

		return converter;
	}

	@Bean
	public TokenEnhancer tokenEnhancer() {
		return new CustomTokenEnhancer();
	}

}
