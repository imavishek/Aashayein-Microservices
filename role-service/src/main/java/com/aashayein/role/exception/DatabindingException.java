/**
 * @ProjectName: role-service
 * @PackageName: com.aashayein.role.exception
 * @FileName: DatabindingException.java
 * @Author: Avishek Das
 * @CreatedDate: 16-07-2019
 * @Modified_By avishek.das @Last_On 16-Jul-2019 9:43:21 PM
 */

package com.aashayein.role.exception;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DatabindingException extends Exception {

	private static final long serialVersionUID = 2954715224356696037L;

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
