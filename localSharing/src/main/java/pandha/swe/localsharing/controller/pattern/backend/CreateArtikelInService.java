package pandha.swe.localsharing.controller.pattern.backend;

import pandha.swe.localsharing.model.dto.AusleihartikelDTO;
import pandha.swe.localsharing.model.dto.HilfeleistungDTO;
import pandha.swe.localsharing.model.dto.TauschartikelDTO;

public class CreateArtikelInService<T> extends SSP_SpeicherEinAngebotDTO<T> {

	public CreateArtikelInService() {
	}

	@Override
	protected Long speichereAusleihartikelDTO() {
		return ausleihartikelService
				.createAusleihartikel((AusleihartikelDTO) getArtikel());
	}

	@Override
	protected Long speichereTauschartikelDTO() {
		return tauschartikelService
				.createTauschartikel((TauschartikelDTO) getArtikel());
	}

	@Override
	protected Long speichereHilfeleistungDTO() {
		return hilfeleistungService
				.createHilfeleistung((HilfeleistungDTO) getArtikel());

	}
}
