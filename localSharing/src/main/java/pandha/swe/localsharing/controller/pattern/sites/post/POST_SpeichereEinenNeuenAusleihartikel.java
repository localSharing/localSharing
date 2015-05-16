package pandha.swe.localsharing.controller.pattern.sites.post;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import pandha.swe.localsharing.controller.pattern.backend.CreateArtikelInService;
import pandha.swe.localsharing.controller.pattern.backend.IstAnfrageErlaubt;
import pandha.swe.localsharing.controller.pattern.backend.Speicherer;
import pandha.swe.localsharing.controller.pattern.sites.EmpfangeDatenMitBild;
import pandha.swe.localsharing.model.dto.AusleihartikelDTO;
import pandha.swe.localsharing.model.enums.FileUploadType;

@Controller
public class POST_SpeichereEinenNeuenAusleihartikel extends
		EmpfangeDatenMitBild<AusleihartikelDTO, AusleihartikelDTO> {

	private final static String REQUEST_URL = "/angebotNeu/ausleihen";
	private final static String ERROR_VIEW = "redirect:/angebote";

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
	protected IstAnfrageErlaubt getAnfrageErlaubt() {
		return new IstAnfrageErlaubt() {

			@Override
			public Boolean istAnfrageErlaubt() {
				return Boolean.TRUE;
			}
		};
	}

	@Override
	protected String getSuccessView() {
		return "redirect:/angebote/" + anfragenderBenutzer.getId();
	}

	@Override
	protected String getErrorView() {
		return ERROR_VIEW;
	}

	@Override
	protected Speicherer<AusleihartikelDTO> getSpeicherer(
			AusleihartikelDTO artikel) {

		createArtikelInServiceAusleihartikel.setArtikel(artikel);
		return createArtikelInServiceAusleihartikel;
	}

	@Override
	protected FileUploadType getFileUploadType() {
		return FileUploadType.AUSLEIHANGEBOT;
	}

}
