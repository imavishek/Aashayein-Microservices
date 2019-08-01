/**
 * @ProjectName: oauth-server
 * @PackageName: com.aashayein.oauth.exception
 * @FileName: CustomOauthExceptionSerializer.java
 * @Author: Avishek Das
 * @CreatedDate: 29-07-2019
 * @Modified_By avishek.das @Last_On 29-Jul-2019 12:53:12 PM
 */

package com.aashayein.oauth.exception;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomOauthExceptionSerializer extends StdSerializer<CustomOauthException> {

	private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	private static final long serialVersionUID = -5663243086423604397L;

	public CustomOauthExceptionSerializer() {
		super(CustomOauthException.class);
	}

	@Override
	public void serialize(CustomOauthException value, JsonGenerator jsonGenerator,
			SerializerProvider serializerProvider) throws IOException {

		log.error("Timestamp: " + formatter.format(new Date()) + ", Status: " + value.getHttpErrorCode() + ", Error: "
				+ value.getOAuth2ErrorCode() + ", Message: " + value.getMessage());

		jsonGenerator.writeStartObject();
		jsonGenerator.writeStringField("timestamp", formatter.format(new Date()));
		jsonGenerator.writeNumberField("status", value.getHttpErrorCode());
		jsonGenerator.writeStringField("error", value.getOAuth2ErrorCode());
		jsonGenerator.writeStringField("message", value.getMessage());
		jsonGenerator.writeEndObject();
	}
}