package pandha.swe.localsharing.controller.pattern.backend.holedaten;

import java.util.List;

import pandha.swe.localsharing.model.Benutzer;
import pandha.swe.localsharing.model.dto.AusleihartikelDTO;
import pandha.swe.localsharing.model.dto.HilfeleistungDTO;
import pandha.swe.localsharing.model.dto.TauschartikelDTO;

public class LadeAlleAngeboteEinesBenutzers extends AlleAngebotsTypen {

	private Benutzer user;

	public LadeAlleAngeboteEinesBenutzers() {
	}

	@Override
	protected List<AusleihartikelDTO> getListeAusleihartikel() {
		return ausleihartikelService.findAllEnabledByBenutzer(user);
	}

	@Override
	protected List<TauschartikelDTO> getListeTauschartikel() {
		return tauschartikelService.findAllEnabledByBenutzer(user);
	}

	@Override
	protected List<HilfeleistungDTO> getListeHilfeleistungen() {
		return hilfeleistungService.findAllEnabledByBenutzer(user);
	}

	public void setUser(Benutzer user) {
		this.user = user;
	}

}
