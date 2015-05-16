package pandha.swe.localsharing.controller.pattern.backend;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import pandha.swe.localsharing.model.Ausleihartikel;
import pandha.swe.localsharing.model.Hilfeleistung;
import pandha.swe.localsharing.model.Tauschartikel;
import pandha.swe.localsharing.service.AusleihartikelService;
import pandha.swe.localsharing.service.HilfeleistungService;
import pandha.swe.localsharing.service.TauschartikelService;

public abstract class BearbeiteEinAngebot extends BearbeiteDaten {

	private static final String ANGEBOT = "angebot";

	@Autowired
	protected AusleihartikelService ausleihartikelService;
	@Autowired
	protected TauschartikelService tauschartikelService;
	@Autowired
	protected HilfeleistungService hilfeleistungService;

	@Override
	public void bearbeite(Map<String, Object> ladeDaten) {

		Object angebot = ladeDaten.get(ANGEBOT);

		if (angebot instanceof Ausleihartikel) {
			bearbeiteEinenAusleihartikel((Ausleihartikel) angebot);
		} else if (angebot instanceof Tauschartikel) {
			bearbeiteEinenTauschartikel((Tauschartikel) angebot);
		} else if (angebot instanceof Hilfeleistung) {
			bearbeiteEineHilfeleistung((Hilfeleistung) angebot);
		}

	}

	protected abstract void bearbeiteEinenAusleihartikel(
			Ausleihartikel ausleihartikel);

	protected abstract void bearbeiteEinenTauschartikel(
			Tauschartikel tauschartikel);

	protected abstract void bearbeiteEineHilfeleistung(
			Hilfeleistung hilfeleistung);

}
