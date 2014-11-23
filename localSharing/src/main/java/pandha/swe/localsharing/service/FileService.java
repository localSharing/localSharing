package pandha.swe.localsharing.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import pandha.swe.localsharing.model.Angebot;
import pandha.swe.localsharing.model.Benutzer;
import pandha.swe.localsharing.model.FileUpload;
import pandha.swe.localsharing.model.enums.FileUploadType;

public interface FileService {

	public FileUpload findByAssociated(Benutzer benutzer);

	public FileUpload findByAssociated(Angebot angebot);

	public List<FileUpload> findAll();

	public List<FileUpload> findAllOfType(FileUploadType type);

	public void save(FileUpload file);

	public void delete(FileUpload file);

	public void save(Benutzer benutzer, MultipartFile file);
	
	public FileUpload findByAssociated(Long id, FileUploadType type);

}
