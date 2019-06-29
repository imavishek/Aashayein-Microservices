/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.command
 * @FileName: EmployeeRegistrationCommand.java
 * @Author: Avishek Das
 * @CreatedDate: 24-06-2019
 * @Modified_By avishek.das @Last_On 24-Jun-2019 3:54:00 PM
 */

package com.aashayein.employee.command;

import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.aashayein.employee.validator.FileSize;
import com.aashayein.employee.validator.FileType;
import com.aashayein.employee.validator.Gender;

import lombok.Data;

@Data
public class EmployeeRegistrationCommand {

	@Pattern(regexp = "[a-zA-Z]{3,25}", message = "{firstName.pattern}")
	private String firstName;

	@Pattern(regexp = "^$|[a-zA-Z]{2,20}", message = "{middleName.pattern}")
	private String middleName;

	@Pattern(regexp = "[a-zA-Z]{2,15}", message = "{lastName.pattern}")
	private String lastName;

	@Gender(listOfGender = "Male|Female|Other", message = "{gender}")
	private String gender;

	@NotNull(message = "{title.notNull}")
	@Digits(integer=2, fraction=0, message = "{title.digits}")
	@Max(value = 15, message = "{title.max}")
	@Min(value = 1, message = "{title.min}")
	private Integer title;

	@NotNull(message = "{role.notNull}")
	@Digits(integer=2, fraction=0, message = "{role.digits}")
	@Max(value = 15, message = "{role.max}")
	@Min(value = 1, message = "{role.min}")
	private Integer role;

	@Pattern(regexp = "[6789]\\d{9}", message = "{mobileNumber.pattern}")
	private String mobileNumber;

	@Pattern(regexp = "^$|[6789]\\d{9}", message = "{alternateMobileNumber.pattern}")
	private String alternateMobileNumber;

	@Pattern(regexp = "([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4})", message = "{email.pattern}")
	private String email;

	@Pattern(regexp = "^$|([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4})", message = "{alternateEmail.pattern}")
	private String alternateEmail;

	@NotNull(message = "{joiningDate.notNull}")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@FutureOrPresent(message = "{joiningDate.futureOrPresent}")
	private Date joiningDate;

	@FileType(extension = "jpg|jpeg|JPG|JPEG", message = "{profilePhoto.fileType}")
	@FileSize(size = 1048576, message = "{profilePhoto.fileSize}")
	private MultipartFile profilePhoto;

}
