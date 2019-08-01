/**
 * @ProjectName: role-service
 * @PackageName: com.aashayein.role.validator
 * @FileName: ModuleIdValidator.java
 * @Author: Avishek Das
 * @CreatedDate: 18-07-2019
 * @Modified_By avishek.das @Last_On 18-Jul-2019 12:43:15 PM
 */

package com.aashayein.role.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ModuleIdValidator implements ConstraintValidator<ModuleId, String[]> {

	private int max;

	@Override
	public void initialize(ModuleId moduleId) {
		this.max = moduleId.max();
	}

	@Override
	public boolean isValid(String[] moduleIds, ConstraintValidatorContext context) {

		// Check permissions is selected or not
		if (moduleIds.length == 0) {
			return false;
		}

		// Validate the moduleId
		for (String module : moduleIds) {
			try {
				Integer moduleId = Integer.parseInt(module);
				if (moduleId > max) {
					return false;
				}
			} catch (NumberFormatException e) {
				return false;
			}
		}

		return true;
	}
}
