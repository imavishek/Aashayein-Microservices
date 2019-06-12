/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.dto
 * @FileName: EmployeeTitleTO.java
 * @Author: Avishek Das
 * @CreatedDate: 12-06-2019
 * @Modified_By avishek.das @Last_On 12-Jun-2019 3:50:28 PM
 */

package com.aashayein.employee.dto;

import java.util.Date;

import lombok.Data;

@Data
public class EmployeeTitleTO {

	private Integer titleId;

	private String titleName;

	private Byte archive;

	private Date recordCreated;

	private Date recordUpdated;
}
