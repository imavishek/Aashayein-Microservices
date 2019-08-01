/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.validator
 * @FileName: EmployeeId.java
 * @Author: Avishek Das
 * @CreatedDate: 01-07-2019
 * @Modified_By avishek.das @Last_On 01-Jul-2019 7:14:53 PM
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
@Constraint(validatedBy = EmployeeIdValidator.class)
@Target({ ElementType.PARAMETER, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface EmployeeId {

	String message() default "Invalid EmployeeId";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
