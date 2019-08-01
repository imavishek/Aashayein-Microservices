/**
 * @ProjectName: oauth-server
 * @PackageName: com.aashayein.oauth.configuration
 * @FileName: SpringSecurityWebApplicationInitializer.java
 * @Author: Avishek Das
 * @CreatedDate: 27-07-2019
 * @Modified_By avishek.das @Last_On 27-Jul-2019 2:43:55 PM
 */

package com.aashayein.oauth.configuration;

import javax.servlet.ServletContext;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.multipart.support.MultipartFilter;

public class SpringSecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {
	// Registering the springSecurityFilterChain

	// Invoked before the springSecurityFilterChain is added.
	@Override
	protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
		insertFilters(servletContext, new MultipartFilter());
	}
}