/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.validator
 * @FileName: GenderValidator.java
 * @Author: Avishek Das
 * @CreatedDate: 24-06-2019
 * @Modified_By avishek.das @Last_On 24-Jun-2019 4:17:48 PM
 */

package com.aashayein.employee.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GenderValidator implements ConstraintValidator<Gender, String> {

	private String listOfGender;

	@Override
	public void initialize(Gender gender) {
		this.listOfGender = gender.listOfGender();
	}

	@Override
	public boolean isValid(String gender, ConstraintValidatorContext context) {

		if (gender == null) {
			return false;
		} else if (gender.matches(listOfGender)) {
			return true;
		} else {
			return false;
		}
	}

}
