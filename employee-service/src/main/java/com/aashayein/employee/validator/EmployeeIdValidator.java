/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.validator
 * @FileName: EmployeeIdValidator.java
 * @Author: Avishek Das
 * @CreatedDate: 01-07-2019
 * @Modified_By avishek.das @Last_On 01-Jul-2019 7:16:29 PM
 */

package com.aashayein.employee.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmployeeIdValidator implements ConstraintValidator<EmployeeId, String> {

	@Override
	public boolean isValid(String id, ConstraintValidatorContext context) {

		try {
			Integer employeeId = Integer.parseInt(id);

			if (employeeId < 1 && employeeId > 1000) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
	}

}
