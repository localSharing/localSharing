package pandha.swe.localsharing.controller.angebot.sites.post;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import pandha.swe.localsharing.controller.angebot.backend.CreateArtikelInService;
import pandha.swe.localsharing.controller.angebot.backend.Speicherer;
import pandha.swe.localsharing.model.dto.TauschartikelDTO;
import pandha.swe.localsharing.model.enums.FileUploadType;

@Controller
public class POST_SpeichereEinenNeuenTauschartikel extends
		POST_NeuesAngebot<TauschartikelDTO, TauschartikelDTO> {

	private static final String REQUEST_URL = "/angebotNeu/tauschen";
	private TauschartikelDTO artikel;

	@Autowired
	private CreateArtikelInService<TauschartikelDTO> createArtikelInServiceTauschartikel;

	@RequestMapping(method = RequestMethod.POST, value = REQUEST_URL)
	public String saveTauschartikel(
			@ModelAttribute("newAngebot") @Valid TauschartikelDTO angebot,
			Principal principal,
			@RequestParam(value = "angebotImage", required = false) MultipartFile image) {

		this.artikel = angebot;

		return bearbeiteAnfrageIntern(principal, image);
	}

	@Override
	protected TauschartikelDTO getArtikel() {
		return artikel;
	}

	@Override
	protected TauschartikelDTO wandleUm(TauschartikelDTO artikel) {
		artikel.setBenutzer(anfragenderBenutzer);
		return artikel;
	}

	@Override
	protected Speicherer getSpeicherer(TauschartikelDTO artikel) {
		createArtikelInServiceTauschartikel.setArtikel(artikel);
		return createArtikelInServiceTauschartikel;
	}

	@Override
	protected FileUploadType getFileUploadType() {
		return FileUploadType.TAUSCHANGEBOT;
	}

}
