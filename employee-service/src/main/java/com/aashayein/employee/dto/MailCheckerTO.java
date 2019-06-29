/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.dto
 * @FileName: MailCheckerTO.java
 * @Author: Avishek Das
 * @CreatedDate: 26-06-2019
 * @Modified_By avishek.das @Last_On 26-Jun-2019 9:29:15 PM
 */

package com.aashayein.employee.dto;

import lombok.Data;

@Data
public class MailCheckerTO {

	/* Contains the exact email address requested. */
	private String email;

	/*
	 * Contains a did-you-mean suggestion in case a potential typo has been
	 * detected.
	 */
	private String did_you_mean;

	/*
	 * Returns the local part of the request email address. (e.g. "paul" in
	 * "paul@company.com")
	 */
	private String user;

	/*
	 * Returns the domain of the requested email address. (e.g. "company.com" in
	 * "paul@company.com")
	 */
	private String domain;

	/*
	 * Returns true or false depending on whether or not the general syntax of the
	 * requested email address is valid.
	 */
	private Boolean format_valid;

	/*
	 * Returns true or false depending on whether or not MX-Records for the
	 * requested domain could be found.
	 */
	private Boolean mx_found;

	/*
	 * Returns true or false depending on whether or not the SMTP check of the
	 * requested email address succeeded.
	 */
	private Boolean smtp_check;

	/*
	 * Returns true or false depending on whether or not the requested email address
	 * is found to be part of a catch-all mailbox.
	 */
	private Boolean catch_all;

	/*
	 * Returns true or false depending on whether or not the requested email address
	 * is a role email address. (e.g. "support@company.com",
	 * "postmaster@company.com")
	 */
	private Boolean role;

	/*
	 * Returns true or false depending on whether or not the requested email address
	 * is a disposable email address. (e.g. "user123@mailinator.com")
	 */
	private Boolean disposable;

	/*
	 * Returns true or false depending on whether or not the requested email address
	 * is a free email address. (e.g. "user123@gmail.com", "user123@yahoo.com")
	 */
	private Boolean free;

	/*
	 * Returns a numeric score between 0 and 1 reflecting the quality and
	 * deliverability of the requested email address.
	 */
	private Integer score;
}
