/**
 * @ProjectName: address-service
 * @PackageName: com.aashayein.address.exception
 * @FileName: DatabindingException.java
 * @Author: Avishek Das
 * @CreatedDate: 14-07-2019
 * @Modified_By avishek.das @Last_On 14-Jul-2019 12:06:03 AM
 */

package com.aashayein.address.exception;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DatabindingException extends Exception {

	private static final long serialVersionUID = 8629002223993510573L;

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
