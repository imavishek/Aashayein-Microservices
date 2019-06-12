/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.dto
 * @FileName: CountryTO.java
 * @Author: Avishek Das
 * @CreatedDate: 12-06-2019
 * @Modified_By avishek.das @Last_On 12-Jun-2019 3:47:48 PM
 */

package com.aashayein.employee.dto;

import lombok.Data;

@Data
public class CountryTO {

	private Integer countryId;

	private String countryName;

	private String isoCode;

	private String isdCode;
}
