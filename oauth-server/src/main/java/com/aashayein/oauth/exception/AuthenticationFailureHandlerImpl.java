/**
 * @ProjectName: oauth-server
 * @PackageName: com.aashayein.oauth.exception
 * @FileName: AuthenticationFailureHandlerImpl.java
 * @Author: Avishek Das
 * @CreatedDate: 29-07-2019
 * @Modified_By avishek.das @Last_On 29-Jul-2019 3:38:46 PM
 */

package com.aashayein.oauth.exception;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {

	private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		ObjectMapper objectMapper = new ObjectMapper();

		log.info(exception.getMessage() + " For Username: " + request.getParameter("username"));

		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		Map<String, Object> data = new HashMap<>();

		data.put("timestamp", formatter.format(new Date()));
		data.put("status", HttpStatus.UNAUTHORIZED.value());
		data.put("error", "Unauthorized");
		data.put("message", "Bad credentials");

		response.getOutputStream().println(objectMapper.writeValueAsString(data));
	}

}
