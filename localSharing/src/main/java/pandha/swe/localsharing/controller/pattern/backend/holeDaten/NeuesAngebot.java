package pandha.swe.localsharing.controller.pattern.backend.holeDaten;

import java.util.Map;

import pandha.swe.localsharing.model.dto.AusleihartikelDTO;
import pandha.swe.localsharing.model.dto.HilfeleistungDTO;
import pandha.swe.localsharing.model.dto.TauschartikelDTO;

public class NeuesAngebot extends EinAngebot {

	private final static String ANGEBOT = "newAngebot";

	public NeuesAngebot() {
	}

	@Override
	protected void addAusleihartikel(Map<String, Object> daten) {
		daten.put(ANGEBOT, new AusleihartikelDTO());
		daten.put(TYPE_AUSLEIHEN, TYPE_AUSLEIHEN);
	}

	@Override
	protected void addTauschartikel(Map<String, Object> daten) {
		daten.put(ANGEBOT, new TauschartikelDTO());
		daten.put(TYPE_TAUSCHEN, TYPE_TAUSCHEN);
	}

	@Override
	protected void addHilfeleistung(Map<String, Object> daten) {
		daten.put(ANGEBOT, new HilfeleistungDTO());
		daten.put(TYPE_HELFEN, TYPE_HELFEN);
	}

}
