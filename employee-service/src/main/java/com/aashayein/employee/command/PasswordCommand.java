/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.command
 * @FileName: PasswordCommand.java
 * @Author: Avishek Das
 * @CreatedDate: 22-07-2019
 * @Modified_By avishek.das @Last_On 22-Jul-2019 11:33:30 PM
 */

package com.aashayein.employee.command;

import javax.validation.constraints.Pattern;

import com.aashayein.employee.validator.PasswordMatches;

import lombok.Data;

@Data
@PasswordMatches(message = "{password.matches}")
public class PasswordCommand {

	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*#&])[A-Za-z\\d@$!%*#&]{8,25}$", message = "{password.pattern}")
	private String password;

	private String confirmPassword;
}
