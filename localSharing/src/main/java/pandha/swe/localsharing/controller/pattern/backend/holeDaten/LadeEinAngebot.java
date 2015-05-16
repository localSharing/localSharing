package pandha.swe.localsharing.controller.pattern.backend.holeDaten;

import pandha.swe.localsharing.model.Ausleihartikel;
import pandha.swe.localsharing.model.Hilfeleistung;
import pandha.swe.localsharing.model.Tauschartikel;

public class LadeEinAngebot extends EinAngebotService {

	private Long id;

	public LadeEinAngebot() {
	}

	@Override
	protected Ausleihartikel getAusleihartikel() {
		return ausleihartikelService.findById(getId());
	}

	@Override
	protected Tauschartikel getTauschartikel() {
		return tauschartikelService.findById(getId());
	}

	@Override
	protected Hilfeleistung getHilfeleistung() {
		return hilfeleistungService.findById(getId());
	}

	protected Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
