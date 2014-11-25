package pandha.swe.localsharing.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import pandha.swe.localsharing.model.Angebot;
import pandha.swe.localsharing.model.Benutzer;
import pandha.swe.localsharing.model.FileUpload;
import pandha.swe.localsharing.model.dao.FileUploadDAO;
import pandha.swe.localsharing.model.enums.FileUploadType;

@Service("fileService")
public class FileServiceImpl implements FileService {

	@Autowired
	FileUploadDAO fileDAO;

	@Override
	public FileUpload findByAssociated(Benutzer benutzer) {

		FileUpload file = fileDAO.findByAssociated(benutzer.getId(),
				FileUploadType.USER);

		return file;
	}

	@Override
	public FileUpload findByAssociated(Angebot angebot) {
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public List<FileUpload> findAll() {
		return fileDAO.findAll();
	}

	@Override
	public List<FileUpload> findAllOfType(FileUploadType type) {
		return fileDAO.findAllOfType(type);
	}

	@Override
	public void save(FileUpload file) {
		fileDAO.save(file);
	}

	@Override
	public void delete(FileUpload file) {
		fileDAO.delete(file);
	}

	@Override
	public void save(Benutzer benutzer, MultipartFile file) {

		FileUpload upload = new FileUpload();

		upload.setAssID(benutzer.getId());
		upload.setFileUploadType(FileUploadType.USER);

		try {
			byte[] bytes = file.getBytes();
			upload.setFile(bytes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fileDAO.save(upload);

	}

	@Override
	public FileUpload findByAssociated(Long id, FileUploadType type) {
		return fileDAO.findByAssociated(id, type);
	}

}
