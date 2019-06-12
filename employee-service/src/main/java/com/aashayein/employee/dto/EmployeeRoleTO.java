/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.dto
 * @FileName: EmployeeRoleTO.java
 * @Author: Avishek Das
 * @CreatedDate: 12-06-2019
 * @Modified_By avishek.das @Last_On 12-Jun-2019 3:49:46 PM
 */

package com.aashayein.employee.dto;

import java.util.Date;

import lombok.Data;

@Data
public class EmployeeRoleTO {

	private Integer roleId;

	private String roleName;

	private Byte archive;

	private Date recordCreated;

	private Date recordUpdated;
}
