/**
 * @ProjectName: oauth-server
 * @PackageName: com.aashayein.oauth.configuration
 * @FileName: CustomTokenEnhancer.java
 * @Author: Avishek Das
 * @CreatedDate: 28-07-2019
 * @Modified_By avishek.das @Last_On 28-Jul-2019 10:43:59 PM
 */

package com.aashayein.oauth.configuration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import com.aashayein.oauth.dto.LoginDetails;
import com.aashayein.oauth.entities.Employee;

/*TokenEnhancer to customize our Access Token*/
public class CustomTokenEnhancer implements TokenEnhancer {

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

		LoginDetails user = (LoginDetails) authentication.getPrincipal();
		
		Employee employee = user.getUser();

		System.out.println(employee);

		Map<String, Object> additionalInfo = new HashMap<String, Object>(accessToken.getAdditionalInformation());

		additionalInfo.put("name", employee.getFullName());
		additionalInfo.put("employeeCode", employee.getEmployeeCode());

		DefaultOAuth2AccessToken customAccessToken = new DefaultOAuth2AccessToken(accessToken);
		customAccessToken.setAdditionalInformation(additionalInfo);

		return customAccessToken;
	}
}