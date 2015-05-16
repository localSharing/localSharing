package pandha.swe.localsharing.controller.pattern.sites.get;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pandha.swe.localsharing.controller.pattern.backend.holeDaten.LadeDaten;
import pandha.swe.localsharing.controller.pattern.backend.holeDaten.LadeEinAngebotDTO;
import pandha.swe.localsharing.controller.pattern.sites.ZeigeSeite_Benutzer;

@Controller
public class GET_ZeigeAngebot extends ZeigeSeite_Benutzer {

	private final static String REQUEST_URL = "/angebot/{id}/{type}";
	private final static String SUCCESS_VIEW = "angebot";
	private final static String ERROR_VIEW = "redirect:/angebote";

	private Long angebotsId;
	private String type;

	@Autowired
	private LadeEinAngebotDTO ladeEinAngebot;

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
	protected void setzeWeitereModelAttribute() {

		Boolean benutzerGleich = benutzerService.sindDieBenutzerGleich(
				anfragenderBenutzer,
				benutzerService.findByAngebotsIdAndType(angebotsId, type));
		if (benutzerGleich) {
			model.addAttribute("besitzer", true);
		} else {
			model.addAttribute("besitzer", false);
		}

	}

}
