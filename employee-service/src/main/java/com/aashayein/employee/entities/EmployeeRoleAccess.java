/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.entities
 * @FileName: EmployeeRoleAccess.java
 * @Author: Avishek Das
 * @CreatedDate: 12-06-2019
 * @Modified_By avishek.das @Last_On 12-Jun-2019 1:15:32 PM
 */

package com.aashayein.employee.entities;

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

	private static final long serialVersionUID = 1117808453074813431L;

	@EmbeddedId
	private RoleId_ModuleId roleId_ModuleId;

	@Column(name = "RecordCreated", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date recordCreated;
}
