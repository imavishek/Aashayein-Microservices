/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.command
 * @FileName: EditEmployeeCommand.java
 * @Author: Avishek Das
 * @CreatedDate: 18-07-2019
 * @Modified_By avishek.das @Last_On 18-Jul-2019 10:41:24 PM
 */

package com.aashayein.employee.command;

import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class EditEmployeeCommand {

	@NotNull(message = "{title.notNull}")
	@Digits(integer = 2, fraction = 0, message = "{title.digits}")
	@Max(value = 15, message = "{title.max}")
	@Min(value = 1, message = "{title.min}")
	private String title;

	@NotNull(message = "{role.notNull}")
	@Digits(integer = 2, fraction = 0, message = "{role.digits}")
	@Max(value = 15, message = "{role.max}")
	@Min(value = 1, message = "{role.min}")
	private String role;

	@NotNull(message = "{joiningDate.notNull}")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@FutureOrPresent(message = "{joiningDate.futureOrPresent}")
	private Date joiningDate;
}
