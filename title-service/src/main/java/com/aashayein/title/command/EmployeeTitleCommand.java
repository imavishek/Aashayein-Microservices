/**
 * @ProjectName: title-service
 * @PackageName: com.aashayein.title.command
 * @FileName: EmployeeTitleCommand.java
 * @Author: Avishek Das
 * @CreatedDate: 15-07-2019
 * @Modified_By avishek.das @Last_On 15-Jul-2019 4:48:06 PM
 */

package com.aashayein.title.command;

import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class EmployeeTitleCommand {

	@Pattern(regexp = "[a-zA-Z]{3,25}", message = "{titleName.pattern}")
	private String titleName;
}
