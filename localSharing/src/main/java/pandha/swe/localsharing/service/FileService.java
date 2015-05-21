package pandha.swe.localsharing.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import pandha.swe.localsharing.model.Ausleihartikel;
import pandha.swe.localsharing.model.Benutzer;
import pandha.swe.localsharing.model.FileUpload;
import pandha.swe.localsharing.model.Hilfeleistung;
import pandha.swe.localsharing.model.Tauschartikel;
import pandha.swe.localsharing.model.enums.FileUploadType;

public interface FileService {

	public FileUpload findByAssociated(Benutzer benutzer);

	public List<FileUpload> findAll();

	public List<FileUpload> findAllOfType(FileUploadType type);

	public void save(FileUpload file);

	public void delete(FileUpload file);

	public void save(Benutzer benutzer, MultipartFile file);

	public FileUpload findByAssociated(Long id, FileUploadType type);

	public void save(Ausleihartikel angebot, MultipartFile image);

	public void save(Tauschartikel angebot, MultipartFile image);

	public void save(Hilfeleistung angebot, MultipartFile image);

	public void save(Long associatedId, FileUploadType type, MultipartFile image);

	public FileUpload findByAssociated(Hilfeleistung angebot);

	public FileUpload findByAssociated(Ausleihartikel angebot);

	public FileUpload findByAssociated(Tauschartikel angebot);

}
