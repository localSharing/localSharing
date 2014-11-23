package pandha.swe.localsharing.model.dao;

import java.util.List;

import pandha.swe.localsharing.model.FileUpload;
import pandha.swe.localsharing.model.enums.FileUploadType;

public interface FileUploadDAO {

	public FileUpload findByAssociated(Long id, FileUploadType type);

	public List<FileUpload> findAll();

	public List<FileUpload> findAllOfType(FileUploadType type);

	public void save(FileUpload file);

	public void delete(FileUpload file);

	public void shutdown();

}
