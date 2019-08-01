/**
 * @ProjectName: oauth-server
 * @PackageName: com.aashayein.oauth.entities
 * @FileName: Country.java
 * @Author: Avishek Das
 * @CreatedDate: 27-07-2019
 * @Modified_By avishek.das @Last_On 27-Jul-2019 2:52:05 PM
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
@Table(name = "TblCountry")
@EntityListeners(AuditingEntityListener.class)
@Data
public class Country implements Serializable {

	private static final long serialVersionUID = 5704356588086718670L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CountryId")
	private Integer countryId;

	@Column(name = "CountryName")
	private String countryName;

	@Column(name = "IsoCode")
	private String isoCode;

	@Column(name = "IsdCode")
	private Integer isdCode;

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
