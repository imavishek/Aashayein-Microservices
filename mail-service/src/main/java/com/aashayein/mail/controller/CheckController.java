/**
 * @ProjectName: mail-service
 * @PackageName: com.aashayein.mail.controller
 * @FileName: CheckController.java
 * @Author: Avishek Das
 * @CreatedDate: 05-07-2019
 * @Modified_By avishek.das @Last_On 05-Jul-2019 3:12:36 PM
 */

package com.aashayein.mail.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckController {

	@GetMapping("check")
	public String returnString() {
		return "sss";
	}
}
