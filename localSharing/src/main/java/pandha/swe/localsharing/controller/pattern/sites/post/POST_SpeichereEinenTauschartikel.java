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

import pandha.swe.localsharing.config.StringConstants;
import pandha.swe.localsharing.controller.pattern.backend.DtoToModelUmwander;
import pandha.swe.localsharing.controller.pattern.backend.ErlaubeAnfrageVonBesitzer;
import pandha.swe.localsharing.controller.pattern.backend.IstAnfrageErlaubt;
import pandha.swe.localsharing.controller.pattern.backend.Speicherer;
import pandha.swe.localsharing.controller.pattern.backend.UpdateArtikelInService;
import pandha.swe.localsharing.controller.pattern.sites.EmpfangeDatenMitBild;
import pandha.swe.localsharing.model.Tauschartikel;
import pandha.swe.localsharing.model.dto.TauschartikelDTO;
import pandha.swe.localsharing.model.enums.FileUploadType;

@Controller
public class POST_SpeichereEinenTauschartikel extends
		EmpfangeDatenMitBild<TauschartikelDTO, Tauschartikel> {

	private static final String REQUEST_URL = "/angebotEdit/{id}/tauschen";
	private static final String ERROR_VIEW = "redirect:angebote";

	private TauschartikelDTO artikel;
	private Tauschartikel tauschartikel;

	@Autowired
	private ErlaubeAnfrageVonBesitzer erlaubeAnfrageVonBesitzer;

	@Autowired
	private UpdateArtikelInService<Tauschartikel> updateArtikelInServiceTauschartikel;

	@Autowired
	private DtoToModelUmwander<TauschartikelDTO, Tauschartikel> umwandler;

	@RequestMapping(method = RequestMethod.POST, value = REQUEST_URL)
	public String saveTauschartikel(
			@ModelAttribute("angebot") @Valid TauschartikelDTO angebot,
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
	protected Tauschartikel wandleUm(TauschartikelDTO artikel) {
		artikel.setBenutzer(anfragenderBenutzer);
		umwandler.setArtikel(artikel);
		tauschartikel = umwandler.wandleUm();
		return tauschartikel;
	}

	@Override
	protected IstAnfrageErlaubt getAnfrageErlaubt() {
		erlaubeAnfrageVonBesitzer.setAnfragendenBenutzer(anfragenderBenutzer);
		erlaubeAnfrageVonBesitzer.setBesitzer(benutzerService
				.findByAngebotsIdAndType(getArtikel().getId(),
						StringConstants.TAUSCHEN));
		return erlaubeAnfrageVonBesitzer;
	}

	@Override
	protected String getSuccessView() {
		return "redirect:../../angebote/" + anfragenderBenutzer.getId();
	}

	@Override
	protected String getErrorView() {
		return ERROR_VIEW;
	}

	@Override
	protected Speicherer getSpeicherer(Tauschartikel artikel) {
		updateArtikelInServiceTauschartikel.setArtikel(artikel);
		return updateArtikelInServiceTauschartikel;
	}

	@Override
	protected FileUploadType getFileUploadType() {
		return FileUploadType.TAUSCHANGEBOT;
	}

}
