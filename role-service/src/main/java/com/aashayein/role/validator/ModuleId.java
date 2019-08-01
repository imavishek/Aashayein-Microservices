/**
 * @ProjectName: role-service
 * @PackageName: com.aashayein.role.validator
 * @FileName: ModuleId.java
 * @Author: Avishek Das
 * @CreatedDate: 18-07-2019
 * @Modified_By avishek.das @Last_On 18-Jul-2019 12:42:38 PM
 */

package com.aashayein.role.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = ModuleIdValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ModuleId {

	int max() default 2;

	String message() default "Please provide valid data";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
