/**
 * @ProjectName: role-service
 * @PackageName: com.aashayein.role.dto
 * @FileName: EmployeeRoleAccessTO.java
 * @Author: Avishek Das
 * @CreatedDate: 16-07-2019
 * @Modified_By avishek.das @Last_On 16-Jul-2019 9:02:06 PM
 */

package com.aashayein.role.dto;

import java.util.Date;

import com.aashayein.role.dto.EmployeeRoleAccessTO;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class EmployeeRoleAccessTO {

	private String[] moduleIds;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+5:30")
	private Date recordCreated;
}
