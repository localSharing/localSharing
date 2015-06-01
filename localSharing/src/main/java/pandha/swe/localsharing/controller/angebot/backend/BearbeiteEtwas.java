package pandha.swe.localsharing.controller.angebot.backend;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;

import pandha.swe.localsharing.controller.angebot.backend.holedaten.LadeDaten;
import pandha.swe.localsharing.controller.angebot.sites.GoToErrorViewException;
import pandha.swe.localsharing.controller.angebot.sites.LocalSharingControllerTemplate;
import pandha.swe.localsharing.model.Benutzer;
import pandha.swe.localsharing.service.AngebotService;
import pandha.swe.localsharing.service.BenutzerService;

public abstract class BearbeiteEtwas extends LocalSharingControllerTemplate {

	@Autowired
	protected BenutzerService benutzerService;

	@Autowired
	protected AngebotService angebotService;
	
	protected Benutzer anfragenderBenutzer;

	protected String bearbeiteAnfrageIntern(Principal principal) {
		this.anfragenderBenutzer = benutzerService
				.getUserByPrincipal(principal);
		return bearbeiteAnfrageLogik();

	}

	@Override
	protected void bearbeiteAnfrageIntern() throws GoToErrorViewException {
		getBearbeiter().bearbeite(attribute().ladeDaten());
	}

	protected abstract LadeDaten attribute();

	protected abstract BearbeiteDaten getBearbeiter();

}
