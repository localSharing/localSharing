package pandha.swe.localsharing.controller.pattern.backend.holedaten;

import java.util.Map;

import pandha.swe.localsharing.model.dto.AusleihartikelDTO;
import pandha.swe.localsharing.model.dto.HilfeleistungDTO;
import pandha.swe.localsharing.model.dto.TauschartikelDTO;

public class NeuesAngebot extends EinAngebot {

	private static final String newAngebot = "newAngebot";

	public NeuesAngebot() {
	}

	@Override
	protected void addAusleihartikel(Map<String, Object> daten) {
		daten.put(newAngebot, new AusleihartikelDTO());
		daten.put(TYPE_AUSLEIHEN, TYPE_AUSLEIHEN);
	}

	@Override
	protected void addTauschartikel(Map<String, Object> daten) {
		daten.put(newAngebot, new TauschartikelDTO());
		daten.put(TYPE_TAUSCHEN, TYPE_TAUSCHEN);
	}

	@Override
	protected void addHilfeleistung(Map<String, Object> daten) {
		daten.put(newAngebot, new HilfeleistungDTO());
		daten.put(TYPE_HELFEN, TYPE_HELFEN);
	}

}
