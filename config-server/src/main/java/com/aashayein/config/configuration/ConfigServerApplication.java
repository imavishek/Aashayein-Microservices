/**
 * @ProjectName: config-server
 * @PackageName: com.aashayein.config.configuration
 * @FileName: ConfigServerApplication.java
 * @Author: Avishek Das
 * @CreatedDate: 06-06-2019
 * @Modified_By avishek.das @Last_On 06-Jun-2019 1:02:52 PM
 */

package com.aashayein.config.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer

public class ConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
	}

}
