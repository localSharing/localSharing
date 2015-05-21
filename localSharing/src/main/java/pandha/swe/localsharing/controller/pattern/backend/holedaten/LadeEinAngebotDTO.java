package pandha.swe.localsharing.controller.pattern.backend.holedaten;

import pandha.swe.localsharing.model.dto.AusleihartikelDTO;
import pandha.swe.localsharing.model.dto.HilfeleistungDTO;
import pandha.swe.localsharing.model.dto.TauschartikelDTO;

public class LadeEinAngebotDTO extends EinAngebotDTOService {

	private Long id;

	public LadeEinAngebotDTO() {
	}

	@Override
	protected AusleihartikelDTO getAusleihartikel() {
		return ausleihartikelService
				.angebot_TO_AngebotDTO(ausleihartikelService.findById(getId()));
	}

	@Override
	protected TauschartikelDTO getTauschartikel() {
		return tauschartikelService
				.angebot_TO_AngebotDTO(tauschartikelService
						.findById(getId()));
	}

	@Override
	protected HilfeleistungDTO getHilfeleistung() {
		return hilfeleistungService
				.angebot_TO_AngebotDTO(hilfeleistungService
						.findById(getId()));
	}

	protected Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
