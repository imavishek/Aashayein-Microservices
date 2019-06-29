/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.validator
 * @FileName: FileSizeValidator.java
 * @Author: Avishek Das
 * @CreatedDate: 24-06-2019
 * @Modified_By avishek.das @Last_On 24-Jun-2019 3:58:59 PM
 */

package com.aashayein.employee.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

public class FileSizeValidator implements ConstraintValidator<FileSize, MultipartFile> {

	private Long size;

	@Override
	public void initialize(FileSize fileSize) {
		this.size = fileSize.size();
	}

	@Override
	public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {

		if (file == null) {
			return false;
		} else if (!file.isEmpty() && (file.getSize() > size)) {
			return false;
		} else {
			return true;
		}
	}

}
