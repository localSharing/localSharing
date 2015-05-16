package pandha.swe.localsharing.controller.pattern.backend.holeDaten;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pandha.swe.localsharing.model.dto.AusleihartikelDTO;
import pandha.swe.localsharing.model.dto.HilfeleistungDTO;
import pandha.swe.localsharing.model.dto.TauschartikelDTO;
import pandha.swe.localsharing.service.AusleihartikelService;
import pandha.swe.localsharing.service.HilfeleistungService;
import pandha.swe.localsharing.service.TauschartikelService;

@Component
public abstract class AlleAngebotsTypen extends LadeDaten {

	protected String LISTE_AUSLEIHARTIKEL = "artikelListA";
	protected String LISTE_TAUSCHARTIKEL = "artikelListT";
	protected String LISTE_HILFELEISTUNGEN = "artikelListH";

	@Autowired
	protected AusleihartikelService ausleihartikelService;
	@Autowired
	protected TauschartikelService tauschartikelService;
	@Autowired
	protected HilfeleistungService hilfeleistungService;

	@Override
	public Map<String, Object> ladeDaten() {
		Map<String, Object> daten = new HashMap<String, Object>();
		daten.put(LISTE_AUSLEIHARTIKEL, getListeAusleihartikel());
		daten.put(LISTE_TAUSCHARTIKEL, getListeTauschartikel());
		daten.put(LISTE_HILFELEISTUNGEN, getListeHilfeleistungen());
		return daten;
	}

	protected abstract List<AusleihartikelDTO> getListeAusleihartikel();

	protected abstract List<TauschartikelDTO> getListeTauschartikel();

	protected abstract List<HilfeleistungDTO> getListeHilfeleistungen();

}