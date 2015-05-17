package pandha.swe.localsharing.controller.pattern.backend;

import org.springframework.beans.factory.annotation.Autowired;

import pandha.swe.localsharing.model.Ausleihartikel;
import pandha.swe.localsharing.model.Hilfeleistung;
import pandha.swe.localsharing.model.Tauschartikel;
import pandha.swe.localsharing.model.dto.AusleihartikelDTO;
import pandha.swe.localsharing.model.dto.HilfeleistungDTO;
import pandha.swe.localsharing.model.dto.TauschartikelDTO;
import pandha.swe.localsharing.service.AusleihartikelService;
import pandha.swe.localsharing.service.HilfeleistungService;
import pandha.swe.localsharing.service.TauschartikelService;

public abstract class AngebotsUmwandler<T, E> extends Umwandler<T, E> {

	@Autowired
	protected AusleihartikelService ausleihartikelService;
	@Autowired
	protected TauschartikelService tauschartikelService;
	@Autowired
	protected HilfeleistungService hilfeleistungService;

	private T artikel;
	private E ergebnis;

	@Override
	protected E wandleUmIntern() {

		ergebnis = null;

		if (getArtikel() instanceof AusleihartikelDTO) {
			ergebnis = (E) wandleAusleihartikelDTO();
		} else if (getArtikel() instanceof TauschartikelDTO) {
			ergebnis = (E) wandleTauschartikelDTO();
		} else if (getArtikel() instanceof HilfeleistungDTO) {
			ergebnis = (E) wandleHilfeleisungDTO();
		}

		return ergebnis;
	}

	protected abstract Ausleihartikel wandleAusleihartikelDTO();

	protected abstract Tauschartikel wandleTauschartikelDTO();

	protected abstract Hilfeleistung wandleHilfeleisungDTO();

	protected T getArtikel() {
		return artikel;
	}

	public void setArtikel(T artikel) {
		this.artikel = artikel;
	}

}
