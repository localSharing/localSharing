package pandha.swe.localsharing.controller.angebot.backend.holedaten;

import java.util.Map;

import pandha.swe.localsharing.StringConstants;
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
		daten.put(StringConstants.AUSLEIHEN, StringConstants.AUSLEIHEN);
	}

	@Override
	protected void addTauschartikel(Map<String, Object> daten) {
		daten.put(newAngebot, new TauschartikelDTO());
		daten.put(StringConstants.TAUSCHEN, StringConstants.TAUSCHEN);
	}

	@Override
	protected void addHilfeleistung(Map<String, Object> daten) {
		daten.put(newAngebot, new HilfeleistungDTO());
		daten.put(StringConstants.HELFEN, StringConstants.HELFEN);
	}

}
