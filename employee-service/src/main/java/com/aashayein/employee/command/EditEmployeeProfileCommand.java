/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.command
 * @FileName: EditEmployeeProfileCommand.java
 * @Author: Avishek Das
 * @CreatedDate: 19-07-2019
 * @Modified_By avishek.das @Last_On 19-Jul-2019 9:13:27 PM
 */

package com.aashayein.employee.command;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import com.aashayein.employee.validator.FileSize;
import com.aashayein.employee.validator.FileType;
import com.aashayein.employee.validator.Gender;

import lombok.Data;

@Data
public class EditEmployeeProfileCommand {

	@Pattern(regexp = "[a-zA-Z]{3,25}", message = "{firstName.pattern}")
	private String firstName;

	@Pattern(regexp = "^$|[a-zA-Z]{2,20}", message = "{middleName.pattern}")
	private String middleName;

	@Pattern(regexp = "[a-zA-Z]{2,15}", message = "{lastName.pattern}")
	private String lastName;

	@Gender(listOfGender = "Male|Female|Other", message = "{gender}")
	private String gender;

	@Pattern(regexp = "[6789]\\d{9}", message = "{mobileNumber.pattern}")
	private String mobileNumber;

	@Pattern(regexp = "^$|[6789]\\d{9}", message = "{alternateMobileNumber.pattern}")
	private String alternateMobileNumber;

	@Pattern(regexp = "^$|([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4})", message = "{alternateEmail.pattern}")
	private String alternateEmail;

	@Pattern(regexp = "[123456789]\\d{0,2}", message = "{country.pattern}")
	private String country;

	@Pattern(regexp = "[123456789]\\d{0,3}", message = "{state.pattern}")
	private String state;

	@Pattern(regexp = "[123456789]\\d{0,4}", message = "{city.pattern}")
	private String city;

	@Pattern(regexp = "[123456789]\\d{5}", message = "{pinCode.pattern}")
	private String pinCode;

	@Size(min = 20, max = 150, message = "{addressLine1.size}")
	private String addressLine1;

	@Size(max = 150, message = "{addressLine2.size}")
	private String addressLine2;

	@FileType(extension = "jpg|jpeg|JPG|JPEG", message = "{profilePhoto.fileType}")
	@FileSize(size = 1048576, message = "{profilePhoto.fileSize}")
	private MultipartFile profilePhoto;
}
