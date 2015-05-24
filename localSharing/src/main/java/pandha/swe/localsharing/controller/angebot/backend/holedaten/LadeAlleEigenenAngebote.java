package pandha.swe.localsharing.controller.angebot.backend.holedaten;

import java.util.List;

import org.springframework.stereotype.Component;

import pandha.swe.localsharing.model.Benutzer;
import pandha.swe.localsharing.model.dto.AusleihartikelDTO;
import pandha.swe.localsharing.model.dto.HilfeleistungDTO;
import pandha.swe.localsharing.model.dto.TauschartikelDTO;

@Component
public class LadeAlleEigenenAngebote extends AlleAngebotsTypen {

	private Benutzer user;

	public LadeAlleEigenenAngebote() {
	}

	@Override
	protected List<AusleihartikelDTO> getListeAusleihartikel() {
		return ausleihartikelService.findAllByBenutzer(user);
	}

	@Override
	protected List<TauschartikelDTO> getListeTauschartikel() {
		return tauschartikelService.findAllByBenutzer(user);
	}

	@Override
	protected List<HilfeleistungDTO> getListeHilfeleistungen() {
		return hilfeleistungService.findAllByBenutzer(user);
	}

	public void setUser(Benutzer user) {
		this.user = user;
	}

}
