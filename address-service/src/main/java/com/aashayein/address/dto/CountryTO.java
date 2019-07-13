/**
 * @ProjectName: address-service
 * @PackageName: com.aashayein.address.dto
 * @FileName: CountryTO.java
 * @Author: Avishek Das
 * @CreatedDate: 13-07-2019
 * @Modified_By avishek.das @Last_On 13-Jul-2019 11:31:56 PM
 */

package com.aashayein.address.dto;

import lombok.Data;

@Data
public class CountryTO {

	private Integer countryId;

	private String countryName;

	private String isoCode;

	private Integer isdCode;
}
