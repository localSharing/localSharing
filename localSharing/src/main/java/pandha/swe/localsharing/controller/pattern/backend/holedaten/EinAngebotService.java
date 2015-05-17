package pandha.swe.localsharing.controller.pattern.backend.holedaten;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import pandha.swe.localsharing.model.Ausleihartikel;
import pandha.swe.localsharing.model.Hilfeleistung;
import pandha.swe.localsharing.model.Tauschartikel;
import pandha.swe.localsharing.service.AusleihartikelService;
import pandha.swe.localsharing.service.HilfeleistungService;
import pandha.swe.localsharing.service.TauschartikelService;

public abstract class EinAngebotService extends EinAngebot {

	private static final String ANGEBOT = "angebot";

	@Autowired
	protected AusleihartikelService ausleihartikelService;
	@Autowired
	protected TauschartikelService tauschartikelService;
	@Autowired
	protected HilfeleistungService hilfeleistungService;

	@Override
	protected void addAusleihartikel(Map<String, Object> daten) {

		Ausleihartikel ausleihartikel = getAusleihartikel();

		if (ausleihartikel != null) {
			daten.put(ANGEBOT, ausleihartikel);
			daten.put("endDatum", ausleihartikel.getEndDatum());
			daten.put("kategorie", ausleihartikel.getKategorie());
			daten.put("dauer", ausleihartikel.getDauer());
		}

	}

	@Override
	protected void addTauschartikel(Map<String, Object> daten) {
		Tauschartikel tauschartikel = getTauschartikel();

		if (tauschartikel != null) {
			daten.put(ANGEBOT, tauschartikel);
			daten.put("kategorie", tauschartikel.getKategorie());
		}

	}

	@Override
	protected void addHilfeleistung(Map<String, Object> daten) {

		Hilfeleistung hilfeleistung = getHilfeleistung();

		if (hilfeleistung != null) {
			daten.put(ANGEBOT, hilfeleistung);
			daten.put("endDatum", hilfeleistung.getEndDatum());
		}
	}

	protected abstract Ausleihartikel getAusleihartikel();

	protected abstract Tauschartikel getTauschartikel();

	protected abstract Hilfeleistung getHilfeleistung();

}
