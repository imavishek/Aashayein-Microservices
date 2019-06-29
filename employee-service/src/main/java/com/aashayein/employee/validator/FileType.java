/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.validator
 * @FileName: FileType.java
 * @Author: Avishek Das
 * @CreatedDate: 24-06-2019
 * @Modified_By avishek.das @Last_On 24-Jun-2019 4:01:19 PM
 */

package com.aashayein.employee.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = FileTypeValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface FileType {

	String extension() default "jpg|jpeg|JPG|JPEG";

	String message() default "Please upload jpg or jpeg image file";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
