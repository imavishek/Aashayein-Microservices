/**
 * @ProjectName: title-service
 * @PackageName: com.aashayein.title.exception
 * @FileName: DatabindingException.java
 * @Author: Avishek Das
 * @CreatedDate: 14-07-2019
 * @Modified_By avishek.das @Last_On 14-Jul-2019 2:22:35 PM
 */

package com.aashayein.title.exception;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DatabindingException extends Exception {

	private static final long serialVersionUID = 2695962745824033705L;

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
