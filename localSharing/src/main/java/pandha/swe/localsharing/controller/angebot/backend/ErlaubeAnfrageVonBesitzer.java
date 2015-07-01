package pandha.swe.localsharing.controller.angebot.backend;

import org.springframework.beans.factory.annotation.Autowired;

import pandha.swe.localsharing.model.Benutzer;
import pandha.swe.localsharing.service.BenutzerService;

public class ErlaubeAnfrageVonBesitzer implements IstAnfrageErlaubt {

	@Autowired
	private BenutzerService benutzerService;

	private Benutzer anfragenderBenutzer;
	private Benutzer angebotsersteller;

	public void setAnfragendenBenutzer(Benutzer anfragenderBenutzer) {
		this.anfragenderBenutzer = anfragenderBenutzer;
	}

	public void setBesitzer(Benutzer besitzer) {
		this.angebotsersteller = besitzer;
	}

	@Override
	public Boolean istAnfrageErlaubt() {
		return benutzerService.sindDieBenutzerGleich(anfragenderBenutzer,
				angebotsersteller);
	}
}
