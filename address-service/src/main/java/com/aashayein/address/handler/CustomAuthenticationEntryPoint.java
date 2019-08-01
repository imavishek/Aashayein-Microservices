/**
 * @ProjectName: address-service
 * @PackageName: com.aashayein.address.handler
 * @FileName: CustomAuthenticationEntryPoint.java
 * @Author: Avishek Das
 * @CreatedDate: 01-08-2019
 * @Modified_By avishek.das @Last_On 01-Aug-2019 1:30:31 AM
 */

package com.aashayein.address.handler;

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
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {

		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> data = new HashMap<>();
		data.put("timestamp", formatter.format(new Date()));
		data.put("status", HttpStatus.UNAUTHORIZED.value());
		data.put("error", "Unauthorized");
		data.put("message", authException.getMessage());
		data.put("path", request.getRequestURI());

		log.error(data.toString());

		response.setContentType("application/json;charset=UTF-8");
		response.setStatus(HttpStatus.UNAUTHORIZED.value());

		response.getOutputStream().println(objectMapper.writeValueAsString(data));

	}

}
