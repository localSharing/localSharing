package pandha.swe.localsharing.controller.pattern.backend;

import pandha.swe.localsharing.model.Angebot;
import pandha.swe.localsharing.model.Ausleihartikel;
import pandha.swe.localsharing.model.Hilfeleistung;
import pandha.swe.localsharing.model.Tauschartikel;

public class UpdateArtikelInService<T extends Angebot> extends
		SSP_SpeicherEinAngebot<T> {

	public UpdateArtikelInService() {
	}

	@Override
	protected Long speichereAusleihartikel() {
		Ausleihartikel ausleihartikel = (Ausleihartikel) getArtikel();
		ausleihartikelService.update(ausleihartikel);
		return ausleihartikel.getAngebotsid();

	}

	@Override
	protected Long speichereTauschartikel() {
		Tauschartikel tauschartikel = (Tauschartikel) getArtikel();
		tauschartikelService.update(tauschartikel);
		return tauschartikel.getAngebotsid();
	}

	@Override
	protected Long speichereHilfeleistung() {
		Hilfeleistung hilfeleistung = (Hilfeleistung) getArtikel();
		hilfeleistungService.update(hilfeleistung);
		return hilfeleistung.getAngebotsid();
	}

}
