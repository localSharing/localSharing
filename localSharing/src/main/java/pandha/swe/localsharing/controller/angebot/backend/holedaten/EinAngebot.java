package pandha.swe.localsharing.controller.angebot.backend.holedaten;

import java.util.HashMap;
import java.util.Map;

import pandha.swe.localsharing.StringConstants;

public abstract class EinAngebot extends LadeDaten {

	private String type;

	@Override
	public Map<String, Object> ladeDaten() {

		Map<String, Object> daten = new HashMap<String, Object>();

		switch (getType()) {
		case StringConstants.AUSLEIHEN:
			addAusleihartikel(daten);
			break;
		case StringConstants.TAUSCHEN:
			addTauschartikel(daten);
			break;
		case StringConstants.HELFEN:
			addHilfeleistung(daten);
			break;
		default:
			daten.put(null, null);
		}
		return daten;
	}

	protected abstract void addAusleihartikel(Map<String, Object> daten);

	protected abstract void addTauschartikel(Map<String, Object> daten);

	protected abstract void addHilfeleistung(Map<String, Object> daten);

	protected String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
