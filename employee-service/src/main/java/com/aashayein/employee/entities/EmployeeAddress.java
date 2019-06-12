/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.entities
 * @FileName: EmployeeAddress.java
 * @Author: Avishek Das
 * @CreatedDate: 12-06-2019
 * @Modified_By avishek.das @Last_On 12-Jun-2019 12:53:03 PM
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
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Entity
@Table(name = "TblEmployeeAddress")
@EntityListeners(AuditingEntityListener.class)
@Data
public class EmployeeAddress implements Serializable {

	private static final long serialVersionUID = -8400877637030061821L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "AddressId")
	private Integer addressId;

	@OneToOne
	@JoinColumn(name = "CountryId", referencedColumnName = "CountryId")
	private Country country;

	@OneToOne
	@JoinColumn(name = "StateId", referencedColumnName = "StateId")
	private State state;

	@OneToOne
	@JoinColumn(name = "CityId", referencedColumnName = "CityId")
	private City city;

	@Column(name = "PostalCode")
	private String postalCode;

	@Column(name = "AddressLine1")
	private String addressLine1;

	@Column(name = "AddressLine2")
	private String addressLine2;

	@Column(name = "RecordCreated", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date recordCreated;

	@Column(name = "RecordUpdated", insertable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date recordUpdated;
}
