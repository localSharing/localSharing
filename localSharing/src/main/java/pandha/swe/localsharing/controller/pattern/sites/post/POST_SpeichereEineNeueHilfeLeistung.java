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
import pandha.swe.localsharing.model.dto.HilfeleistungDTO;
import pandha.swe.localsharing.model.enums.FileUploadType;

@Controller
public class POST_SpeichereEineNeueHilfeLeistung extends
		EmpfangeDatenMitBild<HilfeleistungDTO, HilfeleistungDTO> {

	private static final String REQUEST_URL = "/angebotNeu/helfen";
	private static final String ERROR_VIEW = "redirect:angebote";

	private HilfeleistungDTO artikel;

	@Autowired
	private CreateArtikelInService<HilfeleistungDTO> createArtikelInServiceHilfeleistung;

	@RequestMapping(method = RequestMethod.POST, value = REQUEST_URL)
	public String saveHilfeleistung(
			@ModelAttribute("newAngebot") @Valid HilfeleistungDTO angebot,
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
	protected HilfeleistungDTO wandleUm(HilfeleistungDTO artikel) {
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
	protected Speicherer getSpeicherer(
			HilfeleistungDTO artikel) {
		createArtikelInServiceHilfeleistung.setArtikel(artikel);
		return createArtikelInServiceHilfeleistung;
	}

	@Override
	protected FileUploadType getFileUploadType() {
		return FileUploadType.HILFANGEBOT;
	}

}
