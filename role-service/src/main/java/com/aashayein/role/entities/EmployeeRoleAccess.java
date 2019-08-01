/**
 * @ProjectName: role-service
 * @PackageName: com.aashayein.role.entities
 * @FileName: EmployeeRoleAccess.java
 * @Author: Avishek Das
 * @CreatedDate: 16-07-2019
 * @Modified_By avishek.das @Last_On 16-Jul-2019 8:55:46 PM
 */

package com.aashayein.role.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Entity
@Table(name = "TblEmployeeRoleAccess")
@EntityListeners(AuditingEntityListener.class)
@Data
public class EmployeeRoleAccess implements Serializable {

	private static final long serialVersionUID = 7375456522845942617L;

	@EmbeddedId
	private RoleId_ModuleId roleId_ModuleId;

	@Column(name = "RecordCreated", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date recordCreated;
}
