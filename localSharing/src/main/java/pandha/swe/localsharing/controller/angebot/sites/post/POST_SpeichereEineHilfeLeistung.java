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

import pandha.swe.localsharing.StringConstants;
import pandha.swe.localsharing.controller.angebot.backend.DtoToModelUmwander;
import pandha.swe.localsharing.controller.angebot.backend.ErlaubeAnfrageVonBesitzer;
import pandha.swe.localsharing.controller.angebot.backend.IstAnfrageErlaubt;
import pandha.swe.localsharing.controller.angebot.backend.Speicherer;
import pandha.swe.localsharing.controller.angebot.backend.UpdateArtikelInService;
import pandha.swe.localsharing.controller.angebot.sites.EmpfangeDatenMitBild;
import pandha.swe.localsharing.model.Hilfeleistung;
import pandha.swe.localsharing.model.dto.HilfeleistungDTO;
import pandha.swe.localsharing.model.enums.FileUploadType;

@Controller
public class POST_SpeichereEineHilfeLeistung extends
		EmpfangeDatenMitBild<HilfeleistungDTO, Hilfeleistung> {

	@Autowired
	private ErlaubeAnfrageVonBesitzer erlaubeAnfrageVonBesitzer;

	private static final String REQUEST_URL = "/angebotEdit/{id}/helfen";
	private static final String ERROR_VIEW = "redirect:angebote";

	private HilfeleistungDTO artikel;
	private Hilfeleistung hilfeleistung;

	@Autowired
	private UpdateArtikelInService<Hilfeleistung> updateArtikelInServiceHilfeleistung;

	@Autowired
	private DtoToModelUmwander<HilfeleistungDTO, Hilfeleistung> umwandlerHilfeleistung;

	@RequestMapping(method = RequestMethod.POST, value = REQUEST_URL)
	public String saveHilfeleistung(
			@ModelAttribute("angebot") @Valid HilfeleistungDTO angebot,
			Principal principal,
			@RequestParam(value = "angebotImage", required = false) MultipartFile image) {

		this.artikel = angebot;

		return bearbeiteAnfrageIntern(principal, image);
	}

	@Override
	protected HilfeleistungDTO getArtikel() {
		return artikel;
	}

	@Override
	protected Hilfeleistung wandleUm(HilfeleistungDTO artikel) {
		artikel.setBenutzer(anfragenderBenutzer);
		umwandlerHilfeleistung.setArtikel(artikel);
		hilfeleistung = umwandlerHilfeleistung.wandleUm();
		return hilfeleistung;
	}

	@Override
	protected IstAnfrageErlaubt getAnfrageErlaubt() {
		erlaubeAnfrageVonBesitzer.setAnfragendenBenutzer(anfragenderBenutzer);
		erlaubeAnfrageVonBesitzer.setBesitzer(benutzerService
				.findByAngebotsIdAndType(getArtikel().getId(), StringConstants.HELFEN));
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
	protected Speicherer getSpeicherer(Hilfeleistung artikel) {
		updateArtikelInServiceHilfeleistung.setArtikel(artikel);
		return updateArtikelInServiceHilfeleistung;
	}

	@Override
	protected FileUploadType getFileUploadType() {
		return FileUploadType.HILFANGEBOT;
	}

}
