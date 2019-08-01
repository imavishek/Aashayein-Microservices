/**
 * @ProjectName: export-service
 * @PackageName: com.aashayein.export.configuration
 * @FileName: CustomAccessTokenConverter.java
 * @Author: Avishek Das
 * @CreatedDate: 01-08-2019
 * @Modified_By avishek.das @Last_On 01-Aug-2019 1:59:35 AM
 */

package com.aashayein.export.configuration;

import java.util.Map;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.stereotype.Component;

@Component
public class CustomAccessTokenConverter extends DefaultAccessTokenConverter {

	@Override
	public OAuth2Authentication extractAuthentication(Map<String, ?> claims) {
		OAuth2Authentication authentication = super.extractAuthentication(claims);
		authentication.setDetails(claims);

		return authentication;
	}
}