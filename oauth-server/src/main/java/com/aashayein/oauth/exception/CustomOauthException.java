/**
 * @ProjectName: oauth-server
 * @PackageName: com.aashayein.oauth.exception
 * @FileName: CustomOauthException.java
 * @Author: Avishek Das
 * @CreatedDate: 29-07-2019
 * @Modified_By avishek.das @Last_On 29-Jul-2019 12:52:31 PM
 */

package com.aashayein.oauth.exception;

import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = CustomOauthExceptionSerializer.class)
public class CustomOauthException extends OAuth2Exception {

	private static final long serialVersionUID = -4081041281093915956L;

	public CustomOauthException(String msg) {
		super(msg);
	}
}