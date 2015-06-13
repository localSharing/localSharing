package pandha.swe.localsharing.controller.angebot.sites.get;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;

import pandha.swe.localsharing.controller.angebot.backend.BearbeiteEtwasAdmin;
import pandha.swe.localsharing.controller.angebot.backend.holedaten.LadeDaten;
import pandha.swe.localsharing.controller.angebot.backend.holedaten.LadeEinAngebot;
import pandha.swe.localsharing.service.AngebotService;

public abstract class AktivierenDeaktiveren extends BearbeiteEtwasAdmin {

	protected static String SUCCESS_VIEW = "redirect:/angebote/";
	protected static String ERROR_VIEW = "redirect:angebote";

	protected Long angebotsId;
	protected String type;

	@Autowired
	protected AngebotService angebotService;

	@Autowired
	private LadeEinAngebot ladeEinAngebot;

	protected String bearbeiteAnfrageIntern(Principal principal,
			String angebotsId, String type) {
		this.angebotsId = Long.valueOf(angebotsId);
		this.type = type;
		SUCCESS_VIEW = "redirect:/angebot/" + angebotsId + "/" + type;

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

}