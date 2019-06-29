/**
 * @ProjectName: mail-service
 * @PackageName: com.aashayein.mail.dto
 * @FileName: MailRequestTO.java
 * @Author: Avishek Das
 * @CreatedDate: 29-06-2019
 * @Modified_By avishek.das @Last_On 29-Jun-2019 3:16:41 PM
 */

package com.aashayein.mail.dto;

import lombok.Data;

@Data
public class MailRequestTO {

	private String recipientName;

	private String emailTo;

	private String emailForm;

	private String emailCC;

	private String emailBCC;

	private String emailSubject;

	private String emailTemplateName;

	private String url;

	private Object details;
}
