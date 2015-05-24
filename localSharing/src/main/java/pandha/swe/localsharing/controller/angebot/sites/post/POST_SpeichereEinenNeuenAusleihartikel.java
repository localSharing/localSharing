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
import pandha.swe.localsharing.model.dto.AusleihartikelDTO;
import pandha.swe.localsharing.model.enums.FileUploadType;

@Controller
public class POST_SpeichereEinenNeuenAusleihartikel extends
		POST_NeuesAngebot<AusleihartikelDTO, AusleihartikelDTO> {

	private static final String REQUEST_URL = "/angebotNeu/ausleihen";
	private AusleihartikelDTO artikel;

	@Autowired
	private CreateArtikelInService<AusleihartikelDTO> createArtikelInServiceAusleihartikel;

	@RequestMapping(method = RequestMethod.POST, value = REQUEST_URL)
	public String saveAusleihartikel(
			@ModelAttribute("newAngebot") @Valid AusleihartikelDTO angebot,
			Principal principal,
			@RequestParam(value = "angebotImage", required = false) MultipartFile image) {

		this.artikel = angebot;

		return bearbeiteAnfrageIntern(principal, image);
	}

	@Override
	protected AusleihartikelDTO getArtikel() {
		return artikel;
	}

	@Override
	protected AusleihartikelDTO wandleUm(AusleihartikelDTO artikel) {
		// Keine Umwandlung notwendig
		artikel.setBenutzer(anfragenderBenutzer);
		return artikel;
	}

	@Override
	protected Speicherer getSpeicherer(AusleihartikelDTO artikel) {

		createArtikelInServiceAusleihartikel.setArtikel(artikel);
		return createArtikelInServiceAusleihartikel;
	}

	@Override
	protected FileUploadType getFileUploadType() {
		return FileUploadType.AUSLEIHANGEBOT;
	}

}
