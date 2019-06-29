/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.validator
 * @FileName: FileTypeValidator.java
 * @Author: Avishek Das
 * @CreatedDate: 24-06-2019
 * @Modified_By avishek.das @Last_On 24-Jun-2019 4:02:13 PM
 */

package com.aashayein.employee.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileTypeValidator implements ConstraintValidator<FileType, MultipartFile> {

	private String extension;

	@Override
	public void initialize(FileType fileType) {
		this.extension = fileType.extension();
	}

	@Override
	public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {

		if (file == null) {
			return false;
		} else if (!file.isEmpty() && !FilenameUtils.getExtension(file.getOriginalFilename()).matches(extension)) {
			return false;
		} else {
			return true;
		}
	}

}
