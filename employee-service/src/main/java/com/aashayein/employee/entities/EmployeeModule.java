/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.entities
 * @FileName: EmployeeModule.java
 * @Author: Avishek Das
 * @CreatedDate: 12-06-2019
 * @Modified_By avishek.das @Last_On 12-Jun-2019 1:11:01 PM
 */

package com.aashayein.employee.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Entity
@Table(name = "TblEmployeeModule")
@EntityListeners(AuditingEntityListener.class)
@Data
public class EmployeeModule implements Serializable {

	private static final long serialVersionUID = 6358001774341323615L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ModuleId", unique = true, nullable = false)
	private Integer moduleId;

	@Column(name = "ModuleName", nullable = false, length = 60)
	private String moduleName;

	@Column(name = "ModuleDirectory", nullable = false, length = 60)
	private String moduleDirectory;

	@Column(name = "Archive", nullable = false, columnDefinition = "BIT", length = 1)
	private Byte archive;

	@Column(name = "RecordCreated", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date recordCreated;

	@Column(name = "RecordUpdated", nullable = true, insertable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date recordUpdated;
}
