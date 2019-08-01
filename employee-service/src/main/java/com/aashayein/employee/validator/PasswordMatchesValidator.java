/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.validator
 * @FileName: PasswordMatchesValidator.java
 * @Author: Avishek Das
 * @CreatedDate: 22-07-2019
 * @Modified_By avishek.das @Last_On 22-Jul-2019 11:38:00 PM
 */

package com.aashayein.employee.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.aashayein.employee.command.PasswordCommand;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

	@Override
	public void initialize(PasswordMatches constraintAnnotation) {
	}

	@Override
	public boolean isValid(Object obj, ConstraintValidatorContext context) {

		PasswordCommand passwords = (PasswordCommand) obj;

		Boolean status = passwords.getPassword().equals(passwords.getConfirmPassword());

		if (status == false) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Password Mismatch").addNode("confirmPassword")
					.addConstraintViolation();
		}

		return status;
	}
}