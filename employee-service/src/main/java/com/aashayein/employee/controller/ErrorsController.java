/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.controller
 * @FileName: ErrorsController.java
 * @Author: Avishek Das
 * @CreatedDate: 16-06-2019
 * @Modified_By avishek.das @Last_On 16-Jun-2019 12:20:12 PM
 */

package com.aashayein.employee.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

public class ErrorsController implements ErrorController {

	@RequestMapping("/error")
	public ModelAndView handleError(HttpServletResponse response) {

		ModelAndView modelAndView = new ModelAndView();

		if (response.getStatus() == HttpStatus.NOT_FOUND.value()) {
			modelAndView.setViewName("error-404");
		} else if (response.getStatus() == HttpStatus.FORBIDDEN.value()) {
			modelAndView.setViewName("error-403");
		} else if (response.getStatus() == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
			modelAndView.setViewName("error-500");
		} else {
			modelAndView.setViewName("error");
		}

		return modelAndView;
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}

}
