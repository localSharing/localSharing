package pandha.swe.localsharing.controller.pattern.backend.holeDaten;

import java.util.List;

import pandha.swe.localsharing.model.dto.AusleihartikelDTO;
import pandha.swe.localsharing.model.dto.HilfeleistungDTO;
import pandha.swe.localsharing.model.dto.TauschartikelDTO;

public class LadeAlleDeaktiviertenAngebote extends AlleAngebotsTypen {

	@Override
	protected List<AusleihartikelDTO> getListeAusleihartikel() {
		return ausleihartikelService.findAllDisabled();
	}

	@Override
	protected List<TauschartikelDTO> getListeTauschartikel() {
		return tauschartikelService.findAllDisabled();
	}

	@Override
	protected List<HilfeleistungDTO> getListeHilfeleistungen() {
		return hilfeleistungService.findAllDisabled();
	}
}
