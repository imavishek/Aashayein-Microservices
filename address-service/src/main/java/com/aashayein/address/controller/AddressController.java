/**
 * @ProjectName: address-service
 * @PackageName: com.aashayein.address.controller
 * @FileName: AddressController.java
 * @Author: Avishek Das
 * @CreatedDate: 05-07-2019
 * @Modified_By avishek.das @Last_On 05-Jul-2019 4:41:00 PM
 */

package com.aashayein.address.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressController {

	@GetMapping("check")
	public String returnString() {
		return "sss";
	}
}
