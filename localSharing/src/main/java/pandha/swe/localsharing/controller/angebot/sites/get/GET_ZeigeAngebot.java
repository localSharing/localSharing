package pandha.swe.localsharing.controller.angebot.sites.get;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pandha.swe.localsharing.controller.angebot.backend.holedaten.LadeDaten;
import pandha.swe.localsharing.controller.angebot.backend.holedaten.LadeEinAngebotDTO;
import pandha.swe.localsharing.controller.angebot.sites.GoToErrorViewException;
import pandha.swe.localsharing.controller.angebot.sites.ZeigeSeite_Benutzer;
import pandha.swe.localsharing.model.Angebot;
import pandha.swe.localsharing.model.Ausleihartikel;
import pandha.swe.localsharing.model.Benutzer;
import pandha.swe.localsharing.model.Bewertung;
import pandha.swe.localsharing.model.Hilfeleistung;
import pandha.swe.localsharing.model.Tauschartikel;
import pandha.swe.localsharing.model.dto.BewertungDTO;
import pandha.swe.localsharing.model.enums.Rollen;
import pandha.swe.localsharing.service.AngebotService;
import pandha.swe.localsharing.service.BewertungService;

@Controller
public class GET_ZeigeAngebot extends ZeigeSeite_Benutzer {

	private static final String REQUEST_URL = "/angebot/{id}/{type}";
	private static final String SUCCESS_VIEW = "angebot";
	private static final String ERROR_VIEW = "redirect:/angebote";

	private Long angebotsId;
	private String type;

	@Autowired
	private AngebotService angebotService;

	@Autowired
	private LadeEinAngebotDTO ladeEinAngebot;

	@Autowired
	private BewertungService bewertungService;

	@RequestMapping(method = RequestMethod.GET, value = "/angebot/{id}")
	protected String redirectAnfrage(Model model, Principal principal,
			@PathVariable("id") String angebotsId) {

		this.angebotsId = Long.valueOf(angebotsId);

		Angebot angebot = angebotService.findAngebotById(this.angebotsId);

		if (angebot instanceof Ausleihartikel) {
			return "redirect:/angebot/" + angebotsId + "/ausleihen";
		} else if (angebot instanceof Tauschartikel) {
			return "redirect:/angebot/" + angebotsId + "/tauschen";
		} else if (angebot instanceof Hilfeleistung) {
			return "redirect:/angebot/" + angebotsId + "/helfen";
		}

		return "redirect:/angebote";
	}

	@RequestMapping(method = RequestMethod.GET, value = REQUEST_URL)
	protected String bearbeiteAnfrage(Model model, Principal principal,
			@PathVariable("id") String angebotsId,
			@PathVariable("type") String type) {

		this.angebotsId = Long.valueOf(angebotsId);
		this.type = type;

		return bearbeiteAnfrageIntern(model, principal);
	}

	@Override
	protected String getSuccessView() {
		return SUCCESS_VIEW;
	}

	@Override
	protected String getErrorView() {
		return ERROR_VIEW;
	}

	@Override
	protected LadeDaten attribute() {
		ladeEinAngebot.setId(angebotsId);
		ladeEinAngebot.setType(type);
		return ladeEinAngebot;
	}

	@Override
	protected void setzeWeitereModelAttribute() throws GoToErrorViewException {

		Benutzer besitzer = benutzerService.findByAngebotsIdAndType(angebotsId,
				type);

		if (besitzer == null) {
			throw new GoToErrorViewException();
		}

		Boolean benutzerGleich = benutzerService.sindDieBenutzerGleich(
				anfragenderBenutzer, besitzer);
		if (benutzerGleich) {
			model.addAttribute("besitzer", true);
			model.addAttribute("kommentarErlaubt", false);
		} else {
			if (!angebotService.findAngebotByIdAndType(angebotsId, type)
					.getEnabled()
					&& !benutzerService.hatBenutzerRolle(anfragenderBenutzer,
							Rollen.ADMIN)) {
				throw new GoToErrorViewException();
			}

			model.addAttribute("besitzer", false);

			model.addAttribute("kommentarErlaubt", true);
		}

		Angebot angebot = angebotService.findAngebotByIdAndType(angebotsId,
				type);
		List<Bewertung> bewertungen = bewertungService.findByAngebot(angebot);
		List<BewertungDTO> bewertungenDTO = bewertungService
				.list_Bewertung_TO_BewertungDTO(bewertungen);

		model.addAttribute("bewertungen", bewertungenDTO);

	}

}
