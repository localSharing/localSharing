package pandha.swe.localsharing.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import pandha.swe.localsharing.model.enums.FileUploadType;

@Entity
@Table(name = "FILES")
public class FileUpload {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "fileID")
	private Long fileID;

	@Column(name = "TYPE")
	private FileUploadType fileUploadType;

	@Column(name = "assID")
	private Long assID;

	@Lob
	@Column(name = "fileContent")
	private byte[] file;

	public FileUpload() {

	}

	public FileUploadType getFileUploadType() {
		return fileUploadType;
	}

	public void setFileUploadType(FileUploadType fileUploadType) {
		this.fileUploadType = fileUploadType;
	}

	public Long getAssID() {
		return assID;
	}

	public void setAssID(Long assID) {
		this.assID = assID;
	}

	public Long getFileID() {
		return fileID;
	}

	public void setFileID(Long fileID) {
		this.fileID = fileID;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

}
