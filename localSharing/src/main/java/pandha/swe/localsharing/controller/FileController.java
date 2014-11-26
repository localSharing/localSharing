package pandha.swe.localsharing.controller;

import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pandha.swe.localsharing.model.FileUpload;
import pandha.swe.localsharing.model.enums.FileUploadType;
import pandha.swe.localsharing.service.FileService;

@Controller
public class FileController {

	@Autowired
	private FileService fileService;

	@RequestMapping(value = "/images/user/{userId}")
	@ResponseBody
	public byte[] getFileUser(@PathVariable("userId") Long id) {

		FileUpload image = fileService
				.findByAssociated(id, FileUploadType.USER);

		byte[] content = null;
		if (image == null) {
			// TODO Default Image via Insert Script

			content = getUserDefaultImageIfNotExistInsertIT();
		} else {
			content = image.getFile();
		}

		return content;
	}

	private byte[] getUserDefaultImageIfNotExistInsertIT() {

		FileUpload image = fileService.findByAssociated(new Long(1),
				FileUploadType.DEFAULT_USER);

		if (image == null) {

			FileUpload fileUpload = new FileUpload();

			fileUpload.setAssID(new Long(1));
			fileUpload.setFileUploadType(FileUploadType.DEFAULT_USER);

			byte[] data;
			try {

				ClassPathResource classPathResource = new ClassPathResource(
						"static/images/user_default.jpg");

				data = Files.readAllBytes(classPathResource.getFile().toPath());

				fileUpload.setFile(data);

				fileService.save(fileUpload);

				image = fileUpload;

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return image.getFile();
	}
}
