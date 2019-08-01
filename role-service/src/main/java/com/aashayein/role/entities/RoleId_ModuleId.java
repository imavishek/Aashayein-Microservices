/**
 * @ProjectName: role-service
 * @PackageName: com.aashayein.role.entities
 * @FileName: RoleId_ModuleId.java
 * @Author: Avishek Das
 * @CreatedDate: 16-07-2019
 * @Modified_By avishek.das @Last_On 16-Jul-2019 8:59:13 PM
 */

package com.aashayein.role.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class RoleId_ModuleId implements Serializable {

	private static final long serialVersionUID = -1407167410232858721L;

	@Column(name = "RoleId")
	private Integer roleId;

	@Column(name = "ModuleId")
	private Integer moduleId;
}
