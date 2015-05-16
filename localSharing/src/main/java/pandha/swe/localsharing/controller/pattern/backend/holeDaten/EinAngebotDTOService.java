package pandha.swe.localsharing.controller.pattern.backend.holeDaten;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import pandha.swe.localsharing.model.dto.AusleihartikelDTO;
import pandha.swe.localsharing.model.dto.HilfeleistungDTO;
import pandha.swe.localsharing.model.dto.TauschartikelDTO;
import pandha.swe.localsharing.service.AusleihartikelService;
import pandha.swe.localsharing.service.HilfeleistungService;
import pandha.swe.localsharing.service.TauschartikelService;

public abstract class EinAngebotDTOService extends EinAngebot {

	private static final String ANGEBOT = "angebot";

	@Autowired
	protected AusleihartikelService ausleihartikelService;
	@Autowired
	protected TauschartikelService tauschartikelService;
	@Autowired
	protected HilfeleistungService hilfeleistungService;

	public EinAngebotDTOService() {
	}

	protected void addAusleihartikel(Map<String, Object> daten) {

		AusleihartikelDTO ausleihartikel = getAusleihartikel();

		if (ausleihartikel != null) {
			daten.put(ANGEBOT, ausleihartikel);
			daten.put("endDatum", ausleihartikel.getEndDatum());
			daten.put("kategorie", ausleihartikel.getKategorie());
			daten.put("dauer", ausleihartikel.getDauer());
		}

	}

	protected void addTauschartikel(Map<String, Object> daten) {
		TauschartikelDTO tauschartikel = getTauschartikel();

		if (tauschartikel != null) {
			daten.put(ANGEBOT, tauschartikel);
			daten.put("kategorie", tauschartikel.getKategorie());
		}

	}

	protected void addHilfeleistung(Map<String, Object> daten) {

		HilfeleistungDTO hilfeleistung = getHilfeleistung();

		if (hilfeleistung != null) {
			daten.put(ANGEBOT, hilfeleistung);
			daten.put("endDatum", hilfeleistung.getEndDatum());
		}
	}

	protected abstract AusleihartikelDTO getAusleihartikel();

	protected abstract TauschartikelDTO getTauschartikel();

	protected abstract HilfeleistungDTO getHilfeleistung();

}
