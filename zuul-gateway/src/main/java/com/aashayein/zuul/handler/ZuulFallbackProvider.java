/**
 * @ProjectName: zuul-gateway
 * @PackageName: com.aashayein.zuul.util
 * @FileName: ZuulFallbackProvider.java
 * @Author: Avishek Das
 * @CreatedDate: 04-07-2019
 * @Modified_By avishek.das @Last_On 04-Jul-2019 10:10:34 PM
 */

package com.aashayein.zuul.handler;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ZuulFallbackProvider implements FallbackProvider {

	/* provide a default fallback for all routes */
	@Override
	public String getRoute() {
		return "*";
	}

	@Override
	public ClientHttpResponse fallbackResponse(String route, Throwable throwable) {
		return new ClientHttpResponse() {
			@Override
			public HttpStatus getStatusCode() throws IOException {
				return HttpStatus.SERVICE_UNAVAILABLE;
			}

			@Override
			public int getRawStatusCode() throws IOException {
				return HttpStatus.SERVICE_UNAVAILABLE.value();
			}

			@Override
			public String getStatusText() throws IOException {
				return HttpStatus.SERVICE_UNAVAILABLE.toString();
			}

			@Override
			public void close() {

			}

			@Override
			public InputStream getBody() throws IOException {
				log.info("Service Unavailable. Please try after sometime.");
				return new ByteArrayInputStream("Service Unavailable. Please try after sometime.".getBytes());
			}

			@Override
			public HttpHeaders getHeaders() {
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				return headers;
			}
		};
	}

}
