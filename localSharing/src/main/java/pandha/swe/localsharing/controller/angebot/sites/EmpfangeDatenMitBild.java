package pandha.swe.localsharing.controller.angebot.sites;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import pandha.swe.localsharing.model.enums.FileUploadType;
import pandha.swe.localsharing.service.FileService;

public abstract class EmpfangeDatenMitBild<T, E> extends EmpfangeDaten<T, E> {

	@Autowired
	private FileService fileService;

	private MultipartFile image;

	protected String bearbeiteAnfrageIntern(Principal principal,
			MultipartFile image) {

		this.image = image;

		return bearbeiteAnfrageIntern(principal);
	}

	@Override
	protected void bearbeiteAnfrageIntern() throws GoToErrorViewException {
		Long id = getSpeicherer(wandleUm(getArtikel())).speicher();

		pruefeBildUndSpeichereEs(id);
	}

	private void pruefeBildUndSpeichereEs(Long id) {
		if (image != null && !image.isEmpty()) {
			FileUploadType type = getFileUploadType();
			fileService.save(id, type, image);
		}
	}

	protected abstract FileUploadType getFileUploadType();

}
