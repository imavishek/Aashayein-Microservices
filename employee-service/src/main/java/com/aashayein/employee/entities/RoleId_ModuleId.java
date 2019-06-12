/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.entities
 * @FileName: RoleId_ModuleId.java
 * @Author: Avishek Das
 * @CreatedDate: 12-06-2019
 * @Modified_By avishek.das @Last_On 12-Jun-2019 1:16:18 PM
 */

package com.aashayein.employee.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class RoleId_ModuleId implements Serializable {

	private static final long serialVersionUID = 6813031575074862947L;

	@Column(name = "RoleId")
	private Integer roleId;

	@Column(name = "ModuleId")
	private Integer moduleId;
}
