package pandha.swe.localsharing.controller;

import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
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

	@RequestMapping(value = "/images/user/{userId}", produces = MediaType.IMAGE_JPEG_VALUE)
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

	@RequestMapping(value = "/images/hilfsangebot/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getFileHilfangebotImage(@PathVariable("id") Long id) {

		FileUpload image = fileService.findByAssociated(id,
				FileUploadType.HILFANGEBOT);

		return getImageIfNoImageFound_GetDefaultImage(image);
	}

	@RequestMapping(value = "/images/ausleihangebot/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getFileAusleihangebotImage(@PathVariable("id") Long id) {

		FileUpload image = fileService.findByAssociated(id,
				FileUploadType.AUSLEIHANGEBOT);

		return getImageIfNoImageFound_GetDefaultImage(image);

	}

	@RequestMapping(value = "/images/tauschangebot/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getFileTauschangebotImage(@PathVariable("id") Long id) {

		FileUpload image = fileService.findByAssociated(id,
				FileUploadType.TAUSCHANGEBOT);

		return getImageIfNoImageFound_GetDefaultImage(image);
	}

	@RequestMapping(value = "/images/angebot", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getFileAangebotDefaultImage() {

		return getAngebotDefaultImageIfNotExistInsertIT();
	}

	private byte[] getImageIfNoImageFound_GetDefaultImage(FileUpload image) {
		byte[] content = null;
		if (image == null) {
			// TODO Default Image via Insert Script

			content = getAngebotDefaultImageIfNotExistInsertIT();
		} else {
			content = image.getFile();
		}
		return content;
	}

	private byte[] getAngebotDefaultImageIfNotExistInsertIT() {

		FileUpload image = fileService.findByAssociated(new Long(1),
				FileUploadType.DEFAULT_ANGEBOT);

		if (image == null) {

			FileUpload fileUpload = new FileUpload();

			fileUpload.setAssID(new Long(1));
			fileUpload.setFileUploadType(FileUploadType.DEFAULT_ANGEBOT);

			byte[] data;
			try {

				ClassPathResource classPathResource = new ClassPathResource(
						"static/images/angebot_default.jpg");

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
