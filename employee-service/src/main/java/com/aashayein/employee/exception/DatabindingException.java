/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.exception
 * @FileName: DatabindingException.java
 * @Author: Avishek Das
 * @CreatedDate: 25-06-2019
 * @Modified_By avishek.das @Last_On 25-Jun-2019 10:52:22 PM
 */

package com.aashayein.employee.exception;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DatabindingException extends Exception {

	private static final long serialVersionUID = 7513943937801320721L;

	private List<String> errors;

	public DatabindingException() {
		this(new ArrayList<String>(Collections.singletonList("Error In Databinding")));
	}

	public DatabindingException(List<String> errors) {
		this.errors = errors;
	}

	public List<String> getErrors() {
		return errors;
	}
}
