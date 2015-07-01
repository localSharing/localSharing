package pandha.swe.localsharing.controller.angebot.backend;

import org.springframework.beans.factory.annotation.Autowired;

import pandha.swe.localsharing.model.Benutzer;
import pandha.swe.localsharing.model.enums.Rollen;
import pandha.swe.localsharing.service.BenutzerService;

public class ErlaubeAnfrageVonAdmin implements IstAnfrageErlaubt {

	@Autowired
	private BenutzerService benutzerService;

	private Benutzer anfragenderBenutzer;

	@Override
	public Boolean istAnfrageErlaubt() {
		return benutzerService.hatBenutzerRolle(anfragenderBenutzer,
				Rollen.ADMIN);
	}

	public void setAnfragendenBenutzer(Benutzer anfragenderBenutzer) {
		this.anfragenderBenutzer = anfragenderBenutzer;
	}

}
