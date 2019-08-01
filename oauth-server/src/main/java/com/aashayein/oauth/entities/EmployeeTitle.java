/**
 * @ProjectName: oauth-server
 * @PackageName: com.aashayein.oauth.entities
 * @FileName: EmployeeTitle.java
 * @Author: Avishek Das
 * @CreatedDate: 27-07-2019
 * @Modified_By avishek.das @Last_On 27-Jul-2019 2:51:04 PM
 */

package com.aashayein.oauth.entities;

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
@Table(name = "TblJobTitle")
@EntityListeners(AuditingEntityListener.class)
@Data
public class EmployeeTitle implements Serializable {

	private static final long serialVersionUID = -5545766000650573061L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "JobTitleId", unique = true, nullable = false)
	private Integer titleId;

	@Column(name = "JobTitleName", nullable = false, length = 60)
	private String titleName;

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
}
