package pandha.swe.localsharing.controller.pattern.sites.get;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pandha.swe.localsharing.controller.pattern.backend.BearbeiteDaten;
import pandha.swe.localsharing.controller.pattern.backend.BearbeiteEtwasAdmin;
import pandha.swe.localsharing.controller.pattern.backend.holedaten.LadeDaten;
import pandha.swe.localsharing.controller.pattern.backend.holedaten.LadeEinAngebot;
import pandha.swe.localsharing.controller.pattern.backend.speichereDaten.B_AktiviereEinAngebot;

@Controller
public class GET_AktiviereEinAngebot extends BearbeiteEtwasAdmin {

	private static final String REQUEST_URL = "/enable/{id}/{type}";
	private static String SUCCESS_VIEW = "redirect:../../angebote/";
	private static String ERROR_VIEW = "redirect:angebote";

	private Long angebotsId;
	private String type;

	@Autowired
	private B_AktiviereEinAngebot bAktiviereEinAngebot;

	@Autowired
	private LadeEinAngebot ladeEinAngebot;

	@RequestMapping(method = RequestMethod.GET, value = REQUEST_URL)
	protected String bearbeiteAnfrage(Principal principal,
			@PathVariable("id") String angebotsId,
			@PathVariable("type") String type) {
		this.angebotsId = Long.valueOf(angebotsId);
		this.type = type;
		SUCCESS_VIEW = "redirect:../../angebot/" + angebotsId + "/" + type;
		ERROR_VIEW = SUCCESS_VIEW;

		return bearbeiteAnfrageIntern(principal);
	}

	@Override
	protected LadeDaten attribute() {
		ladeEinAngebot.setId(angebotsId);
		ladeEinAngebot.setType(type);
		return ladeEinAngebot;
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
	protected BearbeiteDaten getBearbeiter() {
		return bAktiviereEinAngebot;
	}

}
