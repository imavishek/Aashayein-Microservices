/**
 * @ProjectName: export-service
 * @PackageName: com.aashayein.export.dto
 * @FileName: EmployeeTO.java
 * @Author: Avishek Das
 * @CreatedDate: 20-06-2019
 * @Modified_By avishek.das @Last_On 20-Jun-2019 12:40:44 AM
 */

package com.aashayein.export.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class EmployeeTO {
	private Integer employeeId;

	private String employeeCode;

	private String firstName;

	private String middleName;

	private String lastName;

	private String fullName;

	private String gender;

	private String mobileNumber;

	private String alternateMobileNumber;

	private String email;

	private String alternateEmail;

	private Integer addressId;

	private String addressName;

	private Integer countryId;

	private String countryName;

	private Integer stateId;

	private String stateName;

	private Integer cityId;

	private String cityName;

	private String postalCode;

	private String addressLine1;

	private String addressLine2;

	private Integer jobTitleId;

	private String jobTitleName;

	private Integer roleId;

	private String roleName;

	private Byte active;

	private Byte archive;

	private String profilePhoto;

	private MultipartFile profilePhotoFile;

	private String tokenUUID;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+5:30")
	private Date tokenGeneratedDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+5:30")
	private Date joiningDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+5:30")
	private Date recordCreated;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+5:30")
	private Date recordUpdated;
}
