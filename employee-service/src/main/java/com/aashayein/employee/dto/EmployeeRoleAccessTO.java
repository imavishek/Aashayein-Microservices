/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.dto
 * @FileName: EmployeeRoleAccessTO.java
 * @Author: Avishek Das
 * @CreatedDate: 12-06-2019
 * @Modified_By avishek.das @Last_On 12-Jun-2019 3:49:11 PM
 */

package com.aashayein.employee.dto;

import java.util.Date;

import lombok.Data;

@Data
public class EmployeeRoleAccessTO {

	private String[] moduleIds;

	private Date recordCreated;
}
