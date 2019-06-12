/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.entities
 * @FileName: City.java
 * @Author: Avishek Das
 * @CreatedDate: 12-06-2019
 * @Modified_By avishek.das @Last_On 12-Jun-2019 12:57:13 PM
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
@Table(name = "TblCity")
@EntityListeners(AuditingEntityListener.class)
@Data
public class City implements Serializable {

	private static final long serialVersionUID = 7514587323254845821L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CityId")
	private Integer cityId;

	@Column(name = "CityName")
	private String cityName;

	@Column(name = "StateId")
	private Integer stateId;

	@Column(name = "Archive", insertable = false, columnDefinition = "BIT", length = 1)
	private Byte archive;

	@Column(name = "RecordCreated", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date recordCreated;

	@Column(name = "RecordUpdated", insertable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date recordUpdated;
}
