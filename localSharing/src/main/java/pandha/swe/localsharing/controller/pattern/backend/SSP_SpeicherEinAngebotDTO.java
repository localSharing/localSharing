package pandha.swe.localsharing.controller.pattern.backend;

import org.springframework.beans.factory.annotation.Autowired;

import pandha.swe.localsharing.model.dto.AusleihartikelDTO;
import pandha.swe.localsharing.model.dto.HilfeleistungDTO;
import pandha.swe.localsharing.model.dto.TauschartikelDTO;
import pandha.swe.localsharing.service.AusleihartikelService;
import pandha.swe.localsharing.service.HilfeleistungService;
import pandha.swe.localsharing.service.TauschartikelService;

public abstract class SSP_SpeicherEinAngebotDTO<T> extends Speicherer {

	private T artikel;

	@Autowired
	protected AusleihartikelService ausleihartikelService;
	@Autowired
	protected TauschartikelService tauschartikelService;
	@Autowired
	protected HilfeleistungService hilfeleistungService;

	public SSP_SpeicherEinAngebotDTO() {
	}

	@Override
	protected Long speichereIntern() {
		if (getArtikel() instanceof AusleihartikelDTO) {
			return speichereAusleihartikelDTO();
		} else if (getArtikel() instanceof TauschartikelDTO) {
			speichereTauschartikelDTO();
		} else if (getArtikel() instanceof HilfeleistungDTO) {
			speichereHilfeleistungDTO();
		}
		return null;
	}

	protected abstract Long speichereAusleihartikelDTO();

	protected abstract Long speichereTauschartikelDTO();

	protected abstract Long speichereHilfeleistungDTO();

	protected T getArtikel() {
		return artikel;
	}

	public void setArtikel(T artikel) {
		this.artikel = artikel;
	}

}
