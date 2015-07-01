package pandha.swe.localsharing.controller.angebot.sites;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import pandha.swe.localsharing.model.Benutzer;
import pandha.swe.localsharing.service.BenutzerService;

public abstract class ZeigeSeite_Benutzer extends ZeigeSeite {

	@Autowired
	protected BenutzerService benutzerService;

	protected Benutzer anfragenderBenutzer;

	protected String bearbeiteAnfrageIntern(Model model, Principal principal) {
		this.anfragenderBenutzer = benutzerService
				.getUserByPrincipal(principal);
		return bearbeiteAnfrageIntern(model);

	}

}
