/**
 * @ProjectName: role-service
 * @PackageName: com.aashayein.role.command
 * @FileName: EmployeeRoleCommand.java
 * @Author: Avishek Das
 * @CreatedDate: 18-07-2019
 * @Modified_By avishek.das @Last_On 18-Jul-2019 12:41:31 PM
 */

package com.aashayein.role.command;

import javax.validation.constraints.Pattern;

import com.aashayein.role.validator.ModuleId;

import lombok.Data;

@Data
public class EmployeeRoleCommand {

	@Pattern(regexp = "[a-zA-Z]{3,25}", message = "{roleName.pattern}")
	private String roleName;

	@ModuleId(max = 15, message = "{moduleIds.moduleId}")
	private String[] moduleIds;
}
