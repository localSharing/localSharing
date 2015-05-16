package pandha.swe.localsharing.controller.pattern.backend;

import pandha.swe.localsharing.model.Ausleihartikel;
import pandha.swe.localsharing.model.Hilfeleistung;
import pandha.swe.localsharing.model.Tauschartikel;
import pandha.swe.localsharing.model.dto.AusleihartikelDTO;
import pandha.swe.localsharing.model.dto.HilfeleistungDTO;
import pandha.swe.localsharing.model.dto.TauschartikelDTO;

public class DtoToModelUmwander<T, E> extends AngebotsUmwandler<T, E> {
	
	
	public DtoToModelUmwander(T artikel) {
		super(artikel);
	}
	

	@Override
	protected Ausleihartikel wandleAusleihartikelDTO() {
		return ausleihartikelService
				.ausleihartikelDTO_TO_Ausleihartikel((AusleihartikelDTO) artikel);
	}

	@Override
	protected Tauschartikel wandleTauschartikelDTO() {
		return tauschartikelService
				.tauschartikelDTO_TO_Tauschartikel((TauschartikelDTO) artikel);
	}

	@Override
	protected Hilfeleistung wandleHilfeleisungDTO() {
		return hilfeleistungService
				.hilfeleistungDTO_TO_Hilfeleistung((HilfeleistungDTO) artikel);
	}

}
