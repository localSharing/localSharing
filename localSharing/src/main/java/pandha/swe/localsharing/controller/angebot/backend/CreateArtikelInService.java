package pandha.swe.localsharing.controller.angebot.backend;

import pandha.swe.localsharing.model.dto.AusleihartikelDTO;
import pandha.swe.localsharing.model.dto.HilfeleistungDTO;
import pandha.swe.localsharing.model.dto.TauschartikelDTO;

public class CreateArtikelInService<T> extends SSP_SpeicherEinAngebotDTO<T> {

	public CreateArtikelInService() {
	}

	@Override
	protected Long speichereAusleihartikelDTO() {
		return ausleihartikelService
				.createAngebot((AusleihartikelDTO) getArtikel());
	}

	@Override
	protected Long speichereTauschartikelDTO() {
		return tauschartikelService
				.createAngebot((TauschartikelDTO) getArtikel());
	}

	@Override
	protected Long speichereHilfeleistungDTO() {
		return hilfeleistungService
				.createAngebot((HilfeleistungDTO) getArtikel());

	}
}
