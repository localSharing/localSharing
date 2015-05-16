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

	protected T artikel;
	private E ergebnis;

	public AngebotsUmwandler(T artikel) {
		this.artikel = artikel;
	}

	@Override
	protected E wandleUmIntern() {

		ergebnis = null;

		if (artikel instanceof AusleihartikelDTO) {
			ergebnis = (E) wandleAusleihartikelDTO();
		} else if (artikel instanceof TauschartikelDTO) {
			ergebnis = (E) wandleTauschartikelDTO();
		} else if (artikel instanceof HilfeleistungDTO) {
			ergebnis = (E) wandleHilfeleisungDTO();
		}

		return ergebnis;
	}

	protected abstract Ausleihartikel wandleAusleihartikelDTO();

	protected abstract Tauschartikel wandleTauschartikelDTO();

	protected abstract Hilfeleistung wandleHilfeleisungDTO();

}
