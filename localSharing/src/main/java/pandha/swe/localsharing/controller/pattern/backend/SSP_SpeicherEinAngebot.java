package pandha.swe.localsharing.controller.pattern.backend;

import org.springframework.beans.factory.annotation.Autowired;

import pandha.swe.localsharing.model.Ausleihartikel;
import pandha.swe.localsharing.model.Hilfeleistung;
import pandha.swe.localsharing.model.Tauschartikel;
import pandha.swe.localsharing.service.AusleihartikelService;
import pandha.swe.localsharing.service.HilfeleistungService;
import pandha.swe.localsharing.service.TauschartikelService;

public abstract class SSP_SpeicherEinAngebot<T> extends Speicherer {

	private T artikel;

	@Autowired
	protected AusleihartikelService ausleihartikelService;
	@Autowired
	protected TauschartikelService tauschartikelService;
	@Autowired
	protected HilfeleistungService hilfeleistungService;

	public SSP_SpeicherEinAngebot() {
	}

	@Override
	protected Long speichereIntern() {
		if (getArtikel() instanceof Ausleihartikel) {
			return speichereAusleihartikel();
		} else if (getArtikel() instanceof Tauschartikel) {
			return speichereTauschartikel();
		} else if (getArtikel() instanceof Hilfeleistung) {
			return speichereHilfeleistung();
		}
		return null;
	}

	protected abstract Long speichereAusleihartikel();

	protected abstract Long speichereTauschartikel();

	protected abstract Long speichereHilfeleistung();

	protected T getArtikel() {
		return artikel;
	}

	public void setArtikel(T artikel) {
		this.artikel = artikel;
	}

}
