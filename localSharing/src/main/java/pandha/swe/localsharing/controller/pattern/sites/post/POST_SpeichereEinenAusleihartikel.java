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

import pandha.swe.localsharing.controller.pattern.backend.DtoToModelUmwander;
import pandha.swe.localsharing.controller.pattern.backend.ErlaubeAnfrageVonBesitzer;
import pandha.swe.localsharing.controller.pattern.backend.IstAnfrageErlaubt;
import pandha.swe.localsharing.controller.pattern.backend.Speicherer;
import pandha.swe.localsharing.controller.pattern.backend.UpdateArtikelInService;
import pandha.swe.localsharing.controller.pattern.sites.EmpfangeDatenMitBild;
import pandha.swe.localsharing.model.Ausleihartikel;
import pandha.swe.localsharing.model.dto.AusleihartikelDTO;
import pandha.swe.localsharing.model.enums.FileUploadType;

@Controller
public class POST_SpeichereEinenAusleihartikel extends
		EmpfangeDatenMitBild<AusleihartikelDTO, Ausleihartikel> {

	private final static String REQUEST_URL = "/angebotEdit/{id}/ausleihen";
	private final static String ERROR_VIEW = "redirect:angebote";

	private AusleihartikelDTO artikel;
	private Ausleihartikel ausleihartikel;

	@Autowired
	private ErlaubeAnfrageVonBesitzer erlaubeAnfrageVonBesitzer;

	@Autowired
	private UpdateArtikelInService<Ausleihartikel> updateArtikelInServiceAusleihartikel;

	@Autowired
	private DtoToModelUmwander<AusleihartikelDTO, Ausleihartikel> umwandler;

	@RequestMapping(method = RequestMethod.POST, value = REQUEST_URL)
	public String saveAusleihartikel(
			@ModelAttribute("angebot") @Valid AusleihartikelDTO angebot,
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
	protected Ausleihartikel wandleUm(AusleihartikelDTO artikel) {
		artikel.setBenutzer(anfragenderBenutzer);
		umwandler.setArtikel(artikel);
		ausleihartikel = umwandler.wandleUm();
		return ausleihartikel;
	}

	@Override
	protected IstAnfrageErlaubt getAnfrageErlaubt() {
		erlaubeAnfrageVonBesitzer.setAnfragendenBenutzer(anfragenderBenutzer);
		erlaubeAnfrageVonBesitzer.setBesitzer(benutzerService
				.findByAngebotsIdAndType(getArtikel().getId(), TYPE_AUSLEIHEN));
		return erlaubeAnfrageVonBesitzer;
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
	protected Speicherer<Ausleihartikel> getSpeicherer(Ausleihartikel artikel) {
		updateArtikelInServiceAusleihartikel.setArtikel(artikel);
		return updateArtikelInServiceAusleihartikel;
	}

	@Override
	protected FileUploadType getFileUploadType() {
		return FileUploadType.AUSLEIHANGEBOT;
	}

}
