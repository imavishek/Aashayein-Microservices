/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.util
 * @FileName: FileUploadUtil.java
 * @Author: Avishek Das
 * @CreatedDate: 26-06-2019
 * @Modified_By avishek.das @Last_On 26-Jun-2019 10:50:19 PM
 */

package com.aashayein.employee.util;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FileUploadUtil {

	@Autowired
	private ServletContext servletContext;

	@Value("${spring.profiles.active}")
	private String activeProfile;

	@Value("${path.employee.photo}")
	private String profilePhotoFolder;

	@Value("${path.dev.mac.employee.photo}")
	private String absPathMac;

	@Value("${path.dev.windows.employee.photo}")
	private String absPathWindows;

	// Upload Profile Picture Into Server
	public String uploadProfilePictureIntoServer(MultipartFile profilePhotoFile, String employeeCode) {

		String fileName = null;
		String extension = null;
		String realPath = null;
		String absPath = null;

		// Return null if file is empty
		if (profilePhotoFile.isEmpty()) {
			return null;
		}

		if (activeProfile.equalsIgnoreCase("dev")) {

			// Development path
			if (System.getProperty("os.name").toLowerCase().indexOf("mac") >= 0) {
				absPath = absPathMac;
			} else {
				absPath = absPathWindows;
			}
		}

		// get the real server path
		realPath = servletContext.getRealPath(profilePhotoFolder);

		log.info("Development Upload Path For ProfilePictures " + absPath);
		log.info("Server Upload Path For ProfilePictures " + realPath);

		// Upload ProfilePicture in both development and server
		try {

			// create the directories if it does not exist
			if (!new File(realPath).exists()) {
				new File(realPath).mkdirs();
			}

			if (!new File(absPath).exists()) {
				new File(absPath).mkdirs();
			}

			/*
			 * Get the file extension and rename the file PP- ProfilePicture
			 */
			extension = FilenameUtils.getExtension(profilePhotoFile.getOriginalFilename());
			fileName = "PP_" + UUID.randomUUID().toString() + "." + extension;

			// Transfer the file to server
			profilePhotoFile.transferTo(new File(realPath + fileName));

			/*
			 * As file already transfered in above. You can't use MultipartFile#transferTo
			 * method twice, second call of transferTo method should be replaced with a
			 * logic of file copying.
			 */
			Files.copy(Paths.get(realPath + fileName), Paths.get(absPath + fileName));

			log.info("Profile Picture Uploaded Successfully For The Employee Having EmployeeCode : " + employeeCode);

			return fileName;
		} catch (Exception e) {

			log.error(e.getMessage() + " [Exception " + e.getClass() + "]");

			return null;
		}
	}
}
