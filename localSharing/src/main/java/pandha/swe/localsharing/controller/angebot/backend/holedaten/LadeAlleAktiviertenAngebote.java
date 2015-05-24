package pandha.swe.localsharing.controller.angebot.backend.holedaten;

import java.util.List;

import org.springframework.stereotype.Component;

import pandha.swe.localsharing.model.dto.AusleihartikelDTO;
import pandha.swe.localsharing.model.dto.HilfeleistungDTO;
import pandha.swe.localsharing.model.dto.TauschartikelDTO;

@Component
public class LadeAlleAktiviertenAngebote extends AlleAngebotsTypen {

	@Override
	protected List<AusleihartikelDTO> getListeAusleihartikel() {
		return ausleihartikelService.findAllEnabled();
	}

	@Override
	protected List<TauschartikelDTO> getListeTauschartikel() {
		return tauschartikelService.findAllEnabled();
	}

	@Override
	protected List<HilfeleistungDTO> getListeHilfeleistungen() {
		return hilfeleistungService.findAllEnabled();
	}

}