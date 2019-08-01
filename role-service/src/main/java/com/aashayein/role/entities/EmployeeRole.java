/**
 * @ProjectName: role-service
 * @PackageName: com.aashayein.role.entities
 * @FileName: EmployeeRole.java
 * @Author: Avishek Das
 * @CreatedDate: 16-07-2019
 * @Modified_By avishek.das @Last_On 16-Jul-2019 8:55:33 PM
 */

package com.aashayein.role.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Entity
@Table(name = "TblEmployeeRole")
@EntityListeners(AuditingEntityListener.class)
@Data
public class EmployeeRole implements Serializable {

	private static final long serialVersionUID = 2881489229356798143L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "RoleId", unique = true, nullable = false)
	private Integer roleId;

	@Column(name = "RoleName", nullable = false, length = 60)
	private String roleName;

	@Column(name = "Archive", nullable = false, insertable = false, columnDefinition = "BIT", length = 1)
	private Byte archive;

	@Column(name = "RecordCreated", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date recordCreated;

	@Column(name = "RecordUpdated", nullable = true, insertable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date recordUpdated;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "TblEmployeeRoleAccess", joinColumns = @JoinColumn(name = "RoleId", referencedColumnName = "RoleId"), inverseJoinColumns = @JoinColumn(name = "ModuleId", referencedColumnName = "ModuleId"))
	private Set<EmployeeModule> modules;
}
