/**
 * @ProjectName: oauth-server
 * @PackageName: com.aashayein.oauth.handler
 * @FileName: AccessDeniedHandlerImpl.java
 * @Author: Avishek Das
 * @CreatedDate: 30-07-2019
 * @Modified_By avishek.das @Last_On 30-Jul-2019 9:09:45 PM
 */

package com.aashayein.oauth.handler;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

	private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	@Autowired
	private TokenStore tokenStore;

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) auth.getDetails();
		OAuth2AccessToken accessToken = tokenStore.readAccessToken(details.getTokenValue());

		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> data = new HashMap<>();
		data.put("timestamp", formatter.format(new Date()));
		data.put("status", HttpStatus.FORBIDDEN.value());
		data.put("error", "Forbidden");
		data.put("message", accessDeniedException.getMessage());
		data.put("path", request.getRequestURI());

		log.error(accessToken.getAdditionalInformation().toString());
		log.error(data.toString());

		response.setContentType("application/json;charset=UTF-8");
		response.setStatus(HttpStatus.FORBIDDEN.value());

		response.getOutputStream().println(objectMapper.writeValueAsString(data));
	}
}