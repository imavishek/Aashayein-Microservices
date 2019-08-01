/**
 * @ProjectName: oauth-server
 * @PackageName: com.aashayein.oauth.exception
 * @FileName: AuthExceptionEntryPoint.java
 * @Author: Avishek Das
 * @CreatedDate: 29-07-2019
 * @Modified_By avishek.das @Last_On 29-Jul-2019 2:32:47 PM
 */

package com.aashayein.oauth.exception;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomWebResponseExceptionTranslator extends DefaultWebResponseExceptionTranslator {

	private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	@Override
	public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {

		ResponseEntity<OAuth2Exception> responseEntity = super.translate(e);
		OAuth2Exception body = responseEntity.getBody();
		HttpStatus statusCode = responseEntity.getStatusCode();

		log.error("Timestamp: " + formatter.format(new Date()) + ", Status: " + body.getHttpErrorCode() + ", Error: "
				+ body.getOAuth2ErrorCode() + ", Message: " + body.getMessage());

		body.addAdditionalInformation("timestamp", formatter.format(new Date()));
		body.addAdditionalInformation("status", Integer.toString(body.getHttpErrorCode()));

		// body.addAdditionalInformation("error", body.getOAuth2ErrorCode());
		// body.addAdditionalInformation("message", body.getMessage());

		HttpHeaders headers = new HttpHeaders();
		headers.setAll(responseEntity.getHeaders().toSingleValueMap());
		// do something with header or response
		return new ResponseEntity<>(body, headers, statusCode);
	}

}