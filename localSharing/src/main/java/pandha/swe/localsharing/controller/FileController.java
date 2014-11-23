package pandha.swe.localsharing.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
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

	private boolean init = true;

	@RequestMapping(value = "/images/user/{userId}")
	@ResponseBody
	public byte[] getFileUser(@PathVariable("userId") Long id) {

		FileUpload image = fileService
				.findByAssociated(id, FileUploadType.USER);

		byte[] content = null;
		if (image == null) {
			if (init) {
				initUserDefaultImage();
			}
			content = getDefaultImage();
		} else {
			image.getFile();
		}

		return content;
	}

	private byte[] getDefaultImage() {

		FileUpload image = fileService.findByAssociated(new Long(1),
				FileUploadType.DEFAULT_USER);

		if (image != null) {
			return image.getFile();
		}
		return null;
	}

	private void initUserDefaultImage() {

		FileUpload image = fileService.findByAssociated(new Long(1),
				FileUploadType.DEFAULT_USER);

		if (image == null) {

			FileUpload fileUpload = new FileUpload();

			fileUpload.setAssID(new Long(1));
			fileUpload.setFileUploadType(FileUploadType.DEFAULT_USER);

			byte[] data;
			try {
				data = Files.readAllBytes(Paths
						.get("target/classes/static/images/user_default.jpg"));

				fileUpload.setFile(data);

				fileService.save(fileUpload);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
