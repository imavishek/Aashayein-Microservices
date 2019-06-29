/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.util
 * @FileName: DateTime.java
 * @Author: Avishek Das
 * @CreatedDate: 27-06-2019
 * @Modified_By avishek.das @Last_On 27-Jun-2019 1:34:03 AM
 */

package com.aashayein.employee.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DateTime {

	public Date getCurrentDateTime() {

		String today = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());

		try {
			return new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(today);
		} catch (ParseException e) {
			log.error(e.getMessage() + " [Exception " + e.getClass() + "]");
		}

		return null;
	}

}
