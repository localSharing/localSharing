package pandha.swe.localsharing.controller.pattern.backend;

import pandha.swe.localsharing.model.Ausleihartikel;
import pandha.swe.localsharing.model.Hilfeleistung;
import pandha.swe.localsharing.model.Tauschartikel;
import pandha.swe.localsharing.model.dto.AusleihartikelDTO;
import pandha.swe.localsharing.model.dto.HilfeleistungDTO;
import pandha.swe.localsharing.model.dto.TauschartikelDTO;

public class DtoToModelUmwander<T, E> extends AngebotsUmwandler<T, E> {

	@Override
	protected Ausleihartikel wandleAusleihartikelDTO() {
		return ausleihartikelService
				.angebotDTO_TO_Angebot((AusleihartikelDTO) getArtikel());
	}

	@Override
	protected Tauschartikel wandleTauschartikelDTO() {
		return tauschartikelService
				.angebotDTO_TO_Angebot((TauschartikelDTO) getArtikel());
	}

	@Override
	protected Hilfeleistung wandleHilfeleisungDTO() {
		return hilfeleistungService
				.angebotDTO_TO_Angebot((HilfeleistungDTO) getArtikel());
	}

}
