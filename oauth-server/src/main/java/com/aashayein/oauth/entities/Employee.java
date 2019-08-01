/**
 * @ProjectName: oauth-server
 * @PackageName: com.aashayein.oauth.entities
 * @FileName: Employee.java
 * @Author: Avishek Das
 * @CreatedDate: 27-07-2019
 * @Modified_By avishek.das @Last_On 27-Jul-2019 2:50:11 PM
 */

package com.aashayein.oauth.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Entity
@DynamicUpdate
/*
 * Generating SQL statements takes time and Hibernate, therefore, uses one
 * cached SQL UPDATE statement per entity. It sets all database columns so that
 * it can be used for all update operations. You can change that with
 * the @DynamicUpdate annotation. It tells Hibernate to generate a new SQL
 * statement for each update operation.
 */
@Table(name = "TblEmployee")
@EntityListeners(AuditingEntityListener.class)
@Data
public class Employee implements Serializable {

	private static final long serialVersionUID = -8885508995211701317L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "EmployeeId")
	private Integer employeeId;

	@Column(name = "EmployeeCode", updatable = false, columnDefinition = "char")
	private String employeeCode;

	@Column(name = "FirstName")
	private String firstName;

	@Column(name = "MiddleName")
	private String middleName;

	@Column(name = "LastName")
	private String lastName;

	@Column(name = "FullName", insertable = false, updatable = false)
	private String fullName;

	@Column(name = "Gender", columnDefinition = "char")
	private String gender;

	@Column(name = "MobileNumber")
	private String mobileNumber;

	@Column(name = "AlternateMobileNumber")
	private String alternateMobileNumber;

	@Column(name = "Email")
	private String email;

	@Column(name = "AlternateEmail")
	private String alternateEmail;

	@OneToOne(fetch = FetchType.EAGER)
	@Cascade({ CascadeType.SAVE_UPDATE })
	@JoinColumn(name = "AddressId", referencedColumnName = "AddressId")
	private EmployeeAddress address;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "JobTitleId", referencedColumnName = "JobTitleId")
	private EmployeeTitle title;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "RoleId", referencedColumnName = "RoleId")
	private EmployeeRole role;

	@Column(name = "Active", insertable = false, columnDefinition = "BIT", length = 1)
	private Byte active;

	@Column(name = "Archive", insertable = false, columnDefinition = "BIT", length = 1)
	private Byte archive;

	@Column(name = "Password")
	private String password;

	@Column(name = "ProfilePhoto")
	private String profilePhoto;

	@Column(name = "TokenUUID", columnDefinition = "char")
	private String tokenUUID;

	@Column(name = "TokenGeneratedDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date tokenGeneratedDate;

	@Column(name = "JoiningDate")
	@Temporal(TemporalType.TIMESTAMP)
	/*
	 * @Temporal annotation is used with java.util.Date and java.util.Calendar
	 * classes. It converts the date and time values from Java Object to compatible
	 * database type and vice versa.
	 */
	private Date joiningDate;

	@Column(name = "RecordCreated", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date recordCreated;

	@Column(name = "RecordUpdated", nullable = true, insertable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date recordUpdated;
}
